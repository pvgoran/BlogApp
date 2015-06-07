package blogapp;

import javax.sql.DataSource;

import logic.model.User;

public class BlogAppContext
{
    public BlogAppConfiguration configuration;
    public DataSource dataSource;
    public BlogAppDatabase db;

    public User user;
}
