import java.util.*;

public class Utilities {

    public static HashMap<Character, ArrayList<String>> seatingPatternArrangeMent(Scanner scanner, long totalSeats, String pattern) {
        while (true) {
            String[] seatPatterns = pattern.split("\\*");
            int rows, seatPerRow, sections;
            if (seatPatterns.length == 3) {
                boolean valid = true;
                for (String seatPattern : seatPatterns) {
                    for (int j = 0; j < seatPattern.length(); j++) {
                        char ch = seatPattern.charAt(j);
                        if (ch > '9' || ch < '0') {
                            valid = false;
                            break;
                        }
                    }
                    if (!valid) {
                        break;
                    }
                }
                if (valid) {
                    rows = Integer.parseInt(seatPatterns[0]);
                    seatPerRow = Integer.parseInt(seatPatterns[1]);
                    sections = Integer.parseInt(seatPatterns[2]);
                } else {
                    System.out.println("Invalid pattern format. Please use the format rows*seatsPerRow*sections.");
                    System.out.print("Enter a valid seat pattern: ");
                    pattern = scanner.nextLine();
                    continue;
                }
            } else {
                System.out.println("Invalid pattern format. Please use the format rows*seatsPerRow*sections.");
                System.out.print("Enter a valid seat pattern: ");
                pattern = scanner.nextLine();
                continue;
            }

            int seatCalculations = rows * seatPerRow * sections;
            if (seatCalculations != totalSeats) {
                System.out.println("The entered seat split value is invalid. Total seats must equal " + totalSeats + ".");
                System.out.print("Enter a valid seat pattern: ");
                pattern = scanner.nextLine();
                continue;
            }

            HashMap<Character, ArrayList<String>> seatings = new HashMap<>();
            char currentRow = 'A';

            for (int r = 0; r < rows; r++) {
                ArrayList<String> rowSeats = new ArrayList<>();
                int seatNumber = 1;

                for (int section = 0; section < sections; section++) {
                    for (int seats = 0; seats < seatPerRow; seats++) {
                        rowSeats.add(currentRow + Integer.toString(seatNumber++));
                    }
                    if (section < sections - 1) {
                        rowSeats.add("\t<\t\t>\t");
                    }
                }

                seatings.put(currentRow, rowSeats);
                currentRow++;
            }
            return seatings;
        }
    }
}