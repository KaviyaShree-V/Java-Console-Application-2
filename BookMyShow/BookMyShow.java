import java.util.*;

public class BookMyShow {

    public static ArrayList<Admin> admin= new ArrayList<>();
    public static ArrayList<User> user=new ArrayList<>();

    public static ArrayList<Theatre> theatres = new ArrayList<>();
    private static HashMap<String , Theatre> theatreName = new HashMap<>();

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
        return theatreName;
    }
}
