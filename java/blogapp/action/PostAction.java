package blogapp.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blogapp.Action;

/**
 *
 * @author paul
 */
public class PostAction extends Action
{
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
