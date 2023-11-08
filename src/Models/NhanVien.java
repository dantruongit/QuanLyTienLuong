/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;
import Utils.Tools;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class NhanVien extends Employee implements IEmployee{
    public static NhanVien clone(Employee e){
        NhanVien m = new NhanVien();
        m.setIdUser(e.getIdUser());
        m.setName(e.getName());
        m.setBirth(e.getBirth());
        m.setAddress(e.getAddress());
        m.setWorksday(e.getWorksday());
        m.setLast_checked(e.getLast_checked());
        m.setDb(e.getDb());
        m.setRole(e.role);
        return m;
    }
    @Override
    public boolean checkOut() {
       return true;
    }
    @Override 
    public double getSalaryPerDay(){
        return this.salaryPerDay * 1;
    }
    @Override
    public boolean timeKeeping(){
        this.worksday += 1;
        this.last_checked = new Date(System.currentTimeMillis());
        try{
            this.updateToDB();
        }
        catch(SQLException e){

        }
        return true;
    }
    @Override
    public boolean updateToDB() throws SQLException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sql = String.format("UPDATE `employee` SET `worksday`='%d',`last_checked`='%s' WHERE id_user='%d'",this.getWorksday(),sdf.format(this.getLast_checked()), this.getIdUser());
        this.db.execute(sql);
        return true;
    }
    @Override
    public double getSalary(){
        return this.getSalaryPerDay() * this.getWorksday();
    }
}
