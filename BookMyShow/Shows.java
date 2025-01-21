import java.time.*;
import java.util.*;
public class Shows {
    private LocalTime movieStartTime;
    private LocalTime movieEndTime;
    private LocalDate showDateForMovie;
    private ScreenMap screen;
    private int movieTicket;
    private  HashMap<Character,ArrayList<String>> seatRequire;
    public Shows(LocalTime movieStartTime,LocalTime movieEndTime,LocalDate showDateForMovie,ScreenMap screen,int movieTicket,HashMap<Character,ArrayList<String>> seatRequire){
        this.seatRequire=seatRequire;
        this.movieEndTime=movieEndTime;
        this.movieStartTime=movieStartTime;
        this.movieTicket=movieTicket;
        this.showDateForMovie=showDateForMovie;
        this.screen=screen;
    }

    public LocalTime getMovieStartTime() {
        return movieStartTime;
    }

    public LocalTime getMovieEndTime() {
        return movieEndTime;
    }

    public LocalDate getShowDateForMovie() {
        return showDateForMovie;
    }

    public ScreenMap getScreen() {
        return screen;
    }

    public int getMovieTicket() {
        return movieTicket;
    }

    public HashMap<Character, ArrayList<String>> getSeatRequire() {
        return seatRequire;
    }

    public void setSeatRequire(HashMap<Character, ArrayList<String>> seatRequire) {
        this.seatRequire = seatRequire;
    }
}
