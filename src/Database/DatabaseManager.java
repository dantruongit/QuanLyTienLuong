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
    
    public boolean execute(String query) throws SQLException {
        Statement stmt = this.con.createStatement();
        boolean rs = stmt.execute(query);
        return rs;
    }

    public void loadUser(List<Employee> employees) throws SQLException{
        String sql = "SELECT * FROM `account` INNER JOIN `employee` ON `account`.id_user = `employee`.`id_user`;";
        ResultSet rs = this.executeQuery(sql);
        employees.clear();
        
        quanlytienluong.Run.managers.clear();
        quanlytienluong.Run.nhanviens.clear();
        quanlytienluong.Run.salarys.clear();
        
        while(rs.next()){
            Employee e = new Employee();
            e.setName(rs.getString("name"));
            e.setBirth(rs.getDate("birth"));
            e.setAddress(rs.getString("address"));
            e.setWorksday(rs.getInt("worksday"));
            e.setLast_checked(rs.getDate("last_checked"));
            e.setIdUser(rs.getInt("id_user"));
            e.setDb(this);
            e.setRole(rs.getString("role"));
            employees.add(e);
            if(Security.Identity.isAdmin(e.getIdUser(), this)){
                quanlytienluong.Run.managers.add(Manager.clone(e));
            }
            else{
                quanlytienluong.Run.nhanviens.add(NhanVien.clone(e));
            }
        }
        rs.close();
    }
    
    
}
