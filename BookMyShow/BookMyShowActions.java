import java.util.*;

public class BookMyShowActions {
    static Scanner scanner= new Scanner(System.in);
    public static void start(){
        while (true){
            System.out.println("Enter the choice: \n1. Admin Login \n2. User Login \n3. SignUp / Register \n4. Exit");
            int choice =Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Admin Login Page");
                    Admin checkAdmin = AdminAction.adminLog(scanner);
                    if (checkAdmin!=null){
                        System.out.println("Login Successful..." + checkAdmin.getAdminName()+"\n A warm welcome from Book My Show");
                        AdminAction.actions(scanner);
                    }
                    else {
                        System.out.println("Invalid details . Please Try Again...");
                    }
                    break;
                case 2:
                    System.out.println("User Login Page");
                    User checkUser = UserAction.userLog(scanner);
                    if (checkUser!=null){
                        System.out.println("Login Successful..." + checkUser.getUserName()+"\n A warm welcome from Book My Show");
                    }
                    else {
                        System.out.println("Invalid details . Please Try Again...");
                    }
                    break;
                case 3:
                    System.out.println("Sign Up / Register");
                    register();
                    break;
                case 4:
                    System.out.println("Exit");
                    return ;
            }
        }
    }
    public static void register(){
        System.out.println("Enter your Name:");
        String regName = scanner.nextLine();
        System.out.println("Enter Password:");
        String regPswd = scanner.nextLine();

        for (User user:BookMyShow.getUser()){
            if (user.getUserName().equals(regName)&&user.getUserPassword().equals(regPswd)){
                System.out.println("Registered Account! \n You can Login ");
                return;
            }
        }
        BookMyShow.user.add(new User(regName,regPswd));
        System.out.println("Successfully Registered...");
    }
}


