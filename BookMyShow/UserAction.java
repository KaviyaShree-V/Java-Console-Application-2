import java.util.Scanner;

public class UserAction {
    public static User userLog(Scanner scanner) {
        System.out.println("Enter the Admin Name");
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
}
