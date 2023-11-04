
package quanlytienluong;
import Database.DatabaseManager;
import Models.*;
import Security.User;
import Utils.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
public class QuanLyTienLuong {
    public static ArrayList<Employee> employees = new ArrayList<Employee>();
    public static Employee currentEmployee  = new Employee();
    
    public static int renderMenu(String options[], Scanner sc){
        int choice = -1;
        while(choice == -1){
            Tools.cout("Menu:","\n");
            for(int i = 0; i < options.length; i++){
                System.out.println("\t\t" + (i + 1) + ". " + options[i]);
            }
            Tools.cout("\t\t0. Exit","\n");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            if(choice < 0 || choice > options.length){
                System.out.println("Invalid choice, please try again.");
                choice = -1;
            }
        }
        return choice;
    }
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
        Utils.User.setUserFromID(currentEmployee,user.getIdUser(),db);
        if(currentEmployee == null){
            Tools.cout("Error when loading user !","\n");
            return ;
        }
        Tools.cout("Login success","\n");
        db.loadUser(employees);
        int choice = -1;
        while (choice != 0) {
            choice = renderMenu(new String[]{"Show profile","View employees"}, sc);
            
            switch (choice) {
                case 1:
                    Security.Identity.showProfile(currentEmployee);
                    sc.nextLine();
                    break;
                case 2:
                    for(Employee e : employees){
                        System.out.printf("%s\n",e.getName());
                    }
                    break;
                case 0:
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
