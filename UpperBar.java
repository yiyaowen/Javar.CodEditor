package javar.upperbar;

import javar.utils.JavarUtils;
import javar.constants.JavarConstants;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class UpperBar extends JPanel
{
    JLabel separator1 = new JLabel();
    JButton runBtn = new JButton();
    JLabel separator2 = new JLabel();
    JLabel infoBox = new JLabel("This is info box...");
    JLabel separator3 = new JLabel();
    JTextField searchBox = new JTextField();
    JLabel searchLabel = new JLabel();
    ImageIcon runIcon = new ImageIcon("images/icons/run.png");
    ImageIcon searchIcon = new ImageIcon("images/icons/search.png");
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
        separator2.setMinimumSize(new Dimension(0, 0));
        infoBox.setMinimumSize(new Dimension(JavarConstants.infoBoxWidth, JavarConstants.infoBoxHeight));
        separator3.setMinimumSize(new Dimension(0, 0));
        searchBox.setMinimumSize(new Dimension(JavarConstants.searchBoxWidth, JavarConstants.searchBoxHeight));
        searchLabel.setMinimumSize(new Dimension(JavarConstants.searchLabelWidth, JavarConstants.searchLabelHeight));
        // Maximum size
        separator1.setMaximumSize(new Dimension(JavarConstants.separator1Width, JavarConstants.separator1Height));
        runBtn.setMaximumSize(new Dimension(JavarConstants.runBtnWidth, JavarConstants.runBtnHeight));
        separator2.setMaximumSize(new Dimension(JavarConstants.greatWidth, JavarConstants.greatHeight));
        infoBox.setMaximumSize(new Dimension(JavarConstants.infoBoxWidth, JavarConstants.infoBoxHeight));
        separator3.setMaximumSize(new Dimension(JavarConstants.greatWidth, JavarConstants.greatHeight));
        searchBox.setMaximumSize(new Dimension(JavarConstants.searchBoxWidth, JavarConstants.searchBoxHeight));
        searchLabel.setMaximumSize(new Dimension(JavarConstants.searchLabelWidth, JavarConstants.searchLabelHeight));
        // Preferred size
        separator1.setPreferredSize(new Dimension(JavarConstants.separator1Width, JavarConstants.separator1Height));
        runBtn.setPreferredSize(new Dimension(JavarConstants.runBtnWidth, JavarConstants.runBtnHeight));
        separator2.setPreferredSize(new Dimension(JavarConstants.separator2Width, JavarConstants.separator2Height));
        infoBox.setPreferredSize(new Dimension(JavarConstants.infoBoxWidth, JavarConstants.infoBoxHeight));
        separator3.setPreferredSize(new Dimension(JavarConstants.separator3Width, JavarConstants.separator3Height));
        searchBox.setPreferredSize(new Dimension(JavarConstants.searchBoxWidth, JavarConstants.searchBoxHeight));
        searchLabel.setPreferredSize(new Dimension(JavarConstants.searchLabelWidth, JavarConstants.searchLabelHeight));
        /* Alignment */
        infoBox.setHorizontalAlignment(SwingConstants.CENTER);
        infoBox.setVerticalAlignment(SwingConstants.CENTER);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(separator1);
        this.add(runBtn);
        this.add(separator2);
        this.add(infoBox);
        this.add(separator3);
        this.add(searchBox);
        this.add(searchLabel);
        runIcon.setImage(JavarUtils.resizeImageToWH(runIcon.getImage(), JavarConstants.runBtnWidth, JavarConstants.runBtnHeight, Image.SCALE_SMOOTH));
        runBtn.setIcon(runIcon);
        infoBox.setBorder(BorderFactory.createEtchedBorder());
        searchIcon.setImage(JavarUtils.resizeImageToWH(searchIcon.getImage(), JavarConstants.searchLabelWidth, JavarConstants.searchLabelHeight, Image.SCALE_SMOOTH));
        searchLabel.setIcon(searchIcon);
    }
}
