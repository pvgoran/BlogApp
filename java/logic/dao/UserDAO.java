package logic.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.DateTime;

import pvgoran.dataaccess.*;

import logic.Util;
import logic.model.User;

public class UserDAO
{
    public UserDAO(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public int createUser(String firstName, String lastName, String username, String passwordSha256) throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement("insert into users(createtimestamp, firstname, lastname, username, passwordsha256) values (?, ?, ?, ?, ?) returning id");

            StatementData sdt = new StatementData(stmt);
            sdt.addTimestamp(DateTime.now());
            sdt.addString(firstName);
            sdt.addString(lastName);
            sdt.addString(username);
            sdt.addString(passwordSha256);

            ResultSet lastIdRes = stmt.executeQuery();
            lastIdRes.next();
            return lastIdRes.getInt(1);
        } finally {
            Util.closeStatement(stmt);
            Util.closeConnection(conn);
        }
    }

    public User getUser(int userId) throws SQLException, DataException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement("select " + generateUserSelectList("u.") + " from users u where u.id=?");

            StatementData sdt = new StatementData(stmt);
            sdt.addInt(userId);

            res = stmt.executeQuery();
            if (res.next()) {
                SqlDataRecord rec = new SqlDataRecord(res);
                return loadUser(rec);
            } else {
                return null;
            }
        } finally {
            Util.closeResultSet(res);
            Util.closeStatement(stmt);
            Util.closeConnection(conn);
        }
    }

    public User getUserForLogin(String username, String passwordSha256) throws SQLException, DataException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement("select " + generateUserSelectList("u.") + " from users u where u.username=? and u.passwordsha256=?");

            StatementData sdt = new StatementData(stmt);
            sdt.addString(username);
            sdt.addString(passwordSha256);

            res = stmt.executeQuery();
            if (res.next()) {
                SqlDataRecord rec = new SqlDataRecord(res);
                return loadUser(rec);
            } else {
                return null;
            }
        } finally {
            Util.closeResultSet(res);
            Util.closeStatement(stmt);
            Util.closeConnection(conn);
        }
    }

    public boolean testUserExists(String username) throws SQLException, DataException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement("select 1 from users u where u.username=?");

            StatementData sdt = new StatementData(stmt);
            sdt.addString(username);

            res = stmt.executeQuery();
            return res.next();
        } finally {
            Util.closeResultSet(res);
            Util.closeStatement(stmt);
            Util.closeConnection(conn);
        }
    }

//    public UserWithStatistics getUserWithStatistics(int userId) throws SQLException, DataException
//    {
//        Connection conn = dataSource.getConnection();
//        PreparedStatement stmt = conn.prepareStatement("select u.id, u.createtimestamp, u.firstname, u.lastname, u.username, u.passwordsha256, (select count(1) from posts p where p.userid=u.id), (select count(1) from comments c where c.userid=u.id) from users u where u.id=?");
//
//        StatementData sdt = new StatementData(stmt);
//        sdt.addInt(userId);
//
//        ResultSet res = stmt.executeQuery();
//        if (res.next()) {
//            SqlDataRecord rec = new SqlDataRecord(res);
//            return loadUser(rec);
//        } else {
//            return null;
//        }
//    }

    public List<User> getUsers() throws SQLException, DataException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement("select " + generateUserSelectList("u.") + " from users u");

            res = stmt.executeQuery();
            List<User> users = new ArrayList<>();
            while (res.next()) {
                SqlDataRecord rec = new SqlDataRecord(res);
                users.add(loadUser(rec));
            }

            return users;
        } finally {
            Util.closeResultSet(res);
            Util.closeStatement(stmt);
            Util.closeConnection(conn);
        }
    }

    public void updateUser(int userId, String firstName, String lastName, String username, String passwordSha256) throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement("update users set firstname=?, lastname=?, username=?, passwordsha256=? where id=?");

            StatementData sdt = new StatementData(stmt);
            sdt.addString(firstName);
            sdt.addString(lastName);
            sdt.addString(username);
            sdt.addString(passwordSha256);

            sdt.addInt(userId);

            stmt.execute();
        } finally {
            Util.closeStatement(stmt);
            Util.closeConnection(conn);
        }
    }

    public void deleteUser(int userId) throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement("delete from users where id=?");

            StatementData sdt = new StatementData(stmt);
            sdt.addInt(userId);

            stmt.execute();
        } finally {
            Util.closeStatement(stmt);
            Util.closeConnection(conn);
        }
    }

    public String canDeleteUser(int userId) throws SQLException, DataException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(
                    "select case when exists (select 1 from posts p where p.createuserid=?) then 'Post'"
                            + "else null end");

            StatementData sdt = new StatementData(stmt);
            sdt.addInt(userId);

            res = stmt.executeQuery();
            res.next();

            return res.getString(1);
        } finally {
            Util.closeResultSet(res);
            Util.closeStatement(stmt);
            Util.closeConnection(conn);
        }
    }

    private String generateUserSelectList(String prefix)
    {
        return String.format("%1$sid, %1$screatetimestamp, %1$sfirstname, %1$slastname, %1$susername, %1$spasswordsha256", prefix);
    }

    private User loadUser(SqlDataRecord rec) throws DataException
    {
        return new User(rec.readInt(), rec.readTimestamp(), rec.readString(), rec.readString(), rec.readString(), rec.readString());
    }

//    private void loadUserPartial(User user, SqlDataRecord rec)
//    {
//        user.initUserPartial(rec.readInt(), rec.readTimestamp(), rec.readString(), rec.readString(), rec.readString(), rec.readString());
//    }
//
//    private void loadUserWithStatisticsPartial(UserWithStatistics userWithStatistics, SqlDataRecord rec)
//    {
//        userWithStatistics.initUserWithStatisticsPartial(rec.readInt(), rec.readInt());
//    }
//
//    private User loadUser(SqlDataRecord rec) throws DataException
//    {
//        User user = new User();
//        loadUserPartial(user, rec);
//
//        return user;
//    }
//
//    private UserWithStatistics loadUserWithStatistics(SqlDataRecord rec) throws DataException
//    {
//        UserWithStatistics userWithStatistics = new UserWithStatistics();
//        loadUserPartial(userWithStatistics, rec);
//        loadUserWithStatisticsPartial(userWithStatistics, rec);
//
//        return userWithStatistics;
//    }

    private DataSource dataSource;
}
