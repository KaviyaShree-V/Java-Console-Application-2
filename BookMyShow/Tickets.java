import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

public class Tickets {
    private String theatreName;
    private String movieName;
    private String screenName;
    private LocalDate showDate;
    private LocalTime showTime;
    private int noOfTickets;
    private HashSet<String> seatNumbers;
    private int price;
    private String location;

    public Tickets(String theatreName,String movieName,String screenName,LocalDate dateOfShow,LocalTime timeOfShow, int noOfTickets, HashSet<String> seatNumbers,int price,String location)
    {
        this.theatreName = theatreName;
        this.movieName = movieName;
        this.screenName = screenName;
        this.showDate = dateOfShow;
        this.showTime = timeOfShow;
        this.noOfTickets = noOfTickets;
        this.seatNumbers = seatNumbers;
        this.price = price;
        this.location=location;
    }

    public LocalDate getShowDate() {
        return showDate;
    }
    public String getLocation(){
        return location;
    }

    public HashSet<String> getSeatNumbers() {
        return seatNumbers;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public String getScreenName() {
        return screenName;
    }

    public int getPrice() {
        return price;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }
}
