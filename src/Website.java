import java.util.Date;

public class Website {

    private String websiteID;
    private String url;
    private Date lastCheckedDate;
    private String content;

    public Website(String websiteID, String url) {
        this.websiteID = websiteID;
        this.url = url;
    }

    public String getWebsiteID() {
        return websiteID;
    }

    public String getUrl() {
        return url;
    }

    public Date getLastCheckedDate() {
        return lastCheckedDate;
    }

    public void setLastCheckedDate(Date lastCheckedDate) {
        this.lastCheckedDate = lastCheckedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
