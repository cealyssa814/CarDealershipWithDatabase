package com.pluralsight.Data;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;

public class DatabaseConnection {

    public static String username = "root";
    public static String password = "yearup24";
    public static String dataBase = "sugarcreek";
    public static String URL = "jdbc:mysql://localhost:3306/" + dataBase;
    //private BasicDataSource  dataSource;

    public static Connection getConnection () throws SQLException {
        Connection connection = DriverManager.getConnection(URL, username, password);
        return connection;
    }
}