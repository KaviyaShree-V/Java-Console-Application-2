//import java.util.Scanner;

import java.util.Scanner;

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
                    //System.out.println("Login is Successfull!...");
                    admin = admins;
                    return admin;
                }
            }
            if (admin==null){
                //System.out.println("Login Unsucessful. \n Please try Again..");
                return null;
            }
        }
        return null;
    }
}
