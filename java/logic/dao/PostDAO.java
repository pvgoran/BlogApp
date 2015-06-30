package logic.dao;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import org.joda.time.DateTime;

import pvgoran.dataaccess.*;

import logic.model.Post;

public class PostDAO
{
    public PostDAO(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public int createPost(int createUserId, String text) throws SQLException
    {
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement("insert into posts(createuserid, createtimestamp, updatetimestamp, text) values (?, ?, ?, ?) returning id");

        StatementData sdt = new StatementData(stmt);
        sdt.addInt(createUserId);
        sdt.addTimestamp(DateTime.now());
        sdt.addTimestamp(DateTime.now());
        sdt.addString(text);

        ResultSet lastIdRes = stmt.executeQuery();
        lastIdRes.next();
        return lastIdRes.getInt(1);
    }

    public Post getPost(int postId) throws SQLException, DataException
    {
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select " + generatePostSelectList("p.") + " from posts p where p.id=?");

        StatementData sdt = new StatementData(stmt);
        sdt.addInt(postId);

        ResultSet res = stmt.executeQuery();
        if (res.next()) {
            SqlDataRecord rec = new SqlDataRecord(res);
            return loadPost(rec);
        } else {
            return null;
        }
    }

    public List<Post> getPosts() throws SQLException, DataException
    {
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select " + generatePostSelectList("p.") + " from posts p");

        ResultSet res = stmt.executeQuery();
        List<Post> posts = new ArrayList<>();
        while (res.next()) {
            SqlDataRecord rec = new SqlDataRecord(res);
            posts.add(loadPost(rec));
        }

        return posts;
    }

    public void updatePost(int postId, DateTime updateTimestamp, String text) throws SQLException
    {
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement("update posts set updatetimestamp=?, text=? where id=?");

        StatementData sdt = new StatementData(stmt);
        sdt.addTimestamp(updateTimestamp);
        sdt.addString(text);

        sdt.addInt(postId);

        stmt.execute();
    }

    public void deletePost(int postId) throws SQLException
    {
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement("delete from posts where id=?");

        StatementData sdt = new StatementData(stmt);
        sdt.addInt(postId);

        stmt.execute();
    }

    private String generatePostSelectList(String prefix)
    {
        return String.format("%1$sid, %1$screateuserid, %1$screatetimestamp, %1$supdatetimestamp, %1$stext", prefix);
    }

    private Post loadPost(SqlDataRecord rec) throws DataException
    {
        return new Post(rec.readInt(), rec.readInt(), rec.readTimestamp(), rec.readTimestamp(), rec.readString());
    }

    private DataSource dataSource;
}
