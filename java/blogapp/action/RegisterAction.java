package blogapp.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pvgoran.dataaccess.DataException;

import blogapp.Action;
import blogapp.BlogAppContext;

public class RegisterAction extends Action
{
    public static class RegisterPayload
    {
        public String errorMessage;
        public String username;
        public String firstName;
        public String lastName;
        public String email;
    }

    public RegisterAction(BlogAppContext appContext)
    {
        super(appContext);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException, DataException
    {
        doForward(null/*errorMessage*/, ""/*username*/, ""/*firstName*/, ""/*lastName*/, ""/*email*/, req, res);
    }

    public void doForward(String errorMessage, String username, String firstName, String lastName, String email, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException, DataException
    {
        RegisterPayload pl = new RegisterPayload();
        pl.errorMessage = errorMessage;
        pl.username = username;
        pl.firstName = firstName;
        pl.lastName = lastName;

        req.setAttribute("pl", pl);
        forward("register.jsp", req, res);
    }
}
