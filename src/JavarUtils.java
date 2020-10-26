package com.yiyaowen.javar;

import com.yiyaowen.javar.JavarConstants;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.text.*;
import java.time.LocalDateTime;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings(value = "unchecked")
public class JavarUtils
{
    ////////////
    // Method //
    ////////////

    ///////////////////////
    /* Global properties */
    ///////////////////////
  
    /**
     * Load global properties from application properties file
     *
     * @param
     * @return
     */
    public static void loadGlobalProperties()
    {
        Properties globalProps = new Properties();
        try (
            InputStream is = new FileInputStream(new File("../configs/properties/Javar.properties")))
        {
            globalProps.load(is);
            JavarConstants.LANG = globalProps.getProperty("LANG");
            JavarConstants.defaultFontSize = Integer.parseInt(globalProps.getProperty("defaultFontSize"));
            JavarConstants.defaultFontFamily = globalProps.getProperty("defaultFontFamily");
            JavarConstants.defaultLAF = globalProps.getProperty("defaultLAF");
        }
        catch (Exception ex)
        {
            Javar.logger.log("f", ex.getMessage());
        }
    }

    /**
     * Save global properties to application properties file
     *
     * @param
     * @return
     */
    public static void saveGlobalProperties()
    {
        Properties globalProps = new Properties();
        try (
            InputStream is = new FileInputStream(new File("../configs/properties/Javar.properties"));
            OutputStream os = new FileOutputStream(new File("../configs/properties/Javar.properties")))
        {
            globalProps.load(is);
            globalProps.setProperty("LANG", JavarConstants.LANG);
            globalProps.setProperty("defaultFontSize", String.valueOf(JavarConstants.defaultFontSize));
            globalProps.setProperty("defaultFontFamily", JavarConstants.defaultFontFamily);
            globalProps.setProperty("defaultLAF", JavarConstants.defaultLAF);
            globalProps.store(os, "Last update time "+LocalDateTime.now());
        }
        catch (Exception ex)
        {
            Javar.logger.log("f", ex.getMessage());
        }
    }
    
    //////////////////
    /* File handler */
    //////////////////
    
