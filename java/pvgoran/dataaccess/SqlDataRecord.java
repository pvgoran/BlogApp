package pvgoran.dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class SqlDataRecord
{
    public SqlDataRecord(ResultSet resultSet) throws SQLException
    {
        this(resultSet, null);
    }

    public SqlDataRecord(ResultSet resultSet, Controller controller) throws SQLException
    {
        this.resultSet = resultSet;
        this.size = resultSet.getMetaData().getColumnCount();
        this.position = 0;
        if (controller != null) {
            controller.dataRecord = this;
        }
    }

    public ResultSet resultSet()
    {
        return resultSet;
    }

    public void assertAtEnd()
    {
        if (position != size) {
            throw new RuntimeException("SqlDataRecord is expected to be at end, but it isn't");
        }
    }

    public int size()
    {
        return size;
    }

    public int position()
    {
        return position;
    }

    public String readString() throws DataException
    {
        try {
            String value = resultSet.getString(1 + position++);
            if (resultSet.wasNull()) {
                throw new DataException("Unexpected NULL string value");
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public String readStringOrNull() throws DataException
    {
        try {
            return resultSet.getString(1 + position++);
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public boolean readBoolean() throws DataException
    {
        try {
            boolean value = resultSet.getBoolean(1 + position++);
            if (resultSet.wasNull()) {
                throw new DataException("Unexpected NULL boolean value");
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public Boolean readBooleanOrNull() throws DataException
    {
        try {
            boolean value = resultSet.getBoolean(1 + position++);
            if (resultSet.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public byte readByte() throws DataException
    {
        try {
            byte value = resultSet.getByte(1 + position++);
            if (resultSet.wasNull()) {
                throw new DataException("Unexpected NULL byte value");
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public Byte readByteOrNull() throws DataException
    {
        try {
            byte value = resultSet.getByte(1 + position++);
            if (resultSet.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public short readShort() throws DataException
    {
        try {
            short value = resultSet.getShort(1 + position++);
            if (resultSet.wasNull()) {
                throw new DataException("Unexpected NULL short value");
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public Short readShortOrNull() throws DataException
    {
        try {
            short value = resultSet.getShort(1 + position++);
            if (resultSet.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public int readInt() throws DataException
    {
        try {
            int value = resultSet.getInt(1 + position++);
            if (resultSet.wasNull()) {
                throw new DataException("Unexpected NULL int value");
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public Integer readIntOrNull() throws DataException
    {
        try {
            int value = resultSet.getInt(1 + position++);
            if (resultSet.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public long readLong() throws DataException
    {
        try {
            long value = resultSet.getLong(1 + position++);
            if (resultSet.wasNull()) {
                throw new DataException("Unexpected NULL long value");
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public Long readLongOrNull() throws DataException
    {
        try {
            long value = resultSet.getLong(1 + position++);
            if (resultSet.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public float readFloat() throws DataException
    {
        try {
            float value = resultSet.getFloat(1 + position++);
            if (resultSet.wasNull()) {
                throw new DataException("Unexpected NULL float value");
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public Float readFloatOrNull() throws DataException
    {
        try {
            float value = resultSet.getFloat(1 + position++);
            if (resultSet.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public double readDouble() throws DataException
    {
        try {
            double value = resultSet.getDouble(1 + position++);
            if (resultSet.wasNull()) {
                throw new DataException("Unexpected NULL double value");
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public Double readDoubleOrNull() throws DataException
    {
        try {
            double value = resultSet.getDouble(1 + position++);
            if (resultSet.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public byte[] readBytes() throws DataException
    {
        try {
            byte[] value = resultSet.getBytes(1 + position++);
            if (resultSet.wasNull()) {
                throw new DataException("Unexpected NULL bytes value");
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public byte[] readBytesOrNull() throws DataException
    {
        try {
            byte[] value = resultSet.getBytes(1 + position++);
            if (resultSet.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public LocalDate readDate() throws DataException
    {
        try {
            java.sql.Date sqlDate = resultSet.getDate(1 + position++);
            if (resultSet.wasNull()) {
                throw new DataException("Unexpected NULL date value");
            }
            return new LocalDate(sqlDate);
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public LocalDate readDateOrNull() throws DataException
    {
        try {
            java.sql.Date sqlDate = resultSet.getDate(1 + position++);
            if (resultSet.wasNull()) {
                return null;
            }
            return new LocalDate(sqlDate);
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public DateTime readTimestamp() throws DataException
    {
        try {
            java.sql.Timestamp sqlTimestamp = resultSet.getTimestamp(1 + position++);
            if (resultSet.wasNull()) {
                throw new DataException("Unexpected NULL date value");
            }
            return new DateTime(sqlTimestamp);
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public DateTime readTimestampOrNull() throws DataException
    {
        try {
            java.sql.Timestamp sqlTimestamp = resultSet.getTimestamp(1 + position++);
            if (resultSet.wasNull()) {
                return null;
            }
            return new DateTime(sqlTimestamp);
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    public boolean testNonNull() throws DataException
    {
        try {
            return (null != resultSet.getObject(1 + position));
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }
    
    public void skip(int offset)
    {
        position += offset;
        if (position > size) {
            throw new RuntimeException("Skipped record data past its size");
        }
    }

    public void skip()
    {
        skip(1);
    }

    public static class Controller
    {
        public void resetDataRecord()
        {
            dataRecord.position = 0;
        }

        private SqlDataRecord dataRecord;
    }

    private final ResultSet resultSet;
    private final int size;

    private int position;
}
