package logic.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import logic.model.Post;

import org.joda.time.DateTime;

import pvgoran.dataaccess.*;

import logic.model.User;

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
        PreparedStatement stmt = conn.prepareStatement("select p.id, p.createuserid, p.createtimestamp, p.updatetimestamp, p.text from posts p where p.id=?");

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
        PreparedStatement stmt = conn.prepareStatement("select p.id, p.createuserid, p.createtimestamp, p.updatetimestamp, p.text from posts p");

        ResultSet res = stmt.executeQuery();
        List<Post> posts = new ArrayList<>();
        while (res.next()) {
            SqlDataRecord rec = new SqlDataRecord(res);
            posts.add(loadPost(rec));
        }

        return posts;
    }

    private Post loadPost(SqlDataRecord rec) throws DataException
    {
        return new Post(rec.readInt(), rec.readInt(), rec.readTimestamp(), rec.readTimestamp(), rec.readString());
    }

    private DataSource dataSource;
}
