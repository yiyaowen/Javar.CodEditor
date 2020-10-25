package com.yiyaowen.javar;

import com.yiyaowen.javar.JavarConstants;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class InfoLabel extends JLabel
{
    //////////////
    // Property //
    //////////////

    String currentFile;
    String fileType;
    String filePath;
    String fileSize;
    String fileCreated;
    String fileLastModified;

    /////////////////
    // Constructor //
    /////////////////

    public InfoLabel()
    {
        // Set preferred size
        this.setPreferredSize(new Dimension(JavarConstants.infoLabelWidth, JavarConstants.infoLabelHeight));
        // Set border
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        // Set alignment
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
    }

    ////////////
    // setter //
    ////////////

    public void setAll(String currentFile, String fileType, String filePath, String fileSize, String fileCreated, String fileLastModified)
    {
        setCurrentFile(currentFile);
        setFileType(fileType);
        setFilePath(filePath);
        setFileSize(fileSize);
        setFileCreated(fileCreated);
        setFileLastModified(fileLastModified);
    }
    public void setCurrentFile(String currentFile)
    {
        this.currentFile = currentFile;
    }
    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }
    public void setFileSize(String fileSize)
    {
        this.fileSize = fileSize;
    }
    public void setFileCreated(String fileCreated)
    {
        this.fileCreated = fileCreated;
    }
    public void setFileLastModified(String fileLastModified)
    {
        this.fileLastModified = fileLastModified;
    }

    ////////////
    // Method //
    ////////////

    /**
     * Update label's text information of current file
     *
     * @param
     * @return
     */
    public void updateText()
    {
        this.setText(JavarTranslator.translate(JavarConstants.infoLabelContent1) + currentFile +
			JavarTranslator.translate(JavarConstants.infoLabelContent2) + fileType + 
			JavarTranslator.translate(JavarConstants.infoLabelContent3) + fileSize + 
			JavarTranslator.translate(JavarConstants.infoLabelContent4) + fileCreated +
			JavarTranslator.translate(JavarConstants.infoLabelContent5) + fileLastModified + 
			JavarTranslator.translate(JavarConstants.infoLabelContent6));
    }
}
