package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import beans.Member;
import beans.Provider;
import util.ConnectionFactory;

public class ProviderDAO {

	public int iOne(Provider provider){

        Connection connection = ConnectionFactory.openConnection();		// Connection to the database
        ResultSet rsInserting = null;
        PreparedStatement pstInserting = null;							// PreparedStatement to process the SQL
        int generatedId = 0;

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


            pstInserting.setString(1, provider.getStAddr());				// Replacing each ? with the correct value
            pstInserting.setString(2, provider.getAddrComp());
            pstInserting.setString(3, provider.getCity());
            pstInserting.setString(4, provider.getState());
            pstInserting.setString(5, provider.getZipCode());
            pstInserting.setString(6, provider.getLstName());
            pstInserting.setString(7, provider.getFstName());
            pstInserting.setString(8, provider.getCellPhone());
            pstInserting.setString(9, provider.getHomePhone());
            pstInserting.setString(10, provider.getWorkPhone());
            pstInserting.setString(11, provider.getEmail());
            
            pstInserting.executeUpdate();								// SQL being executed
            rsInserting = pstInserting.getGeneratedKeys();				// Generated ID being retrieved
        	
            if (rsInserting.next()) {
            	generatedId = rsInserting.getInt(1);						// Assigning generated ID to the returning variable

            	pstInserting = connection.prepareStatement(""
                		+ " INSERT INTO `provider`("
                		+ " fk_id_provider) "
                		+ " VALUES "
                		+ " (?)");											// SQL itself being prepared

                pstInserting.setInt(1, generatedId);						// Replacing each ? with the correct value
                pstInserting.executeUpdate();								// SQL being executed
            }
            else
            {
            	System.out.println("No Insert to the Database happened.");
            	return Provider.UNSUCCESSFUL_INSERT;
            }
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());								// Error Treatment
            return Provider.UNSUCCESSFUL_INSERT;							// Method finished UNsuccessfully
        }
        
        ConnectionFactory.closeConnection(connection, pstInserting);		// Closing connection to the DBMS
        return Provider.SUCCESSFUL_INSERT;									// Method finished successfully
	}

	public int uOne(Provider provider){

        Connection connection = ConnectionFactory.openConnection();			// Connection to the database
        PreparedStatement pstUpdating = null;								// PreparedStatement to process the SQL

        try {
            
        	pstUpdating = connection.prepareStatement(""
            		+ "UPDATE `user` "
            		+ "SET "
            		+ "st_addr = ? "
            		+ "addr_comp =? "
            		+ "city = ? "
            		+ "state = ? "
            		+ "zip_code = ? "
            		+ "lst_name = ? "
            		+ "fst_name = ? "
            		+ "cell_phone = ? "
            		+ "home_phone = ? "
            		+ "work_phone = ? "
            		+ "email = ? "
            		+ "WHERE "
            		+ "user.id_user = ?");									// SQL itself being prepared


        	pstUpdating.setString(1, provider.getStAddr());					// Replacing each ? with the correct value
        	pstUpdating.setString(2, provider.getAddrComp());
        	pstUpdating.setString(3, provider.getCity());
        	pstUpdating.setString(4, provider.getState());
        	pstUpdating.setString(5, provider.getZipCode());
        	pstUpdating.setString(6, provider.getLstName());
        	pstUpdating.setString(7, provider.getFstName());
        	pstUpdating.setString(8, provider.getCellPhone());
        	pstUpdating.setString(9, provider.getHomePhone());
        	pstUpdating.setString(10, provider.getWorkPhone());
        	pstUpdating.setString(11, provider.getEmail());
        	pstUpdating.setInt(12, provider.getFkIdProvider());
            
            pstUpdating.executeUpdate();									// SQL being executed
            pstUpdating.getGeneratedKeys();



        } catch (SQLException e) {
            System.out.println(e.getMessage());								// Error Treatment
            return Member.UNSUCCESSFUL_INSERT;								// Method finished UNsuccessfully
        }
        
        ConnectionFactory.closeConnection(connection, pstUpdating);			// Closing connection to the DBMS
        return Member.SUCCESSFUL_INSERT;									// Method finished successfully
	}

	public int alterStatus(Provider provider){

        Connection connection = ConnectionFactory.openConnection();			// Connection to the database
        PreparedStatement pstUpdating = null;								// PreparedStatement to process the SQL

        try {
            
        	pstUpdating = connection.prepareStatement(""
            		+ "UPDATE `provider` "
            		+ "SET "
            		+ "`status` = ? "
            		+ "WHERE "
            		+ "provider.fk_id_provider = ?");						// SQL itself being prepared


        	pstUpdating.setInt(1, provider.getStatus());					// Replacing each ? with the correct value
        	pstUpdating.setInt(2, provider.getFkIdProvider());
            
            pstUpdating.executeUpdate();									// SQL being executed
            pstUpdating.getGeneratedKeys();



        } catch (SQLException e) {
            System.out.println(e.getMessage());								// Error Treatment
            return Member.UNSUCCESSFUL_INSERT;								// Method finished UNsuccessfully
        }
        
        ConnectionFactory.closeConnection(connection, pstUpdating);			// Closing connection to the DBMS
        return Member.SUCCESSFUL_INSERT;									// Method finished successfully
	}
	
    public Provider sOne(Provider provider)
    {
        Connection connection = ConnectionFactory.openConnection(); 		// Connection to the database
        ResultSet rsSearching = null;										// ResultSet to receive the selected data
        PreparedStatement pstSearching = null;								// PreparedStatement to process the SQL

        try {

        	pstSearching = connection.prepareStatement(""
        			+ "SELECT * "
        			+ "FROM user "
        			+ "WHERE user.id_user = ? ");							// SQL itself being prepared

        	pstSearching.setInt(1, provider.getFkIdProvider());
        	
            rsSearching = pstSearching.executeQuery();						// SQL being executed and results being assigned to ResultSet
            
            if (rsSearching.next()) {
            	provider.setAddrComp(rsSearching.getString("addr_comp"));
            	provider.setCellPhone(rsSearching.getString("cell_phone"));
            	provider.setCity(rsSearching.getString("city"));
            	provider.setEmail(rsSearching.getString("email"));
            	provider.setFstName(rsSearching.getString("fst_name"));
            	provider.setLstName(rsSearching.getString("lst_name"));
            	provider.setHomePhone(rsSearching.getString("home_phone"));
            	provider.setIdUser(rsSearching.getInt("id_user"));
            	provider.setStAddr(rsSearching.getString("st_addr"));
            	provider.setState(rsSearching.getString("state"));
            	provider.setWorkPhone(rsSearching.getString("work_phone"));
            	provider.setZipCode(rsSearching.getString("zip_code"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());								// Error Treatment
            return null;
        }
        
        try {
            
        	pstSearching = connection.prepareStatement(""
        			+ "SELECT * "
        			+ "FROM provider "
        			+ "WHERE provider.fk_id_provider = ? ");				// SQL itself being prepared 

        	pstSearching.setInt(1, provider.getFkIdProvider());
        	
            rsSearching = pstSearching.executeQuery();						// SQL being executed and results being assigned to ResultSet
            
            if (rsSearching.next()) {
            	provider.setStatus(rsSearching.getInt("status"));
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());								// Error Treatment
            return null;
        }

        ConnectionFactory.closeConnection(
        		connection, pstSearching, rsSearching);						// Closing connection to the DBMS
        
        return provider;
    }
	
}