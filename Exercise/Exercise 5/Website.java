package Model;

import Observer.Observer;
import Observer.Subject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Website implements Subject {

    private String websiteID;
    private String url;
    private Date lastCheckedDate;
    private String content;
    private List<Observer> observers;

    public Website(String websiteID, String url) {
        this.websiteID = websiteID;
        this.url = url;
        this.observers = new ArrayList<>();
    }



    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }



    public String getWebsiteID()                        { return websiteID; }
    public String getUrl()                              { return url; }
    public Date getLastCheckedDate()                    { return lastCheckedDate; }
    public void setLastCheckedDate(Date lastCheckedDate){ this.lastCheckedDate = lastCheckedDate; }
    public String getContent()                          { return content; }
    public void setContent(String content)              { this.content = content; }
}
