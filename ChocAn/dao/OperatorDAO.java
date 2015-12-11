package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import beans.Operator;
import util.ConnectionFactory;

public class OperatorDAO {

	public int iOne(Operator operator) {

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


            pstInserting.setString(1, operator.getStAddr());				// Replacing each ? with the correct value
            pstInserting.setString(2, operator.getAddrComp());
            pstInserting.setString(3, operator.getCity());
            pstInserting.setString(4, operator.getState());
            pstInserting.setString(5, operator.getZipCode());
            pstInserting.setString(6, operator.getLstName());
            pstInserting.setString(7, operator.getFstName());
            pstInserting.setString(8, operator.getCellPhone());
            pstInserting.setString(9, operator.getHomePhone());
            pstInserting.setString(10, operator.getWorkPhone());
            pstInserting.setString(11, operator.getEmail());
            
            pstInserting.executeUpdate();								// SQL being executed
            rsInserting = pstInserting.getGeneratedKeys();				// Generated ID being retrieved
        	
            if (rsInserting.next()) {
            	generatedId = rsInserting.getInt(1);						// Assigning generated ID to the returning variable

            	pstInserting = connection.prepareStatement(""
                		+ " INSERT INTO `operator`("
                		+ " fk_id_operator) "
                		+ " VALUES "
                		+ " (?)");											// SQL itself being prepared

                pstInserting.setInt(1, generatedId);						// Replacing each ? with the correct value
                pstInserting.executeUpdate();								// SQL being executed
            }
            else
            {
            	System.out.println("No Insert to the Database happened.");
            	return Operator.UNSUCCESSFUL_SQL_QUERY;
            }
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());							// Error Treatment
            return Operator.UNSUCCESSFUL_SQL_QUERY;							// Method finished UNsuccessfully
        }
        
        ConnectionFactory.closeConnection(connection, pstInserting);	// Closing connection to the DBMS
        return Operator.SUCCESSFUL_SQL_QUERY;								// Method finished successfully
	}

}
