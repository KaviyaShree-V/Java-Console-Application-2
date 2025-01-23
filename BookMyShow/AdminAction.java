import java.util.*;
import java.time.*;
public class AdminAction {
    public static Admin adminLog(Scanner scanner) {
        System.out.println("Enter the Admin Name");
        String aName = scanner.nextLine();
        System.out.println("Enter the Password");
        String pswd = scanner.nextLine();
        Admin admin = null;
        for (Admin admins : BookMyShow.getAdmin()) {
            if (admins instanceof Admin) {
                if (admins.getAdminName().equals(aName) && admins.getAdminPassword().equals(pswd)) {
                    admin = admins;
                    return admin;
                }
            }
        }
        return admin;
    }

    public static void actions(Scanner scanner) {
        System.out.println("The actions for Admin.........\n Select a choice!!!");
        System.out.println("1. Add Movies \n2. Add Theatre \n3. View Movies \n4. View Theatre \n5. Exit");
        int choiceAction = Integer.parseInt(scanner.nextLine());
        while (choiceAction!=5) {
            switch (choiceAction) {
                case 1:
                    System.out.println("Add Movies....");
                    addMovie(scanner);
                    break;
                case 2:
                    System.out.println("Add Theatre....");
                    addTheatre(scanner);
                    break;
                case 3:
                    System.out.println("View Movies....");
                    viewMovies();
                    break;
                case 4:
                    System.out.println("View Theatres....");
                    viewTheatre(scanner);
                    break;
                case 5:
                    System.out.println("Exiting....");
                    return;
                default:
                    System.out.println("Invalid choice. Try Again");
            }
            System.out.println("\nSelect a choice again:");
            System.out.println("1. Add Movies \n2. Add Theatre \n3. View Movies \n4. View Theatre \n5. Exit");
            choiceAction = scanner.nextInt();
            scanner.nextLine();
        }
    }

    public static void addTheatre(Scanner scanner) {
        System.out.println("Enter the Theatre name:");
        String theatreName = scanner.nextLine();

        System.out.println("Enter the Location:");
        String location = scanner.nextLine();

        System.out.println("Total theatres added in Book My Show" + BookMyShow.getTheatreName().keySet());
        if (BookMyShow.getTheatreName().containsKey(theatreName)) {
            System.out.println("Theatre already exists.");
            return;
        }
        System.out.println("Theatre doesn't exist.");
        System.out.println("Re-enter the Theatre name to add:");
        String retheatre = scanner.nextLine();

        System.out.println("Seat Availability:");
        int seats = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the number of screens present in the " + retheatre + " Theatre:");
        int nOfScreens = Integer.parseInt(scanner.nextLine());

        HashMap<String, ScreenMap> screenMapHashMap = new HashMap<>();

        for (int x = 1; x <= nOfScreens; x++) {
            System.out.println("Enter screen name for screen " + x + ":");
            String screenName = scanner.nextLine();
            screenMapHashMap.put(screenName, new ScreenMap(retheatre, seats, screenMapHashMap));
        }

        System.out.println("Enter the seat pattern:");
        String pattern = scanner.nextLine();

        HashMap<Character, ArrayList<String>> seatingPatterns = Utilities.generateSeatingPatterns(seats, pattern);

        if (seatingPatterns != null) {
            System.out.println("\nGenerated Seating Patterns:");

            for (char ch : seatingPatterns.keySet()) {
                System.out.println("Row " + ch + ": " + seatingPatterns.get(ch));
            }
        }
        Theatre theatres = new Theatre(retheatre, screenMapHashMap, location);
        BookMyShow.getTheatreName().put(retheatre, theatres);
        System.out.println("Updated theatres in BookMyShow: " + BookMyShow.getTheatreName().keySet());

        System.out.println(retheatre + " Theatre is added at " + location);
        actions(scanner);
    }


