public class User {
    String userName;
    String userPassword;

    public User(String userName, String userPassword) {
        this.userName=userName;
        this.userPassword=userPassword;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }
}
