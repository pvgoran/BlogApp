package blogapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pvgoran.dataaccess.DataException;

public abstract class Action
{
    protected BlogAppContext appContext;
    protected BlogAppDatabase db;

    public abstract void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException, DataException;

    public void forward(String path, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        req.setAttribute("ac", appContext);
        req.getRequestDispatcher(path).forward(req, res);
    }

    public void setAppContext(BlogAppContext appContext)
    {
        this.appContext = appContext;
        this.db = appContext.db;
    }
}
