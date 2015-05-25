package pvgoran.dataaccess;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class StatementData
{
    public StatementData(PreparedStatement stmt)
    {
        this.stmt = stmt;
        this.position = 1;
    }

    public Statement statement()
    {
        return stmt;
    }

    public int position()
    {
        return position;
    }

    public void reset() throws SQLException
    {
        stmt.clearParameters();
        position = 1;
    }

    public void addBoolean(boolean x) throws SQLException
    {
        stmt.setBoolean(position++, x);
    }

    public void addByte(byte x) throws SQLException
    {
        stmt.setByte(position++, x);
    }

    public void addShort(short x) throws SQLException
    {
        stmt.setShort(position++, x);
    }

    public void addInt(int x) throws SQLException
    {
        stmt.setInt(position++, x);
    }

    public void addLong(long x) throws SQLException
    {
        stmt.setLong(position++, x);
    }

    public void addFloat(float x) throws SQLException
    {
        stmt.setFloat(position++, x);
    }

    public void addDouble(double x) throws SQLException
    {
        stmt.setDouble(position++, x);
    }

    public void addBigDecimal(BigDecimal x) throws SQLException
    {
        stmt.setBigDecimal(position++, x);
    }

    public void addString(String x) throws SQLException
    {
        stmt.setString(position++, x);
    }

    public void addBytes(byte x[]) throws SQLException
    {
        stmt.setBytes(position++, x);
    }

    public void addDate(LocalDate x) throws SQLException
    {
        stmt.setDate(position++, new java.sql.Date(x.toDateTimeAtStartOfDay().getMillis()));
    }

    public void addDateOrNull(LocalDate x) throws SQLException
    {
        stmt.setDate(position++, (x != null ? new java.sql.Date(x.toDateTimeAtStartOfDay().getMillis()) : null));
    }

    public void addTimestamp(DateTime x) throws SQLException
    {
        stmt.setTimestamp(position++, new java.sql.Timestamp(x.getMillis()));
    }

    public void addTimestampOrNull(DateTime x) throws SQLException
    {
        stmt.setTimestamp(position++, (x != null ? new java.sql.Timestamp(x.getMillis()) : null));
    }

    private final PreparedStatement stmt;

    private int position;
}
