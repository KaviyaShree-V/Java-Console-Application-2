import java.time.LocalDate;

public class Movies {
    private String name;
    private LocalDate date;
    private long duration;
    private String location;
    private Theatre theatre;
    private Screen screen;
    private Shows show;
    private long ticket;

    public Movies(String name,String location,LocalDate date,long duration,Theatre theatre,Screen screen,Shows show,long ticket)
    {
        this.name = name;
        this.location = location;
        this.date = date;
        this.duration = duration;
        this.theatre = theatre;
        this.screen = screen;
        this.show = show;
        this.ticket=ticket;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocation()
    {
        return location;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Screen getScreen() {
        return screen;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public Shows getShow() {
        return show;
    }

    public long getDuration() {
        return duration;
    }

    public long getTicket() {
        return ticket;
    }
}
