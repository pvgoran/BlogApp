package blogapp;

import javax.sql.DataSource;

import logic.dao.UserDAO;

public class BlogAppDatabase
{
    public UserDAO userDAO;

    public BlogAppDatabase(DataSource dataSource)
    {
        this.userDAO = new UserDAO(dataSource);
    }
}
