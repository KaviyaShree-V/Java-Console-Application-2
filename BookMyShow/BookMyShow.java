import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class BookMyShow {
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<String> locations = new ArrayList<>();
    private static HashMap<String,ArrayList<Movies>> movieAndMovieName = new HashMap<>();
    private static HashMap<String,Theatre> theatreAndTheatreNAme = new HashMap<>();
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static {
        admins.add(new Admin("admin","a123"));
        users.add(new User("k","5","Pollachi"));
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static ArrayList<String> getLocations() {
        return locations;
    }

    public static HashMap<String, ArrayList<Movies>> getMovieAndMovieName() {
        return movieAndMovieName;
    }

    public static HashMap<String,Theatre> getTheatreAndTheatreNAme() {
        return theatreAndTheatreNAme;
    }

    public static DateTimeFormatter getDateFormatter() {
        return dateFormatter;
    }

    public static DateTimeFormatter getTimeFormatter() {
        return timeFormatter;
    }
}
