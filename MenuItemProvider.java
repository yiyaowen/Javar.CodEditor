package javar.menuitemprovider;

import javar.constants.JavarConstants;
import javar.tabbedpane.TabbedPane;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

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

    public static void initMenuItemProvider()
    {
        newItemListener = e -> {
            TabbedPane.outputTextArea.append("New Item clicked.\n");
        };
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
