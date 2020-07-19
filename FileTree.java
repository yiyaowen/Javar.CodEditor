package javar.filetree;

import javar.utils.JavarUtils;
import javar.constants.JavarConstants;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.io.*;
import java.util.*;

public class FileTree extends JTree
{
    public static DefaultMutableTreeNode root = new DefaultMutableTreeNode(new NodeData(FileType.ROOT, "New Project"));
    DefaultMutableTreeNode javafile = new DefaultMutableTreeNode(new NodeData(FileType.JAVA, "Test.java"));
    DefaultMutableTreeNode dir = new DefaultMutableTreeNode(new NodeData(FileType.DIR, "test"));
    DefaultMutableTreeNode classfile = new DefaultMutableTreeNode(new NodeData(FileType.CLASS, "Test.class"));
    public FileTree()
    {
        super(root);
        initFileTree();
    }
    public void initFileTree()
    {
        root.add(javafile);
        root.add(dir);
        dir.add(classfile);
        this.setCellRenderer(new FileTreeRenderer());
        this.setShowsRootHandles(true);
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }
}

class NodeData
{
    public int nodeType;
    public String nodeName;
    public NodeData(int nodeType, String nodeName)
    {
        this.nodeType = nodeType;
        this.nodeName = nodeName;
    }
    public String toString()
    {
        return nodeName;
    }
}

interface FileType
{
    int ROOT = 0;
    int DIR = 1;
    int JAVA = 2;
    int CLASS = 3;
}

class FileTreeRenderer extends JLabel implements TreeCellRenderer
{
    ImageIcon icon;
    DefaultMutableTreeNode node;
    NodeData data;
    boolean selected;
    Map<String, ImageIcon> nodeIcons = new HashMap<>()
    {
        {
            put("rootFile", new ImageIcon("images/icons/rootIcon.png"));
            put("dirFile", new ImageIcon("images/icons/dirIcon.png"));
            put("javaFile", new ImageIcon("images/icons/javaIcon.png"));
            put("classFile", new ImageIcon("images/icons/classIcon.png"));
        }
    };
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus)
    {
        selected = sel;
        node = (DefaultMutableTreeNode) value;
        data = (NodeData) node.getUserObject();
        switch (data.nodeType)
        {
            case FileType.ROOT:
                icon = nodeIcons.get("rootFile");
                break;
            case FileType.DIR:
                icon = nodeIcons.get("dirFile");
                break;
            case FileType.JAVA:
                icon = nodeIcons.get("javaFile");
                break;
            case FileType.CLASS:
                icon = nodeIcons.get("classFile");
                break;
        }
        return this;
    }
    public void paintComponent(Graphics g)
    {
        if (selected)
        {
            g.setColor(new Color(JavarConstants.itemSelectedColorHex));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        g.setColor(Color.BLACK);
        icon.setImage(JavarUtils.resizeImageToFitHeightWithPadding(this, icon.getImage(), Image.SCALE_SMOOTH, 2));
        g.drawImage(icon.getImage(), 5, 2, null);
        g.drawString(data.nodeName, 25, g.getFontMetrics().getAscent());
    }
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 10);
    }
}
