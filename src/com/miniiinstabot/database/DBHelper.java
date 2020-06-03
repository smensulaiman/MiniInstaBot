package com.miniiinstabot.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

    public static Connection conn;
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/tvnafesta";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
