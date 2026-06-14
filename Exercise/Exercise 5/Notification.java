package Notification;

import Model.User;
import Observer.Observer;

public abstract class Notification implements Observer {

    private String notificationID;
    private User recipient;
    private String message;
    private NotificationStatus status;

    protected Notification(String notificationID, User recipient, String message) {
        this.notificationID = notificationID;
        this.recipient = recipient;
        this.message = message;
        this.status = NotificationStatus.PENDING;
    }

    // ── Observer ──────────────────────────────────────────────────────
    // Wenn website.notifyObserver() aufgerufen wird, wird update() ausgelöst,
    // welches send() der konkreten Klasse (Email oder SMS) aufruft.

    @Override
    public void update() {
        send();
    }

    // ── Abstract ──────────────────────────────────────────────────────

    public abstract void send();

    // ── Helper ────────────────────────────────────────────────────────

    public void markAsSent()   { this.status = NotificationStatus.SENT; }
    public void markAsFailed() { this.status = NotificationStatus.FAILED; }

    // ── Getters ───────────────────────────────────────────────────────

    public String getNotificationID()     { return notificationID; }
    public User getRecipient()            { return recipient; }
    public String getMessage()            { return message; }
    public NotificationStatus getStatus() { return status; }
}
