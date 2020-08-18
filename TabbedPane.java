package javar.tabbedpane;

import javar.constants.JavarConstants;
import javar.codepane.CodePane;
import javar.Javar;

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
    public static JLabel previewLabel = new JLabel();
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
        Font font1 = outputTextArea.getFont();
        outputTextArea.setFont(new Font(font1.getName(), font1.getStyle(), 14));
        Font font2 = debugTextArea.getFont();
        debugTextArea.setFont(new Font(font2.getName(), font2.getStyle(), 14));
        if (JavarConstants.LANG.equals("EN"))
            previewLabel.setText(JavarConstants.previewLabelContent);
        else if (JavarConstants.LANG.equals("CN"))
            previewLabel.setText(JavarConstants.previewLabelContent_cn);
        else
            previewLabel.setText(JavarConstants.previewLabelContent);
        previewLabel.setVerticalAlignment(SwingConstants.TOP);
        if (JavarConstants.LANG.equals("EN"))
        {
            this.addTab("Output", new JScrollPane(outputTextArea));
            this.addTab("Debug", new JScrollPane(debugTextArea));
            this.addTab("Preview", new JScrollPane(previewLabel));
        }
        else if (JavarConstants.LANG.equals("CN"))
        {
            this.addTab("输出", new JScrollPane(outputTextArea));
            this.addTab("调试", new JScrollPane(debugTextArea));
            this.addTab("预览", new JScrollPane(previewLabel));
        }
        else
        {
            this.addTab("Output", new JScrollPane(outputTextArea));
            this.addTab("Debug", new JScrollPane(debugTextArea));
            this.addTab("Preview", new JScrollPane(previewLabel));
        }
    }
    public void initTabbedCodePane()
    {
        JLabel navigatorLabel = new JLabel();
        if (JavarConstants.LANG.equals("EN"))
            navigatorLabel.setText(JavarConstants.navigatorLabelContent);
        else if (JavarConstants.LANG.equals("CN"))
            navigatorLabel.setText(JavarConstants.navigatorLabelContent_cn);
        else
            navigatorLabel.setText(JavarConstants.navigatorLabelContent);
        navigatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        navigatorLabel.setVerticalAlignment(SwingConstants.CENTER);
        if (JavarConstants.LANG.equals("EN"))
            this.addTab(JavarConstants.navigatorLabelName, navigatorLabel);
        else if (JavarConstants.LANG.equals("CN"))
            this.addTab(JavarConstants.navigatorLabelName_cn, navigatorLabel);
        else
            this.addTab(JavarConstants.navigatorLabelName, navigatorLabel);
        this.addChangeListener(e -> {
            var index = this.getSelectedIndex() - 1;
            if (index < 0)
            {
                Javar.fileList.clearSelection();
                return;
            }
            if (index >= Javar.fileList.getModel().getSize())
                index = Javar.fileList.getModel().getSize() - 1;
            Javar.fileList.setSelectedIndex(index);
        });
    }
}
