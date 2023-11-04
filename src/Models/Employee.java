package Models;
import java.util.Date;
import java.sql.SQLException;
public class Employee{
    protected String name;
    protected Date birth;
    protected String address;
    protected double salaryPerDay = 200000;
    protected int worksday;
    protected Date last_checked;
    protected Database.DatabaseManager db;
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
    
    public boolean createData(){
        String sql = "";
        return true;
    }
    
    public boolean commitData(){
        String sql = "";
        return true;
    }
    public boolean deleteSelf(){
        String sql = "";
        return true;
    }
}
