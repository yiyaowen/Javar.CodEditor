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
 
public class Build
{
    ////////////
    // Method //
    ////////////

    /**
     * Try to compile *.cpp files
     *
     * @param dirPath (Target *.cpp file directory path)
     * @param fileName (Target *.cpp file name)
     * @param filePrefix (Target *.cpp file pure name without .cpp)
     * @param hasBuilt (If build successfully equals true, otherwise false)
     * @return
     */
    public static void Cpp(String dirPath, String fileName, String filePrefix, boolean hasBuilt)
    {
        TryBuild("g++ -std=c++11 -o "+filePrefix+" "+fileName, dirPath, hasBuilt);
    }

    /**
     * Try to compile *.c files
     *
     * @param dirPath (Target *.c file directory path)
     * @param fileName (Target *.c file name)
     * @param filePrefix (Target *.c file pure name without .cpp)
     * @param hasBuilt (If build successfully equals true, otherwise false)
     * @return
     */
    public static void C(String dirPath, String fileName, String filePrefix, boolean hasBuilt)
    {
        TryBuild("gcc -std=c11 -o "+filePrefix+" "+fileName, dirPath, hasBuilt);
    }

    /**
     * Try to compile *.java files
     *
     * @param dirPath (Target *.java file directory path)
     * @param fileName (Target *.java file name)
     * @param filePrefix (Target *.java file pure name without .cpp)
     * @param hasBuilt (If build successfully equals true, otherwise false)
     * @return
     */
    public static void Java(String dirPath, String fileName, String filePrefix, boolean hasBuilt)
    {
        TryBuild("javac -encoding utf-8 -d . "+fileName, dirPath, hasBuilt);
    }

    /** Try to run the specific build command
     *
     * @param cmd (The build command)
     * @param workDir (The working directory path)
     * @param hasBuilt (If build successfully equals true, otherwise false)
     * @return
     */
    protected static void TryBuild(String cmd, String workDir, boolean hasBuilt)
    {
        try
        {
            // Set process
            Process buildProcess = Runtime.getRuntime().exec(cmd, null, new File(workDir));
            if (buildProcess.waitFor() == 0)
               hasBuilt = true;
            // Try to print
            try (
                var buildBuffer = new BufferedReader(new InputStreamReader(buildProcess.getInputStream()));
                var buildErrorBuffer = new BufferedReader(new InputStreamReader(buildProcess.getErrorStream())))
            {
                String buff = null;
                if (hasBuilt)
                {   
                    Javar.outputArea.setSelectedIndex(0);
                    if (JavarConstants.LANG.equals("EN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.buildMessage);
                    else if (JavarConstants.LANG.equals("CN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.buildMessage_cn);
                    else
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.buildMessage);
                    // Print build information
                    while ((buff = buildBuffer.readLine()) != null)
                    {
                        TabbedPane.outputTextArea.append(buff);
                        // NEW LINE
                        TabbedPane.outputTextArea.append("\n");
                    }
                }
                else
                {
                    Javar.outputArea.setSelectedIndex(1);
                    if (JavarConstants.LANG.equals("EN"))
                        TabbedPane.debugTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.buildErrorMessage);
                    else if (JavarConstants.LANG.equals("CN"))
                        TabbedPane.debugTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.buildErrorMessage_cn);
                    else
                        TabbedPane.debugTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.buildErrorMessage);
                    // Print build error information
                    while ((buff = buildErrorBuffer.readLine()) != null)
                    {
                        TabbedPane.debugTextArea.append(buff);
                        // NEW LINE
                        TabbedPane.debugTextArea.append("\n");
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
    }
}
