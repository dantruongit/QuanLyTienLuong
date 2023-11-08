
package Utils;

import java.util.Date;
import Database.DatabaseManager;
import Models.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import Utils.Tools;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;


public class User{
    public static void setUserFromID(Employee e, int id, DatabaseManager db) throws SQLException{
        String sql = "SELECT * FROM `account` INNER JOIN `employee` ON `account`.id_user = " + id + " AND `employee`.`id_user` = " + id + ";";
        ResultSet rs = db.executeQuery(sql);
        if(rs.next()){
            e.setName(rs.getString("name"));
            e.setBirth(rs.getDate("birth"));
            e.setAddress(rs.getString("address"));
            e.setWorksday(rs.getInt("worksday"));
            e.setLast_checked(rs.getDate("last_checked"));
            e.setDb(db);
        }
        else{
            e = null;
        }
    }
    public static boolean addNewUser(DatabaseManager db, Scanner sc) throws SQLException{
        int id_user = 999;
        ResultSet rs = db.executeQuery("SELECT id_user FROM account ORDER BY id_user DESC LIMIT 1");
        if(rs.next()){
            id_user = rs.getInt("id_user") + 1;
        }
        
        String username = Tools.input("Input new username", sc);
        String password = Tools.input("Input password", sc);
        String role = Tools.input("Input role", sc);
        String sqlAddAccount = String.format("INSERT INTO `account`(`username`, `password`, `role`, `id_user`) "
                + "VALUES ('%s','%s','%s','%d')",username,password,role,id_user);
        boolean res = db.execute(sqlAddAccount);
        
        String name = Tools.input("Input fullname", sc);
        Date date = new Date(Date.parse(Tools.input("Input birth (dd/MM/yyyy)", sc)));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String birth = sdf.format(date);
        String address = Tools.input("Input address", sc);
        int worksday = Integer.parseInt(Tools.input("Input worksday", sc));
        String last_checked = sdf.format(new Date(System.currentTimeMillis()));
        String sqlAddEmployee = String.format(""
                + "INSERT INTO `employee`(`id_user`, `name`, `birth`, `address`, `worksday`, `last_checked`) "
                + "VALUES ('%d','%s','%s','%s','%d','%s')",id_user,name, birth, address, worksday, last_checked);
        boolean res1 = db.execute(sqlAddEmployee);
        
        Employee e = new Employee(name, date, address, worksday, new Date(), db);
        quanlytienluong.Run.employees.add(e);
        return res && res1;
    }
    public static String getRoleFromID(int id, DatabaseManager db) throws SQLException{
        String sql = "SELECT role FROM `account` WHERE id_user = '" + id + "'";
        ResultSet rs = db.executeQuery(sql);
        if(rs.next()){
            String res = rs.getString("role");
            rs.close();
            return res;
        }
        rs.close();
        return "";
    }
    public static Employee getUserFromID(int id, ArrayList<Employee> es){
        for(Employee e : es){
            if(e.getIdUser() == id) return e;
        }
        return new Employee();
    }
}
