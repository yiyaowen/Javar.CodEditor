package com.yiyaowen.javar;

import com.yiyaowen.javar.JavarConstants;
import com.yiyaowen.javar.JavarUtils;
import com.yiyaowen.javar.Javar;
import com.yiyaowen.javar.MenuProvider;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

@SuppressWarnings(value = "unchecked")
public class FileList extends JList
{
    //////////////
    // Property //
    //////////////
	
    public static Vector<FLItemData> fileItems = new Vector<>();
    
    /////////////////
    // Constructor //
    /////////////////
    
    public FileList()
    {
        initFileList();
    }
    
    ////////////
    // Method //
    ////////////
    
    /**
     * Initialize file list
     * 
     * @param
     * @return
     */
    public void initFileList()
    {
        // Set list popup menu
        this.setComponentPopupMenu(MenuProvider.createPopupMenu(JavarConstants.fileListPopupType));
        // Set list data and renderer
        this.setListData(fileItems);
        this.setPreferredSize(new Dimension(JavarConstants.fileListWidth, JavarConstants.fileListHeight));
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.setCellRenderer(new FLItemCellRenderer(JavarConstants.fileListItemWidth, JavarConstants.fileListItemHeight));
        this.addListSelectionListener(e -> {
            var data = (FLItemData) this.getSelectedValue();
            if (data == null)
            {
                Javar.infoLabel.setText("");
                return;
            }
            // Set editor pane selected tab
            var index = this.getSelectedIndex() + 1;
            if (index < 0)
                index = 0;
            if (index >= Javar.codeEditor.getTabCount())
                index = Javar.codeEditor.getTabCount() - 1;
            Javar.codeEditor.setSelectedIndex(index);
            // Set compiler selector
            String suffix = data.getFileName().substring(data.getFileName().lastIndexOf(".")+1);
            if (suffix.equals("java"))
                Javar.upperBar.compilerSelector.setSelectedItem("Java");
            else if (suffix.equals("c"))
                Javar.upperBar.compilerSelector.setSelectedItem("C");
            else if (suffix.equals("cpp"))
                Javar.upperBar.compilerSelector.setSelectedItem("C++");
            else if (suffix.equals("py"))
                Javar.upperBar.compilerSelector.setSelectedItem("Python");
            else if (suffix.equals("html"))
                Javar.upperBar.compilerSelector.setSelectedItem("html");
            else
                Javar.upperBar.compilerSelector.setSelectedItem("Java");
            // Set info label content
            Javar.infoLabel.setAll(data.getFileName(), data.getFileType(), data.getFilePath(), data.getFileSize(), data.getFileCreated(), data.getFileLastModified());
            Javar.infoLabel.updateText();
        });
    }
    
    ///////////////////////
    // getter and setter //
    ///////////////////////
    
    public String getSelectedItemDataFileName()
    {
        return ((FLItemData) this.getSelectedValue()).getFileName();
    }
    public void setSelectedItemDataFileName(String fileName)
    {
        ((FLItemData) this.getSelectedValue()).setFileName(fileName);
    }
    public String getSelectedItemDataFileType()
    {
        return ((FLItemData) this.getSelectedValue()).getFileType();
    }
    public void setSelectedItemDataFileType(String fileType)
    {
        ((FLItemData) this.getSelectedValue()).setFileType(fileType);
    }
    public void setSelectedItemDataFileSize(String fileSize)
    {
        ((FLItemData) this.getSelectedValue()).setFileSize(fileSize);
    }
    public void setSelectedItemDataFileLastModified(String fileLastModified)
    {
        ((FLItemData) this.getSelectedValue()).setFileLastModified(fileLastModified);
    }
    public String getSelectedItemDataFilePath()
    {
        return ((FLItemData) this.getSelectedValue()).getFilePath();
    }
    public void setSelectedItemDataFilePath(String filePath)
    {
        ((FLItemData) this.getSelectedValue()).setFilePath(filePath);
    }
    public static FLItemData createItemData(String fileType, String fileName, String filePath, String fileSize, String fileCreated, String fileLastModified)
    {
        return new FLItemData(fileType, fileName, filePath, fileSize, fileCreated, fileLastModified);
    }
}

class FLItemData
{   
    //////////////
    // Property //
    //////////////
	
    String fileType;
    String fileName;
    String filePath;
    String fileSize;
    String fileCreated;
    String fileLastModified;
    
    /////////////////
    // Constructor //
    /////////////////
    
    public FLItemData(String fileName, String fileType, String filePath, String fileSize, String fileCreated, String fileLastModified)
    {
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.fileCreated = fileCreated;
        this.fileLastModified = fileLastModified;
    }
    
    ///////////////////////
    // getter and setter //
    ///////////////////////
    
    public String getFileName()
    {
        return fileName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    public String getFileType()
    {
        return fileType;
    }
    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }
    public String getFilePath()
    {
        return filePath;
    }
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }
    public String getFileSize()
    {
        return fileSize;
    }
    public void setFileSize(String fileSize)
    {
        this.fileSize = fileSize;
    }
    public String getFileCreated()
    {
        return fileCreated;
    }
    public void setFileCreated(String fileCreated)
    {
        this.fileCreated = fileCreated;
    }
    public String getFileLastModified()
    {
        return fileLastModified;
    }
    public void setFileLastModified(String fileLastModified)
    {   
        this.fileLastModified = fileLastModified;
    }
}

class FLItemCellRenderer extends JPanel implements ListCellRenderer
{
    //////////////
    // Property //
    //////////////
	
    int W, H;
    ImageIcon icon;
    String fileName;
    Color background;
    Color foreground;
    
    /////////////////
    // Constructor //
    /////////////////
    
    public FLItemCellRenderer(int W, int H)
    {
        this.W = W;
        this.H = H;
    }
    
    ////////////
    // Method //
    ////////////
    
    /**
     * Implementation of ListCellRenderer
     * 
     * @param
     * @return
     */
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        var data = (FLItemData) value;
        fileName = data.getFileName();
        icon = new ImageIcon("../images/icons/" + fileName.substring(fileName.lastIndexOf(".")+1) + "FileTemplateIcon.png");
        background = isSelected ? list.getSelectionBackground() : list.getBackground();
        foreground = isSelected ? list.getSelectionForeground() : list.getForeground();
        return this;
    }
    
    /**
     * Override from JPanel
     * 
     * @param
     * @return
     */
    public void paintComponent(Graphics g)
    {
        icon.setImage(JavarUtils.resizeImageToFitHeightWithPadding(this, icon.getImage(), Image.SCALE_SMOOTH, JavarConstants.fileListIconPadding));
        g.setColor(background);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(foreground);
        g.drawImage(icon.getImage(), JavarConstants.fileListIconOffset, JavarConstants.fileListIconPadding, null);
        g.drawString(fileName, JavarConstants.fileListIconOffset*2+icon.getIconWidth(), (int)(g.getFontMetrics().getAscent()/2+getHeight()/2));
    }
    
    /**
     * Override from JPanel
     * 
     * @param
     * @return
     */
    public Dimension getPreferredSize()
    {
        return new Dimension(W, H);
    }
}
