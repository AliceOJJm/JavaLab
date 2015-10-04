/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javalab;
import dbConnector.*;
import java.sql.Connection;
import java.sql.SQLException;
        
public class JavaLab {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DatabaseConnector dbConnector = new DatabaseConnector();
        Connection dbConnection = dbConnector.getConnection();
        System.out.println("Hello!");
    }
    
}
