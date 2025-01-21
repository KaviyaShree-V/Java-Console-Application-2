import java.util.HashMap;

public class Theatre {
    private String theatreName;
    private String theatreLocation;
    private HashMap<String,ScreenMap> screenHashMap;

    public Theatre(String theatreName, HashMap<String,ScreenMap> screenHashMap, String theatreLocation){
        this.theatreName = theatreName;
        this.screenHashMap=screenHashMap;
        this.theatreLocation=theatreLocation;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public String getTheatreLocation() {
        return theatreLocation;
    }

    public HashMap<String,ScreenMap> getScreenHashMap() {
        return screenHashMap;
    }
}
