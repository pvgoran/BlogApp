package blogapp.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pvgoran.dataaccess.DataException;

import blogapp.*;

public class LogoutAction extends Action
{
    public LogoutAction(BlogAppContext appContext)
    {
        super(appContext);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException, DataException
    {
        BlogServlet.removeLoggedInUser(req);
        res.sendRedirect("?");
    }
}
