package com.yiyaowen.javar;

import com.yiyaowen.javar.CodePane;
import com.yiyaowen.javar.CreatorWindow;
import com.yiyaowen.javar.FileList;
import com.yiyaowen.javar.GeneralWindow;
import com.yiyaowen.javar.InfoLabel;
import com.yiyaowen.javar.JavarConstants;
import com.yiyaowen.javar.JavarLogger;
import com.yiyaowen.javar.ManagerBar;
import com.yiyaowen.javar.MenuItemProvider;
import com.yiyaowen.javar.TabbedPane;
import com.yiyaowen.javar.UpperBar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Javar
{
    static { System.setProperty("java.library.path", System.getProperty("user.dir") + "/c_lib"); }

    ////////////////
    // Properties //
    ////////////////

    public static JFrame mainWindow;
    public static TabbedPane codeEditor;
    public static TabbedPane outputArea;
    public static FileList fileList;
    public static InfoLabel infoLabel;
    public static UpperBar upperBar;
    public static ManagerBar managerBar;
    public static CreatorWindow creatorWindow;
    public static GeneralWindow generalWindow;

    public static JavarLogger logger = new JavarLogger();

    ////////////
    // Method //
    ////////////
    
    /**
     * Initialize main window
     * 
     * @param
     * @return
     */
    public void initMainWindow()
    {
        mainWindow = new JFrame();
        codeEditor = new TabbedPane(JavarConstants.CodePane);
        outputArea = new TabbedPane(JavarConstants.OutputArea);
        fileList = new FileList();
        infoLabel = new InfoLabel();
        upperBar = new UpperBar();
        managerBar = new ManagerBar();
        creatorWindow = new CreatorWindow();
        generalWindow = new GeneralWindow();
        // Set file list scrollable
        var fileListScrollable = new JScrollPane(fileList);
        // Start initalization
        codeEditor.setPreferredSize(new Dimension(JavarConstants.codeEditorWidth, JavarConstants.codeEditorHeight));
        outputArea.setPreferredSize(new Dimension(JavarConstants.outputAreaWidth, JavarConstants.outputAreaHeight));
        fileListScrollable.setPreferredSize(new Dimension(JavarConstants.fileListWidth, JavarConstants.fileListHeight));
        infoLabel.setPreferredSize(new Dimension(JavarConstants.infoLabelWidth, JavarConstants.infoLabelHeight));
        upperBar.setPreferredSize(new Dimension(JavarConstants.upperBarWidth, JavarConstants.upperBarHeight));
        var leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, fileListScrollable, infoLabel);
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
        // Final adjustment
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.pack();
        mainWindow.setLocation((int)(JavarConstants.screenWidth/2-mainWindow.getWidth()/2), 
                               (int)(JavarConstants.screenHeight/2-mainWindow.getHeight()/2));
        // Show main window
        mainWindow.setVisible(true);
    }

    /**
     * Update LAF when application starts
     * 
     * @param
     * @return
     */
    public void setLAF()
    {
        if (JavarConstants.defaultLAF.equals("NONE")) {
            logger.log("d", "Default LAF (Look And Feel) equals NONE\n");
            return;
        }
        try
        {
            for (var info : UIManager.getInstalledLookAndFeels()) {
                if (JavarConstants.defaultLAF.equals(info.getName())) {            	
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception ex)
        {
            Javar.logger.log("e", ex.getMessage());
        }
    }

    //////////
    // Main //
    //////////

    public static void main(String[] args)
    {
    	// Global initialization
        JavarConstants.initJavarConstants();
        MenuItemProvider.initMenuItemProvider();
        // Start application
        Javar app = new Javar();
        app.setLAF();
        app.initMainWindow();
    }
}
