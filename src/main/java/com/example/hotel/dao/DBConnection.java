////package com.example.hotel.dao;
////
////public class DBConnection {
////}
package com.example.hotel.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection
{
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public static Connection getConnection()
    {
        try
        {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e)
        {
            System.out.println("Database Connection Failed: " + e.getMessage());
            return null;
        }
    }
}

