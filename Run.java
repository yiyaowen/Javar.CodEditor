package javar.run;

import javar.Javar;
import javar.upperbar.UpperBar;
import javar.tabbedpane.TabbedPane;
import javar.constants.JavarConstants;
import javar.utils.JavarUtils;

import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.nio.channels.*;

public class Run
{
    /* Run html */
    // html 
    public static void Html(String filePath, String dirPath, String fileName, String filePrefix, boolean hasBuild)
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
    /* Run C++ */
    // C++
    public static void Cpp(String filePath, String dirPath, String fileName, String filePrefix, boolean hasRun)
    {
        try
        {
            Process runProcess = Runtime.getRuntime().exec(dirPath + JavarConstants.pathDelimiter + filePrefix, null, new File(dirPath));
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
    /* Run C */
    // C
    public static void C(String filePath, String dirPath, String fileName, String filePrefix, boolean hasRun)
    {
        try
        {
            Process runProcess = Runtime.getRuntime().exec(dirPath + JavarConstants.pathDelimiter + filePrefix, null, new File(dirPath));
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
