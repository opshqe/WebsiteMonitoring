package Model;

import java.util.Date;

public class Subscription {

    private String subscriptionID;
    private Date timestamp;
    private boolean isActive;
    private int frequency;
    private User user;
    private Website website;

    public Subscription(String subscriptionID, User user, Website website, int frequency) {
        this.subscriptionID = subscriptionID;
        this.user = user;
        this.website = website;
        this.frequency = frequency;
        this.timestamp = new Date();
        this.isActive = true;
    }

    public String getSubscriptionID()           { return subscriptionID; }
    public Date getTimestamp()                  { return timestamp; }
    public boolean isActive()                   { return isActive; }
    public void setActive(boolean active)       { this.isActive = active; }
    public int getFrequency()                   { return frequency; }
    public void setFrequency(int frequency)     { this.frequency = frequency; }
    public User getUser()                       { return user; }
    public Website getWebsite()                 { return website; }
}
