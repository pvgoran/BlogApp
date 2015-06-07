package logic.model;

import org.joda.time.DateTime;

public class User
{
    public int id;
    public DateTime createTimestamp;
    public String firstName;
    public String lastName;
    public String username;
    public String passwordSha256;

    public User(int id, DateTime createTimestamp, String firstName, String lastName, String username, String passwordSha256)
    {
        this.id = id;
        this.createTimestamp = createTimestamp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.passwordSha256 = passwordSha256;
    }

//    public void initUserPartial(int id, DateTime createTimestamp, String firstName, String lastName, String username, String passwordSha256)
//    {
//        this.id = id;
//        this.createTimestamp = createTimestamp;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.username = username;
//        this.passwordSha256 = passwordSha256;
//    }
}
