import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Screen {
    private String screenName;
    private long seatNumber;
    private String grid;
    private HashMap<Character, ArrayList<String>> seatsGrid = new HashMap<>();
    private HashSet<Shows> showsInScreen = new HashSet<>();

    public Screen(String nameOfScreen,long seatNumber, HashMap<Character, ArrayList<String>>grid)
    {
        this.screenName = nameOfScreen;
        this.seatNumber = seatNumber;
        this.seatsGrid = grid;
    }
    public String getScreenName() {
        return screenName;
    }

    public HashSet<Shows> getShowsInScreen() {
        return showsInScreen;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public long getSeatNumber() {
        return seatNumber;
    }

    public String getGrid() {
        return grid;
    }

    public void setSeatNumber(long seatNumber) {
        this.seatNumber = seatNumber;
    }

    public HashMap<Character, ArrayList<String>> getSeatsGrid() {
        return seatsGrid;
    }

    public void setSeatsGrid(HashMap<Character, ArrayList<String>> seatsGrid) {
        this.seatsGrid = seatsGrid;
    }
}
