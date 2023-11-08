package Models;
import java.util.Date;
import java.sql.SQLException;
public class Employee{
    private int id_user;
    protected String name;
    protected Date birth;
    protected String address;
    protected double salaryPerDay = 200000;
    protected int worksday;
    protected Date last_checked;
    protected Database.DatabaseManager db;
    protected String role;
    public Employee(){
        
    }

    public Employee(String name, Date birth, String address, int worksday, Date last_checked, Database.DatabaseManager db) {
        this.name = name;
        this.birth = birth;
        this.address = address;
        this.worksday = worksday;
        this.last_checked = last_checked;
        this.db = db;
    }
    
    @Override 
    public String toString(){
        String res = String.format("Information\nName\t : %s\nBirth\t : %s"
                + "\nAddress\t : %s\nWorks \t : %d days",this.name,this.birth.toString(),this.address, this.worksday);
        return res;
    }
    
    public String display(){
        String res = String.format("|%-8d|%-20s|%-12s|%-20s|%-10d|",this.id_user,this.name,this.birth.toString(),this.address, this.worksday);
        return res;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public String getAddress() {
        return address;
    }

    public double getSalaryPerDay() {
        return salaryPerDay;
    }

    public int getWorksday() {
        return worksday;
    }

    public Date getLast_checked() {
        return last_checked;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWorksday(int worksday) {
        this.worksday = worksday;
    }

    public void setLast_checked(Date last_checked) {
        this.last_checked = last_checked;
    }
    
    public void setDb(Database.DatabaseManager db){
        this.db = db;
    }
    public Database.DatabaseManager getDb(){
        return this.db;
    }
    public void setIdUser(int id){
        this.id_user = id;
    }
    public int getIdUser(){
        return this.id_user;
    }
    public void setRole(String role){
        this.role = role;
    }
    public String getRole(){
        return this.role;
    }
    
}
