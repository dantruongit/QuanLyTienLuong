package Models;

public class Manager extends Employee implements IEmployee{
    @Override
    public boolean checkOut() {
       return true;
    }
    @Override 
    public double getSalaryPerDay(){
        return this.salaryPerDay * 2;
    }
}
