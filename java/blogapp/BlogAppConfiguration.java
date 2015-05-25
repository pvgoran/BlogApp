package blogapp;

import javax.naming.*;
import javax.sql.DataSource;

public class BlogAppConfiguration
{
    public DataSource dataSource;
    public String obscureConfigurationParameter;

    public static BlogAppConfiguration getConfiguration()
    {
        BlogAppConfiguration configuration = new BlogAppConfiguration();
        configuration.dataSource = getDataSource();
        configuration.obscureConfigurationParameter = "I don't know what is here";

        return configuration;
    }

    private static DataSource getDataSource()
    {
        try {
            Context ctx = new InitialContext();
            return (DataSource)ctx.lookup("java:comp/env/jdbc/blogappDataSource");
        } catch (NamingException ex) {
            throw new RuntimeException("No blogapp data source found", ex);
        }
    }
}
