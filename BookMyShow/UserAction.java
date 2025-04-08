import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserAction {
    public static User userLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Name:");
        String adminName = scanner.nextLine();
        System.out.println("Enter your Password:");
        String adminPassword = scanner.nextLine();
        User users = null;
        for (User user : BookMyShow.getUsers()) {
            if (user.getUserName().equals(adminName) && user.getUserPassWord().equals(adminPassword)) {
                return user;
            }
        }
        return users;
    }

    public static void actions(User currentUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n ::::::::::::::::  Actions for USER  ::::::::::::::::");
        while (true) {
            System.out.println("\t\t\t1. Show Movies");
            System.out.println("\t\t\t2. Book Movies");
            System.out.println("\t\t\t3. View Tickets");
            System.out.println("\t\t\t4. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println(" Show Movies...");
                    showMovies(currentUser);
                    break;
                case 2:
                    System.out.println("Book Movies...");
                    bookMovies(currentUser, new ArrayList<>());
                    break;
                case 3:
                    System.out.println("View Tickets...");
                    viewTickets(currentUser);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Enter a Valid Choice...");
            }
        }
    }

    public static void showMovies(User currentUser) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Movies> movieList = new ArrayList<>();
        var movieFind = BookMyShow.getMovieAndMovieName().keySet();
        System.out.println("\n:::::::::::::::: MOVIES ::::::::::::::::");
        while (true) {
            System.out.println("\n\t\t 1. Show all Movies in all Locations..");
            System.out.println("\n\t\t 2. Show Movies in Specific locations..");
            System.out.println("\n\t\t 3. View tickets..");
            System.out.println("\n\t\t 4. EXIT..");
            System.out.println("Enter your choice :");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("\t\t:::::::::::: View Movies in all Locations :::::::::::::::::");
                    //var movieFind = BookMyShow.getMovieAndMovieName().keySet();
                    if (movieFind.isEmpty()) {
                        System.out.println("No movies found..");
                        break;
                    }
                    for (var movie : movieFind) {
                        ArrayList<Movies> moviesList = new ArrayList<>(BookMyShow.getMovieAndMovieName().get(movie));  // Avoid modification error
                        for (Movies moviess : moviesList) {
                            System.out.println("Theatre: " + moviess.getTheatre().getName());
                            System.out.println("Movie Name: " + movie);
                            System.out.println("Screen: " + moviess.getScreen().getScreenName());
                            System.out.println("Location: " + moviess.getLocation());
                            System.out.println("Date: " + moviess.getDate().format(BookMyShow.getDateFormatter()));
                            System.out.println("Show Start Time: " + moviess.getShow().getShowStartTime().format(BookMyShow.getTimeFormatter()));
                            System.out.println("Show End Time: " + moviess.getShow().getShowEndTime().format(BookMyShow.getTimeFormatter()));
                            System.out.println("Movie Amount :" + moviess.getTicket());
                            System.out.println("\n````````````````````````````````````````");
                            movieList.add(moviess);  // Collect movies in a separate list
                        }
                    }
                    break;
                case 2:
                    System.out.println("\t\t:::::::::::: View Movies in specific Locations :::::::::::::::::");
                    System.out.println("\n     Enter the LOCATION to view Movies :");
                    String mov = scanner.nextLine();

                    boolean locationFound = false;
                    for (var entry : BookMyShow.getMovieAndMovieName().entrySet()) {
                        String movieName = entry.getKey();
                        ArrayList<Movies> movieEntries = entry.getValue();

                        for (Movies moviess : movieEntries) {
                            if (moviess.getLocation().equalsIgnoreCase(mov)) {
                                locationFound = true;
                                System.out.println("Theatre: " + moviess.getTheatre().getName());
                                System.out.println("Movie Name: " + movieName);
                                System.out.println("Screen: " + moviess.getScreen().getScreenName());
                                System.out.println("Location: " + moviess.getLocation());
                                System.out.println("Date: " + moviess.getDate().format(BookMyShow.getDateFormatter()));
                                System.out.println("Show Start Time: " + moviess.getShow().getShowStartTime().format(BookMyShow.getTimeFormatter()));
                                System.out.println("Show End Time: " + moviess.getShow().getShowEndTime().format(BookMyShow.getTimeFormatter()));
                                System.out.println("Movie Amount: " + moviess.getTicket());
                                System.out.println("\n````````````````````````````````````````");
                                movieList.add(moviess);
                            }
                        }
                    }

                    if (!locationFound) {
                        System.out.println("No movies found in the specified location.");
                    }
                    break;
                case 3:
                    viewTickets(currentUser);
                    break;
                case 4:
                    System.out.println("\t\tExiting");
                    return;
                default:
                    System.out.println("Invalid Choice...");
                    break;
            }
            if (!(movieFind.isEmpty())) {
                System.out.println("Do you need to Book Movies....");
                System.out.println("\n\t1. Yes \n\t2. No");
                System.out.println("Enter your choice:");
                int select = Integer.parseInt(scanner.nextLine());
                if (select == 1) {
                    bookMovies(currentUser, movieList);
                } else {
                    System.out.println(":::::: Returning Back :::::::");
                    return;
                }
            }
        }
    }




    public static HashSet<String> bookMovies(User currentUser, ArrayList<Movies> movies) {
        System.out.println(":::::::::::::::::::: BOOK YOUR MOVIES :::::::::::::::::::");
        LocalDate currentDate = LocalDate.now();
        System.out.println("Current Date: " + currentDate.format(BookMyShow.getDateFormatter()));
        System.out.println("Current Location: " + currentUser.getUserLocation());

        if (movies.isEmpty()) {
            System.out.println("OOPS!!!! No movies are Available!!!");
            return new HashSet<>();
        }

        System.out.println("Available Movies in your location:");
        for (Movies movie : movies) {
            System.out.println("- " + movie.getName() + " at " + movie.getTheatre().getName());
        }

        seatBooking(currentUser, movies);
        HashSet<String> bookedMovies = new HashSet<>();
        for (Movies movie : movies) {
            bookedMovies.add(movie.getTheatre().getName());
        }
        return bookedMovies;
    }

    public static void seatBooking(User currentUser, ArrayList<Movies> movies) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(":::::::::::::::::::   BOOKING SEATS :::::::::::::::::::");

        LocalDate selectedDate = LocalDate.now();
        String userLocation = currentUser.getUserLocation();

        while (true) {
            HashSet<String> availableTheatres = new HashSet<>();

            for (Movies movie : movies) {
                if (movie.getLocation().equalsIgnoreCase(userLocation) &&
                        movie.getShow().getDateOfShow().equals(selectedDate)) {
                    availableTheatres.add(movie.getTheatre().getName());
                }
            }

            if (availableTheatres.isEmpty()) {
                System.out.println("\nNo theatres found for today.");
                System.out.println("1. Change Date\n2. Change Location\n3. Exit");
                System.out.print("Enter your choice: ");
                String input = scanner.nextLine();

                if (input.equals("1")) {
                    LocalDate newDate = changeLocDate(currentUser);
                    if (newDate != null) selectedDate = newDate;
                } else if (input.equals("2")) {
                    changeLocation(currentUser);
                    userLocation = currentUser.getUserLocation();
                } else {
                    return;
                }
                continue;
            }

            System.out.println("Available Theatres in " + userLocation + " on " +
                    selectedDate.format(BookMyShow.getDateFormatter()) + ":");
            for (String theatre : availableTheatres) {
                System.out.println("- " + theatre);
            }

            String selectedTheatre = "";
            while (!availableTheatres.contains(selectedTheatre)) {
                System.out.print("Enter the theatre name to book a seat: ");
                selectedTheatre = scanner.nextLine().trim();
                if (!availableTheatres.contains(selectedTheatre)) {
                    System.out.println("Invalid theatre. Try again.");
                }
            }

            ArrayList<Movies> theatreMovies = new ArrayList<>();
            for (Movies movie : movies) {
                if (movie.getTheatre().getName().equalsIgnoreCase(selectedTheatre) &&
                        movie.getShow().getDateOfShow().equals(selectedDate)) {
                    theatreMovies.add(movie);
                }
            }

            if (theatreMovies.isEmpty()) {
                System.out.println("No movies available in this theatre on the selected date.");
                return;
            }

            for (int i = 0; i < theatreMovies.size(); i++) {
                System.out.println((i + 1) + ". " + theatreMovies.get(i).getName());
            }

            int movieChoice = -1;
            while (movieChoice < 0 || movieChoice >= theatreMovies.size()) {
                System.out.print("Select movie number: ");
                String input = scanner.nextLine();
                try {
                    movieChoice = Integer.parseInt(input) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Try again.");
                }
            }

            Movies selectedMovie = theatreMovies.get(movieChoice);
            Shows currentShow = selectedMovie.getShow();
            HashMap<Character, ArrayList<String>> seatingGrid = currentShow.getSeatsAndGrid();

            int availableSeats = 0;
            for (Map.Entry<Character, ArrayList<String>> row : seatingGrid.entrySet()) {
                for (String seat : row.getValue()) {
                    if (!seat.equals("[X]")) {
                        availableSeats++;
                    }
                }
            }

            int noOfSeats = 0;
            while (noOfSeats <= 0 || noOfSeats > availableSeats) {
                System.out.print("Enter number of seats to book (max " + availableSeats + "): ");
                try {
                    noOfSeats = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Try again.");
                }
            }

            displaySeating(seatingGrid);
            HashSet<String> bookedSeats = new HashSet<>();

            for (int i = 0; i < noOfSeats; i++) {
                while (true) {
                    System.out.print("Enter seat " + (i + 1) + ": ");
                    String seat = scanner.nextLine().toUpperCase();
                    if (seat.length() < 2) {
                        System.out.println("Invalid seat format.");
                        continue;
                    }

                    char row = seat.charAt(0);
                    if (!seatingGrid.containsKey(row)) {
                        System.out.println("Invalid row.");
                        continue;
                    }

                    ArrayList<String> seats = seatingGrid.get(row);
                    boolean found = false;
                    for (int j = 0; j < seats.size(); j++) {
                        if (seats.get(j).equals(seat)) {
                            seats.set(j, "[X]");
                            bookedSeats.add(seat);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Seat already booked or invalid.");
                    } else {
                        break;
                    }
                }
            }

            long totalAmount = selectedMovie.getTicket() * noOfSeats;
            System.out.println("Total Amount: ₹" + totalAmount);
            confirmBooking(currentShow, seatingGrid);

            Tickets newTicket = new Tickets(
                    selectedMovie.getTheatre().getName(),
                    selectedMovie.getName(),
                    selectedMovie.getScreen().getScreenName(),
                    selectedMovie.getShow().getDateOfShow(),
                    selectedMovie.getShow().getShowStartTime(),
                    noOfSeats,
                    bookedSeats,
                    (int) totalAmount,
                    selectedMovie.getLocation()
            );
            currentUser.getTickets().add(newTicket);
            System.out.println("Tickets successfully booked!");
            return;
        }
    }

    public static LocalDate changeLocDate(User currentUser) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = BookMyShow.getDateFormatter();
        LocalDate currentDate = LocalDate.now();

        while (true) {
            System.out.print("Enter the new date (dd/MM/yyyy): ");
            String inputDate = scanner.nextLine().trim();

            try {
                LocalDate newDate = LocalDate.parse(inputDate, formatter);
                if (newDate.isBefore(currentDate)) {
                    System.out.println("Date cannot be in the past.");
                    continue;
                }
                return newDate;
            } catch (Exception e) {
                System.out.println("Invalid date format.");
            }
        }
    }

    public static void changeLocation(User currentUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the new location: ");
        String newLocation = scanner.nextLine().trim();
        currentUser.setUserLocation(newLocation);
        System.out.println("Location updated to: " + newLocation);
    }

    public static void displaySeating(HashMap<Character, ArrayList<String>> seatingGrid) {
        for (Map.Entry<Character, ArrayList<String>> entry : seatingGrid.entrySet()) {
            System.out.print(entry.getKey() + "    ");
            for (String seat : entry.getValue()) {
                System.out.print(seat + "   ");
            }
            System.out.println();
        }
    }

    public static void confirmBooking(Shows currentShow, HashMap<Character, ArrayList<String>> seatingGrid) {
        System.out.println("Booking confirmed! Updated seating arrangement:");
        displaySeating(seatingGrid);
    }

    public static void viewTickets(User currentUser) {
        System.out.println(":::::::::::::::::   VIEW BOOKED TICKETS :::::::::::::::::::");
        var viewtickets = currentUser.getTickets();

        if (viewtickets.isEmpty()) {
            System.out.println("No tickets booked yet.");
            return;
        }

        for (Tickets ticket : viewtickets) {
            System.out.println("\n============================================");
            System.out.println("Theatre Name   : " + ticket.getTheatreName());
            System.out.println("Location       : " + ticket.getLocation());
            System.out.println("Movie Name     : " + ticket.getMovieName());
            System.out.println("Screen Name    : " + ticket.getScreenName());
            System.out.println("Show Time      : " + ticket.getShowTime().format(BookMyShow.getTimeFormatter()));
            System.out.println("Amount Paid    : ₹" + ticket.getPrice());
            System.out.println("Seat Numbers   : " + String.join(", ", ticket.getSeatNumbers()));
            System.out.println("============================================\n");
        }
    }





