import java.util.Scanner;

public class BookMyShowActions {
    private static Scanner scanner=new Scanner(System.in);

    public static void start(){
        System.out.println("\t\t\t\t\t\tWelcome to Book My Show.......");
        while (true) {
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Sign Up / Register");
            System.out.println("4. Exit");
            System.out.println("Select your Choice and Enter:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("~~~~~~~~~~~~~~******* Be Happy *******~~~~~~~~~~~~~~");
                    System.out.println("Admin Login Page.......");
                    Admin newadmin= AdminAction.adminLogin();
                    if (newadmin==null){
                        System.out.println("Invalid Details...\n \t Try Again");
                    }else {
                        System.out.println("Login Successful...");
                        AdminAction.adminAction();
                    }
                    break;
                case 2:
                    System.out.println("~~~~~~~~~~~~~~******* Be Happy *******~~~~~~~~~~~~~~");
                    System.out.println("User Login Page........");
                    User newuser=UserAction.userLogin();
                    if (newuser==null){
                        System.out.println("Invalid Details...\n\t Try Again" );
                    }else {
                        System.out.println("Login Successful");
                        UserAction.actions(newuser);
                    }
                    break;
                case 3:
                    System.out.println("~~~~~~~~~~~~~~******* Be Happy *******~~~~~~~~~~~~~~");
                    System.out.println("Sign Up / Register");
                    register();
                    break;
                case 4:
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Enter a valid Choice");
                    break;
            }
        }
    }


    public static void register(){
        System.out.println("\t\t\t\t\t\tA good vibes starts ..... Book My Show WELCOMES you...");
        System.out.println("Sign Up...");
        System.out.println("Enter your Name:");
        String userName = scanner.nextLine();
        System.out.println("Enter Id Name:");
        String idName = scanner.nextLine();
        System.out.println("Enter Your Password");
        String userPassword = scanner.nextLine();
        System.out.println("Enter your Location:");
        String nlocation= scanner.nextLine();
        for (User user:BookMyShow.getUsers()){
            if (user.getUserName().equals(userName)&&user.getUserPassWord().equals(userPassword)){
                System.out.println("User already exists...");
                System.out.println("\t\t\t\t\t Login into your Account...");
            }
        }
        BookMyShow.getUsers().add((new User(userName,userPassword,idName,nlocation)));
    }
}
