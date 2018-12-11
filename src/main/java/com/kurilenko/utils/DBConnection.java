package com.kurilenko.utils;

import javax.print.URIException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static String DB_PROPIRTIES = "db.properties";
    private static DBConnection singleInstanse;
    private static Connection connction;
    private DBConnection(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(getClass().getClassLoader().getResource(DB_PROPIRTIES).toURI())));
            connction = new DBConnectionManager(properties).getConnection();
        }catch (URISyntaxException | ClassNotFoundException | SQLException | IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static DBConnection getInstance(){
        if(singleInstanse == null){
            synchronized (DBConnection.class){
                if(singleInstanse == null){
                    singleInstanse = new DBConnection();
                }
            }
        }
        return  singleInstanse;
    }

    public Connection getConnction(){
        return connction;
    }
}
