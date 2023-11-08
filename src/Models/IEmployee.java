/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Models;

import java.sql.SQLException;

public interface IEmployee {
    public double getSalaryPerDay();
    public boolean checkOut();
    public boolean timeKeeping();
    public boolean updateToDB() throws SQLException;
    public double getSalary();
}
