package com.yiyaowen.javar;

import com.yiyaowen.javar.JavarConstants;
import com.yiyaowen.javar.JavarTranslator;
import com.yiyaowen.javar.MenuItemProvider;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MenuProvider
{
    ////////////
    // Method //
    ////////////

    /**
     * Create a JPopupMenu component of the specific type
     *
     * @param type (The type of the popup-menu component to be created)
     * @return menu (The popup-menu component created)
     */
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
    /**
     * Create a JMenu component of the specific type
     *
     * @param type (The type of the menu component to be created)
     * @return menu (The menu component created)
     */
    public static JMenu createMenu(int type)
    {
        switch (type)
        {
        case JavarConstants.newMenuType:
            JMenu newMenu;
            newMenu = new JMenu(JavarTranslator.translate("New"));
            newMenu.add(MenuItemProvider.createMenuItem(JavarConstants.anyItemType));
            newMenu.add(MenuItemProvider.createMenuItem(JavarConstants.javaItemType));
            newMenu.add(MenuItemProvider.createMenuItem(JavarConstants.cItemType));
            newMenu.add(MenuItemProvider.createMenuItem(JavarConstants.cppItemType));
            newMenu.add(MenuItemProvider.createMenuItem(JavarConstants.pyItemType));
            newMenu.add(MenuItemProvider.createMenuItem(JavarConstants.htmlItemType));
            return newMenu;

        case JavarConstants.fileMenuType:
            JMenu fileMenu;
            fileMenu = new JMenu(JavarTranslator.translate("File"));
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
            JMenu editMenu;
            editMenu = new JMenu(JavarTranslator.translate("Edit"));
            editMenu.add(MenuItemProvider.createMenuItem(JavarConstants.copyItemType));
            editMenu.add(MenuItemProvider.createMenuItem(JavarConstants.pasteItemType));
            editMenu.add(MenuItemProvider.createMenuItem(JavarConstants.cutItemType));
            editMenu.addSeparator();
            editMenu.add(MenuItemProvider.createMenuItem(JavarConstants.commentItemType));
            editMenu.add(MenuItemProvider.createMenuItem(JavarConstants.replaceItemType));
            return editMenu;

        case JavarConstants.buildMenuType:
            JMenu buildMenu;
            buildMenu = new JMenu(JavarTranslator.translate("Build"));
            buildMenu.add(MenuItemProvider.createMenuItem(JavarConstants.buildItemType));
            return buildMenu;

        case JavarConstants.runMenuType:
            JMenu runMenu;
            runMenu = new JMenu(JavarTranslator.translate("Run"));
            runMenu.add(MenuItemProvider.createMenuItem(JavarConstants.runItemType));
            return runMenu;

        case JavarConstants.toolsMenuType:
            JMenu toolsMenu;
            toolsMenu = new JMenu(JavarTranslator.translate("Tools"));
            toolsMenu.add(MenuItemProvider.createMenuItem(JavarConstants.codeAnalysisItemType));
            return toolsMenu;

        case JavarConstants.optionsMenuType:
            JMenu optionsMenu;
            optionsMenu = new JMenu(JavarTranslator.translate("Options"));
            optionsMenu.add(MenuItemProvider.createMenuItem(JavarConstants.generalItemType));
            optionsMenu.add(MenuItemProvider.createMenuItem(JavarConstants.highlightConfigurationItemType));
            return optionsMenu;

        case JavarConstants.aboutMenuType:
            JMenu aboutMenu;
            aboutMenu = new JMenu(JavarTranslator.translate("About"));
            aboutMenu.add(MenuItemProvider.createMenuItem(JavarConstants.licenseItemType));
            aboutMenu.add(MenuItemProvider.createMenuItem(JavarConstants.readmeItemType));
            return aboutMenu;

        default:
            return new JMenu();
        }
    }
}
