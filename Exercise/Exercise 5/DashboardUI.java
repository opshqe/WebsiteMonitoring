package GUI;

import Controller.SubscriptionController;
import Controller.WebsiteMonitor;
import Model.Website;
import Observer.Observer;

public class DashboardUI implements Observer {

    private WebsiteMonitor monitor;
    private SubscriptionController subscriptionController;

    public DashboardUI(WebsiteMonitor monitor, SubscriptionController subscriptionController) {
        this.monitor = monitor;
        this.subscriptionController = subscriptionController;
    }

    // ── Observer ──────────────────────────────────────────────────────
    // Wird automatisch aufgerufen wenn eine Website eine Änderung meldet

    @Override
    public void update() {
        System.out.println("[Dashboard] Änderung erkannt — Ansicht wird aktualisiert:");
        displayWebsites();
    }

    // ── View ──────────────────────────────────────────────────────────

    public void displayWebsites() {
        System.out.println("Überwachte Websites:");
        for (Website website : monitor.getWebsites()) {
            System.out.println(" - " + website.getUrl() + " | zuletzt geprüft: " + website.getLastCheckedDate());
        }
    }

    public void checkAllWebsites() {
        monitor.checkAllWebsites();
    }
}
