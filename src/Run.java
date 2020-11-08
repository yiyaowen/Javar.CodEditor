package com.yiyaowen.javar;

import com.yiyaowen.javar.Javar;
import com.yiyaowen.javar.JavarConstants;
import com.yiyaowen.javar.JavarUtils;
import com.yiyaowen.javar.TabbedPane;
import com.yiyaowen.javar.UpperBar;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.HashMap;

public class Run
{
	////////////
	// Method //
	////////////
	
    /**
     * Try to render *.html file
     *
     * @param filePath (Target *.html file path)
     * @return
     */
    public static void Html(String filePath)
    {   
        File file = new File(filePath);
        try (
            var inChannel = new FileInputStream(file).getChannel())
        {   
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder = charset.newDecoder();
            CharBuffer charBuffer = decoder.decode(buffer);
            TabbedPane.previewLabel.setText(charBuffer.toString());
        }   
        catch (Exception ex)
        {   
            Javar.logger.log("e", ex.getMessage());
        }   
        finally
        {
            Javar.outputArea.setSelectedIndex(2);
        }
    }   

    /**
     * Try to run *.exe file
     *
     * @param filePath (Target *.exe file path)
     * @return hasRun (If run successfully return true, otherwise return false)
     */
    public static boolean Exe(String filePath)
    {
    	HashMap<String,String> fileMap = JavarUtils.unpackFilePath(filePath);
    	String dirPath = fileMap.get("dirPath");
    	String filePrefix = fileMap.get("filePrefix");
        return TryRun(dirPath + JavarConstants.pathDelimiter + filePrefix, dirPath);
    }
    
    /**
     * Try to run *.py file
     * 
     * @param filePath (Target *.exe file path)
     * @return hasRun (If run successfully return true, otherwise return false)
     */
    public static boolean Python(String filePath)
    {
    	HashMap<String,String> fileMap = JavarUtils.unpackFilePath(filePath);
    	String fileName = fileMap.get("fileName");
    	String dirPath = fileMap.get("dirPath");
    	return TryRun("python " + fileName, dirPath);
    }
	
    /**
     * Try to run JVM (Java Virtual Machine)
     * 
     * @param filePath (Target *.class file path)
     * @return hasRun (If run successfully return true, otherwise return false)
     */
    public static boolean Java(String filePath)
    {
    	HashMap<String,String> fileMap = JavarUtils.unpackFilePath(filePath);
    	String filePrefix = fileMap.get("filePrefix");
    	String dirPath = fileMap.get("dirPath");
    	return TryRun("java " + filePrefix, dirPath);
    }

    /**
     * Try to run specific run command
     *
     * @param cmd (Run command)
     * @param workDir (Working directory path)
     * @return hasRun (If run successfully return true, otherwise return false)
     */
    protected static boolean TryRun(String cmd, String workDir)
    {
    	boolean hasRun = false;
        try
        {
            // Initialize run process
            Process runProcess = Runtime.getRuntime().exec(cmd, null, new File(workDir));
            if (runProcess.waitFor() == 0)
            {
                hasRun = true;
            }
            // Try to display run result
            try (
                var runBuffer = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                var runErrorBuffer = new BufferedReader(new InputStreamReader(runProcess.getErrorStream())))
            {
                String buff = null;
                Javar.outputArea.setSelectedIndex(0);
                TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") +
                    JavarTranslator.translate(JavarConstants.runPrefix, JavarConstants.runStartMessage) + "\n");
                if (hasRun)
                {
                	// Run successful
                    while ((buff = runBuffer.readLine()) != null)
                    {
                        TabbedPane.outputTextArea.append(buff + "\n");
                    }
                }
                else
                {
                	// Run failed
                	TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + 
                        JavarTranslator.translate(JavarConstants.runPrefix, JavarConstants.runErrorMessage) + "\n");
                    while ((buff = runErrorBuffer.readLine()) != null)
                    {
                        TabbedPane.outputTextArea.append(buff + "\n");
                    }
                }
                TabbedPane.outputTextArea.append(
                    JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + 
                    JavarTranslator.translate(JavarConstants.runPrefix, JavarConstants.runOverMessage) + "\n");
            }
            catch (Exception outputEx)
            {
                Javar.logger.log("e", outputEx.getMessage());
            }
        }
        catch (Exception processEx)
        {
        	Javar.logger.log("e", processEx.getMessage());
        }
        return hasRun;
    }      
}
