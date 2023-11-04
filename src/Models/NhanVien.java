/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author cr4zyb0t
 */
public class NhanVien extends Employee implements IEmployee{
    @Override
    public boolean checkOut() {
       return true;
    }
    @Override 
    public double getSalaryPerDay(){
        return this.salaryPerDay * 2;
    }
}
