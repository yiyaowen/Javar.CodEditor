package javar.buildandrun;

import javar.tabbedpane.TabbedPane;
import javar.constants.JavarConstants;
import javar.utils.JavarUtils;
import javar.Javar;

import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.nio.channels.*;

public class BuildAndRun
{
    /* Build and Run html */
    // html
    public static void Html(String filePath, String dirPath, String fileName, String filePrefix, boolean hasBuild, boolean hasRun)
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
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            Javar.outputArea.setSelectedIndex(2);
        }
    }
    /* Build and Run C++ */
    // C++
    public static void Cpp(String filePath, String dirPath, String fileName, String filePrefix, boolean hasBuild, boolean hasRun)
    {
        try 
        {
            /* Set process */
<<<<<<< HEAD
            Process buildProcess = Runtime.getRuntime().exec("g++ -std=c++11 -o " + filePrefix + " " + fileName, null, new File(dirPath));
            if (buildProcess.waitFor() == 0)
               hasBuild = true; 
            // Adjustment
            Process runProcess = null;
            if (hasBuild)
            {
                runProcess = Runtime.getRuntime().exec(dirPath + JavarConstants.pathDelimiter + filePrefix, null, new File(dirPath));
=======
            Process buildProcess = Runtime.getRuntime().exec("g++ -std=c++1 -o " + filePrefix + " " + fileName, null, new File(dirPath));
            if (buildProcess.waitFor() == 0)
               hasBuild = true; 
            // Adjustment
            Process runProcess = Runtime.getRuntime().exec("cd .");
            if (hasBuild)
            {
                runProcess = Runtime.getRuntime().exec("./" + filePrefix, null, new File(dirPath));
>>>>>>> a28bec5d6fdf8e3c14bc40f126ffb1890dbb11e2
                if (runProcess.waitFor() == 0)
                    hasRun = true;
            }
            /* Try to print */
            try (
                var buildBuffer = new BufferedReader(new InputStreamReader(buildProcess.getInputStream()));
                var buildErrorBuffer = new BufferedReader(new InputStreamReader(buildProcess.getErrorStream()));
                var runBuffer = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                var runErrorBuffer = new BufferedReader(new InputStreamReader(runProcess.getErrorStream())))
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
                    if (JavarConstants.LANG.equals("EN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runStartMessage);
                    else if (JavarConstants.LANG.equals("CN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runStartMessage_cn);
                    else
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runStartMessage);
                    if (hasRun)
                    {
                        // print run information
                        while ((buff = runBuffer.readLine()) != null)
                        {
                            TabbedPane.outputTextArea.append(buff);
                            // new line
                            TabbedPane.outputTextArea.append("\n");
                        }
                    }
                    else
                    {
                        if (JavarConstants.LANG.equals("EN"))
                            TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runErrorMessage);
                        else if (JavarConstants.LANG.equals("CN"))
                            TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runErrorMessage_cn);
                        else
                            TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runErrorMessage);
                        // print run error information
                        while ((buff = runErrorBuffer.readLine()) != null)
                        {
                            TabbedPane.outputTextArea.append(buff);
                            // new line
                            TabbedPane.outputTextArea.append("\n");
                        }
                    }
                    if (JavarConstants.LANG.equals("EN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage);
                    else if (JavarConstants.LANG.equals("CN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage_cn);
                    else
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage);
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
        finally 
        {
            TabbedPane.outputTextArea.setCaretPosition(TabbedPane.outputTextArea.getText().length());
            TabbedPane.debugTextArea.setCaretPosition(TabbedPane.debugTextArea.getText().length());
        }
    }
    /* Build and Run C */
    // C
    public static void C(String filePath, String dirPath, String fileName, String filePrefix, boolean hasBuild, boolean hasRun)
    {
        try 
        {
            /* Set process */
            Process buildProcess = Runtime.getRuntime().exec("gcc -std=c11 -o " + filePrefix + " " + fileName, null, new File(dirPath));
            if (buildProcess.waitFor() == 0)
               hasBuild = true; 
            // Adjustment
<<<<<<< HEAD
            Process runProcess = null;
            if (hasBuild)
            {
                runProcess = Runtime.getRuntime().exec(dirPath + JavarConstants.pathDelimiter + filePrefix, null, new File(dirPath));
=======
            Process runProcess = Runtime.getRuntime().exec("cd .");
            if (hasBuild)
            {
                runProcess = Runtime.getRuntime().exec("./" + filePrefix, null, new File(dirPath));
>>>>>>> a28bec5d6fdf8e3c14bc40f126ffb1890dbb11e2
                if (runProcess.waitFor() == 0)
                    hasRun = true;
            }
            /* Try to print */
            try (
                var buildBuffer = new BufferedReader(new InputStreamReader(buildProcess.getInputStream()));
                var buildErrorBuffer = new BufferedReader(new InputStreamReader(buildProcess.getErrorStream()));
                var runBuffer = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                var runErrorBuffer = new BufferedReader(new InputStreamReader(runProcess.getErrorStream())))
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
                    if (JavarConstants.LANG.equals("EN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runStartMessage);
                    else if (JavarConstants.LANG.equals("CN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runStartMessage_cn);
                    else
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runStartMessage);
                    if (hasRun)
                    {
                        // print run information
                        while ((buff = runBuffer.readLine()) != null)
                        {
                            TabbedPane.outputTextArea.append(buff);
                            // new line
                            TabbedPane.outputTextArea.append("\n");
                        }
                    }
                    else
                    {
                        if (JavarConstants.LANG.equals("EN"))
                            TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runErrorMessage);
                        else if (JavarConstants.LANG.equals("CN"))
                            TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runErrorMessage_cn);
                        else
                            TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runErrorMessage);
                        // print run error information
                        while ((buff = runErrorBuffer.readLine()) != null)
                        {
                            TabbedPane.outputTextArea.append(buff);
                            // new line
                            TabbedPane.outputTextArea.append("\n");
                        }
                    }
                    if (JavarConstants.LANG.equals("EN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage);
                    else if (JavarConstants.LANG.equals("CN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage_cn);
                    else
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage);
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
        finally 
        {
            TabbedPane.outputTextArea.setCaretPosition(TabbedPane.outputTextArea.getText().length());
            TabbedPane.debugTextArea.setCaretPosition(TabbedPane.debugTextArea.getText().length());
        }
    }
    /* Build and Run Python */
    // Python
    public static void Python(String filePath, String dirPath, String fileName, String filePrefix, boolean hasBuild, boolean hasRun)
    {
        try
        {
            /* Set process */
            Process runProcess = Runtime.getRuntime().exec("python3 " + fileName, null, new File(dirPath));
            if (runProcess.waitFor() == 0)
                hasRun = true;
            /* Try to print */
            try (
                var runBuffer = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                var runErrorBuffer = new BufferedReader(new InputStreamReader(runProcess.getErrorStream())))
            {
                String buff = null;
                if (hasRun)
                {
                    Javar.outputArea.setSelectedIndex(0);
                    if (JavarConstants.LANG.equals("EN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runStartMessage);
                    else if (JavarConstants.LANG.equals("CN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runStartMessage_cn);
                    else
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runStartMessage);
                    // Print run information
                    while ((buff = runBuffer.readLine()) != null)
                    {
                        TabbedPane.outputTextArea.append(buff);
                        // NEW LINE
                        TabbedPane.outputTextArea.append("\n");
                    }
                    if (JavarConstants.LANG.equals("EN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage);
                    else if (JavarConstants.LANG.equals("CN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage_cn);
                    else
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage);
                }
                else
                {
                    Javar.outputArea.setSelectedIndex(1);
                    if (JavarConstants.LANG.equals("EN"))
                        TabbedPane.debugTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runErrorMessage);
                    else if (JavarConstants.LANG.equals("CN"))
                        TabbedPane.debugTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runErrorMessage_cn);
                    else
                        TabbedPane.debugTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runErrorMessage);
                    while ((buff = runErrorBuffer.readLine()) != null)
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
        finally 
        {
            TabbedPane.outputTextArea.setCaretPosition(TabbedPane.outputTextArea.getText().length());
            TabbedPane.debugTextArea.setCaretPosition(TabbedPane.debugTextArea.getText().length());
        }
    }
    /* Build and Run Java */
    // Java
    public static void Java(String filePath, String dirPath, String fileName, String filePrefix, boolean hasBuild, boolean hasRun)
    {
        try 
        {
            /* Set process */
            Process buildProcess = Runtime.getRuntime().exec("javac -encoding utf-8 -d . " + fileName, null, new File(dirPath));
            if (buildProcess.waitFor() == 0)
               hasBuild = true; 
            // Adjustment
<<<<<<< HEAD
            Process runProcess = null;
=======
            Process runProcess = Runtime.getRuntime().exec("cd .");
>>>>>>> a28bec5d6fdf8e3c14bc40f126ffb1890dbb11e2
            if (hasBuild)
            {
                runProcess = Runtime.getRuntime().exec("java " + filePrefix, null, new File(dirPath));
                if (runProcess.waitFor() == 0)
                    hasRun = true;
            }
            /* Try to print */
            try (
                var buildBuffer = new BufferedReader(new InputStreamReader(buildProcess.getInputStream()));
                var buildErrorBuffer = new BufferedReader(new InputStreamReader(buildProcess.getErrorStream()));
                var runBuffer = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                var runErrorBuffer = new BufferedReader(new InputStreamReader(runProcess.getErrorStream())))
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
                    if (JavarConstants.LANG.equals("EN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runStartMessage);
                    else if (JavarConstants.LANG.equals("CN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runStartMessage_cn);
                    else
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runStartMessage);
                    if (hasRun)
                    {
                        // print run information
                        while ((buff = runBuffer.readLine()) != null)
                        {
                            TabbedPane.outputTextArea.append(buff);
                            // new line
                            TabbedPane.outputTextArea.append("\n");
                        }
                    }
                    else
                    {
                        if (JavarConstants.LANG.equals("EN"))
                            TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runErrorMessage);
                        else if (JavarConstants.LANG.equals("CN"))
                            TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runErrorMessage_cn);
                        else
                            TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runErrorMessage);
                        // print run error information
                        while ((buff = runErrorBuffer.readLine()) != null)
                        {
                            TabbedPane.outputTextArea.append(buff);
                            // new line
                            TabbedPane.outputTextArea.append("\n");
                        }
                    }
                    if (JavarConstants.LANG.equals("EN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage);
                    else if (JavarConstants.LANG.equals("CN"))
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage_cn);
                    else
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage);
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
        finally 
        {
            TabbedPane.outputTextArea.setCaretPosition(TabbedPane.outputTextArea.getText().length());
            TabbedPane.debugTextArea.setCaretPosition(TabbedPane.debugTextArea.getText().length());
        }
    }
}
