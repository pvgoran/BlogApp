package logic.model;

import org.joda.time.DateTime;

public class Post
{
    public int id;
    public int createUserId;
    public DateTime createTimestamp;
    public DateTime updateTimestamp;
    public String text;
}
