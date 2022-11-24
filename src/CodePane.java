package com.yiyaowen.javar;

import com.yiyaowen.javar.c_SyntaxParser;
import com.yiyaowen.javar.c_SyntaxParseInfo;
import com.yiyaowen.javar.JavarConstants;
import com.yiyaowen.javar.JavarUtils;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class CodePane extends JTextPane
{
    //////////////
    // Property //
    //////////////

    public static int FONT_SIZE = JavarConstants.defaultFontSize;
    public static String FONT_FAMILY = JavarConstants.defaultFontFamily;

    protected StyledDocument doc;
    protected SyntaxFormatter formatter;

    private SimpleAttributeSet normalAttr;
    private SimpleAttributeSet quoteAttr;
    private SimpleAttributeSet commentAttr;

    private String fontFamily;
    private Font font;

    private String syntaxFile;
    private String splitSymbol;
    private byte[] splitSymbols;
    private boolean hasSyntax;

    private int lastLine = 1;

    private c_SyntaxParseInfo info;

    /////////////////
    // Constructor //
    /////////////////

    public CodePane(String syntaxFile, String splitSymbol, boolean hasSyntax)
    {
        // Set syntax file and split symbol
        this.syntaxFile = syntaxFile;
        this.splitSymbol = splitSymbol;
        this.splitSymbols = new byte[splitSymbol.toCharArray().length];
        JavarUtils.convertCharArrayToByteArray(splitSymbol.toCharArray(), this.splitSymbols, -1);
        this.hasSyntax = hasSyntax;
        // Initialize syntax formatter
        formatter = new SyntaxFormatter(syntaxFile);
        normalAttr = formatter.getNormalAttributeSet();
        quoteAttr = formatter.getQuoteAttributeSet();
        commentAttr = formatter.getCommentAttributeSet();
        // Set font
        fontFamily = StyleConstants.getFontFamily(normalAttr);
        font = new Font(fontFamily, Font.PLAIN, FONT_SIZE);
        // Set basic configurations
        this.doc = super.getStyledDocument();
        this.setMargin(new Insets(JavarConstants.codePanePaddingTop, JavarConstants.codePanePaddingLeft, JavarConstants.codePanePaddingBottom, JavarConstants.codePanePaddingRight));
        this.setCharacterAttributes(normalAttr, false);
        this.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent ke)
            {
                // Parse syntax
                syntaxParse(String.valueOf(ke.getKeyChar()));
            }
        });
        // Initialize syntax parser information
        info = new c_SyntaxParseInfo(formatter.getKwTotalCount());
    }

    ////////////
    // Method //
    ////////////

    /**
     * Update highlight
     *
     * @param tail (Equals "" when update manually. What is it? Look at the WARNING below.)
     * @return
     */
    public void syntaxParse(String tail)
    {
        try
        {
            // Initialize character attributes
            doc.setCharacterAttributes(0, doc.getLength(), normalAttr, false);
            // WARNING : Not perfect support
            /*
             * The syntax parsing is processed in the listener of key-typed, but the document
             * of JTextPane does not update immediately, which means that the document does
             * not include the key typed just now.
             *
             * I guess it it because that JTextPane will update its document immediately after
             * its key-typed listener processed. However I do not find any useful or reliable
             * information about this problem.
             *
             * My solution is to pass an extral parameter to update the document manually. That
             * is 'tail'. So 'tail' is actually the string of the key-char just typed.
             *
             * If you know the reason or have more elegant solutions, please let me know.
             * Email: wenyiyao19@mails.ucas.ac.cn
             *
             * Thanks very much.
             */
            int cursorPos = this.getCaretPosition();
            String content = doc.getText(0, doc.getLength());
            // WARNING : Param tail is deprecated
            //if (!(tail.equals("\t") || tail.equals("\b")))
            //content = content.substring(0, cursorPos) + tail + content.substring(cursorPos, content.length());
            if (hasSyntax)
            {
                // Native interface
                c_SyntaxParser.fillSyntaxParseInfo(info, content, formatter.getKeywords(), formatter.getKwTotalCount(), splitSymbols, splitSymbols.length);
                /* DEBUG */
                //JavarUtils.DEBUG_OutputSyntaxParseInfo(info, formatter.getKeywords(), formatter.getKwTotalCount());
                // Color keywords
                for (int i = 0; i < formatter.getKwTotalCount(); ++i)
                {
                    for (int j = 0; j < info.aKeywordInfo[i].count; ++j)
                    {
                        formatter.setHighLight(doc, formatter.getKeywords()[i], info.aKeywordInfo[i].start[j], info.aKeywordInfo[i].end[j]);
                    }
                }
                // Color quote
                for (int i = 0; i < info.quoteInfo.count; ++i)
                {
                    doc.setCharacterAttributes(info.quoteInfo.start[i], info.quoteInfo.end[i]-info.quoteInfo.start[i]+1, quoteAttr, false);
                }
                // Color comment
                for (int i = 0; i < info.commentInfo.count; ++i)
                {
                    doc.setCharacterAttributes(info.commentInfo.start[i], info.commentInfo.end[i]-info.commentInfo.start[i]+1, commentAttr, false);
                }
            }
        }
        catch (Exception ex)
        {
            Javar.logger.log("e", ex.getMessage());
        }
    }

    /**
     * Override from JPanel
     *
     * @param
     * @return
     */
    public void paint(Graphics g)
    {
        super.paint(g);
        // Get document attributes
        Element root = doc.getDefaultRootElement();
        int line = root.getElementIndex(doc.getLength())+1;
        char[] lineChars = String.valueOf(line).toCharArray();
        int charsWidth = g.getFontMetrics().charsWidth(lineChars, 0, lineChars.length);
        int charWidth = g.getFontMetrics().charWidth('0');
        // Set graphics attributes
        g.setColor(new Color(JavarConstants.linebarColorHex));
        g.fillRect(0, 0, charsWidth+charWidth+JavarConstants.codePanePaddingLeft, getSize().height);
        g.setColor(new Color(JavarConstants.linenumColorHex));
        g.setFont(font);
        // Set default caret
        // TODO
        // Set text pane margin
        CodePane.this.setMargin(new Insets(JavarConstants.codePanePaddingTop, JavarConstants.codePanePaddingLeft+charsWidth+charWidth, JavarConstants.codePanePaddingBottom, JavarConstants.codePanePaddingRight));
        // Set text pane content
        if ((line/10) != (lastLine/10))
        {
            // Re-update document manually
            CodePane.this.setText(CodePane.this.getText());
            CodePane.this.syntaxParse("");
        }
        // Paint line number
        for (int count = 1; count <= line; count++)
        {
            g.drawString(String.valueOf(count), JavarConstants.codePanePaddingLeft, count*(g.getFontMetrics().getHeight())+JavarConstants.codePanePaddingTop);
        }
        // Set last line
        lastLine = line;
    }

    ////////////
    // getter //
    ////////////

    public String getSyntaxFile()
    {
        return syntaxFile;
    }
    public String getSplitSymbol()
    {
        return splitSymbol;
    }
    public boolean getHasSyntax()
    {
        return hasSyntax;
    }
}

