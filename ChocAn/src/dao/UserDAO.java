package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Conexao;

public class UserDAO {

	
    public ArrayList<String> sFstNames()
    {
        Connection conexao = Conexao.abrirConexao();
        ArrayList<String> lstNames = new ArrayList<String>();
        ResultSet rsListar = null;
        PreparedStatement pstListar = null;

        try {
            
            pstListar = conexao.prepareStatement(""
            		+ " SELECT * FROM user; ");

            rsListar = pstListar.executeQuery();
            
            while (rsListar.next()) {
            	lstNames.add(
            			rsListar.getString("fst_name")
            			);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        Conexao.fecharConexao(conexao, pstListar, rsListar);
        return lstNames;
    }
	
	
}
