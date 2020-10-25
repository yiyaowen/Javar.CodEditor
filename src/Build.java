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
 
public class Build
{
    ////////////
    // Method //
    ////////////

	/**
     * Try to build C++ project
     * 
     * @param filePath (Target *.cpp file path)
     * @return hasBuilt (If build successfully return true, otherwise return false)
     */
    public static boolean Cpp(String filePath)
    {
    	HashMap<String,String> fileMap = JavarUtils.unpackFilePath(filePath);
    	String dirPath = fileMap.get("dirPath");
    	String fileName = fileMap.get("fileName");
    	String filePrefix = fileMap.get("filePrefix");
    	String fileSuffix = fileMap.get("fileSuffix");
        return TryBuild("g++ -std=c++11 -o "+filePrefix+" "+fileName, dirPath);
    }

    /**
     * Try to build C project
     * 
     * @param filePath (Target *.c file path)
     * @return hasBuilt (If build successfully return true, otherwise return false)
     */
    public static boolean C(String filePath)
    {
    	HashMap<String,String> fileMap = JavarUtils.unpackFilePath(filePath);
    	String dirPath = fileMap.get("dirPath");
    	String fileName = fileMap.get("fileName");
    	String filePrefix = fileMap.get("filePrefix");
    	String fileSuffix = fileMap.get("fileSuffix");
        return TryBuild("gcc -std=c11 -o "+filePrefix+" "+fileName, dirPath);
    }

    /**
     * Try to build Java project
     * 
     * @param filePath (Target *.java file path)
     * @return hasBuilt (If build successfully return true, otherwise return false)
     */
    public static boolean Java(String filePath)
    {
    	HashMap<String,String> fileMap = JavarUtils.unpackFilePath(filePath);
    	String dirPath = fileMap.get("dirPath");
    	String fileName = fileMap.get("fileName");
    	String filePrefix = fileMap.get("filePrefix");
    	String fileSuffix = fileMap.get("fileSuffix");
        return TryBuild("javac -encoding utf-8 -d . "+fileName, dirPath);
    }

    /** Try to run specific build command
     *
     * @param cmd (Build command)
     * @param workDir (Working directory path)
     * @return hasBuilt (If build successfully return true, otherwise return false)
     */
    protected static boolean TryBuild(String cmd, String workDir)
    {
    	boolean hasBuilt = false;
        try
        {
            // Initialize build process
            Process buildProcess = Runtime.getRuntime().exec(cmd, null, new File(workDir));
            if (buildProcess.waitFor() == 0)
            {
               hasBuilt = true;
            }
            // Try to display build result
            try (
                var buildBuffer = new BufferedReader(new InputStreamReader(buildProcess.getInputStream()));
                var buildErrorBuffer = new BufferedReader(new InputStreamReader(buildProcess.getErrorStream())))
            {
                String buff = null;
                if (hasBuilt)
                {
                	// Build successful
                    Javar.outputArea.setSelectedIndex(0);
                    TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarTranslator.translate(JavarConstants.buildMessage) + "\n");
                    while ((buff = buildBuffer.readLine()) != null)
                    {
                        TabbedPane.outputTextArea.append(buff + "\n");
                    }
                }
                else
                {
                	// Build failed
                    Javar.outputArea.setSelectedIndex(1);
                    TabbedPane.debugTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarTranslator.translate(JavarConstants.buildErrorMessage) + "\n");
                    while ((buff = buildErrorBuffer.readLine()) != null)
                    {
                        TabbedPane.debugTextArea.append(buff + "\n");
                    }
                }
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
        return hasBuilt;
    }
}
