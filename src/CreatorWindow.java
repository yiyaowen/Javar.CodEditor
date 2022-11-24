package com.yiyaowen.javar;

import com.yiyaowen.javar.CodePane;
import com.yiyaowen.javar.Javar;
import com.yiyaowen.javar.JavarConstants;
import com.yiyaowen.javar.JavarUtils;
import com.yiyaowen.javar.FileList;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.text.*;

@SuppressWarnings(value = "unchecked")
public class CreatorWindow extends JFrame
{
    //////////////
    // Property //
    //////////////

    public static HashMap<String, String> suffixTypeMap = new HashMap<>()
    {
        {
            put("java", "Java Source File");
            put("py", "Python Source File");
            put("c", "C Source File");
            put("cpp", "C++ Source File");
            put("html", "html Source File");
        }
    };
    public static CWItemData[] categoryItems = new CWItemData[] {
        new CWItemData("sourceFiles", "Source Files", CWItemData.CATEGORY),
        new CWItemData("pyTemplates", "Python Templates", CWItemData.CATEGORY),
        new CWItemData("javaTemplates", "Java Templates", CWItemData.CATEGORY),
        new CWItemData("ccppTemplates", "C/C++ Templates", CWItemData.CATEGORY),
        new CWItemData("htmlTemplates", "html Templates", CWItemData.CATEGORY)
    };
    public static HashMap<String, CWItemData[]> typeItems = new HashMap<>()
    {
        {
            put("sourceFiles", new CWItemData[] {
                new CWItemData("javaFile", "Java Source File", CWItemData.TYPE),
                new CWItemData("pyFile", "Python Source File", CWItemData.TYPE),
                new CWItemData("cFile", "C Source File", CWItemData.TYPE),
                new CWItemData("cppFile", "C++ Source File", CWItemData.TYPE),
                new CWItemData("htmlFile", "html Source File", CWItemData.TYPE)
            });
            put("javaTemplates", new CWItemData[] {
                new CWItemData("javaFile", "Java Source File", CWItemData.TYPE)
            });
            put("pyTemplates", new CWItemData[] {
                new CWItemData("pyFile", "Python Source File", CWItemData.TYPE)
            });
            put("ccppTemplates", new CWItemData[] {
                new CWItemData("cFile", "C Source File", CWItemData.TYPE),
                new CWItemData("cppFile", "C++ Source File", CWItemData.TYPE)
            });
            put("htmlTemplates", new CWItemData[] {
                new CWItemData("htmlFile", "html Source File", CWItemData.TYPE)
            });
        }
    };
    HashMap<String, String> fileSuffixMap = new HashMap<>()
    {
        {
            put("javaFile", ".java");
            put("pyFile", ".py");
            put("cFile", ".c");
            put("cppFile", ".cpp");
            put("htmlFile", ".html");
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
    JButton chooseDirPropertyButton = new JButton();
    JTextField chooseDirPropertyTextField = new JTextField();
    JLabel teamPropertyLabel = new JLabel();
    JTextField teamPropertyTextField = new JTextField();
    JCheckBox hasTemplatePropertyCheckBox = new JCheckBox();
    JCheckBox hasPrefixStatementCheckBox = new JCheckBox();
    JLabel templatePropertyLabel = new JLabel();
    JComboBox templatePropertyComboBox = new JComboBox();
    JButton cancelPropertyButton = new JButton();
    JButton createPropertyButton = new JButton();
    JLabel propertySeparatorLabel1 = new JLabel();
    JLabel propertySeparatorLabel2 = new JLabel();
    JFileChooser chooser = new JFileChooser();
    boolean hasNameProperty = false;
    boolean hasDirProperty = false;
    boolean hasInvalidFileName = false;
    Document namePropertyDocument = namePropertyTextField.getDocument();
    Document chooseDirPropertyDocument = chooseDirPropertyTextField.getDocument();

    ////////////
    // Method //
    ////////////

    /**
     * Initialize creator window
     *
     * @param
     * @return
     */
    public void initCreatorWindow()
    {
        // Set preferred size
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
        namePropertyLabel.setPreferredSize(new Dimension(JavarConstants.namePropertyLabelWidth, JavarConstants.namePropertyLabelHeight));
        namePropertyTextField.setPreferredSize(new Dimension(JavarConstants.namePropertyTextFieldWidth, JavarConstants.namePropertyTextFieldHeight));
        developerPropertyLabel.setPreferredSize(new Dimension(JavarConstants.developerPropertyLabelWidth, JavarConstants.developerPropertyLabelHeight));
        developerPropertyTextField.setPreferredSize(new Dimension(JavarConstants.developerPropertyTextFieldWidth, JavarConstants.developerPropertyTextFieldHeight));
        teamPropertyLabel.setPreferredSize(new Dimension(JavarConstants.teamPropertyLabelWidth, JavarConstants.teamPropertyLabelHeight));
        teamPropertyTextField.setPreferredSize(new Dimension(JavarConstants.teamPropertyTextFieldWidth, JavarConstants.teamPropertyTextFieldHeight));
        chooseDirPropertyButton.setPreferredSize(new Dimension(JavarConstants.chooseDirPropertyButtonWidth, JavarConstants.chooseDirPropertyButtonHeight));
        chooseDirPropertyTextField.setPreferredSize(new Dimension(JavarConstants.chooseDirPropertyTextFieldWidth, JavarConstants.chooseDirPropertyTextFieldHeight));
        hasTemplatePropertyCheckBox.setPreferredSize(new Dimension(JavarConstants.hasTemplatePropertyCheckBoxWidth, JavarConstants.hasTemplatePropertyCheckBoxHeight));
        hasPrefixStatementCheckBox.setPreferredSize(new Dimension(JavarConstants.hasPrefixStatementCheckBoxWidth, JavarConstants.hasPrefixStatementCheckBoxHeight));
        templatePropertyLabel.setPreferredSize(new Dimension(JavarConstants.templatePropertyLabelWidth, JavarConstants.templatePropertyLabelHeight));
        templatePropertyComboBox.setPreferredSize(new Dimension(JavarConstants.templatePropertyComboBoxWidth, JavarConstants.templatePropertyComboBoxHeight));
        cancelPropertyButton.setPreferredSize(new Dimension(JavarConstants.cancelPropertyButtonWidth, JavarConstants.cancelPropertyButtonHeight));
        createPropertyButton.setPreferredSize(new Dimension(JavarConstants.createPropertyButtonWidth, JavarConstants.createPropertyButtonHeight));
        propertySeparatorLabel1.setPreferredSize(new Dimension(JavarConstants.propertySeparatorLabel1Width, JavarConstants.propertySeparatorLabel1Height));
        propertySeparatorLabel2.setPreferredSize(new Dimension(JavarConstants.propertySeparatorLabel2Width, JavarConstants.propertySeparatorLabel2Height));
        // Layout components
        	// Property panel
        propertyPanel.setLayout(new BoxLayout(propertyPanel, BoxLayout.Y_AXIS));
        var nameBox = Box.createHorizontalBox();
        var developerBox = Box.createHorizontalBox();
        var teamBox = Box.createHorizontalBox();
        var checkBox = Box.createHorizontalBox();
        var chooseDirBox = Box.createHorizontalBox();
        var templateBox = Box.createHorizontalBox();
        var buttonBox = Box.createHorizontalBox();
        nameBox.add(Box.createRigidArea(new Dimension(JavarConstants.propertyLabelPrefixPadding, JavarConstants.smallHeight)));
        nameBox.add(namePropertyLabel);
        nameBox.add(namePropertyTextField);
        developerBox.add(Box.createRigidArea(new Dimension(JavarConstants.propertyLabelPrefixPadding, JavarConstants.smallHeight)));
        developerBox.add(developerPropertyLabel);
        developerBox.add(developerPropertyTextField);
        teamBox.add(Box.createRigidArea(new Dimension(JavarConstants.propertyLabelPrefixPadding, JavarConstants.smallHeight)));
        teamBox.add(teamPropertyLabel);
        teamBox.add(teamPropertyTextField);
        chooseDirBox.add(chooseDirPropertyButton);
        chooseDirBox.add(chooseDirPropertyTextField);
        checkBox.add(hasTemplatePropertyCheckBox);
        checkBox.add(hasPrefixStatementCheckBox);
        templateBox.add(Box.createRigidArea(new Dimension(JavarConstants.propertyLabelPrefixPadding, JavarConstants.smallHeight)));
        templateBox.add(templatePropertyLabel);
        templateBox.add(templatePropertyComboBox);
        buttonBox.add(cancelPropertyButton);
        buttonBox.add(Box.createRigidArea(new Dimension(JavarConstants.propertyButtonPadding, JavarConstants.smallHeight)));
        buttonBox.add(createPropertyButton);
        propertyPanel.add(nameBox);
        propertyPanel.add(developerBox);
        propertyPanel.add(teamBox);
        propertyPanel.add(chooseDirBox);
        propertyPanel.add(propertySeparatorLabel1);
        propertyPanel.add(checkBox);
        propertyPanel.add(templateBox);
        propertyPanel.add(propertySeparatorLabel2);
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
        // Set border
        categoryLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        typeLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        propertyLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        categoryList.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        typeList.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        propertyPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        // Set button content
        chooseDirPropertyButton.setText(JavarTranslator.translate(JavarConstants.creatorWindowPrefix, "Create In Directory:"));
        hasPrefixStatementCheckBox.setText(JavarTranslator.translate(JavarConstants.creatorWindowPrefix, "Generate Prefix Statement"));
        hasPrefixStatementCheckBox.setSelected(false);
        hasTemplatePropertyCheckBox.setText(JavarTranslator.translate(JavarConstants.creatorWindowPrefix, "Use Template"));
        hasTemplatePropertyCheckBox.setSelected(false);
        cancelPropertyButton.setText(JavarTranslator.translate(JavarConstants.creatorWindowPrefix, "Cancel"));
        createPropertyButton.setText(JavarTranslator.translate(JavarConstants.creatorWindowPrefix, "Create"));
        createPropertyButton.setEnabled(false);
        // Set label content
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
        namePropertyLabel.setHorizontalAlignment(SwingConstants.LEFT);
        namePropertyLabel.setVerticalAlignment(SwingConstants.CENTER);
        categoryLabel.setText(JavarTranslator.translate(JavarConstants.creatorWindowPrefix, "Category"));
        typeLabel.setText(JavarTranslator.translate(JavarConstants.creatorWindowPrefix, "Type"));
        propertyLabel.setText(JavarTranslator.translate(JavarConstants.creatorWindowPrefix, "Property"));
        //descriptionRightLabel.setText(JavarTranslator.translate("description:"));
        namePropertyLabel.setText(JavarTranslator.translate(JavarConstants.creatorWindowPrefix, "Name:"));
        developerPropertyLabel.setText(JavarTranslator.translate(JavarConstants.creatorWindowPrefix, "Developer:"));
        teamPropertyLabel.setText(JavarTranslator.translate(JavarConstants.creatorWindowPrefix, "Team:"));
        templatePropertyLabel.setText(JavarTranslator.translate(JavarConstants.creatorWindowPrefix, "Template:"));
        // Set listener
        createPropertyButton.addActionListener(e -> {
            String fileName = namePropertyTextField.getText();
            if (fileName.indexOf(".") == 0)
            {
                hasInvalidFileName = true;
                namePropertyLabel.setText(
                    JavarTranslator.translate(JavarConstants.creatorWindowPrefix, JavarConstants.invalidNamePropertyMessage));
                return;
            }
            String fileSuffix = fileName.substring(fileName.lastIndexOf(".")+1);
            String fileType = suffixTypeMap.get(fileSuffix);
            if (fileType == null)
                fileType = fileSuffix;
            String fileDeveloper = developerPropertyTextField.getText();
            String fileTeam = teamPropertyTextField.getText();
            String dirPath = chooseDirPropertyTextField.getText();
            String filePath = dirPath + JavarConstants.pathDelimiter + fileName;
            boolean hasTemplate = hasTemplatePropertyCheckBox.isSelected();
            boolean hasPrefix = hasPrefixStatementCheckBox.isSelected();
            JavarDispatcher.createNewFile(filePath, "");
            // Close window
            this.dispose();
        });
        cancelPropertyButton.addActionListener(e -> {
            this.dispose();
        });
        categoryList.addListSelectionListener(e -> {
            var data = (CWItemData) categoryList.getSelectedValue();
            if (data == null)
                return;
            // Update type list items
            typeList.setListData(typeItems.get(data.getType()));
            // Set description
            ImageIcon icon = new ImageIcon("../images/icons/" + data.getType() + "TemplateIcon.png");
            icon.setImage(JavarUtils.resizeImageToFitWidthWithPadding(descriptionLeftLabel, icon.getImage(), Image.SCALE_SMOOTH, JavarConstants.creatorDescriptionIconPadding));
            descriptionLeftLabel.setIcon(icon);
            descriptionRightLabel.setText(data.toString());
        });
        typeList.addListSelectionListener(e -> {
            var data = (CWItemData) typeList.getSelectedValue();
            if (data == null)
                return;
            // Set description
            ImageIcon icon = new ImageIcon("../images/icons/" + data.getType() + "TemplateIcon.png");
            icon.setImage(JavarUtils.resizeImageToFitWidthWithPadding(descriptionLeftLabel, icon.getImage(), Image.SCALE_SMOOTH, JavarConstants.creatorDescriptionIconPadding));
            descriptionLeftLabel.setIcon(icon);
            descriptionRightLabel.setText(data.toString());
            // Set file suffix
            namePropertyTextField.setText(fileSuffixMap.get(data.getType()));
        });
        namePropertyTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e)
            {
                if (hasInvalidFileName)
                {
                    namePropertyLabel.setText(JavarTranslator.translate(JavarConstants.creatorWindowPrefix, "Name:"));
                    hasInvalidFileName = false;
                }
                namePropertyTextField.setCaretPosition(0);
            }
        });
        chooseDirPropertyButton.addActionListener(e -> {
            int result;
            result = chooser.showDialog(CreatorWindow.this,
                JavarTranslator.translate(JavarConstants.fileChooserPrefix, "Choose Directory"));
            if (result == JFileChooser.APPROVE_OPTION)
            {
                String path = chooser.getSelectedFile().getPath();
                chooseDirPropertyTextField.setText(path);
            }
        });
        namePropertyDocument.addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {}
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                if (!namePropertyTextField.getText().equals(""))
                {
                    hasNameProperty = true;
                    if (hasDirProperty)
                        createPropertyButton.setEnabled(true);
                }
                else
                {
                    hasNameProperty  = false;
                    createPropertyButton.setEnabled(false);
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e)
            {
                if (!namePropertyTextField.getText().equals(""))
                {
                    hasNameProperty = true;
                    if (hasDirProperty)
                        createPropertyButton.setEnabled(true);
                }
                else
                {
                    hasNameProperty  = false;
                    createPropertyButton.setEnabled(false);
                }
            }
        });
        chooseDirPropertyDocument.addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {}
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                if (!chooseDirPropertyTextField.getText().equals(""))
                {
                    hasDirProperty = true;
                    if (hasNameProperty)
                        createPropertyButton.setEnabled(true);
                }
                else
                {
                    hasDirProperty = false;
                    createPropertyButton.setEnabled(false);
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e)
            {
                if (!chooseDirPropertyTextField.getText().equals(""))
                {
                    hasDirProperty = true;
                    if (hasNameProperty)
                        createPropertyButton.setEnabled(true);
                }
                else
                {
                    hasDirProperty = false;
                    createPropertyButton.setEnabled(false);
                }
            }
        });
        hasTemplatePropertyCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED)
                templatePropertyComboBox.setEnabled(true);
            else
                templatePropertyComboBox.setEnabled(false);
        });
        // List adjust
        categoryList.setListData(categoryItems);
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        typeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        categoryList.setCellRenderer(new CWItemCellRenderer(JavarConstants.categoryItemWidth, JavarConstants.categoryItemHeight));
        typeList.setCellRenderer(new CWItemCellRenderer(JavarConstants.typeItemWidth, JavarConstants.typeItemHeight));
        // Other adjust
        templatePropertyComboBox.setEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.pack();
        this.setResizable(false);
        this.setLocation((int)Javar.mainWindow.getLocation().getX() + (int)(Javar.mainWindow.getWidth()/2-this.getWidth()/2), ((int)Javar.mainWindow.getLocation().getY() + (int)(Javar.mainWindow.getHeight()/2-this.getHeight()/2)));
        this.setVisible(true);
    }
}