    public static void viewTheatre(Scanner scanner) {
        System.out.println("Choose : \n\t 1. View All Theatres \n\t 2. Specific Theatre \n\t 3. Exit");
        String input = scanner.nextLine().trim();
        int choose = 0;

        try {
            choose = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        }

        switch (choose) {
            case 1:
                System.out.println("Viewing All Theatres Page......");
                for (String theatreName : BookMyShow.getTheatreName().keySet()) {
                    Theatre theatre = BookMyShow.getTheatreName().get(theatreName);
                    System.out.println("Total theatres added in Book My Show" + BookMyShow.getTheatreName());
                    System.out.println("Name: " + theatreName);
                    System.out.println("Location: " + theatre.getTheatreLocation());
                    System.out.println("*********************************************************");
                }
                break;

            case 2:
                System.out.println("Total theatres added in Book My Show" + BookMyShow.getTheatreName().keySet());
                System.out.println("Enter the Theatre name you required:");
                String localTheatre = scanner.nextLine().trim();
                Theatre theatre = BookMyShow.getTheatreName().get(localTheatre);
                if (theatre != null) {
                    System.out.println("Theatre: " + localTheatre);
                    System.out.println("Location: " + theatre.getTheatreLocation());
                } else {
                    System.out.println("Theatre not found!");
                }
                break;

            case 3:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }


//    public static void addMovie(Scanner scanner) {
//        System.out.println("Enter a Movie to Add:");
//        String movieName = scanner.nextLine();
//
//        System.out.println("Location to add Movie:");
//        String location = scanner.nextLine();
//
//        Theatre selectedTheatre = null;
//        for (Map.Entry<String, Theatre> entry : BookMyShow.getTheatreName().entrySet()) {
//            if (entry.getValue().getTheatreLocation().equals(location)) {
//                selectedTheatre = entry.getValue();
//                break;
//            }
//        }
//
//        if (selectedTheatre == null) {
//            System.out.println("Theatre not found in the given location.");
//            return;
//        }
//
//        System.out.println("Enter the date of the Movie (dd:mm:yy):");
//        LocalDate date;
//        try {
//            date = LocalDate.parse(scanner.nextLine(), BookMyShow.getDateFormatter());
//        } catch (Exception e) {
//            System.out.println("Invalid date format! Please use dd:mm:yy.");
//            return;
//        }
//
//        System.out.println("Enter the duration of the movie (in minutes):");
//        int duration;
//        try {
//            duration = Integer.parseInt(scanner.nextLine());
//        } catch (Exception e) {
//            System.out.println("Invalid duration!");
//            return;
//        }
//
//        System.out.println("Enter the price of a single ticket:");
//        int price;
//        try {
//            price = Integer.parseInt(scanner.nextLine());
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid ticket price!");
//            return;
//        }
//
//        System.out.println("Available Screens:");
//        for (String screenKey : selectedTheatre.getScreenHashMap().keySet()) {
//            System.out.println(screenKey);
//        }
//
//        System.out.println("Enter the screen name:");
//        String screenName = scanner.nextLine();
//        ScreenMap screen = selectedTheatre.getScreenHashMap().get(screenName);
//
//        if (screen == null) {
//            System.out.println("Invalid screen name!");
//            return;
//        }
//
//        System.out.println("Enter the start time of the show (HH:mm):");
//        LocalTime startTime;
//        try {
//            startTime = LocalTime.parse(scanner.nextLine(), BookMyShow.getTimeFormatter());
//        } catch (Exception e) {
//            System.out.println("Invalid time format! Please use HH:mm.");
//            return;
//        }
//
//        LocalTime endTime = startTime.plusMinutes(duration + 30); // Including buffer time
//        for (Shows existingShow : screen.getShows()) {
//            if (date.isEqual(existingShow.getShowDateForMovie())
//                    && !(endTime.isBefore(existingShow.getMovieStartTime()) || startTime.isAfter(existingShow.getMovieEndTime()))) {
//                System.out.println("Show overlaps with an existing one.");
//                return;
//            }
//        }
//        HashMap<Character, ArrayList<String>> duplicateSeatingArrangement = new HashMap<>();
//        for (Map.Entry<Character, ArrayList<String>> entry : screen.getGridSeats().entrySet()) {
//            duplicateSeatingArrangement.put(entry.getKey(), new ArrayList<>(entry.getValue()));
//        }
//
//        Shows newShow = new Shows(startTime, endTime, date, screen, price, duplicateSeatingArrangement);
//        screen.getShows().add(newShow);
//
//        Movies newMovie = new Movies(movieName, location, selectedTheatre, screen, newShow, date, duration);
//        BookMyShow.getTheatres().add(selectedTheatre);
//        BookMyShow.getTheatreName().putIfAbsent(selectedTheatre.getTheatreName(), selectedTheatre);
//
//        System.out.println("Movie added successfully!");
//    }


    public static void addMovie(Scanner scanner) {
        System.out.println("Enter a Movie to Add:");
        String movieName = scanner.nextLine();

        System.out.println("Location to add Movie:");
        String location = scanner.nextLine();

        Theatre selectedTheatre = null;
        for (Map.Entry<String, Theatre> entry : BookMyShow.getTheatreName().entrySet()) {
            if (entry.getValue().getTheatreLocation().equals(location)) {
                selectedTheatre = entry.getValue();
                break;
            }
        }

        if (selectedTheatre == null) {
            System.out.println("Theatre not found in the given location.");
            return;
        }

        LocalDate date = null;
        while (date == null) {
            System.out.println("Enter the date of the Movie (dd:mm:yy):");
            try {
                date = LocalDate.parse(scanner.nextLine(), BookMyShow.getDateFormatter());
            } catch (Exception e) {
                System.out.println("Invalid date format! Please use dd:mm:yy.(Sample - 16/06/2026)");
                System.out.println("Try again:");
            }
        }

        System.out.println("Enter the duration of the movie (in minutes):");
        int duration;
        try {
            duration = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid duration!");
            return;
        }

        System.out.println("Enter the price of a single ticket:");
        int price;
        try {
            price = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ticket price!");
            return;
        }

        System.out.println("Available Screens:");
        for (String screenKey : selectedTheatre.getScreenHashMap().keySet()) {
            System.out.println(screenKey);
        }

        System.out.println("Enter the screen name:");
        String screenName = scanner.nextLine();
        ScreenMap screen = selectedTheatre.getScreenHashMap().get(screenName);

        if (screen == null) {
            System.out.println("Invalid screen name!");
            return;
        }

        System.out.println("Enter the start time of the show (HH:mm):");
        LocalTime startTime;
        try {
            startTime = LocalTime.parse(scanner.nextLine(), BookMyShow.getTimeFormatter());
        } catch (Exception e) {
            System.out.println("Invalid time format! Please use HH:mm.");
            return;
        }

        LocalTime endTime = startTime.plusMinutes(duration + 30); // Including buffer time
        for (Shows existingShow : screen.getShows()) {
            if (date.isEqual(existingShow.getShowDateForMovie())
                    && !(endTime.isBefore(existingShow.getMovieStartTime()) || startTime.isAfter(existingShow.getMovieEndTime()))) {
                System.out.println("Show overlaps with an existing one.");
                return;
            }
        }
        HashMap<Character, ArrayList<String>> duplicateSeatingArrangement = new HashMap<>();
        for (Map.Entry<Character, ArrayList<String>> entry : screen.getGridSeats().entrySet()) {
            duplicateSeatingArrangement.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }

        Shows newShow = new Shows(startTime, endTime, date, screen, price, duplicateSeatingArrangement);
        screen.getShows().add(newShow);

        Movies newMovie = new Movies(movieName, location, selectedTheatre, screen, newShow, date, duration);
        BookMyShow.getTheatres().add(selectedTheatre);
        BookMyShow.getTheatreName().putIfAbsent(selectedTheatre.getTheatreName(), selectedTheatre);

        System.out.println("Movie added successfully!");
    }



    public static void viewMovies() {
        System.out.println("Movies in BookMyShow:");
        for (int i = 0; i < BookMyShow.getTheatres().size(); i++) {
            Theatre theatre = BookMyShow.getTheatres().get(i);
            System.out.println("Theatre: " + theatre.getTheatreName());

            HashMap<String, ScreenMap> screens = theatre.getScreenHashMap();
            for (String screenKey : screens.keySet()) {
                ScreenMap screen = screens.get(screenKey);
                System.out.println("  Screen: " + screen.getName());

                HashSet<Shows> shows = screen.getShows();
                for (Shows show : shows) {
                    System.out.println("    Show Date: " + show.getShowDateForMovie());
                    System.out.println("    Start Time: " + show.getMovieStartTime());
                }
            }
        }
    }

}
