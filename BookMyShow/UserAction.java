import java.util.*;
import java.time.*;

public class UserAction {

    public static User userLog(Scanner scanner) {
        System.out.println("Enter the User Name:");
        String uName = scanner.nextLine();
        System.out.println("Enter the Password:");
        String upswd = scanner.nextLine();
        User user = null;

        for (User users : BookMyShow.getUser()) {
            if (users.getUserName().equals(uName) && users.getUserPassword().equals(upswd)) {
                user = users;
                return user;
            }
        }
        return null;
    }

    public static void actions(Scanner scanner, User user) {
        while (true) {
            System.out.println("The actions for User.........\nSelect a choice!!!");
            System.out.println("1. View Tickets \n2. Show Movies \n3. Exit");
            int choiceAction = Integer.parseInt(scanner.nextLine());

            switch (choiceAction) {
                case 1:
                    viewTickets(user);
                    break;
                case 2:
                    showMovies(user);
                    break;
                case 3:
                    System.out.println("Exiting....");
                    return;
                default:
                    System.out.println("Invalid choice. Try Again.");
            }
        }
    }

    public static void viewTickets(User user) {
        if (user == null) {
            System.out.println("Error: User is not logged in or session is invalid.");
            return;
        }
        List<Tickets> ticketsForUser = user.getTickets();
        if (ticketsForUser == null || ticketsForUser.isEmpty()) {
            System.out.println("No tickets available for this user.");
            return;
        }

        System.out.println("The tickets available for User:");
        for (Tickets ticket : ticketsForUser) {
            System.out.println("*********** " + ticket.getTheatreName() + " *************");
            System.out.println("Movie Name: " + ticket.getMovieName());
            System.out.println("Movie Date: " + ticket.getShowDate().format(BookMyShow.getDateFormatter()));
            System.out.println("Show Time: " + ticket.getShowTime().format(BookMyShow.getTimeFormatter()));
            System.out.println("Total Tickets: " + ticket.getNoOfTickets());
            System.out.println("Seats: " + ticket.getSeatNumbers());
            System.out.println("Amount Paid: " + ticket.getTicketAmount());
        }
    }

    public static void showMovies(User currentUser) {
        if (currentUser == null) {
            System.out.println("Error: User is not logged in or session is invalid.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        ArrayList<Movies> selectedMovies = new ArrayList<>();
        HashSet<String> availableMovies = new HashSet<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate selectedDate = currentDate;

        while (true) {
            System.out.println("Current date: " + currentDate.format(BookMyShow.getDateFormatter()));
            System.out.println("Selected date: " + selectedDate.format(BookMyShow.getDateFormatter()));
            System.out.println("Current location: " + currentUser.gettLocation());

            boolean hasMovies = displayMovies(currentUser, selectedDate, availableMovies);
            if (!hasMovies) {
                System.out.println("No movies available in this location on this date.");
                LocalDate tempDate = changeLocDate(scanner, currentUser, currentDate);
                if (tempDate == null) {
                    // User chose to exit
                    System.out.println("Exiting movie selection...");
                    return; // Exit the `showMovies` method entirely
                }
                selectedDate = tempDate; // Update the selected date if changed
            } else {
                System.out.print("Enter the movie name to book (or type 'change' to change preferences): ");
                String movieToBook = scanner.nextLine();

                if (movieToBook.equalsIgnoreCase("change")) {
                    LocalDate tempDate = changeLocDate(scanner, currentUser, currentDate);
                    if (tempDate == null) {
                        // User chose to exit
                        System.out.println("Exiting movie selection...");
                        return; // Exit the `showMovies` method entirely
                    }
                    selectedDate = tempDate; // Update the selected date if changed
                } else if (availableMovies.contains(movieToBook)) {
                    for (Theatre theatre : BookMyShow.getTheatres()) {
                        if (theatre.getTheatreLocation().equalsIgnoreCase(currentUser.gettLocation())) {
                            for (Movies movie : Movies.getAllMovies()) {
                                if (movie.getMovieName().equalsIgnoreCase(movieToBook)
                                        && movie.getTheatre().getTheatreName().equals(theatre.getTheatreName())
                                        && movie.getLocalDate().isEqual(selectedDate)) {
                                    selectedMovies.add(movie);
                                }
                            }
                        }
                    }
                    break;
                } else {
                    System.out.println("Invalid movie selection. Please try again.");
                }
            }
        }

        if (selectedMovies.isEmpty()) {
            System.out.println("No movies found for the selected date/location.");
            return;
        }

        bookMovies(currentUser, selectedMovies, selectedDate);
    }


    public static LocalDate changeLocDate(Scanner scanner, User currentUser, LocalDate currentDate) {
        System.out.println("Do you want to change the date or location?");
        System.out.println("1. Change Date");
        System.out.println("2. Change Location");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Enter the new date (dd/MM/yyyy): ");
                String newDateString = scanner.nextLine();
                try {
                    LocalDate newDate = LocalDate.parse(newDateString, BookMyShow.getDateFormatter());
                    if (newDate.isBefore(currentDate)) {
                        System.out.println("Selected date cannot be earlier than today.");
                        return null;
                    }
                    return newDate;
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please try again.");
                    return null;
                }

            case 2:
                System.out.print("Enter the new location: ");
                String newLocation = scanner.nextLine();
                currentUser.settLocation(newLocation);
                System.out.println("Location updated to: " + newLocation);
                return null;

            case 3:
                System.out.println("Exiting preferences change...");
                return null; // Exit to the calling method

            default:
                System.out.println("Invalid choice. Please try again.");
                return null;
        }
    }


    public static boolean displayMovies(User currentUser, LocalDate selectedDate, HashSet<String> availableMovies) {
        availableMovies.clear();
        ArrayList<Theatre> theatres = BookMyShow.getTheatres();

        for (Theatre theatre : theatres) {
            if (theatre.getTheatreLocation().equalsIgnoreCase(currentUser.gettLocation())) {
                HashMap<String, ScreenMap> screenMap = theatre.getScreenHashMap();

                for (ScreenMap screen : screenMap.values()) {
                    for (Shows show : screen.getShows()) {
                        if (show.getShowDateForMovie().isEqual(selectedDate)) {
                            availableMovies.add(show.getMovieName());
                        }
                    }
                }
            }
        }

        if (availableMovies.isEmpty()) {
            return false;
        }

        System.out.println("Available movies in your location on " + selectedDate.format(BookMyShow.getDateFormatter()) + ":");
        for (String movieName : availableMovies) {
            System.out.println("- " + movieName);
        }

        return true;
    }

    public static void bookMovies(User currentUser, ArrayList<Movies> movies, LocalDate selectedDate) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("The movie availability is as below for " + selectedDate.format(BookMyShow.getDateFormatter()) + ":");
        for (Movies movie : movies) {
            System.out.println("Theatre: " + movie.getTheatre().getTheatreName());
        }

        Theatre selectedTheatre = null;
        Shows selectedShow = null;

        while (true) {
            System.out.print("Enter the theatre name to book: ");
            String theatreName = scanner.nextLine();

            System.out.print("Enter the show time (HH:mm): ");
            String showTimeInput = scanner.nextLine();

            for (Movies movie : movies) {
                if (movie.getTheatre().getTheatreName().equalsIgnoreCase(theatreName)) {
                    for (Shows show : movie.getScreen().getShows()) {
                        if (show.getMovieStartTime().format(BookMyShow.getTimeFormatter()).equals(showTimeInput)) {
                            selectedTheatre = movie.getTheatre();
                            selectedShow = show;
                            break;
                        }
                    }
                }
                if (selectedTheatre != null && selectedShow != null) break;
            }

            if (selectedTheatre != null && selectedShow != null) break;

            System.out.println("Invalid theatre name or show time. Please try again.");
        }

        System.out.println("Total seats in the theatre: " + selectedShow.getScreen().getNoOfSeats());
        System.out.println("Seat arrangement: ");
        for (var entry : selectedShow.getSeatRequire().entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (String seat : entry.getValue()) {
                System.out.print(seat + " ");
            }
            System.out.println();
        }

        System.out.print("Enter the number of seats to book: ");
        int numSeats = Integer.parseInt(scanner.nextLine());

        ArrayList<String> bookedSeats = new ArrayList<>();
        for (int i = 0; i < numSeats; i++) {
            System.out.print("Enter seat number to book: ");
            String seat = scanner.nextLine();

            boolean seatFound = false;
            for (var entry : selectedShow.getSeatRequire().entrySet()) {
                if (entry.getValue().contains(seat)) {
                    entry.getValue().remove(seat);
                    bookedSeats.add(seat);
                    seatFound = true;
                    break;
                }
            }

            if (!seatFound) {
                System.out.println("Invalid or already booked seat. Please try again.");
                i--;
            }
        }

        System.out.print("Enter payment amount: ");
        int payment = Integer.parseInt(scanner.nextLine());

        int totalCost = selectedShow.getMovieTicket() * numSeats;
        if (payment >= totalCost) {
            System.out.println("Payment successful!");
            System.out.println("Booking details:");
            System.out.println("User: " + currentUser.getUserName());
            System.out.println("Theatre: " + selectedTheatre.getTheatreName());
            System.out.println("Movie: " + movies.get(0).getMovieName());
            System.out.println("Show Time: " + selectedShow.getMovieStartTime().format(BookMyShow.getTimeFormatter()));
            System.out.println("Seats booked: " + String.join(", ", bookedSeats));
            System.out.println("Total amount paid: " + payment);
        } else {
            System.out.println("Payment failed. Insufficient amount.");
        }
    }
}
