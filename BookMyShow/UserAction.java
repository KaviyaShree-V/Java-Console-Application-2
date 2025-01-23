import java.util.Scanner;

public class UserAction {
    public static User userLog(Scanner scanner) {
        System.out.println("Enter the User Name");
        String aName = scanner.nextLine();
        System.out.println("Enter the Password");
        String pswd = scanner.nextLine();
        User user = null;
        for (User users : BookMyShow.getUser()) {
            if (users instanceof User) {
                if (users.getUserName().equals(aName) && users.getUserPassword().equals(pswd)) {
                    //System.out.println("Login is Successfull!...");
                    user = users;
                    return user;
                }
            }
            if (user == null){
                //System.out.println("Login Unsucessful. \n Please try Again..");
                return null;
            }
        }
        return null;
    }

    public static void actions(Scanner scanner , User user) {
        System.out.println("The actions for User.........\n Select a choice!!!");
        System.out.println("1. View Tickets \n2.  \n3.  \n4.  \n5. Exit");
        int choiceAction = Integer.parseInt(scanner.nextLine());
        while (true) {
            switch (choiceAction) {
                case 1:
                    System.out.println("Add Movies....");
                    viewTickets(user);
                    break;
                case 2:
                    System.out.println("Add Theatre....");

                    break;
                case 3:
                    System.out.println("View Movies....");

                    break;
                case 4:
                    System.out.println("View Theatres....");

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
    public static void viewTickets(User user){
        var viewTicketsForUser = user.getTickets();
        System.out.println("The tickets available for User:");
        for (Tickets tickets: viewTicketsForUser){
            System.out.println("***********"+tickets.getTheatreName()+"*************");
            System.out.println("***********"+tickets.getScreenName()+"*************");
            System.out.println("***********"+tickets.getMovieName()+"*************");
            System.out.println("**** \tMovie Date:"+tickets.getShowDate().format(BookMyShow.getDateFormatter())+"****");
            System.out.println("**** \tShow Time:"+tickets.getShowTime().format(BookMyShow.getTimeFormatter())+"****");
            System.out.println("**** \tTotal no. of Tickets:"+tickets.getNoOfTickets()+"****");
            System.out.println("**** \tNo. of Seats:"+tickets.getSeatNumbers());
            System.out.println("**** \tAmount paid for Tickets:"+tickets.getTicketAmount()+"****");
        }
    }
}
