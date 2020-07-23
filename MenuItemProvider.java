package javar.menuitemprovider;

import javar.constants.JavarConstants;
import javar.tabbedpane.TabbedPane;
import javar.Javar;
import javar.creatorwindow.CreatorWindow;
import javar.codepane.CodePane;
import javar.utils.JavarUtils;
import javar.filelist.FileList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;

@SuppressWarnings(value = "unchecked")
public class MenuItemProvider
{
    /* MenuItem listeners */
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
    public static ActionListener javaItemListener;
    /* File chooser */
    public static JFileChooser chooser;

    public static void initMenuItemProvider()
    {
        /* Init listeners */
        newItemListener = e -> {
            Javar.creatorWindow = new CreatorWindow();
            Javar.creatorWindow.initCreatorWindow();
        };
        openItemListener = e -> {
            chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(true);
            var source = ((Component)e.getSource()).getParent();
            int result = chooser.showDialog(source, "Open File");
            if (result == JFileChooser.APPROVE_OPTION)
            {
                File file = chooser.getSelectedFile();
                try (
                    var inChannel = new FileInputStream(file).getChannel())
                {
                    /* Get file attributes */
                    String fileName = file.getName();
                    String fileSuffix = fileName.substring(fileName.lastIndexOf(".")+1);
                    String fileType = CreatorWindow.suffixTypeMap.get(fileSuffix);
                    if (fileType == null)
                        fileType = fileSuffix;
                    Path path = Paths.get(file.getPath());
                    BasicFileAttributeView basicView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
                    BasicFileAttributes basicAttributes = basicView.readAttributes();
                    String createdDate = (new Date(basicAttributes.creationTime().toMillis())).toString();
                    String lastModifiedDate = (new Date(basicAttributes.lastModifiedTime().toMillis()).toString());
                    double byteSize = (double) basicAttributes.size();
                    double kByteSize = (int)(byteSize * 10 / 1024) / 10;
                    double mByteSize = (int)(kByteSize * 10 / 1024) / 10;
                    String fileSize = kByteSize >= 1 ? (mByteSize >= 1 ? mByteSize+"MB" : kByteSize+"KB") : byteSize+"B";
                    var data = FileList.createItemData(fileName, fileType, file.getPath(), fileSize, createdDate, lastModifiedDate);
                    /* Get name and icon*/
                    ImageIcon icon = new ImageIcon("images/icons/" + fileName.substring(fileName.lastIndexOf(".")+1) + "FileTemplateIcon.png");
                    if (icon.getImage()  == null)
                        icon = new ImageIcon("images/icons/defaultFileTemplateIcon.png");
                    icon.setImage(JavarUtils.resizeImageToWH(icon.getImage(), JavarConstants.tabIconWidth, JavarConstants.tabIconHeight, Image.SCALE_SMOOTH));
                    /* Get content */
                    MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
                    Charset charset = Charset.forName("UTF-8");
                    CharsetDecoder decoder = charset.newDecoder();
                    CharBuffer charBuffer = decoder.decode(buffer);
                    String content = charBuffer.toString();
                    // Set tab scrollable
                    var tmpPanel = new JPanel();
                    tmpPanel.setLayout(new BorderLayout());
                    tmpPanel.add(new CodePane());
                    var tmpScroll = new JScrollPane(tmpPanel);
                    tmpScroll.getVerticalScrollBar().setUnitIncrement(JavarConstants.scrollUnitIncrement);
                    Javar.codeEditor.addTab(file.getName(), icon, tmpScroll);
                    var tmp1 = (JScrollPane) Javar.codeEditor.getComponentAt(Javar.codeEditor.getTabCount()-1);
                    var tmp2 = (CodePane) ((JPanel) tmp1.getViewport().getComponents()[0]).getComponents()[0];
                    tmp2.setText(content);
                    // Stay at the top
                    tmp2.setCaretPosition(0);
                    try
                    {
                        /* Set file list */
                        FileList.fileItems.add(data);
                        Javar.fileList.setListData(FileList.fileItems);
                    }
                    catch (Exception ignore) {}
                    /* Set selected tab */
                    Javar.codeEditor.setSelectedIndex(Javar.codeEditor.getTabCount()-1);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(source, JavarConstants.openItemListenerErrorMessage+ex.getMessage(), JavarConstants.openItemListenerErrorTitle, JOptionPane.ERROR_MESSAGE);
                    //ex.printStackTrace();
                    return;
                }
            }
        };
        saveItemListener = e -> {
            if (Javar.fileList.getModel().getSize() <= 0)
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
                Javar.upperBar.infoBox.setText(JavarConstants.saveItemListenerSuccessMessage + Javar.fileList.getSelectedItemDataFileName());
            }
            catch (Exception ex)
            {
                Javar.upperBar.infoBox.setText(JavarConstants.saveItemListenerErrorMessage + ex.getMessage()); 
                //ex.printStackTrace();
            }
            try
            {
                BasicFileAttributeView basicView = Files.getFileAttributeView(Paths.get(filePath), BasicFileAttributeView.class);
                BasicFileAttributes basicAttributes = basicView.readAttributes();
                String lastModifiedDate = (new Date(basicAttributes.lastModifiedTime().toMillis()).toString());
                double byteSize = (double) basicAttributes.size();
                double kByteSize = (int)(byteSize * 10 / 1024) / 10;
                double mByteSize = (int)(kByteSize * 10 / 1024) / 10;
                String fileSize = kByteSize >= 1 ? (mByteSize >= 1 ? mByteSize+"MB" : kByteSize+"KB") : byteSize+"B";
                Javar.infoLabel.setFileLastModified(lastModifiedDate);
                Javar.fileList.setSelectedItemDataFileLastModified(lastModifiedDate);
                Javar.infoLabel.setFileSize(fileSize);
                Javar.fileList.setSelectedItemDataFileSize(fileSize);
                Javar.infoLabel.updateText();
            }
            catch (Exception update)
            {
                Javar.upperBar.infoBox.setText(JavarConstants.saveFileUpdateInfoErrorMessage + update.getMessage()); 
                //update.printStackTrace();
            }
        };
        saveToItemListener = e -> {
            if (Javar.fileList.getModel().getSize() <= 0)
                return;
            var source = ((Component)e.getSource()).getParent();
            chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            String fileName = JOptionPane.showInputDialog(source, JavarConstants.saveToFileChooserLabelContent, JavarConstants.saveToFileChooserLabelTitle, JOptionPane.INFORMATION_MESSAGE);
            if (fileName == null)
                return;
            int result = chooser.showDialog(source, JavarConstants.saveToFileChooserContent);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                String filePath = chooser.getSelectedFile().getPath() + "/" + fileName;
                try (
                    var fw = new FileWriter(filePath))
                {
                    var tmp1 = (JScrollPane) Javar.codeEditor.getComponentAt(Javar.codeEditor.getSelectedIndex());
                    var tmp2 = (CodePane) ((JPanel) tmp1.getViewport().getComponents()[0]).getComponents()[0];
                    String content = tmp2.getText();        
                    fw.write(content);
                    Javar.upperBar.infoBox.setText(JavarConstants.saveToItemListenerSuccessMessage1 + fileName + JavarConstants.saveToItemListenerSuccessMessage2);
                }
                catch (Exception ex)
                {
                    Javar.upperBar.infoBox.setText(JavarConstants.saveToItemListenerErrorMessage1 + ex.getMessage() + JavarConstants.saveToItemListenerErrorMessage2); 
                    //ex.printStackTrace();
                }
            }
        };
        renameItemListener = e -> {
            if (Javar.fileList.getModel().getSize() <= 0)
                return;
            var source = ((Component)e.getSource()).getParent();
            String filePath = Javar.fileList.getSelectedItemDataFilePath();
            String dirPath = filePath.substring(0, filePath.lastIndexOf("/")+1);
            File file = new File(filePath);
            String newName = JOptionPane.showInputDialog(source, JavarConstants.renameItemListenerContent, JavarConstants.renameItemListenerTitle, JOptionPane.INFORMATION_MESSAGE);
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
                ImageIcon icon = new ImageIcon("images/icons/" + fileType + "FileTemplateIcon.png");
                if (icon == null)
                    icon = new ImageIcon("images/icons/defaultFileTemplateIcon.png");
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
                JOptionPane.showMessageDialog(source, JavarConstants.renameItemListenerErrorMessage, JavarConstants.renameItemListenerErrorTitle, JOptionPane.ERROR_MESSAGE);
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
            var source = ((Component)e.getSource()).getParent();
            int delete = JOptionPane.showConfirmDialog(source, JavarConstants.deleteItemListenerContent, JavarConstants.deleteItemListenerTitle, JOptionPane.YES_NO_OPTION);
            if (delete == JOptionPane.OK_OPTION)
            {
                File file = new File(Javar.fileList.getSelectedItemDataFilePath());
                if (file.delete())
                {
                    removeItemListener.actionPerformed(new ActionEvent(source, ActionEvent.ACTION_PERFORMED, ""));
                    Javar.upperBar.infoBox.setText(JavarConstants.deleteItemListenerSuccessMessage + file.getName());
                }
                else
                {
                    Javar.upperBar.infoBox.setText(JavarConstants.deleteItemListenerErrorMessage + file.getName());
                }
            }
        };
        deleteItemPopupListener = deleteItemListener;
    }
    
    public static JMenuItem createMenuItem(int type)
    {
        switch (type)
        {
            case JavarConstants.javaItemType:
                JMenuItem javaItem = new JMenuItem("Java Source file");
                javaItem.addActionListener(javaItemListener);
                return javaItem;
            case JavarConstants.newItemType:
                JMenuItem newItem = new JMenuItem("New");
                newItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.newItemChar, JavarConstants.newItemModifier));
                newItem.addActionListener(newItemListener);
                return newItem;
            case JavarConstants.openItemType:
                JMenuItem openItem = new JMenuItem("Open");
                openItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.openItemChar, JavarConstants.openItemModifier));
                openItem.addActionListener(openItemListener);
                return openItem;
            case JavarConstants.saveItemType:
                JMenuItem saveItem = new JMenuItem("Save");
                saveItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.saveItemChar, JavarConstants.saveItemModifier));
                saveItem.addActionListener(saveItemListener);
                return saveItem;
            case JavarConstants.saveToItemType:
                JMenuItem saveToItem = new JMenuItem("Save To...");
                saveToItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.saveToItemChar, JavarConstants.saveToItemModifier));
                saveToItem.addActionListener(saveToItemListener);
                return saveToItem;
            case JavarConstants.renameItemType:
                JMenuItem renameItem = new JMenuItem("Rename");
                renameItem.addActionListener(renameItemListener);
                return renameItem;
            case JavarConstants.renameItemPopupType:
                JMenuItem renamePopupItem = new JMenuItem("Rename");
                renamePopupItem.addActionListener(renameItemPopupListener);
                return renamePopupItem;
            case JavarConstants.removeItemType:
                JMenuItem removeItem = new JMenuItem("Remove");
                removeItem.addActionListener(removeItemListener);
                removeItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.removeItemChar, JavarConstants.removeItemModifier));
                return removeItem;
            case JavarConstants.removeItemPopupType:
                JMenuItem removePopupItem = new JMenuItem("Remove");
                removePopupItem.addActionListener(removeItemPopupListener);
                return removePopupItem;
            case JavarConstants.deleteItemType:
                JMenuItem deleteItem = new JMenuItem("Delete");
                deleteItem.addActionListener(deleteItemListener);
                return deleteItem;
            case JavarConstants.deleteItemPopupType:
                JMenuItem deletePopupItem = new JMenuItem("Delete");
                deletePopupItem.addActionListener(deleteItemPopupListener);
                return deletePopupItem;
            case JavarConstants.copyItemType:
                JMenuItem copyItem = new JMenuItem("Copy");
                copyItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.copyItemChar, JavarConstants.copyItemModifier));
                copyItem.addActionListener(copyItemListener);
                return copyItem;
            case JavarConstants.pasteItemType:
                JMenuItem pasteItem = new JMenuItem("Paste");
                pasteItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.pasteItemChar, JavarConstants.pasteItemModifier));
                pasteItem.addActionListener(pasteItemListener);
                return pasteItem;
            case JavarConstants.cutItemType:
                JMenuItem cutItem = new JMenuItem("Cut");
                cutItem.addActionListener(cutItemListener);
                return cutItem;
            case JavarConstants.commentItemType:
                JMenuItem commentItem = new JMenuItem("Comment/Uncomment");
                commentItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.commentItemChar, JavarConstants.commentItemModifier));
                commentItem.addActionListener(commentItemListener);
                return commentItem;
            case JavarConstants.replaceItemType:
                JMenuItem replaceItem = new JMenuItem("Replace");
                replaceItem.addActionListener(replaceItemListener);
                return replaceItem;
            case JavarConstants.buildItemType:
                JMenuItem buildItem = new JMenuItem("Build");
                buildItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.buildItemChar, JavarConstants.buildItemModifier));
                buildItem.addActionListener(buildItemListener);
                return buildItem;
            case JavarConstants.runItemType:
                JMenuItem runItem = new JMenuItem("Run");
                runItem.setAccelerator(KeyStroke.getKeyStroke(JavarConstants.runItemChar, JavarConstants.runItemModifier));
                runItem.addActionListener(runItemListener);
                return runItem;
            case JavarConstants.codeAnalysisItemType:
                JMenuItem codeAnalysisItem = new JMenuItem("Code Analysis");
                codeAnalysisItem.addActionListener(codeAnalysisItemListener);
                return codeAnalysisItem;
            case JavarConstants.codeAnalysisItemPopupType:
                JMenuItem codeAnalysisPopupItem = new JMenuItem("Code Analysis");
                codeAnalysisPopupItem.addActionListener(codeAnalysisItemPopupListener);
                return codeAnalysisPopupItem;
            case JavarConstants.generalItemType:
                JMenuItem generalItem = new JMenuItem("General");
                generalItem.addActionListener(generalItemListener);
                return generalItem;
            case JavarConstants.highlightConfigurationItemType:
                JMenuItem highlightConfigurationItem = new JMenuItem("Highlight Configuration");
                highlightConfigurationItem.addActionListener(highlightConfigurationItemListener);
                return highlightConfigurationItem;
            case JavarConstants.licenseItemType:
                JMenuItem licenseItem = new JMenuItem("License");
                licenseItem.addActionListener(licenseItemListener);
                return licenseItem;
            case JavarConstants.readmeItemType:
                JMenuItem readmeItem = new JMenuItem("README");
                readmeItem.addActionListener(readmeItemListener);
                return readmeItem;
            default:
                return new JMenuItem();
        }
    }
}
