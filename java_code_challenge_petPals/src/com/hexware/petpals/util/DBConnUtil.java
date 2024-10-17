package com.hexware.petpals.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnUtil {
    public static Connection getConnection(String filename)  {
   
    	Connection connection = null;
        try {
       
            String connectionString = DBPropertyUtil.getConnectionString(filename);

   
            connection = DriverManager.getConnection(connectionString);
        } catch (Exception e) {
        
            e.printStackTrace();
        }
        return connection;
    }
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
