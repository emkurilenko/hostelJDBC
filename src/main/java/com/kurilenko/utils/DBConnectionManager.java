package com.kurilenko.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionManager {
    private Connection connection;

    public DBConnectionManager(Properties properties)  throws ClassNotFoundException, SQLException {
        String driver = properties.getProperty("jdbc.driver");
        if (driver != null) {
            Class.forName(driver) ;
        }
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        this.connection = DriverManager.getConnection(url,username, password);
    }

    public Connection getConnection(){
        return this.connection;
    }
}
