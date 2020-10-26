package com.yiyaowen.javar;

import com.yiyaowen.javar.Build;
import com.yiyaowen.javar.Javar;
import com.yiyaowen.javar.JavarConstants;
import com.yiyaowen.javar.JavarUtils;
import com.yiyaowen.javar.Run;
import com.yiyaowen.javar.TabbedPane;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class BuildAndRun
{
    ////////////
    // Method //
    ////////////

    /**
     * Redirect to Run.Html(...)
     *
     * @param
     * @return
     */
    public static void Html(String filePath)
    {
        Run.Html(filePath);
    }
    
    /**
     * Try to build and run C++ project
     * 
     * @param filePath (Target *.cpp file path)
     * @return hasBuiltAndRun (If build and run successfully return true, otherwise return false)
     */
    public static boolean Cpp(String filePath)
    {
    	boolean hasBuilt = false;
    	boolean hasRun = false;
        hasBuilt = Build.Cpp(filePath);
        if (hasBuilt)
        {
        	hasRun = Run.Exe(filePath);
        }
        return hasBuilt && hasRun;
    }
    
    /**
     * Try to build and run C project
     * 
     * @param filePath (Target *.c file path)
     * @return hasBuiltAndRun (If build and run successfully return true, otherwise return false)
     */
    public static boolean C(String filePath)
    {
    	boolean hasBuilt = false;
    	boolean hasRun = false;
        hasBuilt = Build.C(filePath);
        if (hasBuilt)
        {
        	hasRun = Run.Exe(filePath);
        }
        return hasBuilt && hasRun;
    }
    
    /**
     * Redirect to Run.Python(...)
     * 
     * @param
     * @return
     */
    public static boolean Python(String filePath)
    {
        return Run.Python(filePath);
    }
    
    /**
     * Try to build and run Java project
     * 
     * @param filePath (Target *.java file path)
     * @return hasBuiltAndRun (If build and run successfully return true, otherwise return false)
     */
    public static boolean Java(String filePath)
    {
    	boolean hasBuilt = false;
    	boolean hasRun = false;
        hasBuilt = Build.Java(filePath);
        if (hasBuilt)
        {
        	hasRun = Run.Java(filePath);
        }
        return hasBuilt && hasRun;
    }
}
