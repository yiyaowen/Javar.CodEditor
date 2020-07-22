package javar;

import javar.constants.JavarConstants;
import javar.codepane.CodePane;
import javar.filelist.FileList;
import javar.tabbedpane.TabbedPane;
import javar.upperbar.UpperBar;
import javar.managerbar.ManagerBar;
import javar.menuitemprovider.MenuItemProvider;
import javar.creatorwindow.CreatorWindow;
import javar.infolabel.InfoLabel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Javar
{
    public JFrame mainWindow = new JFrame();
    public static TabbedPane codeEditor = new TabbedPane(JavarConstants.CodePane);
    public static TabbedPane outputArea = new TabbedPane(JavarConstants.OutputArea);
    public static FileList fileList = new FileList();
    public static InfoLabel infoLabel = new InfoLabel();
    public UpperBar upperBar = new UpperBar();
    public ManagerBar managerBar = new ManagerBar();
    public static CreatorWindow creatorWindow = new CreatorWindow();
    
    public void initMainWindow()
    {
        codeEditor.setPreferredSize(new Dimension(JavarConstants.codeEditorWidth, JavarConstants.codeEditorHeight));
        outputArea.setPreferredSize(new Dimension(JavarConstants.outputAreaWidth, JavarConstants.outputAreaHeight));
        fileList.setPreferredSize(new Dimension(JavarConstants.fileListWidth, JavarConstants.fileListHeight));
        infoLabel.setPreferredSize(new Dimension(JavarConstants.infoLabelWidth, JavarConstants.infoLabelHeight));
        upperBar.setPreferredSize(new Dimension(JavarConstants.upperBarWidth, JavarConstants.upperBarHeight));
        var leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, fileList, infoLabel);
        var rightPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, codeEditor, outputArea);
        var centerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        leftPanel.setContinuousLayout(false);
        leftPanel.resetToPreferredSizes();
        rightPanel.setContinuousLayout(false);
        rightPanel.setOneTouchExpandable(true);
        rightPanel.resetToPreferredSizes();
        centerPanel.setContinuousLayout(false);
        centerPanel.resetToPreferredSizes();
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        mainWindow.setLayout(gb);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gb.setConstraints(upperBar, gbc);
        mainWindow.add(upperBar);
        gbc.weighty = 1;
        gb.setConstraints(centerPanel, gbc);
        mainWindow.add(centerPanel);
        mainWindow.setJMenuBar(managerBar);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }

    public static void main(String[] args)
    {
        JavarConstants.initJavarConstants();
        MenuItemProvider.initMenuItemProvider();
        var app = new Javar();
        app.initMainWindow();
    }
}
