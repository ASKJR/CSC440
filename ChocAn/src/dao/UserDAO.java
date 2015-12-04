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
	
    public User fillData(User user)
    {
        Connection connection = ConnectionFactory.openConnection(); 		// Connection to the database
        ResultSet rsListing = null;											// ResultSet to receive the selected data
        PreparedStatement pstListing = null;								// PreparedStatement to process the SQL

        try {
            
        	pstListing = connection.prepareStatement(""
        			+ "SELECT * "
        			+ "FROM user "
        			+ "WHERE user.id_user = ? ");							// SQL itself being prepared 

        	pstListing.setInt(1, user.getLogin().getFkIdUser());
        	
            rsListing = pstListing.executeQuery();							// SQL being executed and results being assigned to ResultSet
            
            if (rsListing.next()) {
        		user.setAddrComp(rsListing.getString("addr_comp"));
    			user.setCellPhone(rsListing.getString("cell_phone"));
    			user.setCity(rsListing.getString("city"));
    			user.setEmail(rsListing.getString("email"));
    			user.setFstName(rsListing.getString("fst_name"));
    			user.setLstName(rsListing.getString("lst_name"));
    			user.setHomePhone(rsListing.getString("home_phone"));
    			user.setIdUser(rsListing.getInt("id_user"));
    			user.setStAddr(rsListing.getString("st_addr"));
    			user.setState(rsListing.getString("state"));
    			user.setWorkPhone(rsListing.getString("work_phone"));
    			user.setZipCode(rsListing.getString("zip_code"));
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());								// Error Treatment
            return null;
        }
        
        ConnectionFactory.closeConnection(
        		connection, pstListing, rsListing);							// Closing connection to the DBMS
        
        return user;
    }

}