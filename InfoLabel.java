package javar.infolabel;

import javar.constants.JavarConstants;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class InfoLabel extends JLabel
{
    String currentFile;
    int count;
    int total;
    public InfoLabel()
    {
        /* Set preferred size */
        this.setPreferredSize(new Dimension(JavarConstants.infoLabelWidth, JavarConstants.infoLabelHeight));
        /* Set border */
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    }
    public void setAll(String currentFile, int count, int total)
    {
        setCurrentFile(currentFile);
        setCount(count);
        setTotal(total);
    }
    public void setCurrentFile(String currentFile)
    {
        this.currentFile = currentFile;
    }
    public void setCount(int count)
    {
        this.count = count;
    }
    public void setTotal(int total)
    {
        this.total = total;
    }
    public void updateText()
    {
        this.setText(JavarConstants.infoLabelContentPrefix + currentFile
            + JavarConstants.infoLabelContentName + count 
            + JavarConstants.infoLabelContentCount + total
            + JavarConstants.infoLabelContentTotal + JavarConstants.infoLabelContentSuffix);
    }
}
