package javar.creatorwindow;

import javar.utils.JavarUtils;
import javar.constants.JavarConstants;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;

@SuppressWarnings(value = "unchecked")
public class CreatorWindow extends JFrame
{
    ItemData[] tmp1 = new ItemData[] {new ItemData("sourceFiles", "Source Files"), new ItemData("javaTemplates", "Java Templates")};
    HashMap<String, ItemData[]> tmp2 = new HashMap<>() 
    {
        {
            put("sourceFiles", new ItemData[] {new ItemData("javaFile", "Java Source File"), new ItemData("htmlFile", "html Source File")});
            put("javaTemplates", new ItemData[] {new ItemData("javaFile", "Java Source File")});
        }
    };
    JList categoryList = new JList();
    JList typeList = new JList();
    public CreatorWindow()
    {
        initCreatorWindow();
    }
    public void initCreatorWindow()
    {
        categoryList.addListSelectionListener(e -> {
            var tmp = (ItemData) categoryList.getSelectedValue();
            typeList.setListData(tmp2.get(tmp.getType()));
        });
        categoryList.setListData(tmp1);
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        typeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        categoryList.setCellRenderer(new ItemCellRenderer(JavarConstants.categoryItemWidth, JavarConstants.categoryItemHeight));
        typeList.setCellRenderer(new ItemCellRenderer(JavarConstants.typeItemWidth, JavarConstants.typeItemHeight));
        categoryList.setPreferredSize(new Dimension(JavarConstants.categoryListWidth, JavarConstants.categoryListHeight));
        typeList.setPreferredSize(new Dimension(JavarConstants.typeListWidth, JavarConstants.typeListHeight));
        categoryList.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        typeList.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.add(categoryList, BorderLayout.WEST);
        this.add(typeList, BorderLayout.EAST);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }
}

class ItemData
{
    String type;
    String name;
    public ItemData(String type, String name)
    {
        this.type = type;
        this.name = name;
    }
    public String getType()
    {
        return type;
    }
    public String getName()
    {
        return name;
    }
}

class ItemCellRenderer extends JPanel implements ListCellRenderer
{
    int W, H;
    ImageIcon icon;
    String name;
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
        icon = new ImageIcon("images/icons/" + data.getType() + "TemplateIcon.png");
        name = data.getName();
        background = isSelected ? list.getSelectionBackground() : list.getBackground();
        foreground = isSelected ? list.getSelectionForeground() : list.getForeground();
        return this;
    }
    public void paintComponent(Graphics g)
    {
        icon.setImage(JavarUtils.resizeImageToFitHeightWithPadding(this, icon.getImage(), Image.SCALE_SMOOTH, 2));
        g.setColor(background);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(foreground);
        g.drawImage(icon.getImage(), 10, 1, null);
        g.drawString(name, 20+icon.getIconWidth(), (int)(g.getFontMetrics().getAscent()/2+getHeight()/2));
    }
    public Dimension getPreferredSize()
    {
        return new Dimension(W, H);
    }
}
