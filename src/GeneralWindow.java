package com.yiyaowen.javar;

import com.yiyaowen.javar.CodePane;
import com.yiyaowen.javar.Javar;
import com.yiyaowen.javar.JavarConstants;
import com.yiyaowen.javar.JavarUtils;
import com.yiyaowen.javar.JavarTranslator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

@SuppressWarnings(value = "unchecked")
public class GeneralWindow extends JFrame
{
    //////////////
    // Property //
    //////////////

    JLabel iconLabel = new JLabel();
    JLabel themeLabel = new JLabel();
    JComboBox themeComboBox = new JComboBox();
    JLabel fontFamilyLabel = new JLabel();
    JComboBox fontFamilyComboBox = new JComboBox();
    JLabel fontSizeLabel = new JLabel();
    JComboBox fontSizeComboBox = new JComboBox();
    JLabel languageLabel = new JLabel();
    JRadioButton languageBtn_zh_CN = new JRadioButton();
    JRadioButton languageBtn_us_EN = new JRadioButton();
    JButton cancelBtn = new JButton();
    JButton OKBtn = new JButton();
    JPanel generalPanel = new JPanel();

    ////////////
    // Method //
    ////////////

    /**
     * Initialize general window
     *
     * @param
     * @return
     */
    public void initGeneralWindow()
    {
        // Set absolute layout
        this.setLayout(null);
        this.setPreferredSize(new Dimension(JavarConstants.generalWindowWidth, JavarConstants.generalWindowHeight));
        iconLabel.setBounds(0, 0, JavarConstants.generalIconLabelWidth, JavarConstants.generalIconLabelHeight);
        this.add(iconLabel);
        themeLabel.setBounds(JavarConstants.generalThemeLabelPrefixPadding, JavarConstants.generalIconLabelHeight, JavarConstants.generalThemeLabelWidth, JavarConstants.generalThemeLabelHeight);
        this.add(themeLabel);
        themeComboBox.setBounds(JavarConstants.generalThemeLabelPrefixPadding+JavarConstants.generalThemeLabelWidth, JavarConstants.generalIconLabelHeight, JavarConstants.generalThemeComboBoxWidth, JavarConstants.generalThemeComboBoxHeight);
        this.add(themeComboBox);
        fontFamilyLabel.setBounds(JavarConstants.generalFontFamilyLabelPrefixPadding, JavarConstants.generalIconLabelHeight+JavarConstants.generalThemeLabelHeight, JavarConstants.generalFontFamilyLabelWidth, JavarConstants.generalFontFamilyLabelHeight);
        this.add(fontFamilyLabel);
        fontFamilyComboBox.setBounds(JavarConstants.generalFontFamilyLabelPrefixPadding+JavarConstants.generalFontFamilyLabelWidth, JavarConstants.generalIconLabelHeight+JavarConstants.generalThemeComboBoxHeight, JavarConstants.generalFontFamilyComboBoxWidth, JavarConstants.generalFontFamilyComboBoxHeight);
        this.add(fontFamilyComboBox);
        fontSizeLabel.setBounds(JavarConstants.generalFontSizeLabelPrefixPadding, JavarConstants.generalIconLabelHeight+JavarConstants.generalThemeLabelHeight+JavarConstants.generalFontFamilyLabelHeight, JavarConstants.generalFontSizeLabelWidth, JavarConstants.generalFontSizeLabelHeight);
        this.add(fontSizeLabel);
        fontSizeComboBox.setBounds(JavarConstants.generalFontSizeLabelPrefixPadding+JavarConstants.generalFontSizeLabelWidth, JavarConstants.generalIconLabelHeight+JavarConstants.generalThemeComboBoxHeight+JavarConstants.generalFontFamilyComboBoxHeight, JavarConstants.generalFontSizeComboBoxWidth, JavarConstants.generalFontSizeComboBoxHeight);
        this.add(fontSizeComboBox);
        languageLabel.setBounds(JavarConstants.generalLanguageLabelPrefixPadding, JavarConstants.generalIconLabelHeight+JavarConstants.generalThemeLabelHeight+JavarConstants.generalFontFamilyLabelHeight+JavarConstants.generalFontSizeLabelHeight, JavarConstants.generalLanguageLabelWidth, JavarConstants.generalLanguageLabelHeight);
        this.add(languageLabel);
        languageBtn_zh_CN.setBounds(JavarConstants.generalLanguageLabelPrefixPadding+JavarConstants.generalLanguageLabelWidth, JavarConstants.generalIconLabelHeight+JavarConstants.generalThemeComboBoxHeight+JavarConstants.generalFontFamilyComboBoxHeight+JavarConstants.generalFontSizeComboBoxHeight, JavarConstants.generalLanguageBtn1Width, JavarConstants.generalLanguageBtn1Height);
        this.add(languageBtn_zh_CN);
        languageBtn_us_EN.setBounds(JavarConstants.generalLanguageLabelPrefixPadding+JavarConstants.generalLanguageLabelWidth+JavarConstants.generalLanguageBtn1Width, JavarConstants.generalIconLabelHeight+JavarConstants.generalThemeComboBoxHeight+JavarConstants.generalFontFamilyComboBoxHeight+JavarConstants.generalFontSizeComboBoxHeight, JavarConstants.generalLanguageBtn1Width, JavarConstants.generalLanguageBtn1Height);
        this.add(languageBtn_us_EN);
        cancelBtn.setBounds(0, JavarConstants.generalIconLabelHeight+JavarConstants.generalThemeComboBoxHeight+JavarConstants.generalFontFamilyComboBoxHeight+JavarConstants.generalFontSizeComboBoxHeight+JavarConstants.generalLanguageBtn1Height, JavarConstants.generalCancelBtnWidth, JavarConstants.generalCancelBtnHeight);
        this.add(cancelBtn);
        OKBtn.setBounds(JavarConstants.generalCancelBtnWidth+JavarConstants.generalBtnPaddingWidth, JavarConstants.generalIconLabelHeight+JavarConstants.generalThemeComboBoxHeight+JavarConstants.generalFontFamilyComboBoxHeight+JavarConstants.generalFontSizeComboBoxHeight+JavarConstants.generalLanguageBtn2Height, JavarConstants.generalOKBtnWidth, JavarConstants.generalOKBtnHeight);
        this.add(OKBtn);
        // Set component content
        	// Icon
        ImageIcon icon = new ImageIcon("../images/icons/generalIcon.png");
        icon.setImage(JavarUtils.resizeImageToFitHeightWithPadding(iconLabel, icon.getImage(), Image.SCALE_SMOOTH, JavarConstants.generalIconPadding));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);
        iconLabel.setIcon(icon);
        	// Theme
        themeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        themeLabel.setVerticalAlignment(SwingConstants.CENTER);
        themeLabel.setText(JavarTranslator.translate(JavarConstants.generalWindowPrefix, "Theme"));
        for (var LAF : JavarConstants.LAFs)
            themeComboBox.addItem(LAF);
        if (JavarConstants.defaultLAF.equals("NONE"))
            themeComboBox.setSelectedItem(UIManager.getLookAndFeel().getName());
        else
            themeComboBox.setSelectedItem(JavarConstants.defaultLAF);
        // Font family
        fontFamilyLabel.setHorizontalAlignment(SwingConstants.LEFT);
        fontFamilyLabel.setVerticalAlignment(SwingConstants.CENTER);
        fontFamilyLabel.setText(JavarTranslator.translate(JavarConstants.generalWindowPrefix, "Font Family"));
        for (var family : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
            fontFamilyComboBox.addItem(family);
        fontFamilyComboBox.setSelectedItem(JavarConstants.defaultFontFamily);
        // Font size
        fontSizeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        fontSizeLabel.setVerticalAlignment(SwingConstants.CENTER);
        fontSizeLabel.setText(JavarTranslator.translate(JavarConstants.generalWindowPrefix, "Font Size"));
        for (int size = 12; size <= 26; size++)
            fontSizeComboBox.addItem(size);
        fontSizeComboBox.setSelectedItem(JavarConstants.defaultFontSize);
        // Language
        languageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        languageLabel.setVerticalAlignment(SwingConstants.CENTER);
        languageLabel.setText("Language");
        languageBtn_zh_CN.setText("简体中文");
        languageBtn_us_EN.setText("English");
        if (JavarConstants.LANG.equals("zh_CN"))
            languageBtn_zh_CN.setSelected(true);
        else
            languageBtn_us_EN.setSelected(true);
        // Button
        cancelBtn.setText(JavarTranslator.translate(JavarConstants.generalWindowPrefix, "Cancel"));
        OKBtn.setText(JavarTranslator.translate(JavarConstants.generalWindowPrefix, "OK"));
        // Set component border
        iconLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        // Set listener
        OKBtn.addActionListener(e -> {
            // Update LAF
            String theme = (String) themeComboBox.getSelectedItem();
            if (!theme.equals(JavarConstants.defaultLAF))
            {
                JavarConstants.defaultLAF = theme;
                try
                {
                    for (var info : UIManager.getInstalledLookAndFeels())
                    {
                        if (theme.equals(info.getName()))
                        {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                    SwingUtilities.updateComponentTreeUI(Javar.mainWindow.getContentPane());
                    SwingUtilities.updateComponentTreeUI(Javar.managerBar);
                    SwingUtilities.updateComponentTreeUI(Javar.creatorWindow.getContentPane());
                    SwingUtilities.updateComponentTreeUI(Javar.generalWindow.getContentPane());
                }
                catch (Exception failure)
                {
                    failure.printStackTrace();
                }
            }
            // Update font family and size
            if (!((String)fontFamilyComboBox.getSelectedItem()).equals(JavarConstants.defaultFontFamily) ||
                (((Integer)fontSizeComboBox.getSelectedItem()) != JavarConstants.defaultFontSize))
            {
                CodePane.FONT_FAMILY = (String) fontFamilyComboBox.getSelectedItem();
                CodePane.FONT_SIZE = (Integer) fontSizeComboBox.getSelectedItem();
                JavarConstants.defaultFontFamily = CodePane.FONT_FAMILY;
                JavarConstants.defaultFontSize = CodePane.FONT_SIZE;
                int selectedIndex = Javar.codeEditor.getSelectedIndex();
                for (var index = 1; index < Javar.codeEditor.getTabCount(); index++)
                {
                    // Always get second tab
                    String title = Javar.codeEditor.getTitleAt(1);
                    Icon tabIcon = Javar.codeEditor.getIconAt(1);
                    var tmp1 = (JScrollPane) Javar.codeEditor.getComponentAt(1);
                    var tmp2 = (CodePane) ((JPanel) tmp1.getViewport().getComponents()[0]).getComponents()[0];
                    String content = tmp2.getText();
                    tmp2 = new CodePane(tmp2.getSyntaxFile(), tmp2.getSplitSymbol(), tmp2.getHasSyntax());
                    tmp2.setText(content);
                    var tmpPanel = new JPanel();
                    tmpPanel.setLayout(new BorderLayout());
                    tmpPanel.add(tmp2);
                    var tmpScroll = new JScrollPane(tmpPanel);
                    tmpScroll.getVerticalScrollBar().setUnitIncrement(JavarConstants.scrollUnitIncrement);
                    // Always remove second tab
                    Javar.codeEditor.remove(1);
                    // Add new tab to the end
                    Javar.codeEditor.addTab(title, tabIcon, tmpScroll);
                    Javar.codeEditor.setSelectedIndex(selectedIndex);
                }
            }
            // Update language
            if (languageBtn_zh_CN.isSelected())
            {
                JavarConstants.LANG = "zh_CN";
            }
            else
            {
                JavarConstants.LANG = "us_EN";
            }
            // Save global properties
            JavarUtils.saveGlobalProperties();
        });
        cancelBtn.addActionListener(e -> {
            this.dispose();
        });
        languageBtn_zh_CN.addActionListener(e -> {
            languageBtn_us_EN.setSelected(!languageBtn_us_EN.isSelected());
        });
        languageBtn_us_EN.addActionListener(e -> {
            languageBtn_zh_CN.setSelected(!languageBtn_zh_CN.isSelected());
        });
        // Final adjustment
        this.pack();
        //this.setResizable(false);
        this.setLocation((int)Javar.mainWindow.getLocation().getX() + (int)(Javar.mainWindow.getWidth()/2-this.getWidth()/2), (int)Javar.mainWindow.getLocation().getY() + (int)(Javar.mainWindow.getHeight()/2-this.getHeight()/2));
        this.setVisible(true);
    }
}
