package com.yiyaowen.javar;

import com.yiyaowen.javar.Build;
import com.yiyaowen.javar.BuildAndRun;
import com.yiyaowen.javar.CodePane;
import com.yiyaowen.javar.CreatorWindow;
import com.yiyaowen.javar.FileList;
import com.yiyaowen.javar.GeneralWindow;
import com.yiyaowen.javar.Javar;
import com.yiyaowen.javar.JavarConstants;
import com.yiyaowen.javar.JavarTranslator;
import com.yiyaowen.javar.JavarUtils;
import com.yiyaowen.javar.Run;
import com.yiyaowen.javar.TabbedPane;
import com.yiyaowen.javar.UpperBar;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;

@SuppressWarnings(value = "unchecked")
public class MenuItemProvider
{
    //////////////
    // Property //
    //////////////
	
    // MenuItem listeners
    public static ActionListener newItemListener;
    public static ActionListener openItemListener;
    public static ActionListener saveItemListener;
    public static ActionListener saveToItemListener;
    public static ActionListener renameItemListener;
    public static ActionListener renameItemPopupListener;
    public static ActionListener removeItemListener;
    public static ActionListener removeItemPopupListener;
    public static ActionListener deleteItemListener;
    public static ActionListener deleteItemPopupListener;
    public static ActionListener copyItemListener;
    public static ActionListener pasteItemListener;
    public static ActionListener cutItemListener;
    public static ActionListener commentItemListener;
    public static ActionListener replaceItemListener;
    public static ActionListener buildItemListener;
    public static ActionListener runItemListener;
    public static ActionListener codeAnalysisItemListener;
    public static ActionListener codeAnalysisItemPopupListener;
    public static ActionListener generalItemListener;
    public static ActionListener highlightConfigurationItemListener;
    public static ActionListener licenseItemListener;
    public static ActionListener readmeItemListener;
    public static ActionListener anyItemListener;
    public static ActionListener javaItemListener;
    public static ActionListener cItemListener;
    public static ActionListener cppItemListener;
    public static ActionListener pyItemListener;
    public static ActionListener htmlItemListener;
    // File chooser
    public static JFileChooser chooser;

    ////////////
    // Method //
    ////////////
    
