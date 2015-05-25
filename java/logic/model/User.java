package logic.model;

import org.joda.time.DateTime;

public class User
{
    public int id;
    public DateTime createTimestamp;
    public String firstName;
    public String lastName;
    public String login;

    public User(int id, DateTime createTimestamp, String firstName, String lastName, String login)
    {
        this.id = id;
        this.createTimestamp = createTimestamp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
    }

//    public void initUserPartial(int id, DateTime createTimestamp, String firstName, String lastName, String login)
//    {
//        this.id = id;
//        this.createTimestamp = createTimestamp;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.login = login;
//    }
}
