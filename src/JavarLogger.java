package com.yiyaowen.javar;

import java.io.FileWriter;
import java.time.LocalDateTime;

public class JavarLogger
{
    ///////////////
    // Constants //
    ///////////////

    private static final int JavarLogger_OK = 0;
    private static final int JavarLogger_Failed = 1;

    private static final int JavarLogger_DefaultLevel = 2; // LogLevel_Debug
    private static final String JavarLogger_DefaultLogFile = "../Javar.log";

    private static final String JavarLogger_InfoPrefix = " (Info) ";
    private static final String JavarLogger_DebugPrefix = " <<Debug>> ";
    private static final String JavarLogger_ErrorPrefix = " <#>Error<#> ";
    private static final String JavarLogger_FatalPrefix = " @@@Fatal@@@ ";

    public static final int LogLevel_All = 0;
    public static final int LogLevel_Info = 1;
    public static final int LogLevel_Debug = 2;
    public static final int LogLevel_Error = 3;
    public static final int LogLevel_Fatal = 4;
    public static final int LogLevel_None = 100;

    public static final int LevelDesc_Short = 0;
    public static final int LevelDesc_Long = 1;

    //////////////
    // Property //
    //////////////

    private int level;
    private String logFile;

    /////////////////
    // Constructor //
    /////////////////

    public JavarLogger()
    {
        level = JavarLogger_DefaultLevel;
        logFile = JavarLogger_DefaultLogFile;
    }
    public JavarLogger(int level, String logFile)
    {
        this.level = level;
        this.logFile = logFile;
    }

    ////////////
    // setter //
    ////////////

    public void setLevel(int level)
    {
        this.level = level;
    }
    public void setLogFile(String logFile)
    {
        this.logFile = logFile;
    }

    ////////////
    // getter //
    ////////////

    public int getLevel()
    {
        return level;
    }
    public String getLogFile()
    {
        return logFile;
    }

    ////////////
    // Method //
    ////////////

    /**
     * Log specific type of message to current log file
     *
     * @param type (Message type)
     * @param msg (Message text)
     * @return
     */
    public void log(String type, String msg)
    {
        if (type == "i") {
            logInfo(msg, logFile);
        }
        else if (type == "d") {
            logDebug(msg, logFile);
        }
        else if (type == "e") {
            logError(msg, logFile);
        }
        else if (type == "f") {
            logFatal(msg, logFile);
        }
        else {
            logInfo(msg, logFile);
        }
    }

    /**
     * Get current log level's string description
     *
     * @param style (Description style)
     * @return levelDesc (Description of current log level)
     */
    public String getLevelDesc(int style)
    {
        if (level == LogLevel_All) {
            if (style == LevelDesc_Short) {
                return "a";
            }
            else {
                return "All";
            }
        }
        else if (level == LogLevel_Info) {
            if (style == LevelDesc_Short) {
                return "i";
            }
            else {
                return "Informationm";
            }
        }
        else if (level == LogLevel_Debug) {
            if (style == LevelDesc_Short) {
                return "d";
            }
            else {
                return "Debug";
            }
        }
        else if (level == LogLevel_Error) {
            if (style == LevelDesc_Short) {
                return "e";
            }
            else {
                return "Error";
            }
        }
        else if (level == LogLevel_Fatal) {
            if (style == LevelDesc_Short) {
                return "f";
            }
            else {
                return "Fatal error";
            }
        }
        else if (level == LogLevel_None) {
            if (style == LevelDesc_Short) {
                return "n";
            }
            else {
                return "None log";
            }
        }
        else {
            return "Unknow log level";
        }
    }

    /**
     * Log info message to specific log file
     *
     * @param msg (Info message)
     * @param logFile (Target log file)
     * @return logResult (Log result code)
     */
    private int logInfo(String msg, String logFile)
    {
        if (level > LogLevel_Info) {
            return JavarLogger_Failed;
        }
        try
        {
            FileWriter fw = new FileWriter(logFile, true);
            fw.write(LocalDateTime.now()+JavarLogger_InfoPrefix+msg+"\n");
            fw.close();
            return JavarLogger_OK;
        }
        catch (Exception ex)
        {
            return JavarLogger_Failed;
        }
    }

    /**
     * Log debug message to specific log file
     *
     * @param msg (Debug message)
     * @param logFile (Target log file)
     * @return logResult (Log result code)
     */
    private int logDebug(String msg, String logFile)
    {
        if (level > LogLevel_Debug) {
            return JavarLogger_Failed;
        }
        try
        {
            FileWriter fw = new FileWriter(logFile, true);
            fw.write(LocalDateTime.now()+JavarLogger_DebugPrefix+msg+"\n");
            fw.close();
            return JavarLogger_OK;
        }
        catch (Exception ex)
        {
            return JavarLogger_Failed;
        }
    }

    /**
     * Log error message to specific log file
     *
     * @param msg (Error message)
     * @param logFile (Target log file)
     * @return logResult (Log result code)
     */
    private int logError(String msg, String logFile)
    {
        if (level > LogLevel_Error) {
            return JavarLogger_Failed;
        }
        try
        {
            FileWriter fw = new FileWriter(logFile, true);
            fw.write(LocalDateTime.now()+JavarLogger_ErrorPrefix+msg+"\n");
            fw.close();
            return JavarLogger_OK;
        }
        catch (Exception ex)
        {
            return JavarLogger_Failed;
        }
    }

    /**
     * Log fatal message to specific log file
     *
     * @param msg (Fatal message)
     * @param logFile (Target log file)
     * @return logResult (Log result code)
     */
    private int logFatal(String msg, String logFile)
    {
        if (level > LogLevel_Fatal) {
            return JavarLogger_Failed;
        }
        try
        {
            FileWriter fw = new FileWriter(logFile, true);
            fw.write(LocalDateTime.now()+JavarLogger_FatalPrefix+msg+"\n");
            fw.close();
            return JavarLogger_OK;
        }
        catch (Exception ex)
        {
            return JavarLogger_Failed;
        }
    }
}
