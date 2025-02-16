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
            if (!(movieFind.isEmpty())){
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
        System.out.println(":::::::::::::::::   BOOK YOUR MOVIES :::::::::::::::::::");
        boolean moviesAvailable = !movies.isEmpty();
        LocalDate currentDate = LocalDate.now();
        System.out.println("Current Date: " + currentDate.format(BookMyShow.getDateFormatter()));
        System.out.println("Current Location: " + currentUser.getUserLocation());
        var movieFind = BookMyShow.getMovieAndMovieName().keySet();
        if (movieFind.isEmpty()){
            System.out.println("OOPS!!!! No movies are Available!!!");
        }
        for (var theatre : BookMyShow.getTheatreAndTheatreNAme().keySet()) {
            if (BookMyShow.getLocations().contains(currentUser.getUserLocation())) {
                if (BookMyShow.getTheatreAndTheatreNAme().get(theatre).getLocation().equals(currentUser.getUserLocation())) {
//                    boolean moviesAvailable = !movies.isEmpty();
                    if (moviesAvailable) {
                        System.out.println("Available Movies in your location:");
                        for (Movies movie : movies) {
                            System.out.println("- " + movie.getName() + " at " + movie.getTheatre().getName());
                        }
                    }
                    if (!moviesAvailable) {
                        System.out.println("Do you want to change the date or location?");
                        LocalDate newDate = changeLocDate(currentUser);

                        movies = getMoviesForLocationAndDate(currentUser.getUserLocation(), newDate);

                        if (movies.isEmpty()) {
                            System.out.println("Still, no movies found in your updated location.");
                            return new HashSet<>();
                        }
                    }
                }
              } //else {
//                System.out.println("No movies found in your current location.");
//            }
//            if (!moviesAvailable) {
//                System.out.println("Do you want to change the date or location?");
//                LocalDate newDate = changeLocDate(currentUser);
//
//                movies = getMoviesForLocationAndDate(currentUser.getUserLocation(), newDate);
//
//                if (movies.isEmpty()) {
//                    System.out.println("Still, no movies found in your updated location.");
//                    return new HashSet<>();
//                }
//            }
            HashMap<String, HashSet<Shows>> overlapSeats = new HashMap<>();
            HashSet<String> bookedMovies = new HashSet<>();

            for (var movie : movies) {
                overlapSeats.computeIfAbsent(movie.getTheatre().getName(), k -> new HashSet<>()).add(movie.getShow());
                bookedMovies.add(movie.getTheatre().getName());
            }
            seatBooking(currentUser, movies);
            return bookedMovies;
//        }
//        System.out.println("No movies found in the given location!!");
//        if (!moviesAvailable) {
//            System.out.println("Do you want to change the date or location?");
//            LocalDate newDate = changeLocDate(currentUser);
//
//            movies = getMoviesForLocationAndDate(currentUser.getUserLocation(), newDate);
//
//            if (movies.isEmpty()) {
//                System.out.println("Still, no movies found in your updated location.");
//                return new HashSet<>();
//            }
//        }
        }
        return null;
    }

    public static ArrayList<Movies> getMoviesForLocationAndDate(String location, LocalDate date){
                    ArrayList<Movies> resMovies = new ArrayList<>();

                    for (var entry : BookMyShow.getMovieAndMovieName().entrySet()) {
                        ArrayList<Movies> movieEntries = entry.getValue();
                        for (Movies movie : movieEntries) {
                            if (movie.getLocation().equalsIgnoreCase(location) && movie.getDate().equals(date)) {
                                resMovies.add(movie);
                            }
                        }
                    }
        return resMovies;
    }

