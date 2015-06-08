package blogapp.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blogapp.Action;
import blogapp.BlogAppContext;

/**
 *
 * @author paul
 */
public class PostAction extends Action
{
    public PostAction(BlogAppContext appContext)
    {
        super(appContext);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setContentType("text/plain");

        String enteredText = req.getParameter("enteredText");
        
        if (enteredText.equals(""))
        {
            res.getWriter().print("You entered nothing!");
            return;
        }

        res.getWriter().print("You entered: " + enteredText);
    }

}