    /**
     * Initialize menu item provider
     *
     * @param
     * @return
     */
    public static void initMenuItemProvider()
    {
        // Init listeners
        newItemListener = e -> {
            Javar.creatorWindow = new CreatorWindow();
            Javar.creatorWindow.initCreatorWindow();
        };
        openItemListener = e -> {
            chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(true);
            var source = ((Component)e.getSource()).getParent();
            int result;
            result = chooser.showDialog(source, JavarTranslator.translate("Open File"));
            if (result == JFileChooser.APPROVE_OPTION)
            {
                File file = chooser.getSelectedFile();
                try (
                    var inChannel = new FileInputStream(file).getChannel())
                {
                    // Get file attributes
                    HashMap<String,String> fileMap = JavarUtils.unpackFilePath(file.getPath());
                    HashMap<String,String> fileAttrMap = JavarUtils.getFileAttributes(file);
                    String filePath = fileMap.get("filePath");
                    String fileName = fileMap.get("fileName");
                    String fileSuffix = fileMap.get("fileSuffix");
                    String fileSize = fileAttrMap.get("fileSize");
                    String createdDate = fileAttrMap.get("createdDate");
                    String lastModifiedDate = fileAttrMap.get("lastModifiedDate");
                    var data = FileList.createItemData(fileName, fileSuffix, filePath, fileSize, createdDate, lastModifiedDate);
                    // Get name and icon
                    ImageIcon icon = new ImageIcon("../images/icons/" + fileName.substring(fileName.lastIndexOf(".")+1) + "FileTemplateIcon.png");
                    if (icon.getImage()  == null)
                        icon = new ImageIcon("../images/icons/defaultFileTemplateIcon.png");
                    icon.setImage(JavarUtils.resizeImageToWH(icon.getImage(), JavarConstants.tabIconWidth, JavarConstants.tabIconHeight, Image.SCALE_SMOOTH));
                    // Get content
                    MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
                    Charset charset = Charset.forName("UTF-8");
                    CharsetDecoder decoder = charset.newDecoder();
                    CharBuffer charBuffer = decoder.decode(buffer);
                    String content = charBuffer.toString();
                    // Set tab scrollable
                    var tmpPanel = new JPanel();
                    tmpPanel.setLayout(new BorderLayout());
                    // Varies for languages
                    if (fileSuffix.equals("java"))
                        tmpPanel.add(new CodePane(JavarConstants.JavaSyntaxFile, JavarConstants.Java_TokensSplitSymbol, true));
                    else if (fileSuffix.equals("py"))
                        tmpPanel.add(new CodePane(JavarConstants.PythonSyntaxFile, JavarConstants.Python_TokensSplitSymbol, true));
                    else if (fileSuffix.equals("c"))
                        tmpPanel.add(new CodePane(JavarConstants.CSyntaxFile, JavarConstants.C_TokensSplitSymbol, true));
                    else if (fileSuffix.equals("cpp"))
                        tmpPanel.add(new CodePane(JavarConstants.CppSyntaxFile, JavarConstants.Cpp_TokensSplitSymbol, true));
                    else if (fileSuffix.equals("html"))
                        tmpPanel.add(new CodePane(JavarConstants.HtmlSyntaxFile, JavarConstants.Html_TokensSplitSymbol, true));
                    else
                        tmpPanel.add(new CodePane(JavarConstants.JavaSyntaxFile, JavarConstants.Java_TokensSplitSymbol, false));
                    var tmpScroll = new JScrollPane(tmpPanel);
                    tmpScroll.getVerticalScrollBar().setUnitIncrement(JavarConstants.scrollUnitIncrement);
                    Javar.codeEditor.addTab(file.getName(), icon, tmpScroll);
                    var tmp1 = (JScrollPane) Javar.codeEditor.getComponentAt(Javar.codeEditor.getTabCount()-1);
                    var tmp2 = (CodePane) ((JPanel) tmp1.getViewport().getComponents()[0]).getComponents()[0];
                    tmp2.setText(content);
                    tmp2.syntaxParse("");
                    // Stay at the top
                    tmp2.setCaretPosition(0);
                    try
                    {
                        // Set file list
                        FileList.fileItems.add(data);
                        Javar.fileList.setListData(FileList.fileItems);
                    }
                    catch (Exception ignore)
                    {
                    	Javar.logger.log("i", ignore.getMessage());
                    }
                    // Set selected tab
                    Javar.codeEditor.setSelectedIndex(Javar.codeEditor.getTabCount()-1);
                    // Set info box
                    Javar.upperBar.infoBox.setText(JavarTranslator.translate(JavarConstants.openItemListenerSuccessContent) + " " + fileName + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]"));
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(source, JavarTranslator.translate(JavarConstants.openItemListenerErrorMessage) + ex.getMessage(), 
                    		JavarTranslator.translate(JavarConstants.openItemListenerErrorTitle), JOptionPane.ERROR_MESSAGE);
                    Javar.upperBar.infoBox.setText(JavarTranslator.translate(JavarConstants.openItemListenerErrorContent1) + file.getName() + JavarTranslator.translate(JavarConstants.openItemListenerErrorContent2) + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + "</font></html>");
                }
            }
        };
        saveItemListener = e -> {
            if (Javar.fileList.getSelectedIndex() < 0)
                return;
            var source = ((Component)e.getSource()).getParent();
            String filePath = Javar.fileList.getSelectedItemDataFilePath();
            try (
                var fw = new FileWriter(filePath))
            {
                var tmp1 = (JScrollPane) Javar.codeEditor.getComponentAt(Javar.codeEditor.getSelectedIndex());
                var tmp2 = (CodePane) ((JPanel) tmp1.getViewport().getComponents()[0]).getComponents()[0];
                String content = tmp2.getText();        
                fw.write(content);
                Javar.upperBar.infoBox.setText(JavarTranslator.translate(JavarConstants.saveItemListenerSuccessMessage) + Javar.fileList.getSelectedItemDataFileName() + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]"));
            }
            catch (Exception ex)
            {
                Javar.upperBar.infoBox.setText(JavarTranslator.translate(JavarConstants.saveItemListenerErrorMessage) + ex.getMessage() + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + "</font></html>"); 
            }
            try
            {
                HashMap<String,String> fileAttrMap = JavarUtils.getFileAttributes(new File(filePath));
                String fileSize = fileAttrMap.get("fileSize");
                String lastModifiedDate = fileAttrMap.get("lastModifiedDate");
                Javar.infoLabel.setFileLastModified(lastModifiedDate);
                Javar.fileList.setSelectedItemDataFileLastModified(lastModifiedDate);
                Javar.infoLabel.setFileSize(fileSize);
                Javar.fileList.setSelectedItemDataFileSize(fileSize);
                Javar.infoLabel.updateText();
            }
            catch (Exception update)
            {
                Javar.upperBar.infoBox.setText(JavarTranslator.translate(JavarConstants.saveFileUpdateInfoErrorMessage) + update.getMessage() + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + "</font></html>");
            }
        };
        saveToItemListener = e -> {
            if (Javar.fileList.getSelectedIndex() < 0)
                return;
            var source = ((Component)e.getSource()).getParent();
            chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            String fileName;
            fileName = JOptionPane.showInputDialog(source, JavarTranslator.translate(JavarConstants.saveToFileChooserLabelContent), JavarTranslator.translate(JavarConstants.saveToFileChooserLabelTitle), JOptionPane.INFORMATION_MESSAGE);
            if (fileName == null)
                return;
            int result;
            result = chooser.showDialog(source, JavarTranslator.translate(JavarConstants.saveToFileChooserContent));
            if (result == JFileChooser.APPROVE_OPTION)
            {
                String filePath = chooser.getSelectedFile().getPath() + JavarConstants.pathDelimiter + fileName;
                try (
                    var fw = new FileWriter(filePath))
                {
                    var tmp1 = (JScrollPane) Javar.codeEditor.getComponentAt(Javar.codeEditor.getSelectedIndex());
                    var tmp2 = (CodePane) ((JPanel) tmp1.getViewport().getComponents()[0]).getComponents()[0];
                    String content = tmp2.getText();        
                    fw.write(content);
                    Javar.upperBar.infoBox.setText(JavarTranslator.translate(JavarConstants.saveToItemListenerSuccessMessage1) + fileName + JavarTranslator.translate(JavarConstants.saveToItemListenerSuccessMessage2) + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]"));
                }
                catch (Exception ex)
                {
                    Javar.upperBar.infoBox.setText(JavarTranslator.translate(JavarConstants.saveToItemListenerErrorMessage1) + ex.getMessage() + JavarTranslator.translate(JavarConstants.saveToItemListenerErrorMessage2) + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + "</font></html>"); 
                }
            }
        };
        renameItemListener = e -> {
            if (Javar.fileList.getSelectedIndex() < 0)
                return;
            var source = ((Component)e.getSource()).getParent();
            String filePath = Javar.fileList.getSelectedItemDataFilePath();
            String dirPath = filePath.substring(0, filePath.lastIndexOf(JavarConstants.pathDelimiter)+1);
            File file = new File(filePath);
            String newName;
            newName = JOptionPane.showInputDialog(source, JavarTranslator.translate(JavarConstants.renameItemListenerContent), JavarTranslator.translate(JavarConstants.renameItemListenerTitle), JOptionPane.INFORMATION_MESSAGE);
            if (newName == null)
                return;
            String fileType = newName.substring(newName.lastIndexOf(".")+1);
            File newFile = new File(dirPath+newName);
            if (file.renameTo(newFile))
            {
                // Set file list data
                Javar.fileList.setSelectedItemDataFileName(newName);
                Javar.fileList.setSelectedItemDataFileType(CreatorWindow.suffixTypeMap.get(newName.substring(newName.lastIndexOf(".")+1)));
                Javar.fileList.setSelectedItemDataFilePath(newFile.getPath());
                Javar.fileList.setSelectedItemDataFileLastModified((new Date(newFile.lastModified())).toString());
                // Set tab title and icon
                Javar.codeEditor.setTitleAt(Javar.codeEditor.getSelectedIndex(), newName);
                ImageIcon icon = new ImageIcon("../images/icons/" + fileType + "FileTemplateIcon.png");
                if (icon == null)
                    icon = new ImageIcon("../images/icons/defaultFileTemplateIcon.png");
                icon.setImage(JavarUtils.resizeImageToWH(icon.getImage(), JavarConstants.tabIconWidth, JavarConstants.tabIconHeight, Image.SCALE_SMOOTH));
                Javar.codeEditor.setIconAt(Javar.codeEditor.getSelectedIndex(), icon);
                // Set and update info label
                Javar.infoLabel.setCurrentFile(newName);
                Javar.infoLabel.setFileType(CreatorWindow.suffixTypeMap.get(newName.substring(newName.lastIndexOf(".")+1)));
                Javar.infoLabel.setFileLastModified((new Date(newFile.lastModified())).toString());
                Javar.infoLabel.updateText();
            }
            else
            {
                JOptionPane.showMessageDialog(source, JavarTranslator.translate(JavarConstants.renameItemListenerErrorMessage), JavarTranslator.translate(JavarConstants.renameItemListenerErrorTitle), JOptionPane.ERROR_MESSAGE);
            }
        };
        renameItemPopupListener = renameItemListener;
        removeItemListener = e -> {
            int index = Javar.fileList.getSelectedIndex();
            if (index < 0)
                return;
            FileList.fileItems.remove(index);
            Javar.codeEditor.remove(Javar.codeEditor.getSelectedIndex());
            Javar.fileList.setListData(FileList.fileItems);
            int selectedIndex = Javar.codeEditor.getSelectedIndex() - 1;
            if (selectedIndex >= 0)
                Javar.fileList.setSelectedIndex(selectedIndex);
        };
        removeItemPopupListener = removeItemListener;
        deleteItemListener = e -> {
            if (Javar.fileList.getSelectedIndex() < 0)
                return;
            var source = ((Component)e.getSource()).getParent();
            int delete;
            delete = JOptionPane.showConfirmDialog(source, JavarTranslator.translate(JavarConstants.deleteItemListenerContent), JavarTranslator.translate(JavarConstants.deleteItemListenerTitle), JOptionPane.YES_NO_OPTION);
            if (delete == JOptionPane.OK_OPTION)
            {
                File file = new File(Javar.fileList.getSelectedItemDataFilePath());
                if (file.delete())
                {
                    // Fire remove event
                    removeItemListener.actionPerformed(new ActionEvent(source, ActionEvent.ACTION_PERFORMED, ""));
                    Javar.upperBar.infoBox.setText(JavarTranslator.translate(JavarConstants.deleteItemListenerSuccessMessage) + file.getName() + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]"));
                }
                else
                {
                    Javar.upperBar.infoBox.setText(JavarTranslator.translate(JavarConstants.deleteItemListenerErrorMessage) + file.getName() + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + "</font></html>");
                }
            }
        };
        deleteItemPopupListener = deleteItemListener;
        buildItemListener = e -> {
            if (Javar.fileList.getSelectedIndex() < 0)
                return;
            var source = ((Component)e.getSource()).getParent();
            // Fire save event
            saveItemListener.actionPerformed(new ActionEvent(source, ActionEvent.ACTION_PERFORMED, ""));
            /* Build information */
            String filePath = Javar.fileList.getSelectedItemDataFilePath();
            if (UpperBar.compilerSelector.getSelectedItem().equals(JavarConstants.compilerSelectorJava))
                Build.Java(filePath);
            else if (UpperBar.compilerSelector.getSelectedItem().equals(JavarConstants.compilerSelectorC))
                Build.C(filePath);
            else if (UpperBar.compilerSelector.getSelectedItem().equals(JavarConstants.compilerSelectorCpp))
                Build.Cpp(filePath);
        };
        runItemListener = e -> {
            if (Javar.fileList.getSelectedIndex() < 0)
                return;
            var source = ((Component)e.getSource()).getParent();
            // Fire save event
            saveItemListener.actionPerformed(new ActionEvent(source, ActionEvent.ACTION_PERFORMED, ""));
            // Run information
            String filePath = Javar.fileList.getSelectedItemDataFilePath();
            if (UpperBar.compilerSelector.getSelectedItem().equals(JavarConstants.compilerSelectorJava))
                Run.Java(filePath);
            else if (UpperBar.compilerSelector.getSelectedItem().equals(JavarConstants.compilerSelectorPython))
                Run.Python(filePath);
            else if (UpperBar.compilerSelector.getSelectedItem().equals(JavarConstants.compilerSelectorC))
                Run.Exe(filePath);
            else if (UpperBar.compilerSelector.getSelectedItem().equals(JavarConstants.compilerSelectorCpp))
                Run.Exe(filePath);
            else 
                Run.Html(filePath);
        };
        generalItemListener = e -> {
            Javar.generalWindow = new GeneralWindow();
            Javar.generalWindow.initGeneralWindow();
        };
        anyItemListener = e -> {
            var fileName = getFileName(null);
            if (fileName == null) return;
            var filePath = getFilePath();
            if (filePath == null) return;
            JavarDispatcher.createNewFile(filePath + JavarConstants.pathDelimiter + fileName, "");
        };
        javaItemListener = e -> {
            var fileName = getFileName(".java");
            if (fileName == null) return;
            var filePath = getFilePath();
            if (filePath == null) return;
            JavarDispatcher.createNewFile(filePath + JavarConstants.pathDelimiter + fileName, "");
        };
        cItemListener = e -> {
            var fileName = getFileName(".c");
            if (fileName == null) return;
            var filePath = getFilePath();
            if (filePath == null) return;
            JavarDispatcher.createNewFile(filePath + JavarConstants.pathDelimiter + fileName, "");
        };
        cppItemListener = e -> {
            var fileName = getFileName(".cpp");
            if (fileName == null) return;
            var filePath = getFilePath();
            if (filePath == null) return;
            JavarDispatcher.createNewFile(filePath + JavarConstants.pathDelimiter + fileName, "");
        };
        pyItemListener = e -> {
            var fileName = getFileName(".py");
            if (fileName == null) return;
            var filePath = getFilePath();
            if (filePath == null) return;
            JavarDispatcher.createNewFile(filePath + JavarConstants.pathDelimiter + fileName, "");
        };
        htmlItemListener = e -> {
            var fileName = getFileName(".html");
            if (fileName == null) return;
            var filePath = getFilePath();
            if (filePath == null) return;
            JavarDispatcher.createNewFile(filePath + JavarConstants.pathDelimiter + fileName, "");
        };
    }

