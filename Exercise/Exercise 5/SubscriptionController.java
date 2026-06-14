package Controller;

import Model.Subscription;
import Model.User;
import Model.Website;
import Notification.Email;
import Notification.Notification;
import Notification.SMS;
import Observer.Observer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionController {

    private List<Subscription> subscriptions;
    private List<User> users;
    // Speichert Email + SMS Observer pro Subscription-ID für sauberes detach
    private Map<String, List<Observer>> observerMap;

    public SubscriptionController() {
        this.subscriptions = new ArrayList<>();
        this.users         = new ArrayList<>();
        this.observerMap   = new HashMap<>();
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);

        User    user = subscription.getUser();
        Website site = subscription.getWebsite();
        String  id   = "N-" + subscription.getSubscriptionID();
        String  msg  = "Update detected on " + site.getUrl();

        // Email + SMS erstellen und als Observer an die Website hängen
        Notification email = new Email(id + "-email", user, msg, user.getEmail());
        Notification sms   = new SMS(id + "-sms",   user, msg, user.getMobileNumber());

        site.attach(email);
        site.attach(sms);

        // Referenzen speichern für deleteSubscription
        List<Observer> observers = new ArrayList<>();
        observers.add(email);
        observers.add(sms);
        observerMap.put(subscription.getSubscriptionID(), observers);
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
        subscriptions.removeIf(s -> {
            if (s.getSubscriptionID().equals(subscriptionID)) {
                // Beide Observer (Email + SMS) vom Website entfernen
                List<Observer> observers = observerMap.remove(subscriptionID);
                if (observers != null) {
                    for (Observer o : observers) {
                        s.getWebsite().detach(o);
                    }
                }
                return true;
            }
            return false;
        });
    }

    public List<Subscription> getSubscriptions() { return subscriptions; }
    public List<User> getUsers()                 { return users; }
}
