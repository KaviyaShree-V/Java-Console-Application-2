import java.util.HashMap;

public class Theatre {
    private String name;
    private HashMap<String,Screen> screenName = new HashMap<>();
    private String location;

    public Theatre(String name, HashMap<String,Screen> screenNameAndObject, String location)
    {
        this.name = name;
        this.screenName = screenNameAndObject;
        this.location = location;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public HashMap<String,Screen> getScreenName() {
        return screenName;
    }

    public String getLocation() {
        return location;
    }
}
