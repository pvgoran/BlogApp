package sites.tag;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.IOException;
import java.util.*;

public class QuotedValueEncodeTag extends BodyTagSupport
{
    static private Map<Character, String> translations = makeTranslationTable();

    static private Map<Character, String> makeTranslationTable()
    {
        Map<Character, String> table = new HashMap<Character, String>();
        table.put(new Character('\"'), "&quot;");
        return table;
    }

    static public String getTranslation(char c)
    {
        return translations.get(c);
    }

    @Override
    public int doAfterBody() throws JspException
    {
        BodyContent body = getBodyContent();
        String orig = body.getString();
        body.clearBody();
        int length = orig.length();
        StringBuffer result = new StringBuffer(Math.round(length * 1.1f));
        for (int i = 0; i < length; ++i) {
            char c = orig.charAt(i);
            String translation = getTranslation(c);
            if (translation == null) {
                result.append(c);
            } else {
                result.append(translation);
            }
        }
        try {
            getPreviousOut().print(result.toString());
        } catch (IOException e) {
            throw new JspException("unexpected IO error");
        }
        return SKIP_BODY;
    }
}
