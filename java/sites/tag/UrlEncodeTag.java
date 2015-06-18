package sites.tag;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.IOException;

public class UrlEncodeTag extends BodyTagSupport
{
    @Override
    public int doAfterBody() throws JspException
    {
        BodyContent body = getBodyContent();
        String str = body.getString().trim();
        body.clearBody();

        try {
            getPreviousOut().print(java.net.URLEncoder.encode(str, "UTF-8"));
        } catch (IOException e) {
            throw new JspException("unexpected IO error");
        }
        return SKIP_BODY;
    }
}