    private static String getFileName(String type)
    {
        // Get fileName
        String fileName;
        if (type != null)
        {
            fileName = JOptionPane.showInputDialog(Javar.mainWindow, JavarTranslator.translate(JavarConstants.fileItemListenerContent) + type, JavarTranslator.translate(JavarConstants.fileItemListenerTitle), JOptionPane.INFORMATION_MESSAGE);
            if (fileName != null && !fileName.substring(fileName.lastIndexOf(".")+1).equals(type))
                fileName += type;
        }
        else
        {
            fileName = JOptionPane.showInputDialog(Javar.mainWindow, JavarTranslator.translate(JavarConstants.anyFileItemListenerContent), JavarTranslator.translate(JavarConstants.fileItemListenerTitle), JOptionPane.INFORMATION_MESSAGE);
        }
        return fileName;
    }

    private static String getFilePath()
    {
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // Get filePath
        int result;
        String filePath;
        result = chooser.showDialog(Javar.mainWindow, JavarTranslator.translate("Choose Directory"));
        if (result == JFileChooser.APPROVE_OPTION)
        {
            filePath = chooser.getSelectedFile().getPath();
            return filePath;
        }
        else
            return null;
    }
    
    public static JMenuItem createMenuItem(int type)
    {
        switch (type)
        {
        case JavarConstants.anyItemType:
            JMenuItem anyItem = new JMenuItem(JavarTranslator.translate("Any"));
            anyItem.addActionListener(anyItemListener);
            return anyItem;

        case JavarConstants.javaItemType:
            JMenuItem javaItem = new JMenuItem(JavarTranslator.translate("Java Source File"));
            javaItem.addActionListener(javaItemListener);
            return javaItem;

        case JavarConstants.cItemType:
            JMenuItem cItem = new JMenuItem(JavarTranslator.translate("C Source File"));
            cItem.addActionListener(cItemListener);
            return cItem;

        case JavarConstants.cppItemType:
            JMenuItem cppItem = new JMenuItem(JavarTranslator.translate("C++ Source File"));
            cppItem.addActionListener(cppItemListener);
            return cppItem;

        case JavarConstants.pyItemType:
            JMenuItem pyItem = new JMenuItem(JavarTranslator.translate("Python Source File"));
            pyItem.addActionListener(pyItemListener);
            return pyItem;

        case JavarConstants.htmlItemType:
            JMenuItem htmlItem = new JMenuItem(JavarTranslator.translate("html Source File"));
            htmlItem.addActionListener(htmlItemListener);
            return htmlItem;

        case JavarConstants.newItemType:
            JMenuItem newItem = new JMenuItem(JavarTranslator.translate("New"));
            newItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.newItemChar, JavarConstants.newItemModifier));
            newItem.addActionListener(newItemListener);
            return newItem;

