package javar.filetree;

import javar.utils.JavarUtils;
import javar.constants.JavarConstants;
import javar.menuprovider.MenuProvider;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.io.*;
import java.util.*;

public class FileTree extends JTree
{
    /* Root node */
    public static DefaultMutableTreeNode root = new DefaultMutableTreeNode(new NodeData(FileType.ROOT, "Javar Projects"));
    /* Event listeners */
    MouseListener mouseListener;
    public FileTree()
    {
        super(root);
        initFileTree();
    }
    public void initFileTree()
    {
        /* UI configuration */
        this.setCellRenderer(new FileTreeRenderer());
        this.setShowsRootHandles(true);
        this.setBorder(BorderFactory.createEmptyBorder(JavarConstants.fileTreePaddingTop, JavarConstants.fileTreePaddingLeft, JavarConstants.fileTreePaddingBottom, JavarConstants.fileTreePaddingRight));
        /* Set popup menu */
        this.setComponentPopupMenu(MenuProvider.createPopupMenu(JavarConstants.fileTreePopupType));
        /* Set event listeners */
        // Mouse listener
        mouseListener = new MouseAdapter()
        {
            TreePath movePath;
            public void mousePressed(MouseEvent e)
            {
                TreePath treePath = FileTree.this.getPathForLocation(e.getX(), e.getY());
                if (treePath != null)
                {
                    movePath = treePath;
                }
            }
            public void mouseReleased(MouseEvent e)
            {
                TreePath treePath = FileTree.this.getPathForLocation(e.getX(), e.getY());
                if (treePath != null && movePath != null)
                {
                    if (movePath.isDescendant(treePath) && movePath != treePath)
                    {
                        return;
                    }
                    else if (movePath != treePath)
                    {
                        ((DefaultMutableTreeNode) treePath.getLastPathComponent()).add((DefaultMutableTreeNode) movePath.getLastPathComponent());
                        movePath = null;
                        FileTree.this.updateUI();
                    }
                }
            }
        };
        this.addMouseListener(mouseListener);
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

class FileTreeRenderer extends DefaultTreeCellRenderer
{
    HashMap<String, ImageIcon> nodeIcons = new HashMap<>()
    {
        {
            put("rootFile", new ImageIcon("images/icons/rootFileTemplateIcon.png"));
            put("dirFile", new ImageIcon("images/icons/dirFileTemplateIcon.png"));
            put("javaFile", new ImageIcon("images/icons/javaFileTemplateIcon.png"));
            put("classFile", new ImageIcon("images/icons/classFileTemplateIcon.png"));
        }
    };
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus)
    {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        var node = (DefaultMutableTreeNode) value;
        var data = (NodeData) node.getUserObject();
        ImageIcon icon = null;
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
        if (icon != null)
            icon.setImage(JavarUtils.resizeImageToWH(icon.getImage(), 15, 15, Image.SCALE_SMOOTH));
        this.setIcon(icon);
        return this;
    }
}
