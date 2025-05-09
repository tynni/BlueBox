import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        try {
            //comment when working

            String url = "jdbc:mysql://localhost:3306/bluebox";
            String user = "root";
            String password = "blueboxp";

            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;

            //add your connection here, but delete it for privacy reasons before pushing changes


        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
            return null;
        }
    }
}