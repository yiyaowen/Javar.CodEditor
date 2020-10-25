package com.yiyaowen.javar;

import com.yiyaowen.javar.JavarConstants;

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
    private SimpleAttributeSet quotAttr;
    private String fontFamily;
    private Font font = new Font(fontFamily, Font.PLAIN, FONT_SIZE);
    private String syntaxFile;
    private String splitSymbol;
    private boolean hasSyntax;
    private int lastLine = 1;
    
    /////////////////
    // Constructor //
    /////////////////
    
    public CodePane(String syntaxFile, String splitSymbol, boolean hasSyntax)
    {
        // Set syntax file and split symbol
        this.syntaxFile = syntaxFile;
        this.splitSymbol = splitSymbol;
        this.hasSyntax = hasSyntax;
        formatter = new SyntaxFormatter(syntaxFile);
        normalAttr = formatter.getNormalAttributeSet();
        fontFamily = StyleConstants.getFontFamily(normalAttr);
        font = new Font(fontFamily, Font.PLAIN, FONT_SIZE);
        quotAttr = new SimpleAttributeSet();
        // Set basic attribute
        StyleConstants.setForeground(quotAttr, new Color(JavarConstants.quoteColorHex)); 
        StyleConstants.setFontSize(quotAttr, FONT_SIZE);
        StyleConstants.setFontFamily(quotAttr, FONT_FAMILY);
        // Set basic configuration
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
    }
    
    ////////////
    // Method //
    ////////////
    
    /**
     * Update highlight
     * 
     * @param tail (Equals "" when use normal update)
     * @return
     */
    public void syntaxParse(String tail)
    {
        try
        {
            // Initialization
            doc.setCharacterAttributes(0, doc.getLength(), normalAttr, false);
            // WARNING : Not perfect support
            int cursorPos = this.getCaretPosition();
            String content = doc.getText(0, doc.getLength());
            if (!tail.equals("\t") && !tail.equals("\b"))
                content = content.substring(0, cursorPos) + tail + content.substring(cursorPos, content.length());
            if (hasSyntax)
            {
                // Varies for different languages
                String[] tokens = content.split(splitSymbol);
                ArrayList<Integer> startList = new ArrayList<>();
                ArrayList<Integer> endList = new ArrayList<>();
                int curStart = 0;
                boolean singleQuot = false;
                boolean doubleQuot = false;
                for (var token : tokens)
                {
                    int tokenPos = content.indexOf(token, curStart);
                    if (token.startsWith("\"") && token.endsWith("\""))
                    {
                        if (token.length() == 1)
                        {
                            if (!singleQuot)
                            {
                                if (startList.size() > endList.size())
                                    endList.add(tokenPos+token.length());
                                else
                                    startList.add(tokenPos);
                                doubleQuot = !doubleQuot;
                            }
                        }
                        else
                        {
                            if (!singleQuot && !doubleQuot)
                            {
                                startList.add(tokenPos);
                                endList.add(tokenPos+token.length());
                            }
                        }
                    }
                    else if (token.startsWith("'") && token.endsWith("'"))
                    {
                        if (token.length() == 1)
                        {
                            if (!doubleQuot) 
                            {
                                if (startList.size() > endList.size())
                                    endList.add(tokenPos+token.length());
                                else
                                    startList.add(tokenPos);
                                singleQuot = !singleQuot;
                            }
                        }
                        else
                        {
                            if (!singleQuot && !doubleQuot)
                            {
                                startList.add(tokenPos);
                                endList.add(tokenPos+token.length());
                            }
                        }
                    }
                    else if (token.startsWith("\"") ^ token.endsWith("\""))
                    {
                        if (!singleQuot)
                        { 
                            doubleQuot = !doubleQuot;
                            if (startList.size() > endList.size())
                                endList.add(token.startsWith("\"") ? tokenPos : tokenPos+token.length());
                            else
                                startList.add(token.startsWith("\"") ? tokenPos : tokenPos+token.length());
                        }
                    } 
                    else if (token.startsWith("'") ^ token.endsWith("'"))
                    {
                        if (!doubleQuot) 
                        {
                            singleQuot = !singleQuot;
                            if (startList.size() > endList.size())
                                endList.add(token.startsWith("'") ? tokenPos : tokenPos+token.length());
                            else
                                startList.add(token.startsWith("'") ? tokenPos : tokenPos+token.length());
                        }
                    }
                    else
                    {
                        formatter.setHighLight(doc, token, tokenPos, token.length());
                    }
                    curStart = tokenPos + token.length();
                }
                while (startList.size() > endList.size())
                    endList.add(doc.getLength());
                if (startList.size() == endList.size())
                {
                    for (int i = 0; i < startList.size(); i++)
                    {
                        doc.setCharacterAttributes(startList.get(i), endList.get(i)-startList.get(i), quotAttr, false);
                    }
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
        Element root = doc.getDefaultRootElement();
        int line = root.getElementIndex(doc.getLength());
        g.setFont(font);
        char[] lineChars = String.valueOf(line+1).toCharArray();
        int charsWidth = g.getFontMetrics().charsWidth(lineChars, 0, lineChars.length);
        int charWidth = g.getFontMetrics().charWidth('0');
        g.setColor(new Color(JavarConstants.linebarColorHex));
        g.fillRect(0, 0, charsWidth+charWidth, getSize().height);
        g.setColor(new Color(JavarConstants.linenumColorHex));
        // Set text pane margin
        CodePane.this.setMargin(new Insets(JavarConstants.codePanePaddingTop, JavarConstants.codePanePaddingLeft+charsWidth+charWidth, JavarConstants.codePanePaddingBottom, JavarConstants.codePanePaddingRight));
        // Set text pane content
        if ((line/10) != (lastLine/10))
        {
            CodePane.this.setText(CodePane.this.getText());
            CodePane.this.syntaxParse("");
        }
        // Paint line number
        for (int count = 0, j = 1; count <= line; count++, j++)
        {
            g.drawString(String.valueOf(j), 3, count*(g.getFontMetrics().getHeight())+g.getFontMetrics().getAscent()+3); 
        }
        // Set last line
        lastLine = line+1;
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
	
    private Map<SimpleAttributeSet, ArrayList<String>> attMap = new HashMap<>();
    SimpleAttributeSet normalAttr = new SimpleAttributeSet();
    
    /////////////////
    // Constructor //
    /////////////////
    
    public SyntaxFormatter(String syntaxFile)
    {
        StyleConstants.setForeground(normalAttr, Color.BLACK);
        StyleConstants.setFontSize(normalAttr, CodePane.FONT_SIZE);
        StyleConstants.setFontFamily(normalAttr, CodePane.FONT_FAMILY);
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new File(syntaxFile));
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
                    var att = new SimpleAttributeSet();
                    StyleConstants.setForeground(att, new Color(color));
                    StyleConstants.setFontSize(att, CodePane.FONT_SIZE);
                    StyleConstants.setFontFamily(att, CodePane.FONT_FAMILY);
                    attMap.put(att, keywords);
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
            var att = new SimpleAttributeSet();
            StyleConstants.setForeground(att, new Color(color));
            StyleConstants.setFontSize(att, CodePane.FONT_SIZE);
            StyleConstants.setFontFamily(att, CodePane.FONT_FAMILY);
            attMap.put(att, keywords);
        }
    }
    
    ////////////
    // getter //
    ////////////
    
    public SimpleAttributeSet getNormalAttributeSet()
    {
        return normalAttr;
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
    public void setHighLight(StyledDocument doc, String token, int start, int length)
    {
        SimpleAttributeSet currentAttributeSet = null;
        if (token != null)
        {
            outer:
            for (var att : attMap.keySet())
            {
                ArrayList<String> keywords = attMap.get(att);
                for (var keyword : keywords)
                {
                    if (keyword.equals(token))
                    {
                        currentAttributeSet = att;
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
            doc.setCharacterAttributes(start, length, currentAttributeSet, false);
        }
        else
        {
            doc.setCharacterAttributes(start, length, normalAttr, false);
        }
    }
}
