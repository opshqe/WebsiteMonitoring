package controller;

import model.Website;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import strategy.Strategy;
import strategy.HtmlContentStrategy;


public class WebsiteMonitor {

    private List<Website> websites;
    private Map<String, String> snapshots; // websiteID → last fetched content
    private Strategy strategy;


    public WebsiteMonitor() {
        this.websites  = new ArrayList<>();
        this.snapshots = new HashMap<>();
        this.strategy  = new HtmlContentStrategy();
    }


    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
        System.out.println("[WebsiteMonitor] Strategy set to: "
                + strategy.getClass().getSimpleName());
    }

    //Core operations

    public void addWebsite(Website website) {
        websites.add(website);
    }


    public void checkAllWebsites() {
        for (Website website : websites) {
            System.out.println("\n[Checking] " + website.getUrl());

            String newContent = fetchContent(website);
            boolean unchanged = compareSnapshots(website, newContent);

            if (unchanged) {
                System.out.println("  → No change detected.");
            } else {
                System.out.println("  → Change detected! Notifying observers...");
                website.notifyObserver();
            }

            takeSnapshot(website, newContent);
            website.setLastCheckedDate(new Date());
        }
    }


    public String fetchContent(Website website) {
        StringBuilder content = new StringBuilder();
        try {
            HttpURLConnection connection =
                    (HttpURLConnection) new URL(website.getUrl()).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("User-Agent", "WebsiteMonitor");

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }
        } catch (Exception e) {
            System.err.println("  [ERROR] Could not fetch "
                    + website.getUrl() + ": " + e.getMessage());
        }
        return content.toString();
    }


    public void takeSnapshot(Website website, String content) {
        snapshots.put(website.getWebsiteID(), content);
        website.setContent(content);
    }


    public boolean compareSnapshots(Website website, String newContent) {
        String oldSnapshot = snapshots.get(website.getWebsiteID());
        if (oldSnapshot == null) {
            // First run: store the snapshot, treat as unchanged
            return true;
        }
        return strategy.compare(oldSnapshot, newContent);
    }



    public List<Website> getWebsites() { return websites; }
}
