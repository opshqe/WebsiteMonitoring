/**
 * Abstract base class for notifications.
 * SMS and Email inherit notificationID, recipient, message, status,
 * and the markAsSent/markAsFailed operations from here, and each
 * provides its own send() implementation.
 */
public abstract class Notification {

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

    public abstract void send();

    public void markAsSent() {
        this.status = NotificationStatus.SENT;
    }

    public void markAsFailed() {
        this.status = NotificationStatus.FAILED;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public User getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public NotificationStatus getStatus() {
        return status;
    }
}

