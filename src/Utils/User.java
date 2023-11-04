/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Database.DatabaseManager;
import Models.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import Utils.Tools;

/**
 *
 * @author cr4zyb0t
 */
public class User {
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
}
