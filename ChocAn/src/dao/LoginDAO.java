package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Login;
import beans.User;
import util.ConnectionFactory;

public class LoginDAO {

	public int iOne(Login login){

        Connection connection = ConnectionFactory.openConnection();			// Connection to the database
        PreparedStatement pstInserting = null;								// PreparedStatement to process the SQL

        try {
            
        	pstInserting = connection.prepareStatement(""
            		+ " INSERT INTO `login`("
            		+ " fk_id_user, `password`) "
            		+ " VALUES "
            		+ " (?, (SELECT md5(?)));");								// SQL itself being prepared

            pstInserting.setInt(1, login.getFkIdUser());					// Replacing each ? with the correct value
            pstInserting.setString(2, login.getPassword());					// Replacing each ? with the correct value
            pstInserting.executeUpdate();									// SQL being executed

        } catch (SQLException e) {
            System.out.println(e.getMessage());								// Error Treatment
            return Login.UNSUCCESSFUL_INSERT;								// Method finished UNsuccessfully
        }
        
        ConnectionFactory.closeConnection(connection, pstInserting);		// Closing connection to the DBMS
        return Login.SUCCESSFUL_INSERT;										// Method finished successfully
	}
	
    public User logIn(Login login) // IN ORDER TO LOG IN, THIS METHOD RECEIVES A LOGIN OBJECT AND RETURNS THE USER ASSOCIATED TO IT
    {
        Connection connection = ConnectionFactory.openConnection(); 	// Connection to the database
        ResultSet rsLogin = null;										// ResultSet to receive the selected data
        PreparedStatement pstLogin = null;								// PreparedStatement to process the SQL

        User user = new User();
        
        try {
            
        	pstLogin = connection.prepareStatement(""
        			+ " SELECT * FROM user WHERE id_user = ?; ");		// SQL itself being prepared 

        	pstLogin.setInt(1, login.getFkIdUser());					// Replacing each ? with the correct value
        	
        	rsLogin = pstLogin.executeQuery();							// SQL being executed and results being assigned to ResultSet
            
            if (rsLogin.next()) {										// Filling the ArrayList instance with the received data
            	user.setIdUser(rsLogin.getInt("id_user"));
            	user.setStAddr(rsLogin.getString("st_addr"));
            	user.setAddrComp(rsLogin.getString("addr_comp"));
            	user.setCity(rsLogin.getString("city"));
            	user.setState(rsLogin.getString("state"));
            	user.setZipCode(rsLogin.getString("zip_code"));
            	user.setFstName(rsLogin.getString("fst_name"));
            	user.setLstName(rsLogin.getString("lst_name"));
            	user.setCellPhone(rsLogin.getString("cell_phone"));
				user.setHomePhone(rsLogin.getString("home_phone"));
				user.setWorkPhone(rsLogin.getString("work_phone"));
				user.setEmail(rsLogin.getString("email"));
            }
            else
            {
            	user = null;
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());							// Error Treatment
            return null;
        }
        
        ConnectionFactory.closeConnection(
        		connection, pstLogin, rsLogin);						// Closing connection to the DBMS
        
        return user;
    }

    public int verifyPassword(Login login) // Check if the informed password is the correct one
    {
        Connection connection = ConnectionFactory.openConnection(); 	// Connection to the database
        ResultSet rsLogin = null;										// ResultSet to receive the selected data
        PreparedStatement pstLogin = null;								// PreparedStatement to process the SQL
        
        try {
            
        	pstLogin = connection.prepareStatement(""
        			+ " SELECT * FROM login "
        			+ "WHERE fk_id_user = ? "
        			+ "AND password = (SELECT md5(?)) ");				// SQL itself being prepared 

        	pstLogin.setInt(1, login.getFkIdUser());					// Replacing each ? with the correct value
        	pstLogin.setString(2, login.getPassword());					// Replacing each ? with the correct value
        	
        	rsLogin = pstLogin.executeQuery();							// SQL being executed and results being assigned to ResultSet
            
            if (rsLogin.next())
            	return 1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());							// Error Treatment
            return -1;
        }
        
        ConnectionFactory.closeConnection(
        		connection, pstLogin, rsLogin);						// Closing connection to the DBMS
        return 0;
    }
    
    
}
