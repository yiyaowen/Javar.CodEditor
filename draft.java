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
        doc.setCharacterAttributes(startList.get(i), endList.get(i)-startList.get(i), quoteAttr, false);
    }
}
}
}
catch (Exception ex)
{
Javar.logger.log("e", ex.getMessage());
}
