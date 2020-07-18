package javar.codepane;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.util.*;
import java.io.*;

public class CodePane extends JTextPane
{
    protected StyledDocument doc;
    protected SyntaxFormatter formatter = new SyntaxFormatter("Java.stx");
    private SimpleAttributeSet normalAttr = formatter.getNormalAttributeSet();
    private SimpleAttributeSet quotAttr = formatter.getNormalAttributeSet();
    private int docChangeStart = 0;
    private int docChangeLength = 0;
    public CodePane()
    {
        StyleConstants.setForeground(quotAttr, new Color(255,0,255));
        StyleConstants.setFontSize(quotAttr, 16);
        this.doc = super.getStyledDocument();
        this.setMargin(new Insets(3, 40, 0, 0));
        this.addKeyListener(new KeyAdapter() 
        {
            public void keyReleased(KeyEvent ke)
            {
                syntaxParse();
            }
        });
        doc.addDocumentListener(new DocumentListener()
        {
            public void changedUpdate(DocumentEvent de) {}
            public void insertUpdate(DocumentEvent de)
            {
                docChangeStart = de.getOffset();
                docChangeLength = de.getLength();
            }
            public void removeUpdate(DocumentEvent de) {}
        });
    }
    public void syntaxParse()
    {
        try
        {
            Element root = doc.getDefaultRootElement();
            int cursorPos = this.getCaretPosition();
            int line = root.getElementIndex(cursorPos);
            Element para = root.getElement(line);
            int start = para.getStartOffset();
            start = start > docChangeStart ? docChangeStart : start;
            int length = para.getEndOffset() - start;
            length = length < docChangeLength ? docChangeLength+1 : length;
            String s = doc.getText(start, length);
            String[] tokens = s.split("\\s+|\\.|\\(|\\)|\\{|\\}|\\[|\\]");
            int curStart = 0;
            boolean isQuot = false;
            for (var token : tokens)
            {
                int tokenPos = s.indexOf(token, curStart);
                if (isQuot && (token.endsWith("\"") || token.endsWith("\'")))
                {
                    doc.setCharacterAttributes(start + tokenPos, token.length(), quotAttr, false);
                    isQuot = false;
                }
                else if (isQuot && !(token.endsWith("\"") || token.endsWith("\'")))
                {
                    doc.setCharacterAttributes(start + tokenPos, token.length(), quotAttr, false);
                }
                else if ((token.startsWith("\"") || token.startsWith("\'")) && (token.endsWith("\"") || token.endsWith("\'")))
                {
                    doc.setCharacterAttributes(start + tokenPos, token.length(), quotAttr, false);
                }
                else if ((token.startsWith("\"") || token.startsWith("\'")) && !(token.endsWith("\"") || token.endsWith("\'")))
                {
                    doc.setCharacterAttributes(start + tokenPos, token.length(), quotAttr, false);
                    isQuot = false;
                }
                else
                {   
                    formatter.setHighLight(doc, token, start + tokenPos, token.length());
                }
                curStart = tokenPos + token.length();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void paint(Graphics g)
    {
        super.paint(g);
        Element root = doc.getDefaultRootElement();
        int line = root.getElementIndex(doc.getLength());
        g.setColor(new Color(230, 230, 230));
        g.fillRect(0, 0, this.getMargin().left-10, getSize().height);
        g.setColor(new Color(40, 40, 40));
        for (int count = 0, j = 1; count <= line; count++, j++)
        {
            g.drawString(String.valueOf(j), 3, (int)((count+1)*StyleConstants.getFontSize(normalAttr)*1.535));
        }
    }
}

class SyntaxFormatter
{
    private Map<SimpleAttributeSet, ArrayList<String>> attMap = new HashMap<>();
    SimpleAttributeSet normalAttr = new SimpleAttributeSet();
    public SyntaxFormatter(String syntaxFile)
    {
        StyleConstants.setForeground(normalAttr, Color.BLACK);
        StyleConstants.setFontSize(normalAttr, 16);
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new File(syntaxFile));
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException("语法文件丢失：" + e.getMessage());
        }
        var color = -1;
        ArrayList<String> keywords = new ArrayList<>();
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            if (line.startsWith("#"))
            {
                if (keywords.size() > 0 && color > -1)
                {
                    var att = new SimpleAttributeSet();
                    StyleConstants.setForeground(att, new Color(color));
                    StyleConstants.setFontSize(att, 16);
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
            StyleConstants.setFontSize(att, 16);
            attMap.put(att, keywords);
        }
    }
    public SimpleAttributeSet getNormalAttributeSet()
    {
        return normalAttr;
    }
    public void setHighLight(StyledDocument doc, String token, int start, int length)
    {
        SimpleAttributeSet currentAttributeSet = null;
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
