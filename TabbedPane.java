package javar.tabbedpane;

import javar.constants.JavarConstants;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

import javar.codepane.CodePane;

public class TabbedPane extends JTabbedPane
{
    /* All useful components */
    //TODO
    JTextArea outputTextArea = new JTextArea();
    JTextArea debugTextArea = new JTextArea();
    ImageIcon icon = new ImageIcon("images/icons/javaIcon.png");
    public TabbedPane(int type)
    {
        super(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        switch (type)
        {
            case JavarConstants.CodePane:
                initTabbedCodePane();
                break;
            case JavarConstants.OutputArea:
                initTabbedOutputArea();
                break;
        }
    }
    public void initTabbedOutputArea()
    {
        outputTextArea.setEditable(false);
        debugTextArea.setEditable(false);
        this.addTab("Output", new JScrollPane(outputTextArea));
        this.addTab("Debug", new JScrollPane(debugTextArea));
    }
    public void initTabbedCodePane()
    {
        icon.setImage(icon.getImage().getScaledInstance(15,15,Image.SCALE_SMOOTH)); 
        this.addTab("Test.java", icon, new JScrollPane(new CodePane()), "Test.java");
        this.addTab("Another.java", icon, new JScrollPane(new CodePane()), "Another.java");
        this.addTab("Main.java", icon, new JScrollPane(new CodePane()), "Main.java");
    }
}