//    public static HashSet<String> bookMovies(User currentUser, ArrayList<Movies> movies) {
//        System.out.println(":::::::::::::::::::: BOOK YOUR MOVIES :::::::::::::::::::");
//        boolean moviesAvailable = !movies.isEmpty();
//        LocalDate currentDate = LocalDate.now();
//        System.out.println("Current Date: " + currentDate.format(BookMyShow.getDateFormatter()));
//        System.out.println("Current Location: " + currentUser.getUserLocation());
//        var movieFind = BookMyShow.getMovieAndMovieName().keySet();
//        if (movieFind.isEmpty()) {
//            System.out.println("OOPS!!!! No movies are Available!!!");
//        }
//        for (var theatre : BookMyShow.getTheatreAndTheatreNAme().keySet()) {
//            if (BookMyShow.getLocations().contains(currentUser.getUserLocation())) {
//                if (BookMyShow.getTheatreAndTheatreNAme().get(theatre).getLocation().equals(currentUser.getUserLocation())) {
//                    if (moviesAvailable) {
//                        System.out.println("Available Movies in your location:");
//                        for (Movies movie : movies) {
//                            System.out.println("- " + movie.getName() + " at " + movie.getTheatre().getName());
//                        }
//                    }
//                    if (!moviesAvailable) {
//                        System.out.println("Do you want to change the date or location?");
//                        LocalDate newDate = changeLocDate(currentUser);
//
//                        movies = getMoviesForLocationAndDate(currentUser.getUserLocation(), newDate);
//
//                        if (movies.isEmpty()) {
//                            System.out.println("Still, no movies found in your updated location.");
//                            return new HashSet<>();
//                        }
//                    }
//                }
//            }
////else {
////                System.out.println("No movies found in your current location.");
////            }
////            if (!moviesAvailable) {
////                System.out.println("Do you want to change the date or location?");
////                LocalDate newDate = changeLocDate(currentUser);
////
////                movies = getMoviesForLocationAndDate(currentUser.getUserLocation(), newDate);
////
////                if (movies.isEmpty()) {
////                    System.out.println("Still, no movies found in your updated location.");
////                    return new HashSet<>();
////                }
////            }
//            HashMap<String, HashSet<Shows>> overlapSeats = new HashMap<>();
//            HashSet<String> bookedMovies = new HashSet<>();
//
//            for (var movie : movies) {
//                overlapSeats.computeIfAbsent(movie.getTheatre().getName(), k -> new HashSet<>()).add(movie.getShow());
//                bookedMovies.add(movie.getTheatre().getName());
//            }
//            seatBooking(currentUser, movies);
//            return bookedMovies;
//        }
//        return null;
//    }
//
//    public static ArrayList<Movies> getMoviesForLocationAndDate(String location, LocalDate date) {
//        ArrayList<Movies> resMovies = new ArrayList<>();
//
//        for (var entry : BookMyShow.getMovieAndMovieName().entrySet()) {
//            ArrayList<Movies> movieEntries = entry.getValue();
//            for (Movies movie : movieEntries) {
//                if (movie.getLocation().equalsIgnoreCase(location) && movie.getDate().equals(date)) {
//                    resMovies.add(movie);
//                }
//            }
//        }
//        return resMovies;
//    }
//
//
//    public static void seatBooking(User currentUser, ArrayList<Movies> movies) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println(":::::::::::::::::::   BOOKING SEATS :::::::::::::::::::");
//
//        LocalDate selectedDate = LocalDate.now();  // Default date
//        String userLocation = currentUser.getUserLocation();  // Default location
//
//        while (true) {
//            HashMap<String, ArrayList<Movies>> movieAndTheatre = BookMyShow.getMovieAndMovieName();
//            HashSet<String> availableTheatres = new HashSet<>();
//
//            for (var entry : movieAndTheatre.entrySet()) {
//                for (Movies movie : entry.getValue()) {
//                    if (movie.getLocation().equalsIgnoreCase(userLocation) && movie.getShow().getDateOfShow().equals(selectedDate)) {
//                        availableTheatres.add(movie.getTheatre().getName());
//                    }
//                }
//            }
//
//            if (availableTheatres.isEmpty()) {
//                changeLocDate(currentUser);
//
//                int choice = Integer.parseInt(scanner.nextLine());
//
//                if (choice == 1) {
//                    LocalDate newDate = changeLocDate(currentUser);
//                    if (newDate != null) {
//                        selectedDate = newDate;
//                    }
//                } else if (choice == 2) {
//                    changeLocDate(currentUser);
//                    userLocation = currentUser.getUserLocation();
//                } else if (choice == 3) {
//                    return;
//                } else {
//                    System.out.println("Invalid choice. Try again.");
//                }
//                continue;
//            }
//            System.out.println("Available Theatres in " + userLocation + " on " + selectedDate.format(BookMyShow.getDateFormatter()) + ":");
//            for (String theatre : availableTheatres) {
//                System.out.println("Theatre: " + theatre);
//            }
//            System.out.println("Available Theatres in " + userLocation + " on " + selectedDate.format(BookMyShow.getDateFormatter()) + ":");
//            for (String theatre : availableTheatres) {
//                System.out.println("Theatre: " + theatre);
//            }
//
//// Loop until a valid theatre is entered
//            String selectedTheatre = "";
//            while (!availableTheatres.contains(selectedTheatre)) {
//                System.out.print("Enter the theatre name to book a seat: ");
//                selectedTheatre = scanner.nextLine();
//                if (!availableTheatres.contains(selectedTheatre)) {
//                    System.out.println("Invalid theatre. Try again.");
//                }
//            }
//
//// Loop until a valid movie is selected
//            ArrayList<Movies> theatreMovies;
//            int movieChoice = -1;
//            do {
//                theatreMovies = new ArrayList<>();
//                for (var entry : movieAndTheatre.entrySet()) {
//                    for (Movies movie : entry.getValue()) {
//                        if (movie.getTheatre().getName().equalsIgnoreCase(selectedTheatre) && movie.getShow().getDateOfShow().equals(selectedDate)) {
//                            theatreMovies.add(movie);
//                        }
//                    }
//                }
//
//                if (theatreMovies.isEmpty()) {
//                    System.out.println("No movies available in the selected theatre on " + selectedDate.format(BookMyShow.getDateFormatter()));
//                    return;
//                }
//
//                System.out.println("Available Movies in " + selectedTheatre + " on " + selectedDate.format(BookMyShow.getDateFormatter()) + ":");
//                for (int i = 0; i < theatreMovies.size(); i++) {
//                    System.out.println((i + 1) + ". " + theatreMovies.get(i).getName());
//                }
//
//                System.out.print("Enter the movie number to book seats: ");
//                String input = scanner.nextLine();
//
//                if (input.matches("\\d+")) {
//                    movieChoice = Integer.parseInt(input) - 1;
//                }
//
//                if (movieChoice < 0 || movieChoice >= theatreMovies.size()) {
//                    System.out.println("Invalid movie selection. Try again.");
//                    movieChoice = -1;
//                }
//            } while (movieChoice == -1);
//
//            Movies selectedMovie = theatreMovies.get(movieChoice);
//            Shows currentShow = selectedMovie.getShow();
//            HashMap<Character, ArrayList<String>> seatingGrid = currentShow.getSeatsAndGrid();
//
//// Calculate available seats
//            int availableSeats = 0;
//            for (var rowEntry : seatingGrid.entrySet()) {
//                for (String seat : rowEntry.getValue()) {
//                    if (!seat.equals("[X]")) {
//                        availableSeats++;
//                    }
//                }
//            }
//
//// Loop until a valid seat count is entered
//            int noOfSeats = 0;
//            while (noOfSeats < 1 || noOfSeats > availableSeats) {
//                System.out.println("Enter how many seats to book (Available: " + availableSeats + "): ");
//                String input = scanner.nextLine();
//
//                if (input.matches("\\d+")) {
//                    noOfSeats = Integer.parseInt(input);
//                }
//
//                if (noOfSeats < 1 || noOfSeats > availableSeats) {
//                    System.out.println("Invalid input. Try again.");
//                }
//            }
//
//            HashSet<String> bookedSeats = new HashSet<>();
//            System.out.println("Available Seats Layout:");
//            displaySeating(seatingGrid);
//
//// Loop for each seat selection
//            for (int i = 0; i < noOfSeats; i++) {
//                String seatInput = "";
//                boolean seatValid = false;
//
//                while (!seatValid) {
//                    System.out.print("Enter seat " + (i + 1) + ": ");
//                    seatInput = scanner.nextLine().toUpperCase();
//
//                    if (seatInput.length() < 2) {
//                        System.out.println("Invalid seat number format. Try again.");
//                        continue;
//                    }
//
//                    char row = seatInput.charAt(0);
//                    if (!seatingGrid.containsKey(row)) {
//                        System.out.println("Invalid row. Try again.");
//                        continue;
//                    }
//
//                    ArrayList<String> rowSeats = seatingGrid.get(row);
//                    for (int j = 0; j < rowSeats.size(); j++) {
//                        if (rowSeats.get(j).equals(seatInput)) {
//                            rowSeats.set(j, "[X]");
//                            bookedSeats.add(seatInput);
//                            seatValid = true;
//                            break;
//                        }
//                    }
//
//                    if (!seatValid) {
//                        System.out.println("Seat already booked or invalid. Try again.");
//                    }
//                }
//            }
//
//// Calculate and confirm booking
//            long totalAmount = noOfSeats * selectedMovie.getTicket();
//            System.out.println("Total Amount: ₹" + totalAmount);
//            confirmBooking(currentShow, seatingGrid);
//
//// Save the booking
//            Tickets newTicket = new Tickets(
//                    selectedMovie.getTheatre().getName(),
//                    selectedMovie.getName(),
//                    selectedMovie.getScreen().getScreenName(),
//                    selectedMovie.getShow().getDateOfShow(),
//                    selectedMovie.getShow().getShowStartTime(),
//                    noOfSeats,
//                    bookedSeats,
//                    (int) (selectedMovie.getTicket() * noOfSeats),
//                    selectedMovie.getLocation()
//            );
//
//            currentUser.getTickets().add(newTicket);
//            System.out.println("Tickets successfully booked!");
//
//
//            currentUser.getTickets().add(newTicket);
//            System.out.println("Tickets successfully booked!");
//            return;
//        }
//    }
//    public static void displaySeating (HashMap < Character, ArrayList < String >> seatingGrid){
//        for (var entry : seatingGrid.entrySet()) {
//            char row = entry.getKey();
//            ArrayList<String> seats = entry.getValue();
//            System.out.print(row + "    ");
//            for (String seat : seats) {
//                System.out.print(seat + "   ");
//            }
//            System.out.println();
//        }
//    }
//
//    public static void confirmBooking(Shows currentShow, HashMap<Character, ArrayList<String>> seatingGrid) {
//        System.out.println("Booking confirmed! Updated seating arrangement:");
//        displaySeating(seatingGrid);
//    }

//    public static LocalDate changeLocDate(User currentUser) {
//        Scanner scanner = new Scanner(System.in);
//        DateTimeFormatter formatter = BookMyShow.getDateFormatter();
//        LocalDate currentDate = LocalDate.now();
//
//        while (true) {
//            System.out.print("Enter the new date (dd/MM/yyyy): ");
//            String inputDate = scanner.nextLine().trim();
//
//            if (inputDate.length() == 10 && inputDate.charAt(2) == '/' && inputDate.charAt(5) == '/') {
//                String day = inputDate.substring(0, 2);
//                String month = inputDate.substring(3, 5);
//                String year = inputDate.substring(6, 10);
//
//                boolean isValid = true;
//
//                for (int i = 0; i < 2; i++) {
//                    if (day.charAt(i) < '0' || day.charAt(i) > '9' ||
//                            month.charAt(i) < '0' || month.charAt(i) > '9') {
//                        isValid = false;
//                        break;
//                    }
//                }
//                for (int i = 0; i < 4; i++) {
//                    if (year.charAt(i) < '0' || year.charAt(i) > '9') {
//                        isValid = false;
//                        break;
//                    }
//                }
//
//                if (isValid) {
//                    LocalDate newDate = LocalDate.of(
//                            Integer.parseInt(year),
//                            Integer.parseInt(month),
//                            Integer.parseInt(day)
//                    );
//
//                    if (newDate.isBefore(currentDate)) {
//                        System.out.println("Selected date cannot be earlier than today.");
//                        continue;
//                    }
//
//                    return newDate;
//                }
//            }
//
//            System.out.println("Invalid date format. Please try again.");
//        }
//    }

//    public static void changeLocation(User currentUser) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the new location: ");
//        String newLocation = scanner.nextLine().trim();
//        currentUser.setUserLocation(newLocation);
//        System.out.println("Location updated to: " + newLocation);
//    }

//    public static void viewTickets(User currentUser) {
//        System.out.println(":::::::::::::::::   VIEW BOOKED TICKETS :::::::::::::::::::");
//        var viewtickets = currentUser.getTickets();
//
//        if (viewtickets.isEmpty()) {
//            System.out.println("No tickets booked yet.");
//            return;
//        }
//
//        for (Tickets ticket : viewtickets) {
//            System.out.println("\n============================================");
//            System.out.println("Theatre Name   : " + ticket.getTheatreName());
//            System.out.println("Location       : " + ticket.getLocation());
//            System.out.println("Movie Name     : " + ticket.getMovieName());
//            System.out.println("Screen Name    : " + ticket.getScreenName());
//            System.out.println("Show Time      : " + ticket.getShowTime().format(BookMyShow.getTimeFormatter()));
//            System.out.println("Amount Paid    : ₹" + ticket.getPrice());
//            System.out.println("Seat Numbers   : " + String.join(", ", ticket.getSeatNumbers()));
//            System.out.println("============================================\n");
//        }
//    }





//    public static void viewTickets(User currentUser) {
//        System.out.println(":::::::::::::::::   VIEW BOOKED TICKETS :::::::::::::::::::");
//        var viewtickets = currentUser.getTickets();
//
//        if (viewtickets.isEmpty()) {
//            System.out.println("No tickets booked yet.");
//            return;
//        }
//
//        for (Tickets ticket : viewtickets) {
//            System.out.println("\n============================================");
//            System.out.println("Theatre Name   : " + ticket.getTheatreName());
//            System.out.println("Location       : " + ticket.getLocation());
//            System.out.println("Movie Name     : " + ticket.getMovieName());
//            System.out.println("Screen Name    : " + ticket.getScreenName());
//            System.out.println("Show Time      : " + ticket.getShowTime().format(BookMyShow.getTimeFormatter()));
//            System.out.println("Amount Paid    : ₹" + ticket.getPrice());
//            System.out.println("Seat Numbers   : " + String.join(", ", ticket.getSeatNumbers()));
//            System.out.println("============================================\n");
//        }
//    }
}
