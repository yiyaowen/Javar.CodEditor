package javar.constants;

import javar.utils.JavarUtils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.*;
import java.io.*;

public class JavarConstants
{
    /* Invoke in advance */
    public static void initJavarConstants()
    {
        // Lang
        JavarUtils.readProperties();
        if (LANG == "EN")
            LOCALE = Locale.US;
        else if (LANG == "CN")
            LOCALE = Locale.CHINA;
        else
            LOCALE = Locale.US;
        // Look And Feel 
        for (var info : UIManager.getInstalledLookAndFeels())
            LAFs.add(info.getName()); 
        // Device relevant size
        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.getWidth();
        screenHeight = screenSize.getHeight();
        // Modifier
        NATIVE_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
        newItemModifier = NATIVE_MASK;
        openItemModifier = NATIVE_MASK;
        saveItemModifier = NATIVE_MASK;
        saveToItemModifier = NATIVE_MASK | InputEvent.SHIFT_DOWN_MASK;
        removeItemModifier = NATIVE_MASK;
        copyItemModifier = NATIVE_MASK;
        pasteItemModifier = NATIVE_MASK;
        commentItemModifier = NATIVE_MASK;
        buildItemModifier = NATIVE_MASK;
        runItemModifier = NATIVE_MASK;
        if (NATIVE_MASK == InputEvent.META_DOWN_MASK)
        {
<<<<<<< HEAD
			pathDelimiter = "/";
=======
>>>>>>> a28bec5d6fdf8e3c14bc40f126ffb1890dbb11e2
            navigatorLabelContent = 
            "<html>" +
                "<body>" +
                    "<h1>Javar - A Lightweight Powerful Coder</h1>" +
                    "<hr />" +
                    "<h3><strong>⌘+N</strong> - Create New File or Project</h3>" +
                    "<h3><strong>⌘+O</strong> - Open File or Project</h3>" +
                    "<h3><strong>⌘+S</strong> - Save Current File</h3>" +
                    "<h3><strong>⇧+⌘+S</strong> - Save File To...</h3>" +
                    "<h3><strong>⌘+W</strong> - Remove Current File</h3>" +
                    "<h3><strong>⌘+B</strong> - Build/Compile Coder</h3>" +
                    "<h3><strong>⌘+R</strong> - Run/Render Coder</h3>" +
                    "<hr />" +
                    "<p>About: https://github.com/yiyaowen/Javar.Project</p>" +
                    "<p>Version: 2.0.0</p>" +
                "</body>" +
            "</html>";
            navigatorLabelContent_cn = 
            "<html>" +
                "<body>" +
                    "<h1>Javar - 轻量级代码编辑器</h1>" +
                    "<hr />" +
                    "<h3><strong>⌘+N</strong> - 创建新文件或项目</h3>" +
                    "<h3><strong>⌘+O</strong> - 打开文件或项目</h3>" +
                    "<h3><strong>⌘+S</strong> - 保存当前文件</h3>" +
                    "<h3><strong>⇧+⌘+S</strong> - 另存文件为...</h3>" +
                    "<h3><strong>⌘+W</strong> - 移除当前文件</h3>" +
                    "<h3><strong>⌘+B</strong> - 构建/编译 Coder</h3>" +
                    "<h3><strong>⌘+R</strong> - 运行/渲染 Coder</h3>" +
                    "<hr />" +
                    "<p>关于: https://github.com/yiyaowen/Javar.Project</p>" +
                    "<p>版本号: 2.0.0</p>" +
                "</body>" +
            "</html>";
        }
        else if (NATIVE_MASK == InputEvent.CTRL_DOWN_MASK)
        {
<<<<<<< HEAD
			pathDelimiter = "\\";
=======
>>>>>>> a28bec5d6fdf8e3c14bc40f126ffb1890dbb11e2
            navigatorLabelContent = 
            "<html>" +
                "<body>" +
                    "<h1>Javar - A Lightweight Powerful Coder</h1>" +
                    "<hr />" +
                    "<h3><strong>Ctrl+N</strong> - Create New File or Project</h3>" +
                    "<h3><strong>Ctrl+O</strong> - Open File or Project</h3>" +
                    "<h3><strong>Ctrl+S</strong> - Save Current File</h3>" +
                    "<h3><strong>⇧+Ctrl+S</strong> - Save File To...</h3>" +
                    "<h3><strong>Ctrl+W</strong> - Remove Current File</h3>" +
                    "<h3><strong>Ctrl+B</strong> - Build/Compile Coder</h3>" +
                    "<h3><strong>Ctrl+R</strong> - Run/Render Coder</h3>" +
                    "<hr />" +
                    "<p>About: https://github.com/yiyaowen/Javar.Project</p>" +
                    "<p>Version: 2.0.0</p>" +
                "</body>" +
            "</html>";
            navigatorLabelContent_cn = 
            "<html>" +
                "<body>" +
                    "<h1>Javar - 轻量级代码编辑器</h1>" +
                    "<hr />" +
                    "<h3><strong>Ctrl+N</strong> - 创建新文件或项目</h3>" +
                    "<h3><strong>Ctrl+O</strong> - 打开文件或项目</h3>" +
                    "<h3><strong>Ctrl+S</strong> - 保存当前文件</h3>" +
                    "<h3><strong>⇧+Ctrl+S</strong> - 另存文件为...</h3>" +
                    "<h3><strong>Ctrl+W</strong> - 移除当前文件</h3>" +
                    "<h3><strong>Ctrl+B</strong> - 构建/编译 Coder</h3>" +
                    "<h3><strong>Ctrl+R</strong> - 运行/渲染 Coder</h3>" +
                    "<hr />" +
                    "<p>关于: https://github.com/yiyaowen/Javar.Project</p>" +
                    "<p>版本号: 2.0.0</p>" +
                "</body>" +
            "</html>";
        }
        else
        {
            navigatorLabelContent = 
            "<html>" +
                "<body>" +
                    "<h1>Javar - A Lightweight Powerful Coder</h1>" +
                    "<hr />" +
                    "<h3><strong>⌘+N</strong> - Create New File or Project</h3>" +
                    "<h3><strong>⌘+O</strong> - Open File or Project</h3>" +
                    "<h3><strong>⌘+S</strong> - Save Current File</h3>" +
                    "<h3><strong>⇧+⌘+S</strong> - Save File To...</h3>" +
                    "<h3><strong>⌘+W</strong> - Remove Current File</h3>" +
                    "<h3><strong>⌘+B</strong> - Build/Compile Coder</h3>" +
                    "<h3><strong>⌘+R</strong> - Run/Render Coder</h3>" +
                    "<hr />" +
                    "<p>About: https://github.com/yiyaowen/Javar.Project</p>" +
                    "<p>Version: 2.0.0</p>" +
                "</body>" +
            "</html>";
            navigatorLabelContent_cn = 
            "<html>" +
                "<body>" +
                    "<h1>Javar - 轻量级代码编辑器</h1>" +
                    "<hr />" +
                    "<h3><strong>⌘+N</strong> - 创建新文件或项目</h3>" +
                    "<h3><strong>⌘+O</strong> - 打开文件或项目</h3>" +
                    "<h3><strong>⌘+S</strong> - 保存当前文件</h3>" +
                    "<h3><strong>⇧+⌘+S</strong> - 另存文件为...</h3>" +
                    "<h3><strong>⌘+W</strong> - 移除当前文件</h3>" +
                    "<h3><strong>⌘+B</strong> - 构建/编译 Coder</h3>" +
                    "<h3><strong>⌘+R</strong> - 运行/渲染 Coder</h3>" +
                    "<hr />" +
                    "<p>关于: https://github.com/yiyaowen/Javar.Project</p>" +
                    "<p>版本号: 2.0.0</p>" +
                "</body>" +
            "</html>";
        }
    }
<<<<<<< HEAD
	/* Multi-platform */
	// Windows & MacOS
	public static String pathDelimiter = "/";
=======
>>>>>>> a28bec5d6fdf8e3c14bc40f126ffb1890dbb11e2

