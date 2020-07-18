package javar;

import javar.constants.JavarConstants;
import javar.codepane.CodePane;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Javar
{
    JFrame mainWindow = new JFrame();
    JScrollPane codeEditor = new JScrollPane(new CodePane());
    JTextArea outputArea = new JTextArea();
    JTree fileTree = new JTree();
    JPanel tmpPanel = new JPanel();
    
    public void initMainWindow()
    {
        codeEditor.setPreferredSize(new Dimension(JavarConstants.codeEditorWidth, JavarConstants.codeEditorHeight));
        outputArea.setPreferredSize(new Dimension(JavarConstants.outputAreaWidth, JavarConstants.outputAreaHeight));
        fileTree.setPreferredSize(new Dimension(JavarConstants.fileTreeWidth, JavarConstants.fileTreeHeight));
        tmpPanel.setPreferredSize(new Dimension(JavarConstants.tmpPanelWidth, JavarConstants.tmpPanelHeight));
        var leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, fileTree, tmpPanel);
        var rightPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, codeEditor, outputArea);
        var centerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        leftPanel.setContinuousLayout(true);
        leftPanel.resetToPreferredSizes();
        rightPanel.setContinuousLayout(true);
        rightPanel.setOneTouchExpandable(true);
        rightPanel.resetToPreferredSizes();
        centerPanel.setContinuousLayout(true);
        centerPanel.resetToPreferredSizes();
        mainWindow.add(centerPanel);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }

    public static void main(String[] args)
    {
        var app = new Javar();
        app.initMainWindow();
    }
}
