package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ConnectionFactory implements MySQL {
    
    private ConnectionFactory() {}
    
    public static Connection openConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(HOST,USER,PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
    
    public static void closeConnection(Connection connection){
        try {
            if (connection != null){
            	connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement prepStatement){
        try {
            if (connection != null){
            	connection.close();
            }
            
            if (prepStatement != null){
            	prepStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement prepStatement, ResultSet resultSet)
    {
        try
        {
            if(connection!=null)
            	connection.close();
            if(prepStatement != null)
            	prepStatement.close();
            if(resultSet!=null)
            	resultSet.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
}