package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Manager;
import util.ConnectionFactory;

public class ManagerDAO {

	public int iOne(Manager manager){

        Connection connection = ConnectionFactory.openConnection();		// Connection to the database
        PreparedStatement pstInserting = null;							// PreparedStatement to process the SQL

        try {
            
        	pstInserting = connection.prepareStatement(""
            		+ " INSERT INTO `manager`("
            		+ " fk_id_manager) "
            		+ " VALUES "
            		+ " (?)");											// SQL itself being prepared

            pstInserting.setInt(1, manager.getFkIdManager());			// Replacing each ? with the correct value
            pstInserting.executeUpdate();								// SQL being executed

        } catch (SQLException e) {
            System.out.println(e.getMessage());							// Error Treatment
            return Manager.UNSUCCESSFUL_INSERT;							// Method finished UNsuccessfully
        }
        
        ConnectionFactory.closeConnection(connection, pstInserting);	// Closing connection to the DBMS
        return Manager.SUCCESSFUL_INSERT;								// Method finished successfully
	}
       
}