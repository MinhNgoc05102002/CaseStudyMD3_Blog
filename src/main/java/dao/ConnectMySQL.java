package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectMySQL {
    private static Connection connection = null;
    public static Connection getConnection(){
        //if (connection == null) {
            String jdbcURL = "jdbc:mysql://localhost:3306/case3";
            String jdbcUser = "root";
            String jdbcPassword = "2000";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword);
            } catch (Exception e) {
                e.printStackTrace();
            }
        //}
        return connection;
    }
}
