package javar.creatorwindow;

import javar.utils.JavarUtils;
import javar.constants.JavarConstants;
import javar.Javar;
import javar.codepane.CodePane;
import javar.filelist.FileList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.text.*;
import javax.swing.filechooser.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

@SuppressWarnings(value = "unchecked")
public class CreatorWindow extends JFrame
{
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
    public static ItemData[] categoryItems = new ItemData[] {
        new ItemData("sourceFiles", "Source Files", ItemData.CATEGORY), 
        new ItemData("javaTemplates", "Java Templates", ItemData.CATEGORY)
    };
    public static HashMap<String, ItemData[]> typeItems = new HashMap<>() 
    {
        {
            put("allFiles", new ItemData[] {
                new ItemData("javaFile", "Java Source File", ItemData.TYPE), 
                new ItemData("pyFile", "Python Source File", ItemData.TYPE),
                new ItemData("cFile", "C Source File", ItemData.TYPE),
                new ItemData("cppFile", "C++ Source File", ItemData.TYPE),
                new ItemData("htmlFile", "html Source File", ItemData.TYPE)
            });
            put("sourceFiles", new ItemData[] {
                new ItemData("javaFile", "Java Source File", ItemData.TYPE), 
                new ItemData("pyFile", "Python Source File", ItemData.TYPE),
                new ItemData("cFile", "C Source File", ItemData.TYPE),
                new ItemData("cppFile", "C++ Source File", ItemData.TYPE),
                new ItemData("htmlFile", "html Source File", ItemData.TYPE)
            });
            put("javaTemplates", new ItemData[] {
                new ItemData("javaFile", "Java Source File", ItemData.TYPE)
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
        /* Layout components */
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
        /* Set border */
        categoryLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        typeLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        propertyLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        categoryList.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        typeList.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        propertyPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        /* Set button content */
        chooseDirPropertyButton.setText("Create In Directory:");
        hasPrefixStatementCheckBox.setText("Generate Prefix Statement");
        hasPrefixStatementCheckBox.setSelected(false);
        hasTemplatePropertyCheckBox.setText("Use Template");
        hasTemplatePropertyCheckBox.setSelected(false);
        cancelPropertyButton.setText("Cancel");
        createPropertyButton.setText("Create");
        createPropertyButton.setEnabled(false);
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
        namePropertyLabel.setHorizontalAlignment(SwingConstants.LEFT);
        namePropertyLabel.setVerticalAlignment(SwingConstants.CENTER);
        categoryLabel.setText("Category");
        typeLabel.setText("Type");
        propertyLabel.setText("Property");
        //descriptionRightLabel.setText("description:");
        namePropertyLabel.setText("Name:");
        developerPropertyLabel.setText("Developer:");
        teamPropertyLabel.setText("Team:");
        templatePropertyLabel.setText("Template:");
        /* Set listener */
        createPropertyButton.addActionListener(e -> {
            String fileName = namePropertyTextField.getText();
            if (fileName.indexOf(".") == 0)
            {
                hasInvalidFileName = true;
                namePropertyLabel.setText("<html><font>Name: <font color='red'>invalid</font></font></html>");
                return;
            }
            String fileSuffix = fileName.substring(fileName.lastIndexOf(".")+1); 
            String fileType = suffixTypeMap.get(fileSuffix);
            if (fileType == null)
                fileType = fileSuffix;
            String fileDeveloper = developerPropertyTextField.getText();
            String fileTeam = teamPropertyTextField.getText();
            String filePath = chooseDirPropertyTextField.getText();
            boolean hasTemplate = hasTemplatePropertyCheckBox.isSelected();
            boolean hasPrefix = hasPrefixStatementCheckBox.isSelected();
            File file = new File(filePath + "/" + fileName);
            try
            {
                /* Create file */
                if (file.exists()) 
                {
                    int result = JOptionPane.showConfirmDialog(CreatorWindow.this, JavarConstants.creatorWindowFileExistsMessage,
                        JavarConstants.creatorWindowFileExistsTitle, JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (result == JOptionPane.OK_OPTION)
                    {
                        file.delete();
                        file.createNewFile();
                        /* Get file attributes */
                        Path path = Paths.get(file.getPath());
                        BasicFileAttributeView basicView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
                        BasicFileAttributes basicAttributes = basicView.readAttributes();
                        String createdDate = (new Date(basicAttributes.creationTime().toMillis())).toString();
                        String lastModifiedDate = (new Date(basicAttributes.lastModifiedTime().toMillis()).toString());
                        double byteSize = (double) basicAttributes.size();
                        double kByteSize = (int)(byteSize * 10 / 1024) / 10;
                        double mByteSize = (int)(kByteSize * 10 / 1024) / 10;
                        String fileSize = kByteSize >= 1 ? (mByteSize >= 1 ? mByteSize+"MB" : kByteSize+"KB") : byteSize+"B";
                        var data = FileList.createItemData(fileName, fileType, file.getPath(), fileSize, createdDate, lastModifiedDate);
                        /* Set tabbed pane */
                        ImageIcon icon = new ImageIcon("images/icons/" + fileSuffix + "FileTemplateIcon.png");
                        if (icon == null)
                            icon = new ImageIcon("images/icons/defaultFileTemplateIcon.png");
                        icon.setImage(JavarUtils.resizeImageToWH(icon.getImage(), JavarConstants.tabIconWidth, JavarConstants.tabIconHeight, Image.SCALE_SMOOTH));
                        // JScrollPane + JPanel ==> horizontal scrollable
                        var tmpPanel = new JPanel();
                        tmpPanel.setLayout(new BorderLayout());
                        tmpPanel.add(new CodePane());
                        var tmpScroll = new JScrollPane(tmpPanel);
                        tmpScroll.getVerticalScrollBar().setUnitIncrement(JavarConstants.scrollUnitIncrement);
                        Javar.codeEditor.addTab(fileName, icon, tmpScroll);
                        try
                        {
                            /* Set file list */
                            FileList.fileItems.add(data);
                            Javar.fileList.setListData(FileList.fileItems);
                        }
                        catch (Exception ignore) {}
                        /* Set selected tab */
                        Javar.codeEditor.setSelectedIndex(Javar.codeEditor.getTabCount() - 1);
                        /* Set info box */
                        Javar.upperBar.infoBox.setText(JavarConstants.newItemListenerSuccessContent + file.getName() + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]"));
                        this.dispose();
                    }
                    else
                    {
                        return;
                    }
                }
                else if (!file.createNewFile())
                {
                    /* Set info box */
                    Javar.upperBar.infoBox.setText(JavarConstants.newItemListenerErrorContent + file.getName() + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + "</font></html>");
                    JOptionPane.showMessageDialog(CreatorWindow.this, JavarConstants.creatorWindowFileUnknownErrorMessage,
                        JavarConstants.creatorWindowFileUnknownErrorTitle, JOptionPane.ERROR_MESSAGE);
                }
                else if (!file.canWrite() || !file.canRead())
                {
                    /* Set info box */
                    Javar.upperBar.infoBox.setText(JavarConstants.newItemListenerErrorContent + file.getName() + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + "</font></html>");
                    JOptionPane.showMessageDialog(CreatorWindow.this, JavarConstants.creatorWindowFileErrorMessage,
                        JavarConstants.creatorWindowFileErrorTitle, JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    /* Get file attributes */
                    Path path = Paths.get(file.getPath());
                    BasicFileAttributeView basicView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
                    BasicFileAttributes basicAttributes = basicView.readAttributes();
                    String createdDate = (new Date(basicAttributes.creationTime().toMillis())).toString();
                    String lastModifiedDate = (new Date(basicAttributes.lastModifiedTime().toMillis()).toString());
                    double byteSize = (double) basicAttributes.size();
                    double kByteSize = (int)(byteSize * 10 / 1024) / 10;
                    double mByteSize = (int)(kByteSize * 10 / 1024) / 10;
                    String fileSize = kByteSize >= 1 ? (mByteSize >= 1 ? mByteSize+"MB" : kByteSize+"KB") : byteSize+"B";
                    var data = FileList.createItemData(fileName, fileType, file.getPath(), fileSize, createdDate, lastModifiedDate);
                    /* Set tabbed pane */
                    ImageIcon icon = new ImageIcon("images/icons/" + fileSuffix + "FileTemplateIcon.png");
                    if (icon == null)
                        icon = new ImageIcon("images/icons/defaultFileTemplateIcon.png");
                    icon.setImage(JavarUtils.resizeImageToWH(icon.getImage(), JavarConstants.tabIconWidth, JavarConstants.tabIconHeight, Image.SCALE_SMOOTH));
                    var tmpPanel = new JPanel();
                    tmpPanel.setLayout(new BorderLayout());
                    tmpPanel.add(new CodePane());
                    var tmpScroll = new JScrollPane(tmpPanel);
                    tmpScroll.getVerticalScrollBar().setUnitIncrement(JavarConstants.scrollUnitIncrement);
                    Javar.codeEditor.addTab(fileName, icon, tmpScroll);
                    try 
                    {
                        /* Set file list */
                        FileList.fileItems.add(data);
                        Javar.fileList.setListData(FileList.fileItems);
                    }
                    catch (Exception ignore) {}
                    Javar.codeEditor.setSelectedIndex(Javar.codeEditor.getTabCount() - 1);
                    /* Set info box */
                    Javar.upperBar.infoBox.setText(JavarConstants.newItemListenerSuccessContent + file.getName() + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]"));
                    this.dispose();
                }
            }
            catch (Exception ex)
            {
                /* Set info box */
                Javar.upperBar.infoBox.setText(JavarConstants.newItemListenerErrorContent + file.getName() + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + "</font></html>");
                JOptionPane.showMessageDialog(CreatorWindow.this, JavarConstants.creatorWindowFileUnknownErrorMessage+ex.getMessage(),
                    JavarConstants.creatorWindowFileUnknownErrorTitle, JOptionPane.ERROR_MESSAGE);
                //ex.printStackTrace();
            }
        });
        cancelPropertyButton.addActionListener(e -> {
            this.dispose();
        });
        categoryList.addListSelectionListener(e -> {
            var data = (ItemData) categoryList.getSelectedValue();
            if (data == null)
                return;
            // Update type list items
            typeList.setListData(typeItems.get(data.getType()));
            // Set description
            ImageIcon icon = new ImageIcon("images/icons/" + data.getType() + "TemplateIcon.png");
            icon.setImage(JavarUtils.resizeImageToFitWidthWithPadding(descriptionLeftLabel, icon.getImage(), Image.SCALE_SMOOTH, JavarConstants.creatorDescriptionIconPadding));
            descriptionLeftLabel.setIcon(icon);
            descriptionRightLabel.setText(data.toString());
        });
        typeList.addListSelectionListener(e -> {
            var data = (ItemData) typeList.getSelectedValue();
            if (data == null)
                return;
            // Set description
            ImageIcon icon = new ImageIcon("images/icons/" + data.getType() + "TemplateIcon.png");
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
                    namePropertyLabel.setText("Name:");
                    hasInvalidFileName = false;
                }
                namePropertyTextField.setCaretPosition(0);
            }
        });
        chooseDirPropertyButton.addActionListener(e -> {
            int result = chooser.showDialog(CreatorWindow.this, "Choose Directory");
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
        /* List adjust */
        categoryList.setListData(categoryItems);
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        typeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        categoryList.setCellRenderer(new ItemCellRenderer(JavarConstants.categoryItemWidth, JavarConstants.categoryItemHeight));
        typeList.setCellRenderer(new ItemCellRenderer(JavarConstants.typeItemWidth, JavarConstants.typeItemHeight));
        /* Other adjust */
        templatePropertyComboBox.setEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.pack();
        this.setResizable(false);
        this.setLocation((int)Javar.mainWindow.getLocation().getX() + (int)(Javar.mainWindow.getWidth()/2-this.getWidth()/2), (int)Javar.mainWindow.getLocation().getY());
        this.setVisible(true);
    }
}

class ItemData
{
    int list;
    public static int CATEGORY = 1;
    public static int TYPE = 2;
    String type;
    String name;
    public ItemData(String type, String name, int list)
    {
        this.type = type;
        this.name = name;
        this.list = list;
    }
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
    public String toString()
    {
        if (list == CATEGORY)
            return JavarConstants.creatorCategoryDescription1 + name
                + JavarConstants.creatorCategoryDescription2;
        else
            return JavarConstants.creatorDescription1 + name 
                + JavarConstants.creatorDescription2 + name 
                + JavarConstants.creatorDescription3;
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
        icon.setImage(JavarUtils.resizeImageToFitHeightWithPadding(this, icon.getImage(), Image.SCALE_SMOOTH, JavarConstants.creatorListIconPadding));
        g.setColor(background);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(foreground);
        g.drawImage(icon.getImage(), JavarConstants.creatorListIconOffset, JavarConstants.creatorListIconPadding, null);
        g.drawString(name, JavarConstants.creatorListIconOffset*2+icon.getIconWidth(), (int)(g.getFontMetrics().getAscent()/2+getHeight()/2));
    }
    public Dimension getPreferredSize()
    {
        return new Dimension(W, H);
    }
}
