/**
 * View (MVC): only talks to the controller / monitor - it has
 * no direct knowledge of how subscriptions or notifications work.
 */
public class DashboardUI {

    private WebsiteMonitor monitor;
    private SubscriptionController subscriptionController;

    public DashboardUI(WebsiteMonitor monitor, SubscriptionController subscriptionController) {
        this.monitor = monitor;
        this.subscriptionController = subscriptionController;
    }

    public void displayWebsites() {
        System.out.println("Monitored websites:");
        for (Website website : monitor.getWebsites()) {
            System.out.println(" - " + website.getUrl() + " | last checked: " + website.getLastCheckedDate());
        }
    }

    public void checkAllWebsites() {
        monitor.checkAllWebsites(subscriptionController);
    }
}

