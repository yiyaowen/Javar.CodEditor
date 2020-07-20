package javar.managerbar;

import javar.constants.JavarConstants;
import javar.menuprovider.MenuProvider;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ManagerBar extends JMenuBar
{
    public ManagerBar()
    {
        initManagerBar();
    }
    public void initManagerBar()
    {
        this.add(MenuProvider.createMenu(JavarConstants.fileMenuType));
        this.add(MenuProvider.createMenu(JavarConstants.editMenuType));
        this.add(MenuProvider.createMenu(JavarConstants.buildMenuType));
        this.add(MenuProvider.createMenu(JavarConstants.runMenuType));
        this.add(MenuProvider.createMenu(JavarConstants.toolsMenuType));
        this.add(MenuProvider.createMenu(JavarConstants.optionsMenuType));
        this.add(MenuProvider.createMenu(JavarConstants.aboutMenuType));
    }
}
