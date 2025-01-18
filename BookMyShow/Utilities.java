import java.util.HashMap;
import java.util.ArrayList;

public class Utilities {

    static HashMap<Character, ArrayList<String>> generateSeatingPatterns(int totalSeats, String pattern) {
        String[] theatreSeatCounts = pattern.split("\\*");
        int sumOfRows = 0;
        for (String seatsCount : theatreSeatCounts) {
            sumOfRows += Integer.parseInt(seatsCount);
        }
        if (totalSeats % sumOfRows != 0) {
            System.out.println("The entered seat split value is invalid.");
            return null;
        }
        int totalRows = totalSeats / sumOfRows;
        HashMap<Character, ArrayList<String>> seatingArrangement = new HashMap<>();

        char rows = 'z';
        for (int row = 0; row < totalRows; row++) {
            ArrayList<String> rowSeat = new ArrayList<>();
            for (int i = 0; i < theatreSeatCounts.length; i++) {
                int seatsInColumn = Integer.parseInt(theatreSeatCounts[i]);
                for (int j = 0; j < seatsInColumn; j++) {
                    rowSeat.add("[ ]");
                }
                if (i < theatreSeatCounts.length - 1) {
                    rowSeat.add("\t\t\t\t");
                }
            }
            seatingArrangement.put(rows, rowSeat);
            rows++;
        }
        return seatingArrangement;
    }
}
