package Notification;

import Model.User;

public class Email extends Notification {

    private String emailAddress;

    public Email(String notificationID, User recipient, String message, String emailAddress) {
        super(notificationID, recipient, message);
        this.emailAddress = emailAddress;
    }

    @Override
    public void send() {
        System.out.println("Email to " + emailAddress + ": " + getMessage());
        markAsSent();
    }

    public String getEmailAddress() { return emailAddress; }
}

