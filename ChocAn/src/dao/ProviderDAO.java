package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Provider;
import util.ConnectionFactory;

public class ProviderDAO {

	public int iOne(Provider provider){

        Connection connection = ConnectionFactory.openConnection();		// Connection to the database
        PreparedStatement pstInserting = null;							// PreparedStatement to process the SQL

        try {
            
        	pstInserting = connection.prepareStatement(""
            		+ " INSERT INTO `provider`("
            		+ " fk_id_provider) "
            		+ " VALUES "
            		+ " (?)");											// SQL itself being prepared

            pstInserting.setInt(1, provider.getFkIdProvider());			// Replacing each ? with the correct value
            pstInserting.executeUpdate();								// SQL being executed

        } catch (SQLException e) {
            System.out.println(e.getMessage());							// Error Treatment
            return -1;													// Method finished UNsuccessfully
        }
        
        ConnectionFactory.closeConnection(connection, pstInserting);	// Closing connection to the DBMS
        return 0;														// Method finished successfully
	}
}