public class Email extends Notification {

    private String emailAddress;

    public Email(String notificationID, User recipient, String message, String emailAddress) {
        super(notificationID, recipient, message);
        this.emailAddress = emailAddress;
    }

    @Override
    public void send() {
        // Placeholder: integrate with an email service here.
        System.out.println("Email to " + emailAddress + ": " + getMessage());
        markAsSent();
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
