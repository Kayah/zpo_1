package com.akonoriev.database;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import com.mysql.jdbc.PreparedStatement;

import java.sql.*;
import java.util.Properties;

/**
 * Created by sss on 10.03.16.
 */
public class Connecter {
    private static final String URL = "jdbc:mysql://localhost:3306/users";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123";

    private Connection connection;

   public Connecter() {
       try {
           connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
       }catch (SQLException e){
           e.printStackTrace();
       }
   }

    public Connection getConnection() {
        return connection;
    }


}
