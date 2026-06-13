public class Main {
    public static void main(String[] args) throws InterruptedException {

        WebsiteMonitor monitor = new WebsiteMonitor();

        Website fraUAS = new Website("W1", "https://www.frankfurt-university.de");
        Website btcPrice = new Website("W2", "https://api.coinbase.com/v2/prices/BTC-EUR/spot");

        monitor.addWebsite(fraUAS);
        monitor.addWebsite(btcPrice);

        User user = new User("U1", "Shori", "shori@example.com", "+49123456789");

        SubscriptionController controller = new SubscriptionController();
        controller.registerUser(user);

        controller.addSubscription(new Subscription("S1", user, fraUAS, 60));
        controller.addSubscription(new Subscription("S2", user, btcPrice, 60));

        DashboardUI dashboard = new DashboardUI(monitor, controller);

        while (true) {
            dashboard.checkAllWebsites();
            dashboard.displayWebsites();
            Thread.sleep(60000);
        }
    }
}