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
            Process buildProcess = Runtime.getRuntime().exec("g++ -o " + filePrefix + " " + fileName, null, new File(dirPath));
            if (buildProcess.waitFor() == 0)
               hasBuild = true; 
            // Adjustment
            Process runProcess = Runtime.getRuntime().exec("cd .");
            if (hasBuild)
            {
                runProcess = Runtime.getRuntime().exec("./" + filePrefix, null, new File(dirPath));
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
                    TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.buildMessage);
                    // Print build information
                    while ((buff = buildBuffer.readLine()) != null)
                    {
                        TabbedPane.outputTextArea.append(buff);
                        // NEW LINE
                        TabbedPane.outputTextArea.append("\n");
                    }
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
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runErrorMessage);
                        // print run error information
                        while ((buff = runErrorBuffer.readLine()) != null)
                        {
                            TabbedPane.outputTextArea.append(buff);
                            // new line
                            TabbedPane.outputTextArea.append("\n");
                        }
                    }
                    TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage);
                }
                else
                {
                    Javar.outputArea.setSelectedIndex(1);
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
            Process buildProcess = Runtime.getRuntime().exec("gcc -o " + filePrefix + " " + fileName, null, new File(dirPath));
            if (buildProcess.waitFor() == 0)
               hasBuild = true; 
            // Adjustment
            Process runProcess = Runtime.getRuntime().exec("cd .");
            if (hasBuild)
            {
                runProcess = Runtime.getRuntime().exec("./" + filePrefix, null, new File(dirPath));
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
                    TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.buildMessage);
                    // Print build information
                    while ((buff = buildBuffer.readLine()) != null)
                    {
                        TabbedPane.outputTextArea.append(buff);
                        // NEW LINE
                        TabbedPane.outputTextArea.append("\n");
                    }
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
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runErrorMessage);
                        // print run error information
                        while ((buff = runErrorBuffer.readLine()) != null)
                        {
                            TabbedPane.outputTextArea.append(buff);
                            // new line
                            TabbedPane.outputTextArea.append("\n");
                        }
                    }
                    TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage);
                }
                else
                {
                    Javar.outputArea.setSelectedIndex(1);
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
                    TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runStartMessage);
                    // Print run information
                    while ((buff = runBuffer.readLine()) != null)
                    {
                        TabbedPane.outputTextArea.append(buff);
                        // NEW LINE
                        TabbedPane.outputTextArea.append("\n");
                    }
                    TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage);
                }
                else
                {
                    Javar.outputArea.setSelectedIndex(1);
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
            Process buildProcess = Runtime.getRuntime().exec("javac " + fileName, null, new File(dirPath));
            if (buildProcess.waitFor() == 0)
               hasBuild = true; 
            // Adjustment
            Process runProcess = Runtime.getRuntime().exec("cd .");
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
                    TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.buildMessage);
                    // Print build information
                    while ((buff = buildBuffer.readLine()) != null)
                    {
                        TabbedPane.outputTextArea.append(buff);
                        // NEW LINE
                        TabbedPane.outputTextArea.append("\n");
                    }
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
                        TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runErrorMessage);
                        // print run error information
                        while ((buff = runErrorBuffer.readLine()) != null)
                        {
                            TabbedPane.outputTextArea.append(buff);
                            // new line
                            TabbedPane.outputTextArea.append("\n");
                        }
                    }
                    TabbedPane.outputTextArea.append(JavarUtils.getCurrentTimeWithBorderMEDIUM("[", "]") + JavarConstants.runOverMessage);
                }
                else
                {
                    Javar.outputArea.setSelectedIndex(1);
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
