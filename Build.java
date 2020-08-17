package javar.build;

import javar.constants.JavarConstants;
import javar.utils.JavarUtils;
import javar.Javar;
import javar.tabbedpane.TabbedPane;
import javar.upperbar.UpperBar;

import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.nio.channels.*;
 
public class Build
{
    /* Build C++ */
    // C++
    public static void Cpp(String filePath, String dirPath, String fileName, String filePrefix, boolean hasBuild)
    {
        try
        {
            /* Set process */
            Process buildProcess = Runtime.getRuntime().exec("g++ -std=c++11 -o " + filePrefix + " " + fileName, null, new File(dirPath));
            if (buildProcess.waitFor() == 0)
               hasBuild = true;
            /* Try to print */
            try (
                var buildBuffer = new BufferedReader(new InputStreamReader(buildProcess.getInputStream()));
                var buildErrorBuffer = new BufferedReader(new InputStreamReader(buildProcess.getErrorStream())))
            {
                String buff = null;
                if (hasBuild)
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
            catch (Exception outputException)
            {
                outputException.printStackTrace();
            }
        }
        catch (Exception processException)
        {
            processException.printStackTrace();
        }
    }
    /* Build C */
    // C
    public static void C(String filePath, String dirPath, String fileName, String filePrefix, boolean hasBuild)
    {
        try
        {
            /* Set process */
            Process buildProcess = Runtime.getRuntime().exec("gcc -std=c11 -o " + filePrefix + " " + fileName, null, new File(dirPath));
            if (buildProcess.waitFor() == 0)
               hasBuild = true;
            /* Try to print */
            try (
                var buildBuffer = new BufferedReader(new InputStreamReader(buildProcess.getInputStream()));
                var buildErrorBuffer = new BufferedReader(new InputStreamReader(buildProcess.getErrorStream())))
            {
                String buff = null;
                if (hasBuild)
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
            catch (Exception outputException)
            {
                outputException.printStackTrace();
            }
        }
        catch (Exception processException)
        {
            processException.printStackTrace();
        }
    }
    /* Build Java */
    // Java
    public static void Java(String filePath, String dirPath, String fileName, String filePrefix, boolean hasBuild)
    {
        try
        {
            /* Set process */
            Process buildProcess = Runtime.getRuntime().exec("javac -encoding utf-8-d . " + fileName, null, new File(dirPath));
            if (buildProcess.waitFor() == 0)
               hasBuild = true;
            /* Try to print */
            try (
                var buildBuffer = new BufferedReader(new InputStreamReader(buildProcess.getInputStream()));
                var buildErrorBuffer = new BufferedReader(new InputStreamReader(buildProcess.getErrorStream())))
            {
                String buff = null;
                if (hasBuild)
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
            catch (Exception outputException)
            {
                outputException.printStackTrace();
            }
        }
        catch (Exception processException)
        {
            processException.printStackTrace();
        }
    }
}
