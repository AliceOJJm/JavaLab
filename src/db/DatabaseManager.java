
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    protected Connection dbConnection;
    protected Statement dbStatement;
    public DatabaseManager(Connection connection) throws SQLException{
        this.dbConnection = connection;
        this.dbStatement = dbConnection.createStatement();
    }
    
    public ResultSet query(String query) throws SQLException{
        return dbStatement.executeQuery(query); 
    }
}
