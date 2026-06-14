package Controller;

import Model.Website;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebsiteMonitor {

    private List<Website> websites;
    private HttpClient httpClient;

    public WebsiteMonitor() {
        this.websites = new ArrayList<>();
        this.httpClient = HttpClient.newHttpClient();
    }

    public void addWebsite(Website website) {
        websites.add(website);
    }

    // SubscriptionController wird nicht mehr benötigt —
    // website.notifyObserver() übernimmt die Benachrichtigung direkt.

    public void checkAllWebsites() {
        for (Website website : websites) {
            checkSingleWebsite(website);
        }
    }

    public void checkSingleWebsite(Website website) {
        String newContent = fetchContent(website);
        boolean changed   = compareSnapshots(website, newContent);
        takeSnapshot(website, newContent);

        if (changed) {
            System.out.println("CHANGE DETECTED on: " + website.getUrl());
            website.notifyObserver();   // Observer-Pattern: Website benachrichtigt alle Observer
        } else {
            System.out.println("No change on: " + website.getUrl());
        }
    }

    public String fetchContent(Website website) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(website.getUrl()))
                    .header("User-Agent", "Mozilla/5.0 (compatible; WebsiteMonitor/1.0)")
                    .header("Cache-Control", "no-cache, no-store")
                    .header("Pragma", "no-cache")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (Exception e) {
            System.out.println("Failed to fetch " + website.getUrl() + ": " + e.getMessage());
            return "";
        }
    }

    public void takeSnapshot(Website website, String content) {
        website.setContent(content);
        website.setLastCheckedDate(new Date());
    }

    public boolean compareSnapshots(Website website, String newContent) {
        String oldContent = website.getContent();
        return oldContent != null && !oldContent.equals(newContent);
    }

    public List<Website> getWebsites() { return websites; }
}
