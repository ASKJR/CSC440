package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.ServiceProvided;
import util.ConnectionFactory;

public class ServiceProvidedDAO {
	public int iOne(ServiceProvided serviceProvided){

        Connection connection = ConnectionFactory.openConnection();						// Connection to the database
        PreparedStatement pstInserting = null;											// PreparedStatement to process the SQL

        try {
            
        	pstInserting = connection.prepareStatement(""
            		+ " INSERT INTO `service_provided`("
            		+ " fk_id_provider"
            		+ ", fk_id_service"
            		+ ", fk_id_member"
            		+ ", current_date"
            		+ ", occurrence_date"
            		+ ", comment) "
            		+ " VALUES "
            		+ " (?, ?, ?, ?, ?, ?)");											// SQL itself being prepared

            pstInserting.setInt(1, serviceProvided.getProvider().getFkIdProvider());	// Replacing each ? with the correct value
            pstInserting.setInt(2, serviceProvided.getService().getIdService());
            pstInserting.setInt(3, serviceProvided.getMember().getFkIdMember());
            pstInserting.setTimestamp(4, serviceProvided.getCurrentDate());
            pstInserting.setDate(5, serviceProvided.getOccurrenceDate());
            pstInserting.setString(6, serviceProvided.getComment());
            pstInserting.executeUpdate();												// SQL being executed

        } catch (SQLException e) {
            System.out.println(e.getMessage());											// Error Treatment
            return -1;																	// Method finished UNsuccessfully
        }
        
        ConnectionFactory.closeConnection(connection, pstInserting);					// Closing connection to the DBMS
        return 0;																		// Method finished successfully
	}
}
