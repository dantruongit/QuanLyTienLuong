
package quanlytienluong;
import Database.DatabaseManager;
import Security.User;

import java.sql.SQLException;
import java.util.Scanner;
public class QuanLyTienLuong {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        DatabaseManager db = new DatabaseManager();
        try {
            db.connect();
            System.out.println("Connected to database");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        boolean isLogin = false; 
        User user = new User();
        while(!isLogin){
            isLogin = Security.Identity.login(sc, db, user);
        }
        System.out.println("Login success");
        System.out.println("User properties:");
        System.out.println("Username: " + user.getUserName());
        System.out.println("Password: " + user.getPassWord());
        System.out.println("Role: " + user.getRole());
        System.out.println("ID: " + user.getIdUser());
        int choice = 0;

        while (choice != 3) {
            System.out.println("Menu:");
            System.out.println("\t\t1. Option 1");
            System.out.println("\\t\\t2. Option 2");
            System.out.println("\\t\\t3. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // Do something for option 1
                    break;
                case 2:
                    // Do something for option 2
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }

        db.disconnect();
    }
    
}
