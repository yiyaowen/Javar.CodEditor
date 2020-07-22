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
    Vector<ItemData> fileItems = new Vector<>() 
    {
        {
            add(new ItemData("javaFile", "Test.java", "test/path"));
            add(new ItemData("classFile", "Test.class", "test/path"));
            add(new ItemData("defaultFile", "Test.default", "test/path"));
        }
    };
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
            Javar.infoLabel.setCurrentFile(data.getFileName());
            Javar.infoLabel.updateText();
        });
    }
}

class ItemData
{   
    String fileType;
    String fileName;
    String filePath;
    public ItemData(String fileType, String fileName, String filePath)
    {
        this.fileType = fileType;
        this.fileName = fileName;
        this.filePath = filePath;
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
        icon = new ImageIcon("images/icons/" + data.getFileType() + "TemplateIcon.png");
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
        g.drawImage(icon.getImage(), JavarConstants.fileListIconOffset, (int)(JavarConstants.fileListIconPadding/2), null);
        g.drawString(fileName, JavarConstants.fileListIconOffset*2+icon.getIconWidth(), (int)(g.getFontMetrics().getAscent()/2+getHeight()/2));
    }
    public Dimension getPreferredSize()
    {
        return new Dimension(W, H);
    }
}
