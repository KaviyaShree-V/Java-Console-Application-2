import java.util.HashMap;
import java.util.ArrayList;

public class Utilities {

    public static HashMap<Character, ArrayList<String>> generateSeatingPatterns(int totalSeats, String pattern) {
        String[] seatPatternParts = pattern.split("\\*");
        int rows = Integer.parseInt(seatPatternParts[0]);
        int seatsPerRow = Integer.parseInt(seatPatternParts[1]);
        int sections = Integer.parseInt(seatPatternParts[2]);

        int calculatedTotalSeats = rows * seatsPerRow * sections;

        if (calculatedTotalSeats != totalSeats) {
            System.out.println("The entered seat split value is invalid.");
            return null;
        }

        HashMap<Character, ArrayList<String>> seatingArrangement = new HashMap<>();
        char currentRow = 'A';

        for (int r = 0; r < rows; r++) {
            ArrayList<String> rowSeats = new ArrayList<>();

            for (int sec = 0; sec < sections; sec++) {
                for (int seat = 0; seat < seatsPerRow; seat++) {
                    rowSeats.add(currentRow + String.valueOf(seat + 1));
                }
                if (sec < sections - 1) {
                    rowSeats.add("\t\t");
                }
            }

            seatingArrangement.put(currentRow, rowSeats);
            currentRow++;
        }

        return seatingArrangement;
    }
}
