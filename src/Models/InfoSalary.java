/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

public class InfoSalary {
    public int id_user;
    public double salary;
    public Employee employee;

    public InfoSalary(int id_user, double salary, Employee employee) {
        this.id_user = id_user;
        this.salary = salary;
        this.employee = employee;
    }
    
}
