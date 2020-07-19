package javar.utils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

public class JavarUtils
{
    /* UI Image and Icon */
    // Resize by width and height
    public static Image resizeImageToWH(Image img, int width, int height, int type)
    {
        try
        {
            return img.getScaledInstance(width, height, type);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return img;
    }
    // Resize by component
    public static Image resizeImageToFitBoth(Component com, Image img, int type)
    {
        try
        {
            return img.getScaledInstance(com.getWidth(), com.getHeight(), type);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage()); 
        }
        return img;
    }
    public static Image resizeImageToFitWidth(Component com, Image img, int type)
    {
        try
        {
            return img.getScaledInstance(com.getWidth(), -1, type);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage()); 
        }
        return img;
    }
    public static Image resizeImageToFitHeight(Component com, Image img, int type)
    {
        try
        {
            return img.getScaledInstance(-1, com.getHeight(), type);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage()); 
        }
        return img;
    }
    public static Image resizeImageToFitBothWithPadding(Component com, Image img, int type, int padding)
    {
        try
        {
            return img.getScaledInstance(com.getWidth()-2*padding, com.getHeight()-2*padding, type);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage()); 
        }
        return img;
    }
    public static Image resizeImageToFitWidthWithPadding(Component com, Image img, int type, int padding)
    {
        try
        {
            return img.getScaledInstance(com.getWidth()-2*padding, -1, type);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage()); 
        }
        return img;
    }
    public static Image resizeImageToFitHeightWithPadding(Component com, Image img, int type, int padding)
    {
        try
        {
            return img.getScaledInstance(-1, com.getHeight()-2*padding, type);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage()); 
        }
        return img;
    }
}
