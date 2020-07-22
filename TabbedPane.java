package javar.tabbedpane;

import javar.constants.JavarConstants;
import javar.codepane.CodePane;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;


public class TabbedPane extends JTabbedPane
{
    /* All useful components */
    public static JTextArea outputTextArea = new JTextArea();
    public static JTextArea debugTextArea = new JTextArea();
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
        JLabel navigatorLabel = new JLabel();
        navigatorLabel.setText(JavarConstants.navigatorLabelContent);
        navigatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        navigatorLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.addTab(JavarConstants.navigatorLabelName, navigatorLabel);
    }
}
