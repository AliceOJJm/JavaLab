
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseManager {
    protected Connection dbConnection;
    public DatabaseManager(Connection connection){
        this.dbConnection = connection;
    }
}
