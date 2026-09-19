package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnnection {
    public static Connection getConnectionFromConfig(){
        String URL = System.getenv("DB_URL");
        String USER = System.getenv("DB_USER");
        String PASSWORD = System.getenv("DB_PASSWORD");
        try{
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch(SQLException e){
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }
}
