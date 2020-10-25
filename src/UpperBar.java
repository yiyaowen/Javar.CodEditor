package com.yiyaowen.javar;

import com.yiyaowen.javar.JavarUtils;
import com.yiyaowen.javar.JavarConstants;
import com.yiyaowen.javar.TabbedPane;
import com.yiyaowen.javar.Javar;
import com.yiyaowen.javar.MenuItemProvider;
import com.yiyaowen.javar.BuildAndRun;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

@SuppressWarnings(value = "unchecked")
public class UpperBar extends JPanel
{
    JLabel separator1 = new JLabel();
    public static JButton runBtn = new JButton();
    JLabel separator2 = new JLabel();
    public static JComboBox compilerSelector = new JComboBox();
    JLabel separator3 = new JLabel();
    public static JLabel infoBox = new JLabel();
    JLabel separator4 = new JLabel();
    public static JTextField searchBox = new JTextField();
    JLabel searchLabel = new JLabel();
    ImageIcon runIcon = new ImageIcon("../images/icons/run.png");
    ImageIcon searchIcon = new ImageIcon("../images/icons/search.png");
    public UpperBar()
    {
        initUpperBar();
    }
    public void initUpperBar()
    {
        this.setPreferredSize(new Dimension(JavarConstants.upperBarWidth, JavarConstants.upperBarHeight));
        // Minimum size
        separator1.setMinimumSize(new Dimension(JavarConstants.separator1Width, JavarConstants.separator1Height));
        runBtn.setMinimumSize(new Dimension(JavarConstants.runBtnWidth, JavarConstants.runBtnHeight));
        separator2.setMinimumSize(new Dimension(JavarConstants.separator2Width, JavarConstants.separator2Height));
        compilerSelector.setMinimumSize(new Dimension(JavarConstants.compilerSelectorWidth, JavarConstants.compilerSelectorHeight));
        separator3.setMinimumSize(new Dimension(0, 0));
        infoBox.setMinimumSize(new Dimension(JavarConstants.infoBoxWidth, JavarConstants.infoBoxHeight));
        separator4.setMinimumSize(new Dimension(0, 0));
        searchBox.setMinimumSize(new Dimension(JavarConstants.searchBoxWidth, JavarConstants.searchBoxHeight));
        searchLabel.setMinimumSize(new Dimension(JavarConstants.searchLabelWidth, JavarConstants.searchLabelHeight));
        // Maximum size
        separator1.setMaximumSize(new Dimension(JavarConstants.separator1Width, JavarConstants.separator1Height));
        runBtn.setMaximumSize(new Dimension(JavarConstants.runBtnWidth, JavarConstants.runBtnHeight));
        separator2.setMaximumSize(new Dimension(JavarConstants.separator2Width, JavarConstants.separator2Height));
        compilerSelector.setMaximumSize(new Dimension(JavarConstants.compilerSelectorWidth, JavarConstants.compilerSelectorHeight));
        separator3.setMaximumSize(new Dimension(JavarConstants.greatWidth, JavarConstants.greatHeight));
        infoBox.setMaximumSize(new Dimension(JavarConstants.infoBoxWidth, JavarConstants.infoBoxHeight));
        separator4.setMaximumSize(new Dimension(JavarConstants.greatWidth, JavarConstants.greatHeight));
        searchBox.setMaximumSize(new Dimension(JavarConstants.searchBoxWidth, JavarConstants.searchBoxHeight));
        searchLabel.setMaximumSize(new Dimension(JavarConstants.searchLabelWidth, JavarConstants.searchLabelHeight));
        // Preferred size
        separator1.setPreferredSize(new Dimension(JavarConstants.separator1Width, JavarConstants.separator1Height));
        runBtn.setPreferredSize(new Dimension(JavarConstants.runBtnWidth, JavarConstants.runBtnHeight));
        separator2.setPreferredSize(new Dimension(JavarConstants.separator2Width, JavarConstants.separator2Height));
        compilerSelector.setPreferredSize(new Dimension(JavarConstants.compilerSelectorWidth, JavarConstants.compilerSelectorHeight));
        separator3.setPreferredSize(new Dimension(JavarConstants.separator3Width, JavarConstants.separator3Height));
        infoBox.setPreferredSize(new Dimension(JavarConstants.infoBoxWidth, JavarConstants.infoBoxHeight));
        separator4.setPreferredSize(new Dimension(JavarConstants.separator4Width, JavarConstants.separator4Height));
        searchBox.setPreferredSize(new Dimension(JavarConstants.searchBoxWidth, JavarConstants.searchBoxHeight));
        searchLabel.setPreferredSize(new Dimension(JavarConstants.searchLabelWidth, JavarConstants.searchLabelHeight));
        /* Alignment */
        infoBox.setHorizontalAlignment(SwingConstants.CENTER);
        infoBox.setVerticalAlignment(SwingConstants.CENTER);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(separator1);
        this.add(runBtn);
        this.add(separator2);
        this.add(compilerSelector);
        this.add(separator3);
        this.add(infoBox);
        this.add(separator4);
        this.add(searchBox);
        this.add(searchLabel);
        /* Set component renderer */
        // runBtn
        runIcon.setImage(JavarUtils.resizeImageToWH(runIcon.getImage(), JavarConstants.runBtnWidth, JavarConstants.runBtnHeight, Image.SCALE_SMOOTH));
        runBtn.setIcon(runIcon);
        // compilerSelector
        compilerSelector.setRenderer(new CompilerSelectorCellRenderer());
        compilerSelector.addItem(JavarConstants.compilerSelectorJava);
        compilerSelector.addItem(JavarConstants.compilerSelectorPython);
        compilerSelector.addItem(JavarConstants.compilerSelectorC);
        compilerSelector.addItem(JavarConstants.compilerSelectorCpp);
        compilerSelector.addItem(JavarConstants.compilerSelectorHtml);
        // infoBox
        infoBox.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        // searchLabel
        searchIcon.setImage(JavarUtils.resizeImageToWH(searchIcon.getImage(), JavarConstants.searchLabelWidth, JavarConstants.searchLabelHeight, Image.SCALE_SMOOTH));
        searchLabel.setIcon(searchIcon);
        /* Set listener */
        runBtn.addActionListener(e -> {
            if (Javar.fileList.getSelectedIndex() < 0)
                return;
            var source = ((Component)e.getSource()).getParent();
            // Fire save event
            MenuItemProvider.saveItemListener.actionPerformed(new ActionEvent(source, ActionEvent.ACTION_PERFORMED, ""));
            /* Build and Run information */
            String filePath = Javar.fileList.getSelectedItemDataFilePath();
            String dirPath = filePath.substring(0, filePath.lastIndexOf(JavarConstants.pathDelimiter)+1);
            String fileName = filePath.substring(filePath.lastIndexOf(JavarConstants.pathDelimiter)+1);
            String filePrefix = fileName.substring(0, fileName.lastIndexOf("."));
            boolean hasBuild = false;
            boolean hasRun = false;
            // Decide compiler
            if (compilerSelector.getSelectedItem().equals(JavarConstants.compilerSelectorJava))
                BuildAndRun.Java(filePath);
            else if (compilerSelector.getSelectedItem().equals(JavarConstants.compilerSelectorPython))
                BuildAndRun.Python(filePath);
            else if (compilerSelector.getSelectedItem().equals(JavarConstants.compilerSelectorC))
                BuildAndRun.C(filePath);
            else if (compilerSelector.getSelectedItem().equals(JavarConstants.compilerSelectorCpp))
                BuildAndRun.Cpp(filePath);
            else
                BuildAndRun.Html(filePath);
        });
    }
}

class CompilerSelectorCellRenderer extends JPanel implements ListCellRenderer
{
    ImageIcon compilerIcon;
    String compilerName;
    Color background;
    Color foreground;
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        compilerIcon = new ImageIcon("../images/icons/" + value + "CompilerIcon.png");
        compilerName = value.toString();
        background = isSelected ? list.getSelectionBackground() : list.getBackground();
        foreground = isSelected ? list.getSelectionForeground() : list.getForeground();
        return this;
    }
    public void paintComponent(Graphics g)
    {
        compilerIcon.setImage(JavarUtils.resizeImageToFitHeight(this, compilerIcon.getImage(), Image.SCALE_SMOOTH));
        g.setColor(background);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(foreground);
        g.drawImage(compilerIcon.getImage(), JavarConstants.compilerSelectorIconPadding, 0, null);
        g.drawString(compilerName, compilerIcon.getIconWidth()+JavarConstants.compilerSelectorIconPadding*2, (int)(getHeight()/2+g.getFontMetrics().getAscent()/2));
    }
    public Dimension getPreferredSize()
    {
        return new Dimension(JavarConstants.compilerSelectorWidth, JavarConstants.compilerSelectorHeight);
    }
}