    /**
     * Unpack file path to properties map
     * 
     * @param filePath (File path to unpack)
     * @return fileMap (Properties map, including file path, directory path, file name, file prefix and file suffix)
     */
    public static HashMap<String,String> unpackFilePath(String filePath)
    {   
        HashMap<String,String> map = new HashMap<>();
        String dirPath = filePath.substring(0, filePath.lastIndexOf(JavarConstants.pathDelimiter)+1);
        String fileName = filePath.substring(filePath.lastIndexOf(JavarConstants.pathDelimiter)+1);
        String filePrefix = fileName.substring(0, fileName.lastIndexOf("."));
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".")+1);
        map.put("filePath", filePath);
        map.put("dirPath", dirPath);
        map.put("fileName", fileName);
        map.put("filePrefix", filePrefix);
        map.put("fileSuffix", fileSuffix);
        return map;
    }
    
    /**
     * Get specific file's attributes map
     * 
     * @param file (Target file)
     * @return fileAttrMap (Attributes map, including 
     */
    public static HashMap<String,String> getFileAttributes(File file)
    {
        HashMap<String,String> map = new HashMap<>();
        try
        {
            Path path = Paths.get(file.getPath());
            BasicFileAttributeView basicView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
            BasicFileAttributes basicAttributes = basicView.readAttributes();
            String createdDate = (new Date(basicAttributes.creationTime().toMillis())).toString();
            String lastModifiedDate = (new Date(basicAttributes.lastModifiedTime().toMillis()).toString());
            double byteSize = (double) basicAttributes.size();
            double kByteSize = (int)(byteSize * 10 / 1024) / 10;
            double mByteSize = (int)(kByteSize * 10 / 1024) / 10;
            String fileSize = kByteSize >= 1 ? (mByteSize >= 1 ? mByteSize+"MB" : kByteSize+"KB") : byteSize+"B";
            map.put("createdDate", createdDate);
            map.put("lastModifiedDate", lastModifiedDate);
            map.put("fileSize", fileSize);
        }
        catch (Exception ex)
        {
            map.put("createdDate", "Null");
            map.put("lastModifiedDate", "Null");
            map.put("fileSize", "Null");
        }
        return map;
    }

    ///////////////////
    /* Date and time */
    ///////////////////

    /**
     * Get current time in MEDIUM format
     *
     * @param
     * @return currentTime (A curren time string in MEDIUM format)
     */
    public static String getCurrentTimeMEDIUM()
    {
        return ((DateFormat.getTimeInstance(DateFormat.MEDIUM, JavarConstants.LOCALE)).format(new Date())).toString();
    }

    /**
     * Get current time in MEDIUM format enclosed by the specific border symbols
     *
     * @param borderLeft (Left border symbol)
     * @param borderRight (Right border symbol)
     * @return currentTime (A current time string in MEDIUM format enclosed by the specific border symbols)
     */
    public static String getCurrentTimeWithBorderMEDIUM(String borderLeft, String borderRight)
    {
        return borderLeft + getCurrentTimeMEDIUM() + borderRight;
    }

    ///////////////////////
    /* UI image and icon */
    ///////////////////////

    /**
     * Resize image to the specific width and height with zoom type
     *
     * @param img (The image to be resized)
     * @param width (Image width after resizing)
     * @param height (Image height after resizing)
     * @param type (Zoom type)
     * @return resizedImage (If success return image resized, otherwise return original image)
     */
    public static Image resizeImageToWH(Image img, int width, int height, int type)
    {
        try
        {
            return img.getScaledInstance(width, height, type);
        }
        catch (Exception ex)
        {
            Javar.logger.log("d", ex.getMessage());
        }
        return img;
    }

    /**
     * Resize image to fit the specific component with zoom type
     *
     * @param com (The target component)
     * @param img (The image to be resized)
     * @param type (Zoom type)
     * @return resizedImage (If success return image resized, otherwise return original image)
     */
    public static Image resizeImageToFitBoth(Component com, Image img, int type)
    {
        try
        {
            return img.getScaledInstance(com.getWidth(), com.getHeight(), type);
        }
        catch (Exception ex)
        {
            Javar.logger.log("d", ex.getMessage());
        }
        return img;
    }

    /**
     * Resize image to fit the specific component's width with zoom type
     *
     * @param com (The target component)
     * @param img (The image to be resized)
     * @param type (Zoom type)
     * @return resizedImage (If success return image resized, otherwise return original image)
     */
    public static Image resizeImageToFitWidth(Component com, Image img, int type)
    {
        try
        {
            return img.getScaledInstance(com.getWidth(), -1, type);
        }
        catch (Exception ex)
        {
            Javar.logger.log("d", ex.getMessage());
        }
        return img;
    }

    /**
     * Resize image to fit the specific component's height with zoom type
     *
     * @param com (The target component)
     * @param img (The image to be resized)
     * @param type (Zoom type)
     * @return resizedImage (If success return image resized, otherwise return original image)
     */
    public static Image resizeImageToFitHeight(Component com, Image img, int type)
    {
        try
        {
            return img.getScaledInstance(-1, com.getHeight(), type);
        }
        catch (Exception ex)
        {
            Javar.logger.log("d", ex.getMessage());
        }
        return img;
    }

    /**
     * Resize image to fit the specific component with zoom type and padding
     *
     * @param com (The target component)
     * @param img (The image to be resized)
     * @param type (Zoom type)
     * @param padding (Distance between image and component)
     * @return resizedImage (If success return image resized, otherwise return original image)
     */
    public static Image resizeImageToFitBothWithPadding(Component com, Image img, int type, int padding)
    {
        try
        {
            return img.getScaledInstance(com.getWidth()-2*padding, com.getHeight()-2*padding, type);
        }
        catch (Exception ex)
        {
            Javar.logger.log("d", ex.getMessage());
        }
        return img;
    }

    /**
     * Resize image to fit the specific component's width with zoom type and padding
     *
     * @param com (The target component)
     * @param img (The image to be resized)
     * @param type (Zoom type)
     * @param padding (Distance between image and component)
     * @return resizedImage (If success return image resized, otherwise return original image)
     */
    public static Image resizeImageToFitWidthWithPadding(Component com, Image img, int type, int padding)
    {
        try
        {
            return img.getScaledInstance(com.getWidth()-2*padding, -1, type);
        }
        catch (Exception ex)
        {
            Javar.logger.log("d", ex.getMessage());
        }
        return img;
    }

    /**
     * Resize image to fit the specific component's height with zoom type and padding
     *
     * @param com (The target component)
     * @param img (The image to be resized)
     * @param type (Zoom type)
     * @param padding (Distance between image and component)
     * @return resizedImage (If success return image resized, otherwise return original image)
     */
    public static Image resizeImageToFitHeightWithPadding(Component com, Image img, int type, int padding)
    {
        try
        {
            return img.getScaledInstance(-1, com.getHeight()-2*padding, type);
        }
        catch (Exception ex)
        {
            Javar.logger.log("d", ex.getMessage());
        }
        return img;
    }
}