//    public static void seatBooking(User currentUser, ArrayList<Movies> movies) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println(":::::::::::::::::::   BOOKING SEATS :::::::::::::::::::");
//        var movieFind = BookMyShow.getMovieAndMovieName().keySet();
//        if (movieFind.isEmpty()) {
//            System.out.println("No movies found..");
//        }
//        for (var movie : movieFind) {
//            var movieList = BookMyShow.getMovieAndMovieName().get(movie);
//            for (Movies moviess : movieList) {
//                System.out.println("Theatre: " + moviess.getTheatre().getName());
//            }
//            System.out.println("Enter the theatre to book a seat:");
//            String theatretobook = scanner.nextLine();
//            if (movieFind.contains(theatretobook)) {
//                if (movies.isEmpty()) {
//                    System.out.println("No movies selected for booking.");
//                    return;
//                }
//                Movies selectedMovie = movies.get(0);
//                Shows currentShow = selectedMovie.getShow();
//                HashMap<Character, ArrayList<String>> seatingGrid = currentShow.getSeatsAndGrid();
//
//                int availableSeats = 0;
//                for (var rowEntry : seatingGrid.entrySet()) {
//                    for (String seat : rowEntry.getValue()) {
//                        if (!seat.equals("[X]")) {
//                            availableSeats++;
//                        }
//                    }
//                }
//
//                System.out.println("Enter how many seats to book: ");
//                int noOfSeats = Integer.parseInt(scanner.nextLine());
//
//                if (availableSeats < noOfSeats) {
//                    System.out.println("Not enough seats available. Only " + availableSeats + " left.");
//                    return;
//                }
//
//                HashSet<String> bookedSeats = new HashSet<>();
//                System.out.println("Available Seats Layout:");
//                displaySeating(seatingGrid);
//
//                System.out.println("Enter seat numbers to book (e.g., A1, B3):");
//                for (int i = 0; i < noOfSeats; i++) {
//                    System.out.print("Enter seat " + (i + 1) + ": ");
//                    String seatInput = scanner.nextLine().toUpperCase();
//
//                    if (seatInput.length() < 2) {
//                        System.out.println("Invalid seat number format. Try again.");
//                        i--;
//                        continue;
//                    }
//
//                    char row = seatInput.charAt(0);
//                    //String seatNumber = seatInput.substring(1);
//
//                    if (!seatingGrid.containsKey(row)) {
//                        System.out.println("Invalid row. Try again.");
//                        i--;
//                        continue;
//                    }
//
//                    ArrayList<String> rowSeats = seatingGrid.get(row);
//                    boolean seatFound = false;
//
//                    for (int j = 0; j < rowSeats.size(); j++) {
//                        if (rowSeats.get(j).equals(seatInput)) {
//                            rowSeats.set(j, "[X]");
//                            bookedSeats.add(seatInput);
//                            seatFound = true;
//                            break;
//                        }
//                    }
//
//                    if (!seatFound) {
//                        System.out.println("Seat already booked or invalid. Try again.");
//                        i--;
//                    }
//                }
//                long totalAmount = noOfSeats * selectedMovie.getTicket();
//                System.out.println("Total Amount: ₹" + totalAmount);
//                confirmBooking(currentShow, seatingGrid);
//                Tickets newTicket = new Tickets(
//                        selectedMovie.getTheatre().getName(),
//                        selectedMovie.getName(),
//                        selectedMovie.getScreen().getScreenName(),
//                        selectedMovie.getShow().getDateOfShow(),
//                        selectedMovie.getShow().getShowStartTime(),
//                        noOfSeats,
//                        bookedSeats,
//                        (int) (selectedMovie.getTicket() * noOfSeats),
//                        selectedMovie.getLocation());
//                currentUser.getTickets().add(newTicket);
//                System.out.println("Tickets successfully booked!");
//            }
//        }
//    }


