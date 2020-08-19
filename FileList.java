package javar.filelist;

import javar.constants.JavarConstants;
import javar.utils.JavarUtils;
import javar.Javar;
import javar.menuprovider.MenuProvider;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.*;

@SuppressWarnings(value = "unchecked")
public class FileList extends JList
{
    public static Vector<ItemData> fileItems = new Vector<>();
    public FileList()
    {
        initFileList();
    }
    public void initFileList()
    {
        /* Set list popup menu */
        this.setComponentPopupMenu(MenuProvider.createPopupMenu(JavarConstants.fileListPopupType));
        /* Set list data and renderer */
        this.setListData(fileItems);
        this.setPreferredSize(new Dimension(JavarConstants.fileListWidth, JavarConstants.fileListHeight));
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.setCellRenderer(new ItemCellRenderer(JavarConstants.fileListItemWidth, JavarConstants.fileListItemHeight));
        this.addListSelectionListener(e -> {
            var data = (ItemData) this.getSelectedValue();
            if (data == null)
            {
                Javar.infoLabel.setText("");
                return;
            }
            /* Set editor pane selected tab */
            var index = this.getSelectedIndex() + 1;
            if (index < 0)
                index = 0;
            if (index >= Javar.codeEditor.getTabCount())
                index = Javar.codeEditor.getTabCount() - 1;
            Javar.codeEditor.setSelectedIndex(index);
            /* Set compiler selector */
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
            /* Set info label content */
            Javar.infoLabel.setAll(data.getFileName(), data.getFileType(), data.getFilePath(), data.getFileSize(), data.getFileCreated(), data.getFileLastModified());
            Javar.infoLabel.updateText();
        });
    }
    public String getSelectedItemDataFileName()
    {
        return ((ItemData) this.getSelectedValue()).getFileName();
    }
    public void setSelectedItemDataFileName(String fileName)
    {
        ((ItemData) this.getSelectedValue()).setFileName(fileName);
    }
    public String getSelectedItemDataFileType()
    {
        return ((ItemData) this.getSelectedValue()).getFileType();
    }
    public void setSelectedItemDataFileType(String fileType)
    {
        ((ItemData) this.getSelectedValue()).setFileType(fileType);
    }
    public void setSelectedItemDataFileSize(String fileSize)
    {
        ((ItemData) this.getSelectedValue()).setFileSize(fileSize);
    }
    public void setSelectedItemDataFileLastModified(String fileLastModified)
    {
        ((ItemData) this.getSelectedValue()).setFileLastModified(fileLastModified);
    }
    public String getSelectedItemDataFilePath()
    {
        return ((ItemData) this.getSelectedValue()).getFilePath();
    }
    public void setSelectedItemDataFilePath(String filePath)
    {
        ((ItemData) this.getSelectedValue()).setFilePath(filePath);
    }
    public static ItemData createItemData(String fileType, String fileName, String filePath, String fileSize, String fileCreated, String fileLastModified)
    {
        return new ItemData(fileType, fileName, filePath, fileSize, fileCreated, fileLastModified);
    }
}

class ItemData
{   
    String fileType;
    String fileName;
    String filePath;
    String fileSize;
    String fileCreated;
    String fileLastModified;
    public ItemData(String fileName, String fileType, String filePath, String fileSize, String fileCreated, String fileLastModified)
    {
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.fileCreated = fileCreated;
        this.fileLastModified = fileLastModified;
    }
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

class ItemCellRenderer extends JPanel implements ListCellRenderer
{
    int W, H;
    ImageIcon icon;
    String fileName;
    Color background;
    Color foreground;
    public ItemCellRenderer(int W, int H)
    {
        this.W = W;
        this.H = H;
    }
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        var data = (ItemData) value;
        fileName = data.getFileName();
        icon = new ImageIcon("images/icons/" + fileName.substring(fileName.lastIndexOf(".")+1) + "FileTemplateIcon.png");
        background = isSelected ? list.getSelectionBackground() : list.getBackground();
        foreground = isSelected ? list.getSelectionForeground() : list.getForeground();
        return this;
    }
    public void paintComponent(Graphics g)
    {
        icon.setImage(JavarUtils.resizeImageToFitHeightWithPadding(this, icon.getImage(), Image.SCALE_SMOOTH, JavarConstants.fileListIconPadding));
        g.setColor(background);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(foreground);
        g.drawImage(icon.getImage(), JavarConstants.fileListIconOffset, JavarConstants.fileListIconPadding, null);
        g.drawString(fileName, JavarConstants.fileListIconOffset*2+icon.getIconWidth(), (int)(g.getFontMetrics().getAscent()/2+getHeight()/2));
    }
    public Dimension getPreferredSize()
    {
        return new Dimension(W, H);
    }
}