class CWItemData
{
    //////////////
    // Property //
    //////////////

    int list;
    public static int CATEGORY = 1;
    public static int TYPE = 2;
    String type;
    String name;

    /////////////////
    // Constructor //
    /////////////////

    public CWItemData(String type, String name, int list)
    {
        this.type = type;
        this.name = name;
        this.list = list;
    }

    ////////////
    // getter //
    ////////////

    public String getType()
    {
        return type;
    }
    public String getName()
    {
        return name;
    }
    public int getList()
    {
        return list;
    }

    ////////////
    // Method //
    ////////////

    /**
     * Override from Object
     *
     * @param
     * @return
     */
    public String toString()
    {
		if (list == CATEGORY)
		{
	        return String.format(
                JavarTranslator.translate(JavarConstants.creatorDescPrefix, JavarConstants.creatorCategoryDescription),
                name);
		}
	    else
	    {
	        return String.format(
                JavarTranslator.translate(JavarConstants.creatorDescPrefix, JavarConstants.creatorDescription),
                name, name);
        }
    }
}

class CWItemCellRenderer extends JPanel implements ListCellRenderer
{
    //////////////
    // Property //
    //////////////

    int W, H;
    ImageIcon icon;
    String name;
    Color background;
    Color foreground;

    /////////////////
    // Constructor //
    /////////////////

    public CWItemCellRenderer(int W, int H)
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
        var data = (CWItemData) value;
        icon = new ImageIcon("../images/icons/" + data.getType() + "TemplateIcon.png");
        name = data.getName();
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
        icon.setImage(JavarUtils.resizeImageToFitHeightWithPadding(this, icon.getImage(), Image.SCALE_SMOOTH, JavarConstants.creatorListIconPadding));
        g.setColor(background);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(foreground);
        g.drawImage(icon.getImage(), JavarConstants.creatorListIconOffset, JavarConstants.creatorListIconPadding, null);
        g.drawString(name, JavarConstants.creatorListIconOffset*2+icon.getIconWidth(), (int)(g.getFontMetrics().getAscent()/2+getHeight()/2));
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
