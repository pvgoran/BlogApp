package blogapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pvgoran.dataaccess.DataException;

import blogapp.action.*;
import logic.model.User;

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

        Action action = selectAction(req.getParameter("action"), appContext);

        try {
            action.process(req, res);
        } catch (SQLException | DataException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static User getLoggedInUser(HttpServletRequest req)
    {
        return (User)req.getSession().getAttribute("loggedInUser");
    }

    public static void setLoggedInUser(HttpServletRequest req, User user)
    {
        req.getSession().setAttribute("loggedInUser", user);
    }

    public static void removeLoggedInUser(HttpServletRequest req)
    {
        req.getSession().removeAttribute("loggedInUser");
    }

    private BlogAppContext createAppContext(HttpServletRequest req, HttpServletResponse res)
    {
        BlogAppConfiguration configuration = BlogAppConfiguration.getConfiguration();
        User user = getLoggedInUser(req);
        
        BlogAppContext appContext = new BlogAppContext();
        appContext.configuration = configuration;
        appContext.dataSource = configuration.dataSource;
        appContext.db = new BlogAppDatabase(configuration.dataSource);
        appContext.user = user;

        return appContext;
    }

    private Action selectAction(String actionName, BlogAppContext appContext)
    {
        if (actionName == null)
        {
            return new TestAction(appContext);
        }

        switch (actionName)
        {
            case "":
            case "test":
                return new TestAction(appContext);
            case "login":
                return new LoginAction(appContext);
            case "logout":
                return new LogoutAction(appContext);
            case "register":
                return new RegisterAction(appContext);
            case "registerJson":
                return new RegisterJsonAction(appContext);
            case "doRegister":
                return new DoRegisterAction(appContext);
            case "post":
                return new PostAction(appContext);
            default:
                throw new RuntimeException("Unknown action");
        }
    }
}
