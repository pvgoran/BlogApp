package blogapp.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pvgoran.dataaccess.DataException;

import blogapp.Action;
import blogapp.BlogAppContext;
import logic.model.User;

public class TestAction extends Action
{
    public static class TestPayload
    {
        public int postCount;
        public List<User> users;
    }

    public TestAction(BlogAppContext appContext)
    {
        super(appContext);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException, DataException
    {
        List<User> users = db.userDAO.getUsers();

        TestPayload pl = new TestPayload();
        pl.postCount = 14;
        pl.users = users;

        req.setAttribute("pl", pl);
        forward("test.jsp", req, res);
    }
}
