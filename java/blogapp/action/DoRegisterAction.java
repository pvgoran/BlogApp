package blogapp.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import pvgoran.dataaccess.DataException;

import blogapp.Action;
import blogapp.BlogAppContext;
import logic.Util;
import logic.model.User;
import util.LocalException;

public class DoRegisterAction extends Action
{
    public static class RegisteredPayload
    {
        public User user;
    }

    public DoRegisterAction(BlogAppContext appContext)
    {
        super(appContext);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException, DataException
    {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");

        User user;
        try {
            if (StringUtils.isEmpty(username)) {
                throw new LocalException("User Name is not specified");
            }

            if (db.userDAO.testUserExists(username)) {
                throw new LocalException("The specified User Name is already used");
            }

            if (StringUtils.isEmpty(password)) {
                throw new LocalException("Password is not specified");
            }

            if (StringUtils.isEmpty(firstName)) {
                throw new LocalException("First Name is not specified");
            }

            if (StringUtils.isEmpty(email)) {
                throw new LocalException("E-mail is not specified");
            }

            int userId = db.userDAO.createUser(firstName, lastName, username, Util.hashSha256(password));
            user = db.userDAO.getUser(userId);
        } catch (LocalException ex) {
            new RegisterAction(appContext).doForward(ex.getMessage(), username, firstName, lastName, email, req, res);
            return;
        }

        showRegisteredPage(user, req, res);
    }

    private void showRegisteredPage(User user, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        RegisteredPayload pl = new RegisteredPayload();
        pl.user = user;

        req.setAttribute("pl", pl);
        forward("registered.jsp", req, res);
    }
}
