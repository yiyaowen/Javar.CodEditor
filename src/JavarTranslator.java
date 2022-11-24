package com.yiyaowen.javar;

import com.yiyaowen.javar.JavarConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class JavarTranslator
{
    //////////////
    // Property //
    //////////////

    public static Properties translator;
    public static Properties default_translator;
    private static Properties chinese_translator;
    static
    {
        default_translator = new Properties();
        chinese_translator = new Properties();
        try (
            InputStream chinese_is = new FileInputStream(new File("../configs/trans/Javar_zh_CN.trans")))
        {
            chinese_translator.load(chinese_is);
        }
        catch (Exception ex)
        {
            Javar.logger.log("e", ex.getMessage());
        }
        // Set as default translator
        translator = default_translator;
    }

    ////////////
    // Method //
    ////////////

    /**
     * Update translator according to specific language
     *
     * @param lang (Language of the target translator)
     * @return
     */
    public static void UpdateTranslator(String lang)
    {
        if (lang == "zh_CN") {
            translator = chinese_translator;
        }
        else {
            translator = default_translator;
        }
    }

    /**
     * Translate specific text to current language preference
     *
     * @param prefix (A short description to differentiate text with the same content)
     * @param text (Text to be translated)
     * @return trans_text (Text translated)
     */
    public static String translate(String prefix, String text)
    {
        if (translator == default_translator) {
            return text;
        }
        else {
            String trans_text = translator.getProperty(prefix+"@"+text);
            /*
             * Translation file format:
             * prefix1@text1
             * prefix1@text2
             * prefix1@text3
             * ...
             * prefix2@text1
             * prefix2@text2
             * prefix2@text3
             * ...
             */
            if (trans_text == null) {
                return text;
            }
            else {
                return trans_text;
            }
        }
    }
}
