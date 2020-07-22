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
    public static ActionListener openItemPopupListener;
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
    public static ActionListener dirItemListener;
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
                    String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
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
                    Javar.codeEditor.addTab(file.getName(), icon, new JScrollPane(new CodePane()));
                    var tmp1 = (JScrollPane) Javar.codeEditor.getComponentAt(Javar.codeEditor.getTabCount()-1);
                    var tmp2 = (CodePane) tmp1.getViewport().getComponents()[0];
                    tmp2.setText(content);
                    Javar.codeEditor.setSelectedIndex(Javar.codeEditor.getTabCount()-1);
                    /* Set file list */
                    Javar.fileList.fileItems.add(data);
                    Javar.fileList.setListData(Javar.fileList.fileItems);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(source, JavarConstants.openItemListenerErrorMessage, JavarConstants.openItemListenerErrorTitle, JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        };
        /*saveItemListener = e -> {
            try (
                var outChannel = new FileOutputStream(
            var tmp1 = (JScrollPane) Javar.codeEditor.getComponentAt(Javar.codeEditor.getTabCount()-1);
            var tmp2 = (CodePane) tmp1.getViewport().getComponents()[0];*/
    }
    
    public static JMenuItem createMenuItem(int type)
    {
        switch (type)
        {
            case JavarConstants.dirItemType:
                JMenuItem dirItem = new JMenuItem("Directory");
                dirItem.addActionListener(dirItemListener);
                return dirItem;
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
            case JavarConstants.openItemPopupType:
                JMenuItem openPopupItem = new JMenuItem("Open");
                openPopupItem.addActionListener(openItemPopupListener);
                return openPopupItem;
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
