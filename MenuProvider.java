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
                JMenu newMenu;
                if (JavarConstants.LANG.equals("EN"))
                    newMenu = new JMenu("New");
                else if (JavarConstants.LANG.equals("CN"))
                    newMenu = new JMenu("新建");
                else
                    newMenu = new JMenu("New");
                newMenu.add(MenuItemProvider.createMenuItem(JavarConstants.anyItemType));
                newMenu.add(MenuItemProvider.createMenuItem(JavarConstants.javaItemType));
                newMenu.add(MenuItemProvider.createMenuItem(JavarConstants.cItemType));
                newMenu.add(MenuItemProvider.createMenuItem(JavarConstants.cppItemType));
                newMenu.add(MenuItemProvider.createMenuItem(JavarConstants.pyItemType));
                newMenu.add(MenuItemProvider.createMenuItem(JavarConstants.htmlItemType));
                return newMenu;
            case JavarConstants.fileMenuType:
                JMenu fileMenu;
                if (JavarConstants.LANG.equals("EN"))
                    fileMenu = new JMenu("File");
                else if (JavarConstants.LANG.equals("CN"))
                    fileMenu = new JMenu("文件");
                else
                    fileMenu = new JMenu("File");
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
                if (JavarConstants.LANG.equals("EN"))
                    editMenu = new JMenu("Edit");
                else if (JavarConstants.LANG.equals("CN"))
                    editMenu = new JMenu("编辑");
                else
                    editMenu = new JMenu("Edit");
                editMenu.add(MenuItemProvider.createMenuItem(JavarConstants.copyItemType));
                editMenu.add(MenuItemProvider.createMenuItem(JavarConstants.pasteItemType));
                editMenu.add(MenuItemProvider.createMenuItem(JavarConstants.cutItemType));
                editMenu.addSeparator();
                editMenu.add(MenuItemProvider.createMenuItem(JavarConstants.commentItemType));
                editMenu.add(MenuItemProvider.createMenuItem(JavarConstants.replaceItemType));
                return editMenu;
            case JavarConstants.buildMenuType:
                JMenu buildMenu;
                if (JavarConstants.LANG.equals("EN"))
                    buildMenu = new JMenu("Build");
                else if (JavarConstants.LANG.equals("CN"))
                    buildMenu = new JMenu("编译");
                else
                    buildMenu = new JMenu("Build");
                buildMenu.add(MenuItemProvider.createMenuItem(JavarConstants.buildItemType));
                return buildMenu;
            case JavarConstants.runMenuType:
                JMenu runMenu;
                if (JavarConstants.LANG.equals("EN"))
                    runMenu = new JMenu("Run");
                else if (JavarConstants.LANG.equals("CN"))
                    runMenu = new JMenu("运行");
                else
                    runMenu = new JMenu("Run");
                runMenu.add(MenuItemProvider.createMenuItem(JavarConstants.runItemType));
                return runMenu;
            case JavarConstants.toolsMenuType:
                JMenu toolsMenu;
                if (JavarConstants.LANG.equals("EN"))
                    toolsMenu = new JMenu("Tools");
                else if (JavarConstants.LANG.equals("CN"))
                    toolsMenu = new JMenu("工具");
                else
                    toolsMenu = new JMenu("Tools");
                toolsMenu.add(MenuItemProvider.createMenuItem(JavarConstants.codeAnalysisItemType));
                return toolsMenu;
            case JavarConstants.optionsMenuType:
                JMenu optionsMenu;
                if (JavarConstants.LANG.equals("EN"))
                    optionsMenu = new JMenu("Options");
                else if (JavarConstants.LANG.equals("CN"))
                    optionsMenu = new JMenu("首选项");
                else
                    optionsMenu = new JMenu("Options");
                optionsMenu.add(MenuItemProvider.createMenuItem(JavarConstants.generalItemType));
                optionsMenu.add(MenuItemProvider.createMenuItem(JavarConstants.highlightConfigurationItemType));
                return optionsMenu;
            case JavarConstants.aboutMenuType:
                JMenu aboutMenu;
                if (JavarConstants.LANG.equals("EN"))
                    aboutMenu = new JMenu("About");
                else if (JavarConstants.LANG.equals("CN"))
                    aboutMenu = new JMenu("关于");
                else
                    aboutMenu = new JMenu("About");
                aboutMenu.add(MenuItemProvider.createMenuItem(JavarConstants.licenseItemType));
                aboutMenu.add(MenuItemProvider.createMenuItem(JavarConstants.readmeItemType));
                return aboutMenu;
            default:
                return new JMenu();
        }
    }
}
