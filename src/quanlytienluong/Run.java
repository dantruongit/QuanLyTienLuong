package quanlytienluong;
import Database.DatabaseManager;
import Models.*;
import Security.User;
import Utils.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Run {
    public static ArrayList<Employee> employees = new ArrayList<Employee>();
    public static ArrayList<Manager> managers = new ArrayList<Manager>();
    public static ArrayList<NhanVien> nhanviens = new ArrayList<NhanVien>();
    public static ArrayList<InfoSalary> salarys = new ArrayList<InfoSalary>();
    
    public static Employee currentEmployee  = new Employee();
    
    public static int renderMenu(String options[], Scanner sc){
        int choice = -1;
        while(choice == -1){
            Tools.cout("\n-------------------------------------------------------------------", "\n");
            Tools.cout("Menu:","\n");
            for(int i = 0; i < options.length; i++){
                System.out.println("\t\t" + (i + 1) + ". " + options[i]);
            }
            Tools.cout("\t\t0. Exit","\n");
            Tools.cout("\n-------------------------------------------------------------------", "\n");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            if(choice < 0 || choice > options.length){
                System.out.println("Invalid choice, please try again.");
                choice = -1;
            }
        }
        sc.nextLine();
        return choice;
    }
    
    public static void renderEmployees(ArrayList<Employee> e){
        Tools.cout("----------------------------------------------------------------------------", "\n");
        Tools.cout("|   ID   |      Ho ten        |  Ngay sinh |      Que quan      |   Days   |", "\n");
        Tools.cout("----------------------------------------------------------------------------", "\n");
        for(int i = 0;i<e.size();i++){
            Tools.cout(((Employee)e.get(i)).display(), "\n");
            Tools.cout("----------------------------------------------------------------------------", "\n");
        }
    }
    
    public static String renderSalaryManager(ArrayList<Manager> t){
        String res = "";
        for(Manager tmp : t){
            res += String.format("|%-8d|%-20s|%-20s|%-9.1f |\n",tmp.getIdUser(),tmp.getName(),tmp.getRole(),tmp.getSalary());
            res += "---------------------------------------------------------------\n";
            InfoSalary inf = new InfoSalary(tmp.getIdUser(), tmp.getSalary(), Utils.User.getUserFromID(tmp.getIdUser(), employees));
            salarys.add(inf);
        }
        return res;
    }
    
    public static String renderSalaryNhanVien(ArrayList<NhanVien> t){
        String res = "";
        for(NhanVien tmp : t){
            res += String.format("|%-8d|%-20s|%-20s|%-9.1f |\n",tmp.getIdUser(),tmp.getName(),tmp.getRole(),tmp.getSalary());
            res += "---------------------------------------------------------------\n";
            InfoSalary inf = new InfoSalary(tmp.getIdUser(), tmp.getSalary(), Utils.User.getUserFromID(tmp.getIdUser(), employees));
            salarys.add(inf);
        }
        return res;
    }
    
    public static void sortByAsc(){
        for(int i = 0; i< salarys.size() - 1;i++)
            for(int j = i+1 ;j<salarys.size();j++){
                if(salarys.get(i).salary > salarys.get(j).salary){
                    Collections.swap(salarys, i, j);
                }
            }
    }
    
    public static void sortByDesc(){
        for(int i = 0; i< salarys.size() - 1;i++)
            for(int j = i+1 ;j<salarys.size();j++){
                if(salarys.get(i).salary < salarys.get(j).salary){
                    Collections.swap(salarys, i, j);
                }
            }
    }
    
    public static String render(){
        String res = "";
        for(InfoSalary tmp : salarys){
            res += String.format("|%-8d|%-20s|%-20s|%-9.1f |\n",tmp.id_user, tmp.employee.getName(),tmp.employee.getRole(),tmp.salary);
            res += "---------------------------------------------------------------\n";
        }
        return res;
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
        int choice;
        while (1 > 0) {
            choice = renderMenu(new String[]{"Show profile","View employees", "Manage employees","Timekeeping", "Caculate Salary", "Search by name"}, sc);
            
            switch (choice) {
                //Show profile
                case 1:{
                    Security.Identity.showProfile(currentEmployee);
                    Tools.input("Enter to continue", sc);
                    break;
                }
                //View employees
                case 2:{
                    renderEmployees(employees);
                    Tools.input("Enter to continue", sc);
                    break;
                }
                // Manage employees
                case 3 : {
                    if(user.isAdmin()){
                        choice = renderMenu(new String[]{"Add employee", "Delete employee"}, sc);
                        boolean res = false;
                        switch(choice){
                            //Add employee
                            case 1 : {
                                res = !Utils.User.addNewUser(db, sc);
                            }
                            //Delete employee
                            case 2 : {
                                renderEmployees(employees);
                                int id = Tools.input("Input ID to delete", sc, -1);
                                String sqlDeleteAccount = String.format("DELETE FROM `account` WHERE id_user = '%d'",id);
                                String sqlDeleteEmployee = String.format("DELETE FROM `employee` WHERE id_user = '%d'",id);
                                
                                db.execute(sqlDeleteAccount);
                                db.execute(sqlDeleteEmployee);
                                
                                db.loadUser(employees);
                                
                                res = true;
                                break;
                            }
                            case 0 : {
                                break;
                            }
                            default:{
                                Tools.cout("Invalid choice, please try again.","\n");
                                break;
                            }
                        }
                        String output = String.format("\nResponse : %s",(res ? "Success !" : "Failed"));
                        Tools.cout(output, "\n");
                    }
                    else{
                        Tools.cout("You don't have permission access here !", "\n");
                    }
                    Tools.input("Enter to continue", sc);
                    break;
                }
                case 4 :{
                    if(user.isAdmin()){
                        for(Manager mng : managers){
                            if(mng.getIdUser() == user.getIdUser()){
                                mng.timeKeeping();
                                Tools.cout(String.format("Timekeeping success ! Worksday : %d",mng.getWorksday()),"\n");
                                Tools.input("Enter to continue", sc);
                                break;
                            }
                        }
                    }
                    else{
                        for(NhanVien nv : nhanviens){
                            if(nv.getIdUser() == user.getIdUser()){
                                boolean res = nv.timeKeeping();
                                if(res){
                                    Tools.cout(String.format("Timekeeping success ! Worksday : %d",nv.getWorksday()),"\n");
                                }
                                else{
                                    Tools.cout(String.format("Timekeeping failed ! Worksday : %d",nv.getWorksday()),"\n");
                                }
                                Tools.input("Enter to continue", sc);
                                break;
                            }
                        }
                    }
                    break;
                }
                case 5:{
                    salarys.clear();
                    String render = renderSalaryManager(managers) + renderSalaryNhanVien(nhanviens);
                    Tools.cout("---------------------------------------------------------------", "\n");
                    Tools.cout("|   ID   |      Ho ten        |      Chuc vu       |   Luong  |", "\n");
                    Tools.cout("---------------------------------------------------------------", "\n");
                    Tools.cout(render, "\n");
                    
                    choice = renderMenu(new String[]{"Sort ascending by salary", "Sort descending by salary"}, sc);
                    switch(choice){
                        //Sx tang dan
                        case 1 :{
                            sortByAsc();
                            String re_render = render();
                            Tools.cout("\t\tSORT BY ASCENDING", "\n");
                            Tools.cout("---------------------------------------------------------------", "\n");
                            Tools.cout("|   ID   |      Ho ten        |      Chuc vu       |   Luong  |", "\n");
                            Tools.cout("---------------------------------------------------------------", "\n");
                            Tools.cout(re_render, "\n");
                            break;
                        }
                        //Sx giam dan
                        case 2 :{
                            sortByDesc();
                            String re_render = render();
                            Tools.cout("\t\tSORT BY DESCENDING", "\n");
                            Tools.cout("---------------------------------------------------------------", "\n");
                            Tools.cout("|   ID   |      Ho ten        |      Chuc vu       |   Luong  |", "\n");
                            Tools.cout("---------------------------------------------------------------", "\n");
                            Tools.cout(re_render, "\n");
                            break;
                        }
                        case 0 : {
                            break;
                        }
                    }
                    Tools.input("Enter to continue", sc);
                    break;
                }
                case 6: {
                    String name = Tools.input("Input name need to search", sc);
                    ArrayList<Employee> results = new ArrayList<Employee>();
                    for(Employee e : employees){
                        if(e.getName().contains(name)){
                            results.add(e);
                        }
                    }
                    Tools.cout("\t\tSearch results", "\n");
                    renderEmployees(results);
                    Tools.input("Enter to continue", sc);
                    break;
                }
                case 0:{
                    db.disconnect();
                    Tools.cout("Exiting...","\n");
                    System.exit(0);
                }
                default:{
                    Tools.cout("Invalid choice, please try again.","\n");
                    break;
                }
            }
        }
    }
}
