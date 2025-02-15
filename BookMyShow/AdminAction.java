import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdminAction {
    public static Admin adminLogin() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your Name:");
        String adminName = scanner.nextLine();
        System.out.println("Enter your Password:");
        String adminPassword = scanner.nextLine();
        Admin admins = null;
        for (Admin admin : BookMyShow.getAdmins()) {
            if (admin.getAdminName().equals(adminName) && admin.getAdminPassWord().equals(adminPassword)) {
                return admin;
            }
        }
        return admins;
    }


    public static void adminAction() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("::::::::::::::::  Actions for ADMIN  ::::::::::::::::");
        while (true) {
            System.out.println("\n");
            System.out.println("\t\t\t1. Add Theatres");
            System.out.println("\t\t\t2. Add Locations");
            System.out.println("\t\t\t3. Add Movies");
            System.out.println("\t\t\t4. View Theatres");
            System.out.println("\t\t\t5. View Movies");
            System.out.println("\t\t\t6. EXit");
            System.out.println("\t\t Enter your choice:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Adding Theatre Process");
                    addTheatre();
                    break;
                case 2:
                    System.out.println("Adding Location Process");
                    addLocation();
                    break;
                case 3:
                    System.out.println("Adding Movies Process");
                    addMovies();
                    break;
                case 4:
                    System.out.println("View Theatres ");
                    viewTheatre();
                    break;
                case 5:
                    System.out.println("View Movies");
                    viewMovies();
                    break;
                case 6:
                    System.out.println("Exiting..");
                    return;
                default:
                    System.out.println("Invalid choice.... Try Again");
                    break;
            }
        }
    }

    public static void addTheatre() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Location where to add a Theatre:");
        String locations = scanner.nextLine();
        if (BookMyShow.getLocations().contains(locations)) {
            System.out.println("Enter the Theatre Name:");
            String theatreName = scanner.nextLine();
            for (var theatre : BookMyShow.getTheatreAndTheatreNAme().keySet()) {
                if (theatre.equals(theatreName)) {
                    System.out.println("Theatre already exists...");
                    break;
                }
            }
            System.out.println("Enter the Number of screens in a theatre:");
            int screens = Integer.parseInt(scanner.nextLine());
            HashMap<String, Screen> screenHashMap = new HashMap<>();
            int i = 1;
            while (i <= screens) {
                System.out.println("Enter the name of a screen " + i + ":");
                String screenName = scanner.nextLine();
                if (screenHashMap.containsKey(screenName)) {
                    System.out.println("Oops! Screen Already exists...");
                    continue;
                }
                System.out.println("Seats in a Screen " + screenName + ":");
                long seatScreen = Long.parseLong(scanner.nextLine());

                System.out.println("Enter the seat pattern of a Screen " + screenName + " for " + seatScreen + " seats :");
                String seatPattern = scanner.nextLine();
                var seatsAndPatterns = Utilities.seatingPatternArrangeMent(scanner, seatScreen, seatPattern);
                Screen screen = new Screen(screenName, seatScreen, seatsAndPatterns);
                screenHashMap.put(screenName, screen);
                i++;
                Theatre theatre = new Theatre(theatreName, screenHashMap, locations);
                BookMyShow.getTheatreAndTheatreNAme().put(theatreName, theatre);
            }
            System.out.println("\t\t\t\tTheatre added successfully.....");
        } else {
            System.out.println("Location doesn't exists..");
            System.out.println("Do you need add new Location:");
            System.out.println("\n\t 1. Yes \n\t 2. No");
            System.out.println("Enter your choice :");
            int selectChoice = Integer.parseInt(scanner.nextLine());
            if (selectChoice == 1) {
                addLocation();
            }else {
                System.out.println("Exiting....");
            }
        }
    }

    public static void addLocation() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the location:");
        String location = scanner.nextLine();
        if (BookMyShow.getLocations().contains(location)) {
            System.out.println("Sorry Location already exists!!!");
        } else {
            System.out.println("Location not Exists...");
            BookMyShow.getLocations().add(location);
            System.out.println("New Locations are Added successfully!!!");
        }
    }


