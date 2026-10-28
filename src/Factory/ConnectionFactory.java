package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/banquinho";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection() {
    try {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASS);
    } catch (SQLException | ClassNotFoundException ex) {
        throw new RuntimeException(ex);
    }
}
    
    public static void closeConnection(Connection con){
        try{
            if(con != null ){
                con.close();
            }
        }catch(SQLException ex){
            throw new RuntimeException("Problema ao encerrar a conexão: ", ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
       closeConnection(con);
       try{
           if(stmt != null){
               stmt.close();
           }
       }catch(SQLException ex){
           throw new RuntimeException("Problema ao encerrar a conexão: ", ex);
       }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        closeConnection(con, stmt);
        try{
            if(rs != null){
                rs.close();
            }
        }catch(SQLException ex){
            throw new RuntimeException("Problema ao encerrar a conexão: ", ex);
        }
    }
    
}