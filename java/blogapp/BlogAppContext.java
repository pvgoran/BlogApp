package blogapp;

import javax.sql.DataSource;

public class BlogAppContext
{
    public BlogAppConfiguration configuration;
    public DataSource dataSource;
    public BlogAppDatabase db;

    public String username;
}
