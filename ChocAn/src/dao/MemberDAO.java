package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Member;
import util.ConnectionFactory;

public class MemberDAO {
	public int iOne(Member member){

        Connection connection = ConnectionFactory.openConnection();		// Connection to the database
        PreparedStatement pstInserting = null;							// PreparedStatement to process the SQL

        try {
            
        	pstInserting = connection.prepareStatement(""
            		+ " INSERT INTO `member`("
            		+ " fk_id_member, status) "
            		+ " VALUES "
            		+ " (?, ?)");											// SQL itself being prepared

        	pstInserting.setInt(1, member.getFkIdMember());				// Replacing each ? with the correct value
        	pstInserting.setInt(2, Member.STATUS_VALID);
            pstInserting.executeUpdate();								// SQL being executed

        } catch (SQLException e) {
            System.out.println(e.getMessage());							// Error Treatment
            return -1;													// Method finished UNsuccessfully
        }
        
        ConnectionFactory.closeConnection(connection, pstInserting);	// Closing connection to the DBMS
        return 0;														// Method finished successfully
	}
}
