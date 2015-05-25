package pvgoran.dataaccess;

public class DataException extends Exception
{
    public DataException(final String message)
    {
        super(message);
    }

    public DataException(final Throwable throwable)
    {
        super(throwable);
    }

    public DataException(final String message, final Throwable throwable)
    {
        super(message, throwable);
    }
}
