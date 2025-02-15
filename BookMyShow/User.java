import java.util.ArrayList;

public class User {
    private String userName;
    private String userPassWord;
    private String viewName;
    private String userLocation;
    private static ArrayList<Tickets> tickets = new ArrayList<>();

    public User(String userName,String userPassWord,String viewName,String userLocation){
        this.userName=userName;
        this.userPassWord=userPassWord;
        this.viewName=viewName;
        this.userLocation=userLocation;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public String getViewName() {
        return viewName;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public ArrayList<Tickets> getTickets() {
        return tickets;
    }
}
