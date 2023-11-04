//
package Database;
import Models.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseManager {
    private String URL = "jdbc:mysql://localhost:3306/quanlytienluong";
    private String USERNAME = "admin";
    private String PASSWORD = "123456";
    private Connection con;

    public DatabaseManager() throws SQLException {
        this.connect();
    }

    public void disconnect() throws SQLException {
        this.con.close();
    }

    public void connect() throws SQLException {
        this.con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    public ResultSet executeQuery(String query) throws SQLException {
        Statement stmt = this.con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }

    public void loadUser(List<Employee> employees) throws SQLException{
        String sql = "SELECT * FROM `account` INNER JOIN `employee` ON `account`.id_user = `employee`.`id_user`;";
        ResultSet rs = this.executeQuery(sql);
        employees.clear();
        while(rs.next()){
            Employee e = new Employee(rs.getString("name"), rs.getDate("birth"), rs.getString("address"), rs.getInt("worksday"), rs.getDate("last_checked"), this);
            employees.add(e);
        }
    }
    
    
}
