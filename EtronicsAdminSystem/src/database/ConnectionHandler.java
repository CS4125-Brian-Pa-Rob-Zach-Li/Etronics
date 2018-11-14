/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Brian,XintingLi
 */
public class ConnectionHandler {
    
    private final String url;
    private final String userName;
    private final String passWord;
    private Connection connect = null;
    private static ConnectionHandler instance = null;
    
    private ConnectionHandler(){
        url = "jdbc:mysql://212.17.40.243:3030/etronics";
        userName = "brian";
        passWord = "test";
    }
    
    public static ConnectionHandler getInstance(){
            if(instance == null) {
                instance = new ConnectionHandler();
            }
            return instance;
	}
    
    public Connection makeConnection(){
        try {
        // This will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.jdbc.Driver");
       
        // Setup the connection with the DB
        connect = DriverManager.getConnection(url, userName, passWord);
        
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database server.", e);
        }
        return connect;
    }
}
