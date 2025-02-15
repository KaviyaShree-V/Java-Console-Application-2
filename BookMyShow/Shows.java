import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Shows {

    private LocalTime showStartTime;
    private LocalTime showEndTime;
    private LocalDate dateOfShow;
    private Screen screen;
    private HashMap<Character, ArrayList<String>> seatsAndGrid = new HashMap<>();
    private int ticketPrice;

    public Shows(LocalDate dateOfShow, LocalTime startTime, LocalTime endTime, Screen screen, HashMap<Character, ArrayList<String>> seatsAndGrid, int price) {
        this.dateOfShow = dateOfShow;
        this.showStartTime = startTime;
        this.showEndTime = endTime;
        this.screen = screen;
        this.seatsAndGrid = seatsAndGrid;
        this.ticketPrice = price;
    }

    public LocalDate getDateOfShow() {
        return dateOfShow;
    }

    public LocalTime getShowStartTime() {
        return showStartTime;
    }

    public void setSeatsAndGrid(HashMap<Character, ArrayList<String>> seatsAndGrid) {
        this.seatsAndGrid = seatsAndGrid;
    }

    public LocalTime getShowEndTime() {
        return showEndTime;
    }

    public HashMap<Character, ArrayList<String>> getSeatsAndGrid() {
        return seatsAndGrid;
    }

    public Screen getScreens() {
        return screen;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public String toString() {
        return " * " + showStartTime.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Shows shows = (Shows) o;
        return Objects.equals(this.showStartTime, shows.showStartTime) && Objects.equals(this.showEndTime, shows.showEndTime) && Objects.equals(dateOfShow, shows.getDateOfShow());
    }

    @Override
    public int hashCode() {
        return Objects.hash(showStartTime, showEndTime, dateOfShow);
    }
}
