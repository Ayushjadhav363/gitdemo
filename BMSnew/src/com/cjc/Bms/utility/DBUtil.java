package com.cjc.Bms.utility;
import java.sql.Connection;
import java.sql.DriverManager;


public class DBUtil {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BMS","root","Ayush");
        } catch (Exception e) {
            System.out.println("Database Connection Failed: " + e);
        }
        return con;
    }
}

