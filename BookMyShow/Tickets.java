import java.time.*;
import java.util.*;

public class Tickets{
    private String theatreName;
    private String movieName;
    private String screenName;
    private LocalDate showDate;
    private LocalTime showTime;
    private int noOfTickets;
    private HashSet<String> seatNumbers;
    private int ticketAmount;

    public Tickets(String theatreName,String movieName,String screenName,LocalTime showTime,int noOfTickets,LocalDate showDate,HashSet<String> seatNumbers,int ticketAmount ){
        this.theatreName=theatreName;
        this.movieName=movieName;
        this.screenName=screenName;
        this.showTime=showTime;
        this.showDate=showDate;
        this.noOfTickets=noOfTickets;
        this.ticketAmount=ticketAmount;
    }
    public String getTheatreName() {
        return theatreName;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getScreenName() {
        return screenName;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public HashSet<String> getSeatNumbers() {
        return seatNumbers;
    }

    public int getTicketAmount() {
        return ticketAmount;
    }
}
