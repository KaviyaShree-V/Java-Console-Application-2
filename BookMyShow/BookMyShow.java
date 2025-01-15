import java.util.*;

public class BookMyShow {

    public static ArrayList<Admin> admin= new ArrayList<>();
    public static ArrayList<User> user=new ArrayList<>();

    static {
        admin.add(new Admin("BMS","bms565"));
    }
    public static ArrayList<Admin> getAdmin() {
        return admin;
    }

    public static ArrayList<User> getUser() {
        return user;
    }
}
