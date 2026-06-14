import Controller.SubscriptionController;
import Controller.WebsiteMonitor;
import GUI.DashboardUI;
import Model.Subscription;
import Model.User;
import Model.Website;

public class Main {
    public static void main(String[] args) throws InterruptedException {


        WebsiteMonitor monitor = new WebsiteMonitor();

        Website FRAUAS   = new Website("W1", "https://www.frankfurt-university.de");
        Website btcPrice = new Website("W2", "https://api.coinbase.com/v2/prices/BTC-EUR/spot");

        monitor.addWebsite(FRAUAS);
        monitor.addWebsite(btcPrice);

        User user = new User("U1", "Opshori", "opshori@example.com", "+49123456789");

        //Controller
        SubscriptionController controller = new SubscriptionController();
        controller.registerUser(user);

        // addSubscription hängt automatisch Email + SMS Observer an die Website
        controller.addSubscription(new Subscription("S1", user, FRAUAS,   60));
        controller.addSubscription(new Subscription("S2", user, btcPrice, 60));


        DashboardUI dashboard = new DashboardUI(monitor, controller);


        FRAUAS.attach(dashboard);
        btcPrice.attach(dashboard);


        dashboard.displayWebsites();

        // ── Hauptschleife ──────────────────────────────────────────────
        // Bei Änderung: website.notifyObserver() → Email.update() + SMS.update() + DashboardUI.update()
        while (true) {
            dashboard.checkAllWebsites();
            Thread.sleep(60_000);
        }
    }
}
