package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import beans.*;
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

	public int verifyLogin (Login login){

        Connection connection = ConnectionFactory.openConnection(); 	// Connection to the database
        ResultSet rsLogin = null;										// ResultSet to receive the selected data
        PreparedStatement pstLogin = null;								// PreparedStatement to process the SQL
        
        try {
            
        	pstLogin = connection.prepareStatement(" "
        			+ "SELECT * "
        			+ "FROM login "
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
	
    public User retrieveUserType(Login login){
    	
        Connection connection = ConnectionFactory.openConnection(); 	// Connection to the database
        ResultSet rsType = null;										// ResultSet to receive the selected data
        PreparedStatement pstType = null;								// PreparedStatement to process the SQL
        UserDAO userDAO = new UserDAO();
        
     // Verifying if there is a registry for this user in the PROVIDER table
        try {
            
        	pstType = connection.prepareStatement(""
        			+ " SELECT * FROM provider "
        			+ "WHERE fk_id_provider = ?; ");					// SQL itself being prepared 

        	pstType.setInt(1, login.getFkIdUser());						// Replacing each ? with the correct value
        	
        	rsType = pstType.executeQuery();							// SQL being executed and results being assigned to ResultSet
            
            if (rsType.next())
            {
            	Provider provider = new Provider();
            	provider.setLogin(login);
            	
        		userDAO.fillData(provider);
            	return provider;
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());							// Error Treatment
            return null;
        }

     // Verifying if there is a registry for this user in the MANAGER table
        try {

        	pstType = connection.prepareStatement(""
        			+ " SELECT * FROM manager "
        			+ "WHERE fk_id_manager = ?; ");							// SQL itself being prepared 

        	pstType.setInt(1, login.getFkIdUser());							// Replacing each ? with the correct value

        	rsType = pstType.executeQuery();								// SQL being executed and results being assigned to ResultSet

            if (rsType.next())
            {
            	Manager manager = new Manager();
            	manager.setLogin(login);
            	
            	userDAO.fillData(manager);
            	return manager;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());								// Error Treatment
            return null;
        }

     // Verifying if there is a registry for this user in the OPERATOR table
        try {

        	pstType = connection.prepareStatement(""
        			+ " SELECT * FROM operator "
        			+ "WHERE fk_id_operator = ?; ");						// SQL itself being prepared 

        	pstType.setInt(1, login.getFkIdUser());							// Replacing each ? with the correct value
        	
        	rsType = pstType.executeQuery();								// SQL being executed and results being assigned to ResultSet
            
            if (rsType.next())
            {
            	Operator operator = new Operator();
            	operator.setLogin(login);
            	
            	userDAO.fillData(operator);
            	return operator;
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());								// Error Treatment
            return null;
        }
        
     // Verifying if there is a registry for this user in the MEMBER table
        try {
            
        	pstType = connection.prepareStatement(""
        			+ " SELECT * FROM member "
        			+ "WHERE fk_id_member = ?; ");							// SQL itself being prepared 

        	pstType.setInt(1, login.getFkIdUser());							// Replacing each ? with the correct value
        	
        	rsType = pstType.executeQuery();								// SQL being executed and results being assigned to ResultSet
            
            if (rsType.next())
            {
            	Member member = new Member();
            	member.setLogin(login);
            	member.setStatus(rsType.getInt("status"));
            	
            	userDAO.fillData(member);
            	return member;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());								// Error Treatment
            return null;
        }
        
        ConnectionFactory.closeConnection(connection, pstType, rsType);		// Closing connection to the DBMS
        
        return null;
    }
    
    /*
    public int verifyPassword(Login login) // Check if the informed password is the correct one
    {
        Connection connection = ConnectionFactory.openConnection(); 	// Connection to the database
        ResultSet rsLogin = null;										// ResultSet to receive the selected data
        PreparedStatement pstLogin = null;								// PreparedStatement to process the SQL
        
        try {
            
        	pstLogin = connection.prepareStatement(" "
        			+ "SELECT * FROM login "
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
    */
    
}
