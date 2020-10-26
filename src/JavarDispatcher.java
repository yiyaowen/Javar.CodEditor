package com.yiyaowen.javar;

import com.yiyaowen.javar.JavarUtils;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings(value = "unchecked")
public class JavarDispatcher
{
	////////////
	// Method //
	////////////
	
	/**
	 * Create a new file initialized with specific file template
	 * 
	 * @param filePath (New file's path)
	 * @param fileTemp (Specific file template)
	 * @return
	 */
	public static void createNewFile(String filePath, String fileTemp)
	{
		File file = new File(filePath);
        try
        {
            if (file.exists()) 
            {
            	// File already exists
				int result;
				result = JOptionPane.showConfirmDialog(null, JavarTranslator.translate(JavarConstants.creatorWindowFileExistsMessage), 
						JavarTranslator.translate(JavarConstants.creatorWindowFileExistsTitle), JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (result == JOptionPane.OK_OPTION)
                {
                	// Overwrite file
                    file.delete();
                    file.createNewFile();
                }
                else
                {
                    return;
                }
            }
            else if (!file.createNewFile())
            {
                // Set info box
                Javar.upperBar.infoBox.setText(JavarTranslator.translate(JavarConstants.newItemListenerErrorContent) + file.getName() + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + "</font></html>");
                // Show error message
                JOptionPane.showMessageDialog(null, JavarTranslator.translate(JavarConstants.creatorWindowFileUnknownErrorMessage),
                		JavarTranslator.translate(JavarConstants.creatorWindowFileUnknownErrorTitle), JOptionPane.ERROR_MESSAGE);
                return;
            }
            else if (!file.canWrite() || !file.canRead())
            {
                // Set info box
                Javar.upperBar.infoBox.setText(JavarTranslator.translate(JavarConstants.newItemListenerErrorContent) + file.getName() + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + "</font></html>");
                // Show error message
                JOptionPane.showMessageDialog(null, JavarTranslator.translate(JavarConstants.creatorWindowFileErrorMessage),
                		JavarTranslator.translate(JavarConstants.creatorWindowFileErrorTitle), JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Get file properties and attributes
            HashMap<String,String> fileMap = JavarUtils.unpackFilePath(filePath);
            HashMap<String,String> fileAttrMap = JavarUtils.getFileAttributes(file);
            String fileName = fileMap.get("fileName");
            String fileSuffix = fileMap.get("fileSuffix");
            String fileSize = fileAttrMap.get("fileSize");
            String createdDate = fileAttrMap.get("createdDate");
            String lastModifiedDate = fileAttrMap.get("lastModifiedDate");
            var data = FileList.createItemData(fileName, fileSuffix, filePath, fileSize, createdDate, lastModifiedDate);
            // Set tabbed pane
            ImageIcon icon = new ImageIcon("../images/icons/" + fileSuffix + "FileTemplateIcon.png");
            if (icon == null)
                icon = new ImageIcon("../images/icons/defaultFileTemplateIcon.png");
            icon.setImage(JavarUtils.resizeImageToWH(icon.getImage(), JavarConstants.tabIconWidth, JavarConstants.tabIconHeight, Image.SCALE_SMOOTH));
            // JScrollPane + JPanel ==> horizontal scrollable
            var tmpPanel = new JPanel();
            tmpPanel.setLayout(new BorderLayout());
            // Varies for languages
            if (fileSuffix.equals("java"))
                tmpPanel.add(new CodePane(JavarConstants.JavaSyntaxFile, JavarConstants.Java_TokensSplitSymbol, true));
            else if (fileSuffix.equals("py"))
                tmpPanel.add(new CodePane(JavarConstants.PythonSyntaxFile, JavarConstants.Python_TokensSplitSymbol, true));
            else if (fileSuffix.equals("c"))
                tmpPanel.add(new CodePane(JavarConstants.CSyntaxFile, JavarConstants.C_TokensSplitSymbol, true));
            else if (fileSuffix.equals("cpp"))
                tmpPanel.add(new CodePane(JavarConstants.CppSyntaxFile, JavarConstants.Cpp_TokensSplitSymbol, true));
            else if (fileSuffix.equals("html"))
                tmpPanel.add(new CodePane(JavarConstants.HtmlSyntaxFile, JavarConstants.Html_TokensSplitSymbol, true));
            else
                tmpPanel.add(new CodePane(JavarConstants.JavaSyntaxFile, JavarConstants.Java_TokensSplitSymbol, false));
            var tmpScroll = new JScrollPane(tmpPanel);
            tmpScroll.getVerticalScrollBar().setUnitIncrement(JavarConstants.scrollUnitIncrement);
            Javar.codeEditor.addTab(fileName, icon, tmpScroll);
            try
            {
                // Set file list
                FileList.fileItems.add(data);
                Javar.fileList.setListData(FileList.fileItems);
            }
            catch (Exception ignore) 
            {
            	Javar.logger.log("i", ignore.getMessage());
            }
            // Set selected tab
            Javar.codeEditor.setSelectedIndex(Javar.codeEditor.getTabCount() - 1);
            // Set info box
            Javar.upperBar.infoBox.setText(JavarTranslator.translate(JavarConstants.newItemListenerSuccessContent) + file.getName() + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]"));
        }
        catch (Exception ex)
        {
            // Set info box
            Javar.upperBar.infoBox.setText(JavarTranslator.translate(JavarConstants.newItemListenerErrorContent) + file.getName() + " " + JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + "</font></html>");
            // Show error message
            JOptionPane.showMessageDialog(null, JavarTranslator.translate(JavarConstants.creatorWindowFileUnknownErrorMessage) + ex.getMessage(),
            		JavarTranslator.translate(JavarConstants.creatorWindowFileUnknownErrorTitle), JOptionPane.ERROR_MESSAGE);
        }
    }
}