        case JavarConstants.openItemType:
            JMenuItem openItem = new JMenuItem(JavarTranslator.translate("Open"));
            openItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.openItemChar, JavarConstants.openItemModifier));
            openItem.addActionListener(openItemListener);
            return openItem;

        case JavarConstants.saveItemType:
            JMenuItem saveItem = new JMenuItem(JavarTranslator.translate("Save"));
            saveItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.saveItemChar, JavarConstants.saveItemModifier));
            saveItem.addActionListener(saveItemListener);
            return saveItem;

        case JavarConstants.saveToItemType:
            JMenuItem saveToItem = new JMenuItem(JavarTranslator.translate("Save To..."));
            saveToItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.saveToItemChar, JavarConstants.saveToItemModifier));
            saveToItem.addActionListener(saveToItemListener);
            return saveToItem;

        case JavarConstants.renameItemType:
            JMenuItem renameItem = new JMenuItem(JavarTranslator.translate("Rename"));
            renameItem.addActionListener(renameItemListener);
            return renameItem;

        case JavarConstants.renameItemPopupType:
            JMenuItem renamePopupItem = new JMenuItem(JavarTranslator.translate("Rename"));
            renamePopupItem.addActionListener(renameItemPopupListener);
            return renamePopupItem;

        case JavarConstants.removeItemType:
            JMenuItem removeItem = new JMenuItem(JavarTranslator.translate("Remove"));
            removeItem.addActionListener(removeItemListener);
            removeItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.removeItemChar, JavarConstants.removeItemModifier));
            return removeItem;

        case JavarConstants.removeItemPopupType:
            JMenuItem removePopupItem = new JMenuItem(JavarTranslator.translate("Remove"));
            removePopupItem.addActionListener(removeItemPopupListener);
            return removePopupItem;

        case JavarConstants.deleteItemType:
            JMenuItem deleteItem = new JMenuItem(JavarTranslator.translate("Delete"));
            deleteItem.addActionListener(deleteItemListener);
            return deleteItem;

        case JavarConstants.deleteItemPopupType:
            JMenuItem deletePopupItem = new JMenuItem(JavarTranslator.translate("Delete"));
            deletePopupItem.addActionListener(deleteItemPopupListener);
            return deletePopupItem;

        case JavarConstants.copyItemType:
            JMenuItem copyItem = new JMenuItem(JavarTranslator.translate("Copy"));
            copyItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.copyItemChar, JavarConstants.copyItemModifier));
            copyItem.addActionListener(copyItemListener);
            return copyItem;

        case JavarConstants.pasteItemType:
            JMenuItem pasteItem = new JMenuItem(JavarTranslator.translate("Paste"));
            pasteItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.pasteItemChar, JavarConstants.pasteItemModifier));
            pasteItem.addActionListener(pasteItemListener);
            return pasteItem;

        case JavarConstants.cutItemType:
            JMenuItem cutItem = new JMenuItem(JavarTranslator.translate("Cut"));
            cutItem.addActionListener(cutItemListener);
            return cutItem;

        case JavarConstants.commentItemType:
            JMenuItem commentItem = new JMenuItem(JavarTranslator.translate("Comment/Uncomment"));
            commentItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.commentItemChar, JavarConstants.commentItemModifier));
            commentItem.addActionListener(commentItemListener);
            return commentItem;

        case JavarConstants.replaceItemType:
            JMenuItem replaceItem = new JMenuItem(JavarTranslator.translate("Replace"));
            replaceItem.addActionListener(replaceItemListener);
            return replaceItem;

        case JavarConstants.buildItemType:
            JMenuItem buildItem = new JMenuItem(JavarTranslator.translate("Build"));
            buildItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.buildItemChar, JavarConstants.buildItemModifier));
            buildItem.addActionListener(buildItemListener);
            return buildItem;

        case JavarConstants.runItemType:
            JMenuItem runItem = new JMenuItem(JavarTranslator.translate("Run"));
            runItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.runItemChar, JavarConstants.runItemModifier));
            runItem.addActionListener(runItemListener);
            return runItem;

        case JavarConstants.codeAnalysisItemType:
            JMenuItem codeAnalysisItem = new JMenuItem(JavarTranslator.translate("Code Analysis"));
            codeAnalysisItem.addActionListener(codeAnalysisItemListener);
            return codeAnalysisItem;

        case JavarConstants.codeAnalysisItemPopupType:
            JMenuItem codeAnalysisPopupItem = new JMenuItem(JavarTranslator.translate("Code Analysis"));
            codeAnalysisPopupItem.addActionListener(codeAnalysisItemPopupListener);
            return codeAnalysisPopupItem;

        case JavarConstants.generalItemType:
            JMenuItem generalItem = new JMenuItem(JavarTranslator.translate("General"));
            generalItem.addActionListener(generalItemListener);
            return generalItem;

        case JavarConstants.highlightConfigurationItemType:
            JMenuItem highlightConfigurationItem = new JMenuItem(JavarTranslator.translate("Highlight Configuration"));
            highlightConfigurationItem.addActionListener(highlightConfigurationItemListener);
            return highlightConfigurationItem;

        case JavarConstants.licenseItemType:
            JMenuItem licenseItem = new JMenuItem(JavarTranslator.translate("License"));
            licenseItem.addActionListener(licenseItemListener);
            return licenseItem;

        case JavarConstants.readmeItemType:
            JMenuItem readmeItem = new JMenuItem(JavarTranslator.translate("README"));
            readmeItem.addActionListener(readmeItemListener);
            return readmeItem;

        default:
            return new JMenuItem();
        }
    }
}
