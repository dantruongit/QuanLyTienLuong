package Models;

public class Employee {
    protected String name;
    protected int age;
    
    protected double salaryPerDay;
    
    public Employee(String name, int age, double salaryPerDay) {
        this.name = name;
        this.age = age;
        this.salaryPerDay = salaryPerDay;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public double getsalaryPerDay() {
        return salaryPerDay;
    }

}
