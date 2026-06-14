import controller.SubscriptionController;
import controller.WebsiteMonitor;
import GUI.DashboardUI;
import model.Subscription;
import model.User;
import model.Website;
import strategy.ContentSizeStrategy;
import strategy.TextContentStrategy;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        WebsiteMonitor monitor = new WebsiteMonitor();

        Website FRAUAS   = new Website("W1", "https://www.frankfurt-university.de");
        Website btcPrice = new Website("W2", "https://api.coinbase.com/v2/prices/BTC-EUR/spot");

        monitor.addWebsite(FRAUAS);
        monitor.addWebsite(btcPrice);

        User user = new User("User 1", "Opshori", "opshori@example.com", "+49123456789");

        // Controller
        SubscriptionController controller = new SubscriptionController();
        controller.registerUser(user);

        controller.addSubscription(new Subscription("S1", user, FRAUAS,   60));
        controller.addSubscription(new Subscription("S2", user, btcPrice, 60));

        DashboardUI dashboard = new DashboardUI(monitor, controller);

        FRAUAS.attach(dashboard);
        btcPrice.attach(dashboard);


        monitor.setStrategy(new TextContentStrategy());
        monitor.setStrategy(new ContentSizeStrategy());

        dashboard.displayWebsites();

        while (true) {
            dashboard.checkAllWebsites();
            Thread.sleep(60_000);
        }
    }
}