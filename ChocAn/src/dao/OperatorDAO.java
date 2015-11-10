package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Operator;
import util.ConnectionFactory;

public class OperatorDAO {
	public int iOne(Operator operator){

        Connection connection = ConnectionFactory.openConnection();		// Connection to the database
        PreparedStatement pstInserting = null;							// PreparedStatement to process the SQL

        try {
            
        	pstInserting = connection.prepareStatement(""
            		+ " INSERT INTO `operator`("
            		+ " fk_id_operator) "
            		+ " VALUES "
            		+ " (?)");											// SQL itself being prepared

        	pstInserting.setInt(1, operator.getFkIdOperator());			// Replacing each ? with the correct value
            pstInserting.executeUpdate();								// SQL being executed

        } catch (SQLException e) {
            System.out.println(e.getMessage());							// Error Treatment
            return -1;													// Method finished UNsuccessfully
        }
        
        ConnectionFactory.closeConnection(connection, pstInserting);	// Closing connection to the DBMS
        return 0;														// Method finished successfully
	}
}