//    public static void addMovies() {
//        Scanner scanner=new Scanner(System.in);
//        while (true) {
//            System.out.println("Enter the Location to Add Movie:");
//            String nlocation = scanner.nextLine();
//            for (var loc : BookMyShow.getLocations()) {
//                for (var theatre : BookMyShow.getTheatreAndTheatreNAme().keySet()) {
//                    if (BookMyShow.getLocations().contains(nlocation)) {
//                        if (BookMyShow.getTheatreAndTheatreNAme().get(theatre).getLocation().equals(nlocation)) {
//                            System.out.println("Enter the movie name:");
//                            String movie = scanner.nextLine();
//                            System.out.print("Enter the Date of the movie (dd/mm/yyyy): ");
//                            LocalDate dateOfMovie = LocalDate.parse(scanner.nextLine(), BookMyShow.getDateFormatter());
//                            System.out.print("Enter the Duration of the movie: ");
//                            long duration = Long.parseLong(scanner.nextLine());
//                            System.out.print("Enter the price of the Movie: ");
//                            int priceOfMovie = Integer.parseInt(scanner.nextLine());
//
//                            System.out.println("Available Theatre:");
//                            //for (var theatre : BookMyShow.getTheatreAndTheatreNAme().keySet()) {
//                            if (BookMyShow.getTheatreAndTheatreNAme().get(theatre).getLocation().equals(nlocation)) {
//                                System.out.println("\t" + theatre);
//                            }
//                            //}
//                            String movieTheatre;
//                            String screenName;
//                            while (true) {
//                                System.out.println("Add Movie...");
//                                System.out.println("Enter the theatre to add a movie:");
//                                movieTheatre = scanner.nextLine();
//                                //var theatre = BookMyShow.getTheatreAndTheatreNAme().keySet();
//                                if (theatre.contains(movieTheatre)) {
//                                    Theatre theatre1 = BookMyShow.getTheatreAndTheatreNAme().get(movieTheatre);
//                                    System.out.println("Available screens..");
//                                    for (var screen : theatre1.getScreenName().keySet()) {
//                                        System.out.println("\t" + screen);
//                                    }
//                                    System.out.println("Enter the screen name to add movie:");
//                                    screenName = scanner.nextLine();
//                                    var screens = theatre1.getScreenName().keySet();
//                                    if (screens.contains(screenName)) {
//                                        Screen screen = theatre1.getScreenName().get(screenName);
//                                    } else {
//                                        System.out.println("No screen found");
//                                    }
//                                } else {
//                                    System.out.println("No theatres found in this location..");
//                                    continue;
//                                }
//                                break;
//                            }
//                            System.out.println("Enter the show time (HH:mm) :");
//                            LocalTime mstart = LocalTime.parse(scanner.nextLine(), BookMyShow.getTimeFormatter());
//                            LocalTime mend = mstart.plusMinutes(duration + 40);
//                            //var theatre = BookMyShow.getTheatreAndTheatreNAme().keySet();
//                            Theatre theatre1 = BookMyShow.getTheatreAndTheatreNAme().get(movieTheatre);
//                            Screen screen = theatre1.getScreenName().get(screenName);
//
//                            for (var show : screen.getShowsInScreen()) {
//                                if (dateOfMovie.isEqual(show.getDateOfShow())) {
//                                    if (mstart.isBefore(show.getShowStartTime()) && mend.isBefore(show.getShowStartTime()) || mstart.isAfter(show.getShowEndTime()) && mend.isAfter(show.getShowStartTime())) {
//                                        System.out.println("Show already exixts..");
//                                    }
//                                }
//                            }
//                            Shows currentShow = new Shows(dateOfMovie, mstart, mend, screen, screen.getSeatsGrid(), priceOfMovie);
//                            screen.getShowsInScreen().add(currentShow);
//                            Movies currentMovie = new Movies(movie, nlocation, dateOfMovie, duration, theatre1, screen, currentShow, priceOfMovie);
//                            ArrayList<Movies> moviesArrayList = BookMyShow.getMovieAndMovieName().get(movie);
//                            if (moviesArrayList == null) {
//                                moviesArrayList = new ArrayList<>();
//                                BookMyShow.getMovieAndMovieName().put(movie, moviesArrayList);
//                            }
//                            moviesArrayList.add(currentMovie);
//                            System.out.println("Movie added successfully in " + currentMovie.getTheatre().getName() + " on " + dateOfMovie.format(BookMyShow.getDateFormatter()) + " at " + mstart.format(BookMyShow.getTimeFormatter()));
//                            break;
//                        }
//                    } else {
//                        System.out.println("Sorry ! No Locations found...");
//                    }
//                    break;
//                }
//                System.out.println("Sorry ! No Locations found...");
//                break;
//            }
//            break;
//        }
//    }

    public static void addMovies() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Location to Add Movie:");
        String nlocation = scanner.nextLine();

        if (!BookMyShow.getLocations().contains(nlocation)) {
            System.out.println("Sorry! No locations found.");
            return;
        }

        System.out.println("Enter the movie name:");
        String movie = scanner.nextLine();
        System.out.print("Enter the Date of the movie (dd/MM/yyyy): ");
        LocalDate dateOfMovie = LocalDate.parse(scanner.nextLine(), BookMyShow.getDateFormatter());
        System.out.print("Enter the Duration of the movie (in minutes): ");
        long duration = Long.parseLong(scanner.nextLine());
        System.out.print("Enter the price of the Movie: ");
        int priceOfMovie = Integer.parseInt(scanner.nextLine());

        System.out.println("Available Theatres:");
        for (var theatre : BookMyShow.getTheatreAndTheatreNAme().keySet()) {
            if (BookMyShow.getTheatreAndTheatreNAme().get(theatre).getLocation().equals(nlocation)) {
                System.out.println("\t" + theatre);
            }
        }

        System.out.println("Enter the theatre to add a movie:");
        String movieTheatre = scanner.nextLine();

        if (!BookMyShow.getTheatreAndTheatreNAme().containsKey(movieTheatre)) {
            System.out.println("No theatres found in this location.");
            return;
        }

        Theatre theatre = BookMyShow.getTheatreAndTheatreNAme().get(movieTheatre);
        System.out.println("Available screens:");
        for (var screen : theatre.getScreenName().keySet()) {
            System.out.println("\t" + screen);
        }

        System.out.println("Enter the screen name to add movie:");
        String screenName = scanner.nextLine();

        if (!theatre.getScreenName().containsKey(screenName)) {
            System.out.println("No screen found.");
            return;
        }

        Screen screen = theatre.getScreenName().get(screenName);

        System.out.println("Enter the show time (HH:mm):");
        LocalTime mstart = LocalTime.parse(scanner.nextLine(), BookMyShow.getTimeFormatter());
        LocalTime mend = mstart.plusMinutes(duration + 40);

        for (var show : screen.getShowsInScreen()) {
            if (dateOfMovie.isEqual(show.getDateOfShow())) {
                if (!(mend.isBefore(show.getShowStartTime()) || mstart.isAfter(show.getShowEndTime()))) {
                    System.out.println("Show time conflicts with an existing show.");
                    return;
                }
            }
        }

        // Creating new show and movie
        Shows currentShow = new Shows(dateOfMovie, mstart, mend, screen, screen.getSeatsGrid(), priceOfMovie);
        screen.getShowsInScreen().add(currentShow);

        Movies currentMovie = new Movies(movie, nlocation, dateOfMovie, duration, theatre, screen, currentShow, priceOfMovie);
        ArrayList<Movies> moviesArrayList = BookMyShow.getMovieAndMovieName().get(movie);

        if (moviesArrayList == null) {
            moviesArrayList = new ArrayList<>();
            BookMyShow.getMovieAndMovieName().put(movie, moviesArrayList);
        }

        moviesArrayList.add(currentMovie);
        System.out.println("Movie added successfully in " + currentMovie.getTheatre().getName() +
                " on " + dateOfMovie.format(BookMyShow.getDateFormatter()) +
                " at " + mstart.format(BookMyShow.getTimeFormatter()));
    }


    public static void viewMovies() {
        System.out.println("************ View Movies ************");
        var movieFind = BookMyShow.getMovieAndMovieName().keySet();
        if (movieFind.isEmpty()) {
            System.out.println("No movies found..");
        }
        for (var movie : movieFind) {
            var movieList = BookMyShow.getMovieAndMovieName().get(movie);
            for (Movies moviess : movieList) {
                System.out.println("Theatre: " + moviess.getTheatre().getName());
                System.out.println("Movie Name: " + movie);
                System.out.println("Screen: " + moviess.getScreen().getScreenName());
                System.out.println("Location: " + moviess.getLocation());
                System.out.println("Date: " + moviess.getDate().format(BookMyShow.getDateFormatter()));
                System.out.println("Show Start Time: " + moviess.getShow().getShowStartTime().format(BookMyShow.getTimeFormatter()));
                System.out.println("Show End Time: " + moviess.getShow().getShowEndTime().format(BookMyShow.getTimeFormatter()));
                System.out.println("Movie Amount :" + moviess.getTicket());
                System.out.println("\n````````````````````````````````````````");
            }
        }
    }

    public static void viewTheatre() {
        System.out.println("\n****************** View Theaters ******************");
        for (var theatres : BookMyShow.getTheatreAndTheatreNAme().keySet()) {
            System.out.println("Theatre Name :" + theatres);
            Theatre theatre = BookMyShow.getTheatreAndTheatreNAme().get(theatres);
            System.out.println("Location :" + theatre.getLocation());
            for (var screen : theatre.getScreenName().keySet()) {
                System.out.println("Screen Name:" + screen);
                System.out.println("Number of seats in Screen " + theatre.getScreenName().get(screen).getSeatNumber());
                System.out.println("Seat arrangement : \n");
//                for (var seats : theatre.getScreenName().get(screen).getSeatsGrid().keySet()) {
//                    System.out.print(seats);
//                    System.out.println(theatre.getScreenName().get(screen).getSeatsGrid().values());
//                }
                for (var row : theatre.getScreenName().get(screen).getSeatsGrid().keySet()) {
                    System.out.print(row + " ");
                    for (var seat : theatre.getScreenName().get(screen).getSeatsGrid().get(row)) {
                        System.out.print(seat + " ");
                    }
                    System.out.println();
                }
                System.out.println("*********************************************************************************");
            }
        }
    }
}












