import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookMyShow {

    public static ArrayList<Admin> admin= new ArrayList<>();
    public static ArrayList<User> user=new ArrayList<>();

    public static ArrayList<Theatre> theatres = new ArrayList<>();
    private static HashMap<String , Theatre> theatreNameMap = new HashMap<>();

    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static {
        admin.add(new Admin("BMS", "bms565"));
    }

    public static ArrayList<Admin> getAdmin() {
        return admin;
    }

    public static ArrayList<User> getUser() {
        return user;
    }

    public static ArrayList<Theatre> getTheatres() {
        return theatres;
    }

    public static HashMap<String,Theatre> getTheatreName() {
        return theatreNameMap;
    }

    public static DateTimeFormatter getTimeFormatter() {
        return timeFormatter;
    }

    public static DateTimeFormatter getDateFormatter() {
        return dateFormatter;
    }
}

