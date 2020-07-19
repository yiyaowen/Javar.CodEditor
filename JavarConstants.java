package javar.constants;

public class JavarConstants
{
    /* TabbedPane initialization parameter */
    public static final int CodePane = 1;
    public static final int OutputArea = 2;

    /* Programming Language Syntax Split Symbol */
    public static String Java_TokensSplitSymbol = "\\s+|\\.|\\(|\\)|\\{|\\}|\\[|\\]|;|\n|\t";

    /* Component Color Constants */
    // Quote color
    public static int quoteColorR = 205;
    public static int quoteColorG = 92;
    public static int quoteColorB = 92;
    public static int quoteColorHex = 0xCD5C5C;
    // Linebar color
    public static int linebarColorR = 230;
    public static int linebarColorG = 230;
    public static int linebarColorB = 230;
    public static int linebarColorHex = 0xE6E6E6;
    // Linenum color
    public static int linenumColorR = 40;
    public static int linenumColorG = 40;
    public static int linenumColorB = 40;
    public static int linenumColorHex = 0x282828;
    // Item selected color
    public static int itemSelectedColorR = 176;
    public static int itemSelectedColorG = 196;
    public static int itemSelectedColorB = 222;
    public static int itemSelectedColorHex = 0xB0C4DE;

    /* Component Size Constants */
    public static int greatWidth = 3000;
    public static int greatHeight = 3000;
    public static int mainWindowWidth = 1200;
    public static int mainWindowHeight = 750;
    public static int centerPanelWidth = mainWindowWidth; 
    public static int centerPanelHeight = 720;
    public static int fileTreeWidth = centerPanelWidth*1/5;
    public static int fileTreeHeight = centerPanelHeight*3/4;
    public static int tmpPanelWidth = fileTreeWidth;
    public static int tmpPanelHeight = centerPanelHeight*1/4;
    public static int codeEditorWidth = centerPanelWidth*4/5;
    public static int codeEditorHeight = fileTreeHeight;
    public static int outputAreaWidth = codeEditorWidth;
    public static int outputAreaHeight = tmpPanelHeight;
    public static int leftPanelWidth = fileTreeWidth;
    public static int leftPanelHeight = centerPanelHeight;
    public static int rightPanelWidth = codeEditorWidth;
    public static int rightPanelHeight = centerPanelHeight;
    public static int upperBarWidth = mainWindowWidth;
    public static int upperBarHeight = 30;
    public static int separator1Width = 30;
    public static int separator1Height = upperBarHeight;
    public static int runBtnWidth = 60;
    public static int runBtnHeight = upperBarHeight;
    public static int separator2Width = 285;
    public static int separator2Height = upperBarHeight;
    public static int infoBoxWidth = 450;
    public static int infoBoxHeight = upperBarHeight;
    public static int separator3Width = 175;
    public static int separator3Height = upperBarHeight;
    public static int searchLabelWidth = 30;
    public static int searchLabelHeight = upperBarHeight;
    public static int searchBoxWidth = 170;
    public static int searchBoxHeight = upperBarHeight;
}
