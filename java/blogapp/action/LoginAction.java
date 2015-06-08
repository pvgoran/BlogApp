package blogapp.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pvgoran.dataaccess.DataException;

import blogapp.*;
import logic.Util;
import logic.model.User;

public class LoginAction extends Action
{
    public static class LoginPayload
    {
        public String errorMessage;
        public String username;
    }

    public LoginAction(BlogAppContext appContext)
    {
        super(appContext);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException, DataException
    {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username != null || password != null) {
            processLogin(username, password, req, res);
        } else {
            showLoginPage(null/*errorMessage*/, ""/*username*/, req, res);
        }
    }

    private void showLoginPage(String errorMessage, String username, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        LoginPayload pl = new LoginPayload();
        pl.errorMessage = errorMessage;
        pl.username = username;

        req.setAttribute("pl", pl);
        forward("login.jsp", req, res);
    }

    private void processLogin(
            String username, String password,
            HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException, DataException
    {
        User maybeUser = db.userDAO.getUserForLogin(username, Util.hashSha256(password));
        if (maybeUser != null) {
            BlogServlet.setLoggedInUser(req, maybeUser);
            res.sendRedirect("?");
        } else {
            showLoginPage("Incorrect username or password", username, req, res);
        }
    }
}
