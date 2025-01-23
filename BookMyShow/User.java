import java.util.ArrayList;

public class User {
    private String userName;
    private String userPassword;
    private String surName;
    private String tLocation;
    private ArrayList<Tickets> tickets =new ArrayList<>();

    public User(String userName, String userPassword, String thName,String tLocation) {
        this.userName=userName;
        this.userPassword=userPassword;
        this.surName =thName;
        this.tLocation=tLocation;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<Tickets> getTickets() {
        return tickets;
    }

    public String getSurName() {
        return surName;
    }

    public String gettLocation() {
        return tLocation;
    }
}
