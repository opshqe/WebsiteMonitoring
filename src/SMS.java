public class SMS extends Notification {

    private String phoneNumber;

    public SMS(String notificationID, User recipient, String message, String phoneNumber) {
        super(notificationID, recipient, message);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send() {
        // Placeholder: integrate with an SMS gateway here.
        System.out.println("SMS to " + phoneNumber + ": " + getMessage());
        markAsSent();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