class SyntaxFormatter
{
    //////////////
    // Property //
    //////////////

    private int kwTotalCount = 0;
    private ArrayList<String> kwTotal = new ArrayList<>();
    private Map<SimpleAttributeSet, ArrayList<String>> attrMap = new HashMap<>();
    // Special attributes
    SimpleAttributeSet normalAttr = new SimpleAttributeSet();
    SimpleAttributeSet quoteAttr = new SimpleAttributeSet();
    SimpleAttributeSet commentAttr = new SimpleAttributeSet();

    /////////////////
    // Constructor //
    /////////////////

    public SyntaxFormatter(String syntaxFile)
    {
        // Initialize special attributes
            // Normal attr
        StyleConstants.setForeground(normalAttr, Color.BLACK);
        StyleConstants.setFontSize(normalAttr, CodePane.FONT_SIZE);
        StyleConstants.setFontFamily(normalAttr, CodePane.FONT_FAMILY);
            // Quote attr
        StyleConstants.setForeground(quoteAttr, new Color(JavarConstants.quoteColorHex));
        StyleConstants.setFontSize(quoteAttr, CodePane.FONT_SIZE);
        StyleConstants.setFontFamily(quoteAttr, CodePane.FONT_FAMILY);
            // Comment attr
        StyleConstants.setForeground(commentAttr, new Color(JavarConstants.commentColorHex));
        StyleConstants.setFontSize(commentAttr, CodePane.FONT_SIZE);
        StyleConstants.setFontFamily(commentAttr, CodePane.FONT_FAMILY);
        // Parse syntax file
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new File("../configs/stx/" + syntaxFile));
            /*
             * Syntax file format:
             * &42AB33
             * keyword1
             * keyword2
             * keyword3
             * ...
             * &59C3EF
             * keyword1
             * keyword2
             * keyword3
             * ...
             */
        }
        catch (FileNotFoundException ex)
        {
        	Javar.logger.log("e", ex.getMessage());
        }
        var color = -1;
        ArrayList<String> keywords = new ArrayList<>();
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            if (line.startsWith("&"))
            {
                if (keywords.size() > 0 && color > -1)
                {
                    var attr = new SimpleAttributeSet();
                    StyleConstants.setForeground(attr, new Color(color));
                    StyleConstants.setFontSize(attr, CodePane.FONT_SIZE);
                    StyleConstants.setFontFamily(attr, CodePane.FONT_FAMILY);
                    // Update keywords and attributes information
                    kwTotalCount += keywords.size();
                    kwTotal.addAll(keywords);
                    attrMap.put(attr, keywords);
                }
                keywords = new ArrayList<>();
                color = Integer.parseInt(line.substring(1), 16);
            }
            else
            {
                if (line.trim().length() > 0)
                {
                    keywords.add(line.trim());
                }
            }
        }
        // DO NOT FORGET LAST PUT
        if (keywords.size() > 0 && color > -1)
        {
            var attr = new SimpleAttributeSet();
            StyleConstants.setForeground(attr, new Color(color));
            StyleConstants.setFontSize(attr, CodePane.FONT_SIZE);
            StyleConstants.setFontFamily(attr, CodePane.FONT_FAMILY);
            // Update keywords and attributes information
            kwTotalCount += keywords.size();
            kwTotal.addAll(keywords);
            attrMap.put(attr, keywords);
        }
    }

    ////////////
    // getter //
    ////////////

    public int getKwTotalCount()
    {
        return kwTotalCount;
    }
    public String[] getKeywords()
    {
        String[] keywords = new String[kwTotal.size()];
        kwTotal.toArray(keywords);
        return keywords;
    }

    public SimpleAttributeSet getNormalAttributeSet()
    {
        return normalAttr;
    }
    public SimpleAttributeSet getQuoteAttributeSet()
    {
        return quoteAttr;
    }
    public SimpleAttributeSet getCommentAttributeSet()
    {
        return commentAttr;
    }

    ////////////
    // Method //
    ////////////

    /**
     * Set specific document's highlight
     *
     * @param doc (Target document)
     * @param token (Key word)
     * @param start (Start position)
     * @param length (Key word length)
     * @return
     */
    public void setHighLight(StyledDocument doc, String token, int start, int end)
    {
        SimpleAttributeSet currentAttributeSet = null;
        if (token != null)
        {
            outer:
            for (var attr : attrMap.keySet())
            {
                ArrayList<String> keywords = attrMap.get(attr);
                for (var keyword : keywords)
                {
                    if (keyword.equals(token))
                    {
                        currentAttributeSet = attr;
                        break outer;
                    }
                }
            }
        }
        else
        {
            return;
        }
        if (currentAttributeSet != null)
        {
            doc.setCharacterAttributes(start, end-start+1, currentAttributeSet, false);
        }
        else
        {
            doc.setCharacterAttributes(start, end-start+1, normalAttr, false);
        }
    }
}