    /* Properties: LANG and LOCALE */
    public static String LANG = "EN"; 
    public static Locale LOCALE = Locale.US;
    /* Font */
    public static int defaultFontSize = 18;
    public static String defaultFontFamily = "Monaco";
    /* Look And Feel */
    public static ArrayList<String> LAFs = new ArrayList<>();
    public static String defaultLAF = "NONE";

    /* String : names and contents */
    // Names
    // Code editor
    public static final String navigatorLabelName = "Navigator";
    public static final String navigatorLabelName_cn = "主页";
    // Compiler selector
    public static final String compilerSelectorJava = "Java";
    public static final String compilerSelectorPython = "Python";
    public static final String compilerSelectorC = "C";
    public static final String compilerSelectorCpp = "C++";
    public static final String compilerSelectorHtml = "html";
    // Creator window
    public static final String invalidNamePropertyMessage = "<html><font>Name: <font color='rgb(180,0,0)'>invalid</font></font></html>";
    public static final String invalidNamePropertyMessage_cn = "<html><font>文件名: <font color='rgb(180,0,0)'>无效</font></font></html>";
    // Contents
    // Creator window contents
    public static final String creatorWindowFileExistsMessage = "File already exists!\nDo you overwrite the file?\n";
    public static final String creatorWindowFileExistsMessage_cn = "文件已经存在!\n是否覆盖该文件?\n";
    public static final String creatorWindowFileExistsTitle = "File Already Exists";
    public static final String creatorWindowFileExistsTitle_cn = "文件已存在";
    public static final String creatorWindowFileErrorMessage = "Unable to read or write the directory/file!\nPlease check the relevant permissions.\n";
    public static final String creatorWindowFileErrorMessage_cn = "无法读/写该目录/文件!\n请检查相关权限.\n";
    public static final String creatorWindowFileErrorTitle = "Unable To Read Or Write";
    public static final String creatorWindowFileErrorTitle_cn = "无法读/写";
    public static final String creatorWindowFileUnknownErrorMessage = "An unknown error has occurred!\nUnable to create the file.\n";
    public static final String creatorWindowFileUnknownErrorMessage_cn = "发生了未知错误!\n无法创建文件.\n";
    public static final String creatorWindowFileUnknownErrorTitle = "Unknown Error Occurred";
    public static final String creatorWindowFileUnknownErrorTitle_cn = "发生了未知错误";
    public static final String creatorCategoryDescription1 = 
    "<html>" + 
        "<body>" + 
            "<h1>";
    public static final String creatorCategoryDescription1_cn = 
    "<html>" + 
        "<body>" + 
            "<h1>";
    public static final String creatorCategoryDescription2 = 
            "</h1>" +
        "</body>" +
    "</html>";
    public static final String creatorCategoryDescription2_cn = 
            "</h1>" +
        "</body>" +
    "</html>";
    public static final String fileDescription1 = 
        "Create a(n) ";
    public static final String fileDescription1_cn = 
        "创建一个 ";
    public static final String fileDescription2 = 
        ". Check 'Use Template' to enable the designed java template. " +
        "Check 'Generate Prefix Statement' to generate some identifier information automatically. "+
        "The identifier information comes from 'Name', 'Developer', 'Team' and local date (If accessible).";
    public static final String fileDescription2_cn = 
        ". 勾选‘使用模板’来使用预定义的java模板. " +
        "勾选‘生成声明前缀’来自动生成预定义的版权信息. "+
        "版权信息可在‘文件名’, ‘开发者’, ‘团队’中设置. 该信息也将包含文件创建时间 (如果可以获取).";
    public static final String creatorDescription1 = 
    "<html>" +
        "<body>" +
            "<p><strong>";
    public static final String creatorDescription1_cn = 
    "<html>" +
        "<body>" +
            "<p><strong>";
    public static String creatorDescription2 = 
            "</strong></p>" +
            "<hr />" +
            "<p>" + fileDescription1;
    public static String creatorDescription2_cn = 
            "</strong></p>" +
            "<hr />" +
            "<p>" + fileDescription1_cn;
    public static String creatorDescription3 = 
            fileDescription2 + 
            "</p>" +
        "</body>" +
    "</html>";
    public static String creatorDescription3_cn = 
            fileDescription2_cn + 
            "</p>" +
        "</body>" +
    "</html>";
    // Main window contents
    public static final String openItemListenerErrorMessage = "Unable to open the file!\nPlease check the integrity of the file and try again.\n";
    public static final String openItemListenerErrorMessage_cn = "无法打开文件!\n请检查文件的完整性并尝试重新打开.\n";
    public static final String openItemListenerErrorTitle = "Unable To Open The File";
    public static final String openItemListenerErrorTitle_cn = "无法打开文件";
    public static final String openItemListenerSuccessContent = "Open File - ";
    public static final String openItemListenerSuccessContent_cn = "打开文件 - ";
    public static final String openItemListenerErrorContent1 = "<html><font color='rgb(180,0,0)'>Open File</font><font> - ";
    public static final String openItemListenerErrorContent1_cn = "<html><font color='rgb(180,0,0)'>打开文件</font><font> - ";
    public static final String openItemListenerErrorContent2 = "</font><font color='rgb(180,0,0)'> Failed </font><font>";
    public static final String openItemListenerErrorContent2_cn = "</font><font color='rgb(180,0,0)'> 失败 </font><font>";
    public static final String newItemListenerSuccessContent = "Create File - ";
    public static final String newItemListenerSuccessContent_cn = "创建文件 - ";
    public static final String newItemListenerErrorContent = "<html><font color='rgb(180,0,0)'>Create File Failed</font><font> - ";
    public static final String newItemListenerErrorContent_cn = "<html><font color='rgb(180,0,0)'>创建文件失败</font><font> - ";
    public static final String saveItemListenerSuccessMessage = "File Saved Successfully - ";
    public static final String saveItemListenerSuccessMessage_cn = "文件保存成功 - ";
    public static final String saveItemListenerErrorMessage = "<html><font color='rgb(180,0,0)'>Save File Failed</font><font> - ";
    public static final String saveItemListenerErrorMessage_cn = "<html><font color='rgb(180,0,0)'>文件保存失败</font><font> - ";
    public static final String saveFileUpdateInfoErrorMessage = "<html><font color='rgb(180,0,0)'>Update File Info Failed</font><font> - ";
    public static final String saveFileUpdateInfoErrorMessage_cn = "<html><font color='rgb(180,0,0)'>更新文件信息失败</font><font> - ";
    public static final String saveToItemListenerSuccessMessage1 = "File Saved To - ";
    public static final String saveToItemListenerSuccessMessage1_cn = "文件保存至 - ";
    public static final String saveToItemListenerSuccessMessage2 = " - Successfully";
    public static final String saveToItemListenerSuccessMessage2_cn = " - 成功";
    public static final String saveToItemListenerErrorMessage1 = "<html><font color='rgb(180,0,0)'>File Saved To</font><font> - ";
    public static final String saveToItemListenerErrorMessage1_cn = "<html><font color='rgb(180,0,0)'>文件保存至</font><font> - ";
    public static final String saveToItemListenerErrorMessage2 = "</font><font color='rgb(180,0,0)'> Failed </font><font>";
    public static final String saveToItemListenerErrorMessage2_cn = "</font><font color='rgb(180,0,0)'> 失败 </font><font>";
    public static final String saveToFileChooserLabelContent = "File Name:";
    public static final String saveToFileChooserLabelContent_cn = "文件名:";
    public static final String saveToFileChooserLabelTitle = "Input File Name";
    public static final String saveToFileChooserLabelTitle_cn = "输入文件名";
    public static final String saveToFileChooserContent = "Save To";
    public static final String saveToFileChooserContent_cn = "保存至";
    public static final String saveToFileChooserWarningMessage = "The filename cannot be empty or starts with \".\".\nPlease try again.";
    public static final String saveToFileChooserWarningMessage_cn = "文件名不得为空或者以“.”开头\n请重新输入.";
    public static final String saveToFileChooserWarningTitle = "Invalid File Name";
    public static final String saveToFileChooserWarningTitle_cn = "无效的文件名";
    public static final String renameItemListenerContent = "New Name:";
    public static final String renameItemListenerContent_cn = "新文件名:";
    public static final String renameItemListenerTitle = "Input New Name";
    public static final String renameItemListenerTitle_cn = "输入新文件名";
    public static final String fileItemListenerContent = "Auto-expand ";
    public static final String fileItemListenerContent_cn = "自动补全 ";
    public static final String fileItemListenerTitle = "Create New File";
    public static final String fileItemListenerTitle_cn = "创建新文件";
    public static final String anyFileItemListenerContent = "Input File Name:";
    public static final String anyFileItemListenerContent_cn = "输入文件名:";
    public static final String renameItemListenerErrorMessage = "Unable to rename the file.\nPlease try again.";
    public static final String renameItemListenerErrorMessage_cn = "无法重命名文件.\n请再尝试一次.";
    public static final String renameItemListenerErrorTitle = "Unable To Rename";
    public static final String renameItemListenerErrorTitle_cn = "无法重命名";
    public static final String deleteItemListenerContent = "Do you delete the file completely?";
    public static final String deleteItemListenerContent_cn = "是否彻底删除文件?";
    public static final String deleteItemListenerTitle = "Delete The File";
    public static final String deleteItemListenerTitle_cn = "删除文件";
    public static final String deleteItemListenerSuccessMessage = "Delete Successfully - ";
    public static final String deleteItemListenerSuccessMessage_cn = "删除成功 - ";
    public static final String deleteItemListenerErrorMessage = "<html><font color='rgb(180,0,0)'>Delete Failed</font><font> - ";
    public static final String deleteItemListenerErrorMessage_cn = "<html><font color='rgb(180,0,0)'>删除失败</font><font> - ";
    public static final String buildMessage = "===> Build Successful <===\n";
    public static final String buildMessage_cn = "===> 构建成功 <===\n";
    public static final String buildErrorMessage = "===> Build Error(s) <===\n";
    public static final String buildErrorMessage_cn = "===> 构建失败 <===\n";
    public static final String runStartMessage = "===> Run Start <===\n";
    public static final String runStartMessage_cn = "===> 开始运行 <===\n";
    public static final String runOverMessage = "===> Run Over <===\n";
    public static final String runOverMessage_cn = "===> 运行结束 <===\n";
    public static final String runErrorMessage = "===> Run Error(s) <===\n";
    public static final String runErrorMessage_cn = "===> 运行错误 <===\n";
    public static final String infoLabelContent1 = 
    "<html>" +
        "<body>" +
            "<strong>";
    public static final String infoLabelContent1_cn = 
    "<html>" +
        "<body>" +
            "<strong>";
    public static final String infoLabelContent2 = 
            "</strong> - <i>";
    public static final String infoLabelContent2_cn = 
            "</strong> - <i>";
    public static final String infoLabelContent3 = 
            "</i>" +
            "<hr />" +
            "<strong>Size:</strong> <div>";
    public static final String infoLabelContent3_cn = 
            "</i>" +
            "<hr />" +
            "<strong>文件大小:</strong> <div>";
    public static final String infoLabelContent4 = 
            "</div>" +
            "<br />" +
            "<strong>Created:</strong> <div>";
    public static final String infoLabelContent4_cn = 
            "</div>" +
            "<br />" +
            "<strong>创建时间:</strong> <div>";
    public static final String infoLabelContent5 = 
            "</div>" +
            "<br />" +
            "<strong>Last Modified:</strong> <div>";
    public static final String infoLabelContent5_cn = 
            "</div>" +
            "<br />" +
            "<strong>上次修改时间:</strong> <div>";
    public static final String infoLabelContent6 = 
            "</div>" +
        "</body>" +
    "</html>";
    public static final String infoLabelContent6_cn = 
            "</div>" +
        "</body>" +
    "</html>";
    public static String navigatorLabelContent = 
    "<html>" +
        "<body>" +
            "<h1>Javar - A Lightweight Powerful Coder</h1>" +
            "<hr />" +
            "<h3><strong>⌘+N</strong> - Create New File or Project</h3>" +
            "<h3><strong>⌘+O</strong> - Open File or Project</h3>" +
            "<h3><strong>⌘+S</strong> - Save Current File</h3>" +
            "<h3><strong>⇧+⌘+S</strong> - Save File To...</h3>" +
            "<h3><strong>⌘+W</strong> - Remove Current File</h3>" +
            "<h3><strong>⌘+B</strong> - Build/Compile Coder</h3>" +
            "<h3><strong>⌘+R</strong> - Run/Render Coder</h3>" +
            "<hr />" +
            "<p>About: https://github.com/yiyaowen/Javar.Project</p>" +
            "<p>Version: 2.0.0</p>" +
        "</body>" +
    "</html>";
    public static String navigatorLabelContent_cn = 
    "<html>" +
        "<body>" +
            "<h1>Javar - 轻量级代码编辑器</h1>" +
            "<hr />" +
            "<h3><strong>⌘+N</strong> - 创建新文件或项目</h3>" +
            "<h3><strong>⌘+O</strong> - 打开文件或项目</h3>" +
            "<h3><strong>⌘+S</strong> - 保存当前文件</h3>" +
            "<h3><strong>⇧+⌘+S</strong> - 另存文件为...h3>" +
            "<h3><strong>⌘+W</strong> - 移除当前文件</h3>" +
            "<h3><strong>⌘+B</strong> - 构建/编译 Coder</h3>" +
            "<h3><strong>⌘+R</strong> - 运行/渲染 Coder</h3>" +
            "<hr />" +
            "<p>关于: https://github.com/yiyaowen/Javar.Project</p>" +
            "<p>版本号: 2.0.0</p>" +
        "</body>" +
    "</html>";
    public static final String previewLabelContent = 
    "<html>" +
        "<body>" +
            "<h1>Coder Preview - Web Simulator</h1>" +
        "</body>" +
    "</html>";
    public static final String previewLabelContent_cn = 
    "<html>" +
        "<body>" +
            "<h1>预览 - Web 模拟器</h1>" +
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
    public static final int javaItemType = 21;
    public static final int cItemType = 22;
    public static final int cppItemType = 23;
    public static final int pyItemType = 24;
    public static final int htmlItemType = 25;
    public static final int anyItemType = 26;
    // Popup menu
    public static final int newItemPopupType = 27;
    public static final int renameItemPopupType = 28;
    public static final int removeItemPopupType = 29;
    public static final int deleteItemPopupType = 30;
    public static final int codeAnalysisItemPopupType = 31;

    /* MenuItem accelerator parameter */
    // Char
    public static final char newItemChar = 'N';
    public static final char openItemChar = 'O';
    public static final char saveItemChar = 'S';
    public static final char saveToItemChar = 'S';
    public static final char removeItemChar = 'W';
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
    public static final String Java_TokensSplitSymbol = "\\s+|\\.|;|,|\\(|\\)|\\{|\\}|\\[|\\]|\n|\t";
    public static final String Python_TokensSplitSymbol = "\\s+|\\.|;|,|\\(|\\)|\\{|\\}|\\[|\\]|\n|\t";
    public static final String C_TokensSplitSymbol = "\\s+|\\.|;|,|\\(|\\)|\\{|\\}|\\[|\\]|\n|\t";
    public static final String Cpp_TokensSplitSymbol = "\\s+|\\.|;|,|\\(|\\)|\\{|\\}|\\[|\\]|\n|\t";
    public static final String Html_TokensSplitSymbol = "";
    /* Programming language syntax file */
    public static final String JavaSyntaxFile = "Java.stx";
    public static final String PythonSyntaxFile = "Python.stx";
    public static final String CSyntaxFile = "C.stx";
    public static final String CppSyntaxFile = "Cpp.stx";
    public static final String HtmlSyntaxFile = "Html.stx";

    /* Component color */
    // Quote color
    public static final int quoteColorR = 153;
    public static final int quoteColorG = 51;
    public static final int quoteColorB = 0;
    public static final int quoteColorHex = 0x993300;
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
    // Device relvevant size
    public static double screenWidth;
    public static double screenHeight;
    public static final int scrollUnitIncrement = 20;
    // Great size
    public static final int greatWidth = 3000;
    public static final int greatHeight = 3000;
    public static final int smallWidth = 1;
    public static final int smallHeight = 1;
    // Padding and Icon size
    // General window Padding and Icon size
    public static final int generalIconPadding = 2;
    // Creator window Padding and Icon size
    public static final int creatorListIconPadding = 2;
    public static final int creatorListIconOffset = 10;
    public static final int creatorDescriptionIconPadding = 10;
    // Main window Padding and Icon size
    public static final int compilerSelectorIconPadding = 10;
    public static final int tabIconWidth = 15;
    public static final int tabIconHeight = 15;
    public static final int fileListIconPadding = 10;
    public static final int fileListIconOffset = 20;
    public static final int codePanePaddingTop = 3;
    public static final int codePanePaddingLeft = 3;
    public static final int codePanePaddingBottom = 0;
    public static final int codePanePaddingRight = 0;
    // Master window size
    // General window size
    public static final int generalWindowWidth = 350;
    public static final int generalWindowHeight = 450; // Extra 50 padding
    public static final int generalPanelWidth = 300;
    public static final int generalPanelHeight = 400;
    public static final int generalIconLabelWidth = generalWindowWidth;
    public static final int generalIconLabelHeight = 100;
    public static final int generalThemeLabelPrefixPadding = 60;
    public static final int generalThemeLabelWidth = 90;
    public static final int generalThemeLabelHeight = 60;
    public static final int generalThemeComboBoxWidth = 150;
    public static final int generalThemeComboBoxHeight = 60;
    public static final int generalFontFamilyLabelPrefixPadding = 60;
    public static final int generalFontFamilyLabelWidth = 90;
    public static final int generalFontFamilyLabelHeight = 60;
    public static final int generalFontFamilyComboBoxWidth = 150;
    public static final int generalFontFamilyComboBoxHeight = 60;
    public static final int generalFontSizeLabelPrefixPadding = 60;
    public static final int generalFontSizeLabelWidth = 90;
    public static final int generalFontSizeLabelHeight = 60;
    public static final int generalFontSizeComboBoxWidth = 150;
    public static final int generalFontSizeComboBoxHeight = 60;
    public static final int generalLanguageLabelPrefixPadding = 10;
    public static final int generalLanguageLabelWidth = 70;
    public static final int generalLanguageLabelHeight = 60;
    public static final int generalLanguageBtn1Width = 100;
    public static final int generalLanguageBtn1Height = 60;
    public static final int generalLanguageBtn2Width = 100;
    public static final int generalLanguageBtn2Height = 60;
    public static final int generalCancelBtnWidth = 100;
    public static final int generalCancelBtnHeight = 40;
    public static final int generalBtnPaddingWidth = 100;
    public static final int generalBtnPaddingHeight = 40;
    public static final int generalOKBtnWidth = 100;
    public static final int generalOKBtnHeight = 40;
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
    public static final int fileListScrollableWidth = fileListWidth;
    public static final int fileListScrollableHeight = fileListHeight;
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
    public static final int compilerSelectorWidth = 130;
    public static final int compilerSelectorHeight = upperBarHeight;
    public static final int separator3Width = 155;
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