//    public static void seatBooking(User currentUser, ArrayList<Movies> movies) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println(":::::::::::::::::::   BOOKING SEATS :::::::::::::::::::");
//
//        LocalDate selectedDate = LocalDate.now();  // Default to today's date
//        String userLocation = currentUser.getUserLocation();  // Get the user's current location
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
//                System.out.println("No movies found in your current location on " + selectedDate.format(BookMyShow.getDateFormatter()));
//                System.out.println("Do you want to change the date or location?");
//                System.out.println("1. Change Date");
//                System.out.println("2. Change Location");
//                System.out.println("3. Exit");
//                System.out.print("Enter your choice: ");
//
//                int choice = Integer.parseInt(scanner.nextLine());
//                LocalDate newDate = changeLocDate(currentUser);
//
//                if (choice == 1 && newDate != null) {
//                    selectedDate = newDate;  // Update the selected date
//                    continue;  // Refresh theatres
//                } else if (choice == 2) {
//                    userLocation = currentUser.getUserLocation();  // Update location and refresh
//                    continue;
//                } else {
//                    return;
//                }
//            }
//
//            System.out.println("Available Theatres in " + userLocation + " on " + selectedDate.format(BookMyShow.getDateFormatter()) + ":");
//            for (String theatre : availableTheatres) {
//                System.out.println("Theatre: " + theatre);
//            }
//
//            System.out.print("Enter the theatre name to book a seat: ");
//            String selectedTheatre = scanner.nextLine();
//
//            if (!availableTheatres.contains(selectedTheatre)) {
//                System.out.println("Invalid theatre. Try again.");
//                continue;  // Restart loop if theatre is invalid
//            }
//
//            // Filter movies for the selected theatre and date
//            ArrayList<Movies> theatreMovies = new ArrayList<>();
//            for (var entry : movieAndTheatre.entrySet()) {
//                for (Movies movie : entry.getValue()) {
//                    if (movie.getTheatre().getName().equalsIgnoreCase(selectedTheatre) && movie.getShow().getDateOfShow().equals(selectedDate)) {
//                        theatreMovies.add(movie);
//                    }
//                }
//            }
//
//            if (theatreMovies.isEmpty()) {
//                System.out.println("No movies available in the selected theatre on " + selectedDate.format(BookMyShow.getDateFormatter()));
//                return;
//            }
//
//            System.out.println("Available Movies in " + selectedTheatre + " on " + selectedDate.format(BookMyShow.getDateFormatter()) + ":");
//            for (int i = 0; i < theatreMovies.size(); i++) {
//                System.out.println((i + 1) + ". " + theatreMovies.get(i).getName());
//            }
//
//            System.out.print("Enter the movie number to book seats: ");
//            int movieChoice = Integer.parseInt(scanner.nextLine()) - 1;
//
//            if (movieChoice < 0 || movieChoice >= theatreMovies.size()) {
//                System.out.println("Invalid movie selection.");
//                return;
//            }
//
//            Movies selectedMovie = theatreMovies.get(movieChoice);
//            Shows currentShow = selectedMovie.getShow();
//            HashMap<Character, ArrayList<String>> seatingGrid = currentShow.getSeatsAndGrid();
//
//            int availableSeats = 0;
//            for (var rowEntry : seatingGrid.entrySet()) {
//                for (String seat : rowEntry.getValue()) {
//                    if (!seat.equals("[X]")) {
//                        availableSeats++;
//                    }
//                }
//            }
//
//            System.out.println("Enter how many seats to book: ");
//            int noOfSeats = Integer.parseInt(scanner.nextLine());
//
//            if (availableSeats < noOfSeats) {
//                System.out.println("Not enough seats available. Only " + availableSeats + " left.");
//                return;
//            }
//
//            HashSet<String> bookedSeats = new HashSet<>();
//            System.out.println("Available Seats Layout:");
//            displaySeating(seatingGrid);
//
//            System.out.println("Enter seat numbers to book (e.g., A1, B3):");
//            for (int i = 0; i < noOfSeats; i++) {
//                System.out.print("Enter seat " + (i + 1) + ": ");
//                String seatInput = scanner.nextLine().toUpperCase();
//
//                if (seatInput.length() < 2) {
//                    System.out.println("Invalid seat number format. Try again.");
//                    i--;
//                    continue;
//                }
//
//                char row = seatInput.charAt(0);
//                if (!seatingGrid.containsKey(row)) {
//                    System.out.println("Invalid row. Try again.");
//                    i--;
//                    continue;
//                }
//
//                ArrayList<String> rowSeats = seatingGrid.get(row);
//                boolean seatFound = false;
//
//                for (int j = 0; j < rowSeats.size(); j++) {
//                    if (rowSeats.get(j).equals(seatInput)) {
//                        rowSeats.set(j, "[X]");
//                        bookedSeats.add(seatInput);
//                        seatFound = true;
//                        break;
//                    }
//                }
//
//                if (!seatFound) {
//                    System.out.println("Seat already booked or invalid. Try again.");
//                    i--;
//                }
//            }
//
//            long totalAmount = noOfSeats * selectedMovie.getTicket();
//            System.out.println("Total Amount: ₹" + totalAmount);
//            confirmBooking(currentShow, seatingGrid);
//
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
//            return;
//        }
//    }

    public static void seatBooking(User currentUser, ArrayList<Movies> movies) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(":::::::::::::::::::   BOOKING SEATS :::::::::::::::::::");

        LocalDate selectedDate = LocalDate.now();  // Default date
        String userLocation = currentUser.getUserLocation();  // Default location

        while (true) {
            HashMap<String, ArrayList<Movies>> movieAndTheatre = BookMyShow.getMovieAndMovieName();
            HashSet<String> availableTheatres = new HashSet<>();

            for (var entry : movieAndTheatre.entrySet()) {
                for (Movies movie : entry.getValue()) {
                    if (movie.getLocation().equalsIgnoreCase(userLocation) && movie.getShow().getDateOfShow().equals(selectedDate)) {
                        availableTheatres.add(movie.getTheatre().getName());
                    }
                }
            }

            if (availableTheatres.isEmpty()) {
//                System.out.println("No movies found in your current location on " + selectedDate.format(BookMyShow.getDateFormatter()));
//                System.out.println("Do you want to change the date or location?");
//                System.out.println("1. Change Date");
//                System.out.println("2. Change Location");
//                System.out.println("3. Exit");
//                System.out.print("Enter your choice: ");
                changeLocDate(currentUser);

                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    LocalDate newDate = changeLocDate(currentUser);
                    if (newDate != null) {
                        selectedDate = newDate;  // Update the selected date
                    }
                } else if (choice == 2) {
                    changeLocDate(currentUser);
                    userLocation = currentUser.getUserLocation();  // Update location
                } else if (choice == 3) {
                    return;  // Exit booking process
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
                continue;
            }

            // Display available theatres and proceed with booking
            System.out.println("Available Theatres in " + userLocation + " on " + selectedDate.format(BookMyShow.getDateFormatter()) + ":");
            for (String theatre : availableTheatres) {
                System.out.println("Theatre: " + theatre);
            }

            System.out.print("Enter the theatre name to book a seat: ");
            String selectedTheatre = scanner.nextLine();

            if (!availableTheatres.contains(selectedTheatre)) {
                System.out.println("Invalid theatre. Try again.");
                continue;
            }

            // Proceed with movie selection
            ArrayList<Movies> theatreMovies = new ArrayList<>();
            for (var entry : movieAndTheatre.entrySet()) {
                for (Movies movie : entry.getValue()) {
                    if (movie.getTheatre().getName().equalsIgnoreCase(selectedTheatre) && movie.getShow().getDateOfShow().equals(selectedDate)) {
                        theatreMovies.add(movie);
                    }
                }
            }

            if (theatreMovies.isEmpty()) {
                System.out.println("No movies available in the selected theatre on " + selectedDate.format(BookMyShow.getDateFormatter()));
                return;
            }

            System.out.println("Available Movies in " + selectedTheatre + " on " + selectedDate.format(BookMyShow.getDateFormatter()) + ":");
            for (int i = 0; i < theatreMovies.size(); i++) {
                System.out.println((i + 1) + ". " + theatreMovies.get(i).getName());
            }

            System.out.print("Enter the movie number to book seats: ");
            int movieChoice = Integer.parseInt(scanner.nextLine()) - 1;

            if (movieChoice < 0 || movieChoice >= theatreMovies.size()) {
                System.out.println("Invalid movie selection.");
                return;
            }

            Movies selectedMovie = theatreMovies.get(movieChoice);
            Shows currentShow = selectedMovie.getShow();
            HashMap<Character, ArrayList<String>> seatingGrid = currentShow.getSeatsAndGrid();

            int availableSeats = 0;
            for (var rowEntry : seatingGrid.entrySet()) {
                for (String seat : rowEntry.getValue()) {
                    if (!seat.equals("[X]")) {
                        availableSeats++;
                    }
                }
            }

            System.out.println("Enter how many seats to book: ");
            int noOfSeats = Integer.parseInt(scanner.nextLine());

            if (availableSeats < noOfSeats) {
                System.out.println("Not enough seats available. Only " + availableSeats + " left.");
                return;
            }

            HashSet<String> bookedSeats = new HashSet<>();
            System.out.println("Available Seats Layout:");
            displaySeating(seatingGrid);

            System.out.println("Enter seat numbers to book (e.g., A1, B3):");
            for (int i = 0; i < noOfSeats; i++) {
                System.out.print("Enter seat " + (i + 1) + ": ");
                String seatInput = scanner.nextLine().toUpperCase();

                if (seatInput.length() < 2) {
                    System.out.println("Invalid seat number format. Try again.");
                    i--;
                    continue;
                }

                char row = seatInput.charAt(0);
                if (!seatingGrid.containsKey(row)) {
                    System.out.println("Invalid row. Try again.");
                    i--;
                    continue;
                }

                ArrayList<String> rowSeats = seatingGrid.get(row);
                boolean seatFound = false;

                for (int j = 0; j < rowSeats.size(); j++) {
                    if (rowSeats.get(j).equals(seatInput)) {
                        rowSeats.set(j, "[X]");
                        bookedSeats.add(seatInput);
                        seatFound = true;
                        break;
                    }
                }

                if (!seatFound) {
                    System.out.println("Seat already booked or invalid. Try again.");
                    i--;
                }
            }

            long totalAmount = noOfSeats * selectedMovie.getTicket();
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
                    (int) (selectedMovie.getTicket() * noOfSeats),
                    selectedMovie.getLocation()
            );

            currentUser.getTickets().add(newTicket);
            System.out.println("Tickets successfully booked!");
            return;
        }
    }


    public static void displaySeating (HashMap < Character, ArrayList < String >> seatingGrid){
        for (var entry : seatingGrid.entrySet()) {
            char row = entry.getKey();
            ArrayList<String> seats = entry.getValue();
            System.out.print(row + "    ");
            for (String seat : seats) {
                System.out.print(seat + "   ");
            }
            System.out.println();
        }
    }

    public static void confirmBooking(Shows currentShow, HashMap<Character, ArrayList<String>> seatingGrid) {
        System.out.println("Booking confirmed! Updated seating arrangement:");
        displaySeating(seatingGrid);
    }
    public static LocalDate changeLocDate(User currentUser) {
        LocalDate currentDate = LocalDate.now();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to change the date or location?");
        System.out.println("1. Change Date");
        System.out.println("2. Change Location");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");

        int choice = Integer.parseInt(scanner.nextLine());
        while (true) {
            System.out.println("Current date : " + currentDate.format(BookMyShow.getDateFormatter()));
            System.out.println("Selected date : " + currentDate.format(BookMyShow.getDateFormatter()));
            System.out.println("Current location : " + currentUser.getUserLocation());
            switch (choice) {
                case 1:
                    System.out.print("Enter the new date (dd/MM/yyyy): ");
                    String inputDate = scanner.nextLine();

                    DateTimeFormatter formatter = BookMyShow.getDateFormatter();
                    LocalDate newDate = null;

                    if (inputDate.length() == 10 && inputDate.charAt(2) == '/' && inputDate.charAt(5) == '/') {
                        newDate = LocalDate.parse(inputDate, formatter);

                        if (newDate.isBefore(currentDate)) {
                            System.out.println("Selected date cannot be earlier than today.");
                            return null;
                        }
                        return newDate;
                    }
                    System.out.println("Invalid date format. Please try again.");
                    return newDate;

                case 2:
                    System.out.print("Enter the new location: ");
                    String newLocation = scanner.nextLine();
                    currentUser.setUserLocation(newLocation);
                    System.out.println("Location updated to: " + newLocation);
                    return null;

                case 3:
                    System.out.println("Exiting ...");
                    return null;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    return null;
            }
        }
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
