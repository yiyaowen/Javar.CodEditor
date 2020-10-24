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
    }

    ////////////
    // Method //
    ////////////

    /**
     * Update translator according to the specific language
     * 
     * @param lang (The language of the target translator)
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
     * Translate the specific text to current language preference
     *
     * @param text (The text to be translated)
     * @return trans_text (The text translated)
     */
    public static String translate(String text)
    {
        if (translator == default_translator) {
            return text;
        }
        else {
            String trans_text = translator.getProperty(text);
            if (trans_text == null) {
                return text;
            }
            else {
                return translator.getProperty(text);
            }
        }
    }
}
