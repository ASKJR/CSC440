package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

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
            System.out.println(e.getMessage());							// Error Treatment
            return Provider.UNSUCCESSFUL_INSERT;							// Method finished UNsuccessfully
        }
        
        ConnectionFactory.closeConnection(connection, pstInserting);	// Closing connection to the DBMS
        return Provider.SUCCESSFUL_INSERT;								// Method finished successfully
	}

}