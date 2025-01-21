import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ScreenMap {
    private String name;
    private int noOfSeats;
    private HashMap<String, ScreenMap> seatArrangement;
    private HashMap<Character, ArrayList<String>> gridSeats = new HashMap<>();
    private HashSet<Shows> shows= new HashSet<>();

    public ScreenMap(String name, int noOfSeats , HashMap<String, ScreenMap> seatArrangement){
        this.name=name;
        this.noOfSeats= noOfSeats;
        this.seatArrangement= seatArrangement;
    }

    public String getName() {
        return name;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public HashMap<String, ScreenMap> getSeatArrangement() {
        return seatArrangement;
    }

    public HashSet<Shows> getShows() {
        return shows;
    }

    public HashMap<Character, ArrayList<String>> getGridSeats() {
        return gridSeats;
    }
}
