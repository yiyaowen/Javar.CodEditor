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
    JLabel categoryLabel = new JLabel();
    JLabel typeLabel = new JLabel();
    JPanel labelPanel = new JPanel();
    JLabel propertyLabel = new JLabel();
    JList categoryList = new JList();
    JList typeList = new JList();
    JPanel listPanel = new JPanel();
    JPanel descriptionPanel = new JPanel();
    JLabel descriptionLeftLabel = new JLabel();
    JLabel descriptionRightLabel = new JLabel();
    JPanel creatorLeftPanel = new JPanel();
    JPanel propertyPanel = new JPanel();
    JPanel creatorRightPanel = new JPanel();
    JLabel namePropertyLabel = new JLabel();
    JTextField namePropertyTextField = new JTextField();
    JLabel developerPropertyLabel = new JLabel();
    JTextField developerPropertyTextField = new JTextField();
    JLabel teamPropertyLabel = new JLabel();
    JTextField teamPropertyTextField = new JTextField();
    JCheckBox hasTemplatePropertyCheckBox = new JCheckBox();
    JCheckBox hasPrefixStatementCheckBox = new JCheckBox();
    JComboBox templatePropertyComboBox = new JComboBox();
    JButton cancelButton = new JButton();
    JButton createButton = new JButton();
    public CreatorWindow()
    {
        initCreatorWindow();
    }
    public void initCreatorWindow()
    {
        /* Set preferred size */
        categoryLabel.setPreferredSize(new Dimension(JavarConstants.categoryLabelWidth, JavarConstants.categoryLabelHeight));
        typeLabel.setPreferredSize(new Dimension(JavarConstants.typeLabelWidth, JavarConstants.typeLabelHeight));
        labelPanel.setPreferredSize(new Dimension(JavarConstants.labelPanelWidth, JavarConstants.labelPanelHeight));
        propertyLabel.setPreferredSize(new Dimension(JavarConstants.propertyLabelWidth, JavarConstants.propertyLabelHeight));
        categoryList.setPreferredSize(new Dimension(JavarConstants.categoryListWidth, JavarConstants.categoryListHeight));
        typeList.setPreferredSize(new Dimension(JavarConstants.typeListWidth, JavarConstants.typeListHeight));
        listPanel.setPreferredSize(new Dimension(JavarConstants.listPanelWidth, JavarConstants.listPanelHeight));
        descriptionPanel.setPreferredSize(new Dimension(JavarConstants.descriptionPanelWidth, JavarConstants.descriptionPanelHeight));
        descriptionLeftLabel.setPreferredSize(new Dimension(JavarConstants.descriptionLeftLabelWidth, JavarConstants.descriptionLeftLabelHeight));
        descriptionRightLabel.setPreferredSize(new Dimension(JavarConstants.descriptionRightLabelWidth, JavarConstants.descriptionRightLabelHeight));
        creatorLeftPanel.setPreferredSize(new Dimension(JavarConstants.creatorLeftPanelWidth, JavarConstants.creatorLeftPanelHeight));
        propertyPanel.setPreferredSize(new Dimension(JavarConstants.propertyPanelWidth, JavarConstants.propertyPanelHeight));
        creatorRightPanel.setPreferredSize(new Dimension(JavarConstants.creatorRightPanelWidth, JavarConstants.creatorRightPanelHeight));
        /* Layout components */
        // Property panel
        propertyPanel.setLayout(new BoxLayout(propertyPanel, BoxLayout.Y_AXIS));
        var nameBox = Box.createHorizontalBox();
        var developerBox = Box.createHorizontalBox();
        var teamBox = Box.createHorizontalBox();
        var buttonBox = Box.createHorizontalBox();
        nameBox.add(namePropertyLabel);
        nameBox.add(namePropertyTextField);
        developerBox.add(developerPropertyLabel);
        developerBox.add(developerPropertyTextField);
        teamBox.add(teamPropertyLabel);
        teamBox.add(teamPropertyTextField);
        buttonBox.add(cancelButton);
        buttonBox.add(createButton);
        propertyPanel.add(nameBox);
        propertyPanel.add(developerBox);
        propertyPanel.add(teamBox);
        propertyPanel.add(hasPrefixStatementCheckBox);
        propertyPanel.add(hasTemplatePropertyCheckBox);
        propertyPanel.add(templatePropertyComboBox);
        propertyPanel.add(buttonBox);
        // Window
        labelPanel.setLayout(new BorderLayout());
        labelPanel.add(categoryLabel, BorderLayout.WEST);
        labelPanel.add(typeLabel, BorderLayout.EAST);
        listPanel.setLayout(new BorderLayout());
        listPanel.add(categoryList, BorderLayout.WEST);
        listPanel.add(typeList, BorderLayout.EAST);
        descriptionPanel.setLayout(new BorderLayout());
        descriptionPanel.add(descriptionLeftLabel, BorderLayout.WEST);
        descriptionPanel.add(descriptionRightLabel, BorderLayout.EAST);
        creatorLeftPanel.setLayout(new BorderLayout());
        creatorLeftPanel.add(labelPanel, BorderLayout.NORTH);
        creatorLeftPanel.add(listPanel, BorderLayout.CENTER);
        creatorLeftPanel.add(descriptionPanel, BorderLayout.SOUTH);
        creatorRightPanel.setLayout(new BorderLayout());
        creatorRightPanel.add(propertyLabel, BorderLayout.NORTH);
        creatorRightPanel.add(propertyPanel, BorderLayout.SOUTH);
        this.add(creatorLeftPanel, BorderLayout.WEST);
        this.add(creatorRightPanel, BorderLayout.EAST);
        /* Set border */
        categoryLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        typeLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        propertyLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        categoryList.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        typeList.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        propertyPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        /* Set button content */
        hasPrefixStatementCheckBox.setText("Generate Prefix Statement");
        hasPrefixStatementCheckBox.setSelected(false);
        hasTemplatePropertyCheckBox.setText("Use Template");
        hasTemplatePropertyCheckBox.setSelected(false);
        cancelButton.setText("Cancel");
        createButton.setText("Create");
        /* Set label content */
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryLabel.setVerticalAlignment(SwingConstants.CENTER);
        typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        typeLabel.setVerticalAlignment(SwingConstants.CENTER);
        propertyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        propertyLabel.setVerticalAlignment(SwingConstants.CENTER);
        descriptionLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionLeftLabel.setVerticalAlignment(SwingConstants.CENTER);
        descriptionRightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionRightLabel.setVerticalAlignment(SwingConstants.CENTER);
        namePropertyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        namePropertyLabel.setVerticalAlignment(SwingConstants.CENTER);
        categoryLabel.setText("Category");
        typeLabel.setText("Type");
        propertyLabel.setText("Property");
        descriptionRightLabel.setText("description:");
        namePropertyLabel.setText("Name:");
        developerPropertyLabel.setText("Developer:");
        teamPropertyLabel.setText("Team:");
        /* Set listener */
        categoryList.addListSelectionListener(e -> {
            var data = (ItemData) categoryList.getSelectedValue();
            if (data == null)
                return;
            // Update type list items
            typeList.setListData(tmp2.get(data.getType()));
            // Set description
            ImageIcon icon = new ImageIcon("images/icons/" + data.getType() + "TemplateIcon.png");
            icon.setImage(JavarUtils.resizeImageToFitHeightWithPadding(descriptionLeftLabel, icon.getImage(), Image.SCALE_SMOOTH, 10));
            descriptionLeftLabel.setIcon(icon);
            descriptionRightLabel.setText("Type: " + data.getType());
        });
        typeList.addListSelectionListener(e -> {
            var data = (ItemData) typeList.getSelectedValue();
            if (data == null)
                return;
            // Set description
            ImageIcon icon = new ImageIcon("images/icons/" + data.getType() + "TemplateIcon.png");
            icon.setImage(JavarUtils.resizeImageToFitHeightWithPadding(descriptionLeftLabel, icon.getImage(), Image.SCALE_SMOOTH, 10));
            descriptionLeftLabel.setIcon(icon);
            descriptionRightLabel.setText("Type: " + data.getType());
        });
        /* List adjust */
        categoryList.setListData(tmp1);
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        typeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        categoryList.setCellRenderer(new ItemCellRenderer(JavarConstants.categoryItemWidth, JavarConstants.categoryItemHeight));
        typeList.setCellRenderer(new ItemCellRenderer(JavarConstants.typeItemWidth, JavarConstants.typeItemHeight));
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
