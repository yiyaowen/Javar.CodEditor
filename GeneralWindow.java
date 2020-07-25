package javar.generalwindow;

import javar.utils.JavarUtils;
import javar.constants.JavarConstants;
import javar.Javar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

@SuppressWarnings(value = "unchecked")
public class GeneralWindow extends JFrame
{
    JLabel iconLabel = new JLabel();
    JLabel themeLabel = new JLabel();
    JComboBox themeComboBox = new JComboBox();
    JLabel fontFamilyLabel = new JLabel();
    JComboBox fontFamilyComboBox = new JComboBox();
    JLabel fontSizeLabel = new JLabel();
    JComboBox fontSizeComboBox = new JComboBox();
    JLabel languageLabel = new JLabel();
    JRadioButton languageBtn1 = new JRadioButton();
    JRadioButton languageBtn2 = new JRadioButton();
    JButton cancelBtn = new JButton();
    JButton OKBtn = new JButton();
    JPanel generalPanel = new JPanel();
    public void initGeneralWindow()
    {
        /* Set absolute layout */
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
        languageBtn1.setBounds(JavarConstants.generalLanguageLabelPrefixPadding+JavarConstants.generalLanguageLabelWidth, JavarConstants.generalIconLabelHeight+JavarConstants.generalThemeComboBoxHeight+JavarConstants.generalFontFamilyComboBoxHeight+JavarConstants.generalFontSizeComboBoxHeight, JavarConstants.generalLanguageBtn1Width, JavarConstants.generalLanguageBtn1Height);
        this.add(languageBtn1);
        languageBtn2.setBounds(JavarConstants.generalLanguageLabelPrefixPadding+JavarConstants.generalLanguageLabelWidth+JavarConstants.generalLanguageBtn1Width, JavarConstants.generalIconLabelHeight+JavarConstants.generalThemeComboBoxHeight+JavarConstants.generalFontFamilyComboBoxHeight+JavarConstants.generalFontSizeComboBoxHeight, JavarConstants.generalLanguageBtn1Width, JavarConstants.generalLanguageBtn1Height);
        this.add(languageBtn2);
        cancelBtn.setBounds(0, JavarConstants.generalIconLabelHeight+JavarConstants.generalThemeComboBoxHeight+JavarConstants.generalFontFamilyComboBoxHeight+JavarConstants.generalFontSizeComboBoxHeight+JavarConstants.generalLanguageBtn1Height, JavarConstants.generalCancelBtnWidth, JavarConstants.generalCancelBtnHeight);
        this.add(cancelBtn);
        OKBtn.setBounds(JavarConstants.generalCancelBtnWidth+JavarConstants.generalBtnPaddingWidth, JavarConstants.generalIconLabelHeight+JavarConstants.generalThemeComboBoxHeight+JavarConstants.generalFontFamilyComboBoxHeight+JavarConstants.generalFontSizeComboBoxHeight+JavarConstants.generalLanguageBtn2Height, JavarConstants.generalOKBtnWidth, JavarConstants.generalOKBtnHeight);
        this.add(OKBtn);
        /* Set component content */
        // Icon 
        ImageIcon icon = new ImageIcon("images/icons/generalIcon.png");
        icon.setImage(JavarUtils.resizeImageToFitHeightWithPadding(iconLabel, icon.getImage(), Image.SCALE_SMOOTH, JavarConstants.generalIconPadding));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);
        iconLabel.setIcon(icon);
        // Theme 
        themeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        themeLabel.setVerticalAlignment(SwingConstants.CENTER);
        themeLabel.setText("Theme");
        for (var LAF : JavarConstants.LAFs)
            themeComboBox.addItem(LAF);
        themeComboBox.setSelectedItem(UIManager.getLookAndFeel().getName());
        // Font family
        fontFamilyLabel.setHorizontalAlignment(SwingConstants.LEFT);
        fontFamilyLabel.setVerticalAlignment(SwingConstants.CENTER);
        fontFamilyLabel.setText("Font Family");
        // Font size
        fontSizeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        fontSizeLabel.setVerticalAlignment(SwingConstants.CENTER);
        fontSizeLabel.setText("Font Size");
        // Language
        languageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        languageLabel.setVerticalAlignment(SwingConstants.CENTER);
        languageLabel.setText("Language");
        languageBtn1.setText("简体中文");
        languageBtn2.setText("English");
        languageBtn2.setSelected(true);
        // Button
        cancelBtn.setText("Cancel");
        OKBtn.setText("OK");
        /* Set component border */
        iconLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        /* Set listener */
        OKBtn.addActionListener(e -> {
            // Update LAF
            String theme = (String) themeComboBox.getSelectedItem();
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
                SwingUtilities.updateComponentTreeUI(Javar.creatorWindow.getContentPane());
                SwingUtilities.updateComponentTreeUI(Javar.generalWindow.getContentPane());
            }
            catch (Exception failure)
            {
                failure.printStackTrace();
            }
        });
        cancelBtn.addActionListener(e -> {
            this.dispose();
        });
        languageBtn1.addActionListener(e -> {
            languageBtn2.setSelected(!languageBtn2.isSelected());
        });
        languageBtn2.addActionListener(e -> {
            languageBtn1.setSelected(!languageBtn1.isSelected());
        });
        /* Final adjustment */
        this.pack();
        this.setResizable(false);
        this.setLocation((int)Javar.mainWindow.getLocation().getX() + (int)(Javar.mainWindow.getWidth()/2-this.getWidth()/2), (int)Javar.mainWindow.getLocation().getY() + (int)(Javar.mainWindow.getHeight()/2-this.getHeight()/2));
        this.setVisible(true);
    }
}
