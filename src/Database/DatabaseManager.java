package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
