package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Conexao implements MySQL {
    
    private Conexao() {}
    
    public static Connection abrirConexao() {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(HOST,USER,PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
    
    public static void fecharConexao(Connection con){
        try {
            if (con != null){
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void fecharConexao(Connection con, PreparedStatement pstm){
        try {
            if (con != null){
                con.close();
            }
            
            if (pstm != null){
                pstm.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void fecharConexao(Connection con, PreparedStatement pstm, ResultSet rst)
    {
        try
        {
            if(con!=null)
                con.close();
            if(pstm != null)
                pstm.close();
            if(rst!=null)
                rst.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
}