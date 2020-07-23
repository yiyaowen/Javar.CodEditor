package javar.menuprovider;

import javar.constants.JavarConstants;
import javar.menuitemprovider.MenuItemProvider;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MenuProvider
{
    public static JPopupMenu createPopupMenu(int type)
    {
        switch (type)
        {
            case JavarConstants.fileListPopupType:
                JPopupMenu fileListPopupMenu = new JPopupMenu();
                fileListPopupMenu.add(createMenu(JavarConstants.newMenuType));
                fileListPopupMenu.add(MenuItemProvider.createMenuItem(JavarConstants.renameItemPopupType));
                fileListPopupMenu.addSeparator();
                fileListPopupMenu.add(MenuItemProvider.createMenuItem(JavarConstants.codeAnalysisItemPopupType));
                fileListPopupMenu.addSeparator();
                fileListPopupMenu.add(MenuItemProvider.createMenuItem(JavarConstants.removeItemPopupType));
                fileListPopupMenu.add(MenuItemProvider.createMenuItem(JavarConstants.deleteItemPopupType));
                return fileListPopupMenu;
            default:
                return new JPopupMenu();
        }
    }

    public static JMenu createMenu(int type)
    {
        switch (type)
        {
            case JavarConstants.newMenuType:
                JMenu newMenu = new JMenu("New");
                newMenu.add(MenuItemProvider.createMenuItem(JavarConstants.javaItemType));
                return newMenu;
            case JavarConstants.fileMenuType:
                JMenu fileMenu = new JMenu("File");
                fileMenu.add(MenuItemProvider.createMenuItem(JavarConstants.newItemType));
                fileMenu.add(MenuItemProvider.createMenuItem(JavarConstants.openItemType));
                fileMenu.addSeparator();
                fileMenu.add(MenuItemProvider.createMenuItem(JavarConstants.saveItemType));
                fileMenu.add(MenuItemProvider.createMenuItem(JavarConstants.saveToItemType));
                fileMenu.add(MenuItemProvider.createMenuItem(JavarConstants.renameItemType));
                fileMenu.addSeparator();
                fileMenu.add(MenuItemProvider.createMenuItem(JavarConstants.removeItemType));
                fileMenu.add(MenuItemProvider.createMenuItem(JavarConstants.deleteItemType));
                return fileMenu;
            case JavarConstants.editMenuType:
                JMenu editMenu = new JMenu("Edit");
                editMenu.add(MenuItemProvider.createMenuItem(JavarConstants.copyItemType));
                editMenu.add(MenuItemProvider.createMenuItem(JavarConstants.pasteItemType));
                editMenu.add(MenuItemProvider.createMenuItem(JavarConstants.cutItemType));
                editMenu.addSeparator();
                editMenu.add(MenuItemProvider.createMenuItem(JavarConstants.commentItemType));
                editMenu.add(MenuItemProvider.createMenuItem(JavarConstants.replaceItemType));
                return editMenu;
            case JavarConstants.buildMenuType:
                JMenu buildMenu = new JMenu("Build");
                buildMenu.add(MenuItemProvider.createMenuItem(JavarConstants.buildItemType));
                return buildMenu;
            case JavarConstants.runMenuType:
                JMenu runMenu = new JMenu("Run");
                runMenu.add(MenuItemProvider.createMenuItem(JavarConstants.runItemType));
                return runMenu;
            case JavarConstants.toolsMenuType:
                JMenu toolsMenu = new JMenu("Tools");
                toolsMenu.add(MenuItemProvider.createMenuItem(JavarConstants.codeAnalysisItemType));
                return toolsMenu;
            case JavarConstants.optionsMenuType:
                JMenu optionsMenu = new JMenu("Options");
                optionsMenu.add(MenuItemProvider.createMenuItem(JavarConstants.generalItemType));
                optionsMenu.add(MenuItemProvider.createMenuItem(JavarConstants.highlightConfigurationItemType));
                return optionsMenu;
            case JavarConstants.aboutMenuType:
                JMenu aboutMenu = new JMenu("About");
                aboutMenu.add(MenuItemProvider.createMenuItem(JavarConstants.licenseItemType));
                aboutMenu.add(MenuItemProvider.createMenuItem(JavarConstants.readmeItemType));
                return aboutMenu;
            default:
                return new JMenu();
        }
    }
}
