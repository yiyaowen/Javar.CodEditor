package javar.filelist;

import javar.constants.JavarConstants;
import javar.utils.JavarUtils;
import javar.Javar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.*;

@SuppressWarnings(value = "unchecked")
public class FileList extends JList
{
    public Vector<ItemData> fileItems = new Vector<>();
    public FileList()
    {
        initFileList();
    }
    public void initFileList()
    {
        this.setListData(fileItems);
        this.setPreferredSize(new Dimension(JavarConstants.fileListWidth, JavarConstants.fileListHeight));
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.setCellRenderer(new ItemCellRenderer(JavarConstants.fileListItemWidth, JavarConstants.fileListItemHeight));
        this.addListSelectionListener(e -> {
            var data = (ItemData) this.getSelectedValue();
            Javar.infoLabel.setAll(data.getFileName(), data.getFileType(), data.getFilePath(), data.getFileSize(), data.getFileCreated(), data.getFileLastModified());
            Javar.infoLabel.updateText();
        });
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
    public String getFileType()
    {
        return fileType;
    }
    public String getFilePath()
    {
        return filePath;
    }
    public String getFileSize()
    {
        return fileSize;
    }
    public String getFileCreated()
    {
        return fileCreated;
    }
    public String getFileLastModified()
    {
        return fileLastModified;
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
        icon = new ImageIcon("images/icons/" + data.getFileType() + "FileTemplateIcon.png");
        fileName = data.getFileName();
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
