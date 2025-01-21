import java.time.LocalDate;

public class Movies {
    private String movieName;
    private String location;
    private Theatre theatre;
    private ScreenMap screen;
    private Shows show;
    private LocalDate localDate;
    private double duration;


    public Movies(String movieName,String location,Theatre theatre,ScreenMap screen,Shows show,LocalDate localDate,double duration){
        this.movieName=movieName;
        this.location=location;
        this.theatre=theatre;
        this.screen=screen;
        this.show=show;
        this.localDate=localDate;
        this.duration=duration;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getLocation() {
        return location;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public ScreenMap getScreen() {
        return screen;
    }

    public Shows getShow() {
        return show;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public double getDuration() {
        return duration;
    }
}
