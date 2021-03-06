package logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Calendar;

import org.joda.time.DateTime;

public class Util
{
    public static void closeConnection(Connection conn)
    {
        try {
            if (conn != null) conn.close();
        } catch (Exception ex) {
            // ignore it
        }
    }

    public static void closeStatement(PreparedStatement stmt)
    {
        try {
            if (stmt != null) stmt.close();
        } catch (Exception ex) {
            // ignore it
        }
    }

    public static void closeResultSet(ResultSet res)
    {
        try {
            if (res != null) res.close();
        } catch (Exception ex) {
            // ignore it
        }
    }

    public static DateTime getDateTime(ResultSet res, int pos) throws SQLException
    {
        java.sql.Timestamp dateTime = res.getTimestamp(pos);
        if (dateTime == null) throw new SQLException("Unexpected NULL DateTime value");
        return dateTimeFromSqlTimestamp(dateTime);
    }

    public static void setTimestamp(PreparedStatement stmt, int counter, DateTime dateTime) throws SQLException
    {
        stmt.setTimestamp(counter, sqlTimestampFromDateTime(dateTime));
    }

    private static DateTime dateTimeFromSqlTimestamp(java.sql.Timestamp sqlTimestamp)
    {
        Calendar timestampCalendar = Calendar.getInstance();
        timestampCalendar.setTime(sqlTimestamp);

        return new DateTime(timestampCalendar.get(Calendar.YEAR), 1 + timestampCalendar.get(Calendar.MONTH), timestampCalendar.get(Calendar.DAY_OF_MONTH), timestampCalendar.get(Calendar.HOUR_OF_DAY), timestampCalendar.get(Calendar.MINUTE), timestampCalendar.get(Calendar.SECOND), timestampCalendar.get(Calendar.MILLISECOND));
    }

    public static java.sql.Timestamp sqlTimestampFromDateTime(DateTime dateTime)
    {
        Calendar timestampCalendar = Calendar.getInstance();
        timestampCalendar.set(dateTime.getYear(), -1 + dateTime.getMonthOfYear(), dateTime.getDayOfMonth(), dateTime.getHourOfDay(), dateTime.getMinuteOfHour(), dateTime.getSecondOfMinute());
        timestampCalendar.set(Calendar.MILLISECOND, dateTime.getMillisOfSecond());

        return new java.sql.Timestamp(timestampCalendar.getTimeInMillis());
    }

    public static String hashSha256(String str)
    {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] result = digest.digest(str.getBytes());

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("SHA256 not implemented. Weird.");
        }
    }
}
