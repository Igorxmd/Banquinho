package factory;

import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.sql.*;

public class TesteConexao {
    public static void main(String[] args) throws SQLException, IOException{
        Connection connection = (Connection) new ConnectionFactory().getConnection();
        System.out.print("COnexão aberta!");
        connection.close();
    }
}