package sites.tag;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.IOException;

import org.json.simple.JSONValue;

public class JsonStringEncodeTag extends BodyTagSupport
{
    @Override
    public int doAfterBody() throws JspException
    {
        String originalString = getBodyContent().getString();

        try {
            getPreviousOut().print(JSONValue.escape(originalString));
        } catch (IOException ex) {
            throw new JspException("Unexpected IO error", ex);
        }

        return SKIP_BODY;
    }
}
