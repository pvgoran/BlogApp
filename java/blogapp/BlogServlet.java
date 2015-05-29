package blogapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pvgoran.dataaccess.DataException;

import blogapp.action.PostAction;
import blogapp.action.TestAction;

/**
 *
 * @author paul
 */
public class BlogServlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        BlogAppContext appContext = createAppContext(req, res);

        Action action = selectAction(req.getParameter("action"));
        action.setAppContext(appContext);

        try {
            action.process(req, res);
        } catch (SQLException | DataException ex) {
            throw new RuntimeException(ex);
        }
    }

    private BlogAppContext createAppContext(HttpServletRequest req, HttpServletResponse res)
    {
        BlogAppConfiguration configuration = BlogAppConfiguration.getConfiguration();
        String username = "dummy";
        
        BlogAppContext appContext = new BlogAppContext();
        appContext.configuration = configuration;
        appContext.dataSource = configuration.dataSource;
        appContext.db = new BlogAppDatabase(configuration.dataSource);
        appContext.username = username;

        return appContext;
    }

    private Action selectAction(String actionName)
    {
        if (actionName == null)
        {
            return new TestAction();
        }

        switch (actionName)
        {
            case "":
            case "test":
                return new TestAction();
            case "post":
                return new PostAction();
            default:
                throw new RuntimeException("Unknown action");
        }
    }
}