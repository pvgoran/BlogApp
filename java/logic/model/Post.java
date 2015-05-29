package logic.model;

import org.joda.time.DateTime;

public class Post
{
    public int id;
    public int createUserId;
    public DateTime createTimestamp;
    public DateTime updateTimestamp;
    public String text;
    
    public Post(int id, int createIserId, DateTime createTimestamp, DateTime updateTimestamp, String text)
    {
        this.id = id;
        this.createUserId = createIserId;
        this.createTimestamp = createTimestamp;
        this.updateTimestamp = updateTimestamp;
        this.text = text;
    }
}
