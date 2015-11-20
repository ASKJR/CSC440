package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import beans.*;
import util.ConnectionFactory;

public class UserDAO {

	
    public ArrayList<User> sAll()
    {
        Connection connection = ConnectionFactory.openConnection(); 	// Connection to the database
        ResultSet rsListing = null;										// ResultSet to receive the selected data
        PreparedStatement pstListing = null;							// PreparedStatement to process the SQL
        
        ArrayList<User> userList = new ArrayList<User>();				// Object intended to be filled and returned

        try {
            
        	pstListing = connection.prepareStatement(""
        			+ " SELECT * FROM user; ");							// SQL itself being prepared 

            rsListing = pstListing.executeQuery();						// SQL being executed and results being assigned to ResultSet
            
            while (rsListing.next()) {									// Filling the ArrayList instance with the received data
            	userList.add(
        			new User(
        				rsListing.getInt("id_user"),
        				rsListing.getString("st_addr"),
        				rsListing.getString("addr_comp"),
        				rsListing.getString("city"),
        				rsListing.getString("state"),
        				rsListing.getString("zip_code"),
        				rsListing.getString("fst_name"),
        				rsListing.getString("lst_name"),
        				rsListing.getString("cell_phone"),
        				rsListing.getString("home_phone"),
        				rsListing.getString("work_phone"),
        				rsListing.getString("email")
        					)
        			);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());							// Error Treatment
            return null;
        }
        
        ConnectionFactory.closeConnection(
        		connection, pstListing, rsListing);						// Closing connection to the DBMS
        
        return userList;
    }
    
	public int iOne(User user){
		
        Connection connection = ConnectionFactory.openConnection();		// Connection to the database
        ResultSet rsInserting = null;									// ResultSet to receive the selected data
        PreparedStatement pstInserting = null;							// PreparedStatement to process the SQL
        
        int generatedId = -1;											// Generated ID to be returned

        try {
            
        	pstInserting = connection.prepareStatement(""
            		+ "INSERT INTO `user`("
            		+ "st_addr, "
            		+ "addr_comp, "
            		+ "city, "
            		+ "state, "
            		+ "zip_code, "
            		+ "lst_name, "
            		+ "fst_name, "
            		+ "cell_phone, "
            		+ "home_phone, "
            		+ "work_phone, "
            		+ "email) "
            		+ "VALUES "
            		+ "(?,?,?,?,?,?,?,?,?,?,?)"
            		, Statement.RETURN_GENERATED_KEYS);					// SQL itself being prepared


            pstInserting.setString(1, user.getStAddr());				// Replacing each ? with the correct value
            pstInserting.setString(2, user.getAddrComp());
            pstInserting.setString(3, user.getCity());
            pstInserting.setString(4, user.getState());
            pstInserting.setString(5, user.getZipCode());
            pstInserting.setString(6, user.getLstName());
            pstInserting.setString(7, user.getFstName());
            pstInserting.setString(8, user.getCellPhone());
            pstInserting.setString(9, user.getHomePhone());
            pstInserting.setString(10, user.getWorkPhone());
            pstInserting.setString(11, user.getEmail());
        	
            pstInserting.executeUpdate();								// SQL being executed
            rsInserting = pstInserting.getGeneratedKeys();				// Generated ID being retrieved
            
            if (rsInserting.next()) {
            	generatedId = rsInserting.getInt(1);					// Assigning generated ID to the returning variable
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());							// Error Treatment
            return -1;
        }
        
        ConnectionFactory.closeConnection(
        		connection, pstInserting, rsInserting);					// Closing connection to the DBMS
        return generatedId;
	}
    
    public User retrieveUserType(User user){
        Connection connection = ConnectionFactory.openConnection(); 	// Connection to the database
        ResultSet rsType = null;										// ResultSet to receive the selected data
        PreparedStatement pstType = null;								// PreparedStatement to process the SQL        
        
     // Verifying if there is a registry for this user in the PROVIDER table
        try {
            
        	pstType = connection.prepareStatement(""
        			+ " SELECT * FROM provider "
        			+ "WHERE fk_id_provider = ?; ");					// SQL itself being prepared 

        	pstType.setInt(1, user.getIdUser());						// Replacing each ? with the correct value
        	
        	rsType = pstType.executeQuery();							// SQL being executed and results being assigned to ResultSet
            
            if (rsType.next())
            {
            	return new Provider(user.getIdUser(), null);
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

        	pstType.setInt(1, user.getIdUser());							// Replacing each ? with the correct value
        	
        	rsType = pstType.executeQuery();								// SQL being executed and results being assigned to ResultSet
            
            if (rsType.next())
            {
            	return new Manager(user.getIdUser());
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

        	pstType.setInt(1, user.getIdUser());							// Replacing each ? with the correct value
        	
        	rsType = pstType.executeQuery();								// SQL being executed and results being assigned to ResultSet
            
            if (rsType.next())
            {
            	return new Operator(user.getIdUser());
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

        	pstType.setInt(1, user.getIdUser());							// Replacing each ? with the correct value
        	
        	rsType = pstType.executeQuery();								// SQL being executed and results being assigned to ResultSet
            
            if (rsType.next())
            {
            	return new Member(user.getIdUser(), rsType.getInt("status"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());								// Error Treatment
            return null;
        }
        
        ConnectionFactory.closeConnection(connection, pstType, rsType);		// Closing connection to the DBMS
        
        return null;
    }
    
}