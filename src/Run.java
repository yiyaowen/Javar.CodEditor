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

public class Run
{
    /**
     * Try to render *.html files
     *
     * @param filePath (Target *.html file path)
     * @param dirPath (Target *.html file directory path)
     * @param fileName (Target *.html file name)
     * @param filePrefix (Target *.html file pure name without .html)
     * @param hasRun (If run successfully equals true, otherwise false)
     * @return
     */
    public static void Html(String filePath, String dirPath, String fileName, String filePrefix, boolean hasRun)
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
     * Try to run *.exe files
     *
     * @param dirPath (Target *.exe file directory path)
     * @param fileName (Target *.exe file name)
     * @param filePrefix (Target *.exe file pure name without .exe)
     * @param hasRun (If run successfully equals true, otherwise false)
     * @return
     */
    public static void Cpp(String dirPath, String fileName, String filePrefix, boolean hasRun)
    {
        TryRun(dirPath+JavarConstants.pathDelimiter+filePrefix, dirPath, hasRun);
    }

    /**
     * Try to run *.exe files
     *
     * @param dirPath (Target *.exe file directory path)
     * @param fileName (Target *.exe file name)
     * @param filePrefix (Target *.exe file pure name without .exe)
     * @param hasRun (If run successfully equals true, otherwise false)
     * @return
     */
    public static void C(String dirPath, String fileName, String filePrefix, boolean hasRun)
    {
        TryRun(dirPath+JavarConstants.pathDelimiter+filePrefix, dirPath, hasRun);
    }

    /**
     * Try to run the specific run command
     *
     * @param cmd (The run command)
     * @param workDir (The working directory path)
     * @param hasRun (If run successfully equals true, otherwise false)
     * @return
     */
    protected static void TryRun(String cmd, String workDir, boolean hasRun)
    {
        try
        {
            // Set process
            Process runProcess = Runtime.getRuntime().exec(cmd, null, new File(workDir));
            if (runProcess.waitFor() == 0)
                hasRun = true;
            // Try to print
            try (
                var runBuffer = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                var runErrorBuffer = new BufferedReader(new InputStreamReader(runProcess.getErrorStream())))
            {
                String buff = null;
                Javar.outputArea.setSelectedIndex(0);
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
    /* Run Python */
    // Python
    public static void Python(String filePath, String dirPath, String fileName, String filePrefix, boolean hasRun)
    {
        try
        {
			Process runProcess = Runtime.getRuntime().exec("python3 " + fileName, null, new File(dirPath));
			if (runProcess.waitFor() == 0)
				hasRun = true;
			if (hasRun)
			{
				try (
					var runBuffer = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
					var runErrorBuffer = new BufferedReader(new InputStreamReader(runProcess.getErrorStream())))
				{
					String buff = null;
					Javar.outputArea.setSelectedIndex(0);
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
				catch (Exception outputException)
				{
					// Redirect to python
					//outputException.printStackTrace();
				}
			}
			else
			{
				runProcess = Runtime.getRuntime().exec("python " + fileName, null, new File(dirPath));
				if (runProcess.waitFor() == 0)
					hasRun = true;
				try (
					var runBuffer = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
					var runErrorBuffer = new BufferedReader(new InputStreamReader(runProcess.getErrorStream())))
				{
					String buff = null;
					Javar.outputArea.setSelectedIndex(0);
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
				catch (Exception outputException)
				{
					outputException.printStackTrace();
				}
			}
		}
		catch (Exception processException)
		{
			processException.printStackTrace();
		}
    }
    /* Run Java */
    // Java
    public static void Java(String filePath, String dirPath, String fileName, String filePrefix, boolean hasRun)
    {
        try
        {
            Process runProcess = Runtime.getRuntime().exec("java " + filePrefix, null, new File(dirPath));
            if (runProcess.waitFor() == 0)
                hasRun = true;
            try (
                var runBuffer = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                var runErrorBuffer = new BufferedReader(new InputStreamReader(runProcess.getErrorStream())))
            {
                String buff = null;
                Javar.outputArea.setSelectedIndex(0);
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
