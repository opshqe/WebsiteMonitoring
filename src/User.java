public class User {

    private String userID;
    private String name;
    private String email;
    private String mobileNumber;

    public User(String userID, String name, String email, String mobileNumber) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}