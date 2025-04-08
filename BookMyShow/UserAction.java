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
}
