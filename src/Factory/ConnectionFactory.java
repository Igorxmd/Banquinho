package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class ConnectionFactory {
    public Connection getConnection() throws SQLException, SQLException{
        try{
            return DriverManager.getConnection("jhbc:mysql://localhost/banquinho","root","");
        }catch(SQLException excecao){
            throw new RuntimeException(excecao);
        }
    }
    
}