import java.util.ArrayList;
import java.util.List;

/**
 * Controller (MVC): mediates between the model (User, Website,
 * Subscription, Notification) and the view (DashboardUI).
 */
public class SubscriptionController {

    private List<Subscription> subscriptions;
    private List<User> users;

    public SubscriptionController() {
        this.subscriptions = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    public void modifySubscription(String subscriptionID, int newFrequency) {
        for (Subscription subscription : subscriptions) {
            if (subscription.getSubscriptionID().equals(subscriptionID)) {
                subscription.setFrequency(newFrequency);
                return;
            }
        }
    }

    public void deleteSubscription(String subscriptionID) {
        subscriptions.removeIf(s -> s.getSubscriptionID().equals(subscriptionID));
    }

    public void notifySubscribers(Website website) {
        for (Subscription subscription : subscriptions) {
            if (subscription.isActive() && subscription.getWebsite() == website) {
                Notification notification = createNotification(subscription, website);
                notification.send();
            }
        }
    }

    private Notification createNotification(Subscription subscription, Website website) {
        User user = subscription.getUser();
        String notificationID = "N-" + subscription.getSubscriptionID();
        String message = "Update detected on " + website.getUrl();

        return new Email(notificationID, user, message, user.getEmail());
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public List<User> getUsers() {
        return users;
    }
}
