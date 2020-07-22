package javar.constants;

import java.awt.*;
import java.awt.event.*;

public class JavarConstants
{
    /* Invoke in advance */
    public static void initJavarConstants()
    {
        // Modifier
        NATIVE_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
        newItemModifier = NATIVE_MASK;
        openItemModifier = NATIVE_MASK;
        saveItemModifier = NATIVE_MASK;
        saveToItemModifier = NATIVE_MASK | InputEvent.SHIFT_DOWN_MASK;
        copyItemModifier = NATIVE_MASK;
        pasteItemModifier = NATIVE_MASK;
        commentItemModifier = NATIVE_MASK;
        buildItemModifier = NATIVE_MASK;
        runItemModifier = NATIVE_MASK;
    }

    /* String : names and contents */
    // Names
    public static final String navigatorLabelName = "Navigator";
    // Contents
    // Creator window contents
    public static final String creatorWindowFileExistsMessage = "File already exists!\nDo you overwrite the file?";
    public static final String creatorWindowFileExistsTitle = "File Already Exists";
    public static final String creatorWindowFileErrorMessage = "Unable to read or write the directory/file!\nPlease check the relevant permissions.";
    public static final String creatorWindowFileErrorTitle = "Unable To Read Or Write";
    public static final String creatorWindowFileUnknownErrorMessage = "An unknown error has occurred!\nUnable to create the file.";
    public static final String creatorWindowFileUnknownErrorTitle = "Unknown Error Occurred";
    public static final String openItemListenerErrorMessage = "Unable to open the file!\nPlease check the integrity of the file and try again.";
    public static final String openItemListenerErrorTitle = "Unable To Open The File";
    public static final String creatorCategoryDescriptionPrefix = 
    "<html>" + 
        "<body>" + 
            "<h1>";
    public static final String creatorCategoryDescriptionSuffix = 
            "</h1>" +
        "</body>" +
    "</html>";
    public static final String fileDescriptionPrefix = 
        "Create a(n) ";
    public static final String fileDescriptionSuffix = 
        ". Check 'Use Template' to enable the designed java template. " +
        "Check 'Generate Prefix Statement' to generate some identifier information automatically. "+
        "The identifier information comes from 'Name', 'Developer', 'Team' and local date (If accessible).";
    public static final String creatorDescriptionPrefix = 
    "<html>" +
        "<body>" +
            "<p><strong>";
    public static String creatorDescriptionMiddle = 
            "</strong></p>" +
            "<hr />" +
            "<p>" + fileDescriptionPrefix;
    public static String creatorDescriptionSuffix = 
            fileDescriptionSuffix + 
            "</p>" +
        "</body>" +
    "</html>";
    // Main window contents
    public static final String infoLabelContentPrefix = 
    "<html>" + 
        "<body>" + 
        "<h2>File Information</h2>" + 
        "<hr />" + 
        "<h4><strong>Current File</strong> - ";
    public static final String infoLabelContentName =
        "</h4>" +
        "<h4><strong>Selected File(s)</strong> - ";
    public static final String infoLabelContentCount = 
        "</h4>" + 
        "<h4><strong>Total File(s)</strong> - ";
    public static final String infoLabelContentTotal =
        "</h4>" +
        "<br />";
    public static final String infoLabelContentSuffix = 
        "</body>" +
    "</html>";
    public static final String navigatorLabelContent = 
    "<html>" +
        "<body>" +
            "<h1>Javar - A Lightweight Powerful Coder</h1>" +
            "<hr />" +
            "<h3><strong>⌘N</strong> - Create New File or Project</h3>" +
            "<h3><strong>⌘O</strong> - Open File or Project</h3>" +
            "<hr />" +
            "<p>Develeper: Fort_W</p>" +
            "<p>Version: 1.0.0</p>" +
        "</body>" +
    "</html>";

    /* PopupMenu type */
    public static final int fileListPopupType = 1;

    /* Menu type */
    public static final int fileMenuType = 1;
    public static final int editMenuType = 2;
    public static final int buildMenuType = 3;
    public static final int runMenuType = 4;
    public static final int toolsMenuType = 5;
    public static final int optionsMenuType = 6;
    public static final int aboutMenuType = 7;
    public static final int newMenuType = 8;

    /* MenuItem type */
    // File menu
    public static final int newItemType = 1;
    public static final int openItemType = 3;
    public static final int saveItemType = 4;
    public static final int saveToItemType = 5;
    public static final int renameItemType = 6;
    public static final int removeItemType = 7;
    public static final int deleteItemType = 8;
    // Edit menu
    public static final int copyItemType = 9;
    public static final int pasteItemType = 10;
    public static final int cutItemType = 11;
    public static final int commentItemType = 12;
    public static final int replaceItemType = 13;
    // Build menu
    public static final int buildItemType = 14;
    // Run menu
    public static final int runItemType = 15;
    // Tools menu
    public static final int codeAnalysisItemType = 16;
    // Options menu
    public static final int generalItemType = 17;
    public static final int highlightConfigurationItemType = 18;
    // About menu
    public static final int licenseItemType = 19;
    public static final int readmeItemType = 20;
    // New menu
    public static final int dirItemType = 21;
    public static final int javaItemType = 22;
    // Popup menu
    public static final int newItemPopupType = 23;
    public static final int openItemPopupType = 24;
    public static final int renameItemPopupType = 25;
    public static final int removeItemPopupType = 26;
    public static final int deleteItemPopupType = 27;
    public static final int codeAnalysisItemPopupType = 28;

    /* MenuItem accelerator parameter */
    // Char
    public static final char newItemChar = 'N';
    public static final char openItemChar = 'O';
    public static final char saveItemChar = 'S';
    public static final char saveToItemChar = 'S';
    public static final char copyItemChar = 'C';
    public static final char pasteItemChar = 'V';
    public static final char commentItemChar = '/';
    public static final char buildItemChar = 'B';
    public static final char runItemChar = 'R';
    // Modifier
    public static int NATIVE_MASK; 
    public static int newItemModifier;
    public static int openItemModifier;
    public static int saveItemModifier;
    public static int saveToItemModifier;
    public static int removeItemModifier;
    public static int copyItemModifier;
    public static int pasteItemModifier;
    public static int commentItemModifier;
    public static int buildItemModifier;
    public static int runItemModifier;

    /* TabbedPane initialization parameter */
    public static final int CodePane = 1;
    public static final int OutputArea = 2;

    /* Programming language syntax split symbol */
    public static final String Java_TokensSplitSymbol = "\\s+|\\.|\\(|\\)|\\{|\\}|\\[|\\]|;|\n|\t";

    /* Component color */
    // Quote color
    public static final int quoteColorR = 205;
    public static final int quoteColorG = 92;
    public static final int quoteColorB = 92;
    public static final int quoteColorHex = 0xCD5C5C;
    // Linebar color
    public static final int linebarColorR = 230;
    public static final int linebarColorG = 230;
    public static final int linebarColorB = 230;
    public static final int linebarColorHex = 0xE6E6E6;
    // Linenum color
    public static final int linenumColorR = 40;
    public static final int linenumColorG = 40;
    public static final int linenumColorB = 40;
    public static final int linenumColorHex = 0x282828;
    // Item selected color
    public static final int itemSelectedColorR = 176;
    public static final int itemSelectedColorG = 196;
    public static final int itemSelectedColorB = 222;
    public static final int itemSelectedColorHex = 0xB0C4DE;

    /* Component size */
    // Great size
    public static final int greatWidth = 3000;
    public static final int greatHeight = 3000;
    public static final int smallWidth = 1;
    public static final int smallHeight = 1;
    // Padding and Icon size
    // Main window Padding and Icon size
    public static final int compilerSelectorIconPadding = 10;
    public static final int tabIconWidth = 15;
    public static final int tabIconHeight = 15;
    public static final int fileListIconPadding = 10;
    public static final int fileListIconOffset = 20;
    // Creator window Padding and Icon size
    public static final int creatorListIconPadding = 2;
    public static final int creatorListIconOffset = 10;
    public static final int creatorDescriptionIconPadding = 10;
    // Creator window size
    public static final int creatorWindowWidth = 800;
    public static final int creatorWindowHeight = 550;
    public static final int categoryLabelWidth = 200;
    public static final int categoryLabelHeight = 30;
    public static final int categoryListWidth = categoryLabelWidth;
    public static final int categoryListHeight = 350;
    public static final int typeLabelWidth = 250;
    public static final int typeLabelHeight = categoryLabelHeight;
    public static final int typeListWidth = typeLabelWidth;
    public static final int typeListHeight = categoryListHeight;
    public static final int labelPanelWidth = categoryLabelWidth+typeLabelWidth;
    public static final int labelPanelHeight = categoryLabelHeight;
    public static final int listPanelWidth = categoryListWidth+typeListWidth;
    public static final int listPanelHeight = categoryListHeight;
    public static final int categoryItemWidth = categoryListWidth;
    public static final int categoryItemHeight = 40;
    public static final int typeItemWidth = typeListWidth;
    public static final int typeItemHeight = 50;
    public static final int descriptionPanelWidth = categoryLabelWidth+typeLabelWidth;
    public static final int descriptionPanelHeight = 170;
    public static final int descriptionLeftLabelWidth = 120;
    public static final int descriptionLeftLabelHeight = descriptionPanelHeight;
    public static final int descriptionRightLabelWidth = 330;
    public static final int descriptionRightLabelHeight = descriptionPanelHeight;
    public static final int creatorLeftPanelWidth = categoryLabelWidth+typeLabelWidth;
    public static final int creatorLeftPanelHeight = creatorWindowHeight;
    public static final int propertyLabelWidth = 350;
    public static final int propertyLabelHeight = categoryLabelHeight;
    public static final int propertyPanelWidth = propertyLabelWidth;
    public static final int propertyPanelHeight = 520;
    public static final int creatorRightPanelWidth = propertyLabelWidth;
    public static final int creatorRightPanelHeight = creatorWindowHeight;
    public static final int namePropertyLabelWidth = 155;
    public static final int namePropertyLabelHeight = 30;
    public static final int namePropertyTextFieldWidth = 175;
    public static final int namePropertyTextFieldHeight = 30;
    public static final int developerPropertyLabelWidth = 155;
    public static final int developerPropertyLabelHeight = 30;
    public static final int developerPropertyTextFieldWidth = 175;
    public static final int developerPropertyTextFieldHeight = 30;
    public static final int teamPropertyLabelWidth = 155;
    public static final int teamPropertyLabelHeight = 30;
    public static final int teamPropertyTextFieldWidth = 175;
    public static final int teamPropertyTextFieldHeight = 30;
    public static final int chooseDirPropertyButtonWidth = 175;
    public static final int chooseDirPropertyButtonHeight = 30;
    public static final int chooseDirPropertyTextFieldWidth = 175;
    public static final int chooseDirPropertyTextFieldHeight = 30;
    public static final int hasTemplatePropertyCheckBoxWidth = 175;
    public static final int hasTemplatePropertyCheckBoxHeight = 30;
    public static final int hasPrefixStatementCheckBoxWidth = 175;
    public static final int hasPrefixStatementCheckBoxHeight = 30;
    public static final int templatePropertyLabelWidth = 155;
    public static final int templatePropertyLabelHeight = 30;
    public static final int templatePropertyComboBoxWidth = 175;
    public static final int templatePropertyComboBoxHeight = 30;
    public static final int cancelPropertyButtonWidth = 165;
    public static final int cancelPropertyButtonHeight = 40;
    public static final int createPropertyButtonWidth = 165;
    public static final int createPropertyButtonHeight = 40;
    public static final int propertyLabelPrefixPadding = 20;
    public static final int propertyButtonPadding = 150;
    public static final int propertySeparatorLabel1Width = 350;
    public static final int propertySeparatorLabel1Height = 100;
    public static final int propertySeparatorLabel2Width = 350;
    public static final int propertySeparatorLabel2Height = 185;
    // Main window size
    public static final int mainWindowWidth = 1200;
    public static final int mainWindowHeight = 750;
    public static final int centerPanelWidth = mainWindowWidth; 
    public static final int centerPanelHeight = 720;
    public static final int fileListWidth = centerPanelWidth*1/5;
    public static final int fileListHeight = centerPanelHeight*3/4;
    public static final int infoLabelWidth = fileListWidth;
    public static final int infoLabelHeight = centerPanelHeight*1/4;
    public static final int codeEditorWidth = centerPanelWidth*4/5;
    public static final int codeEditorHeight = fileListHeight;
    public static final int outputAreaWidth = codeEditorWidth;
    public static final int outputAreaHeight = infoLabelHeight;
    public static final int leftPanelWidth = fileListWidth;
    public static final int leftPanelHeight = centerPanelHeight;
    public static final int rightPanelWidth = codeEditorWidth;
    public static final int rightPanelHeight = centerPanelHeight;
    public static final int upperBarWidth = mainWindowWidth;
    public static final int upperBarHeight = 35;
    public static final int separator1Width = 30;
    public static final int separator1Height = upperBarHeight;
    public static final int runBtnWidth = 60;
    public static final int runBtnHeight = upperBarHeight;
    public static final int separator2Width = 30;
    public static final int separator2Height = upperBarHeight;
    public static final int compilerSelectorWidth = 120;
    public static final int compilerSelectorHeight = upperBarHeight;
    public static final int separator3Width = 165;
    public static final int separator3Height = upperBarHeight;
    public static final int infoBoxWidth = 450;
    public static final int infoBoxHeight = upperBarHeight-8;
    public static final int separator4Width = 175;
    public static final int separator4Height = upperBarHeight;
    public static final int searchLabelWidth = 30;
    public static final int searchLabelHeight = upperBarHeight-6;
    public static final int searchBoxWidth = 170;
    public static final int searchBoxHeight = upperBarHeight-6;
    public static final int fileListItemWidth = fileListWidth;
    public static final int fileListItemHeight = 60;
}
