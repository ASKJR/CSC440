package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import beans.ServiceProvided;
import beans.User;
import controller.MemberCtrl;
import controller.ProviderCtrl;
import controller.ServiceCtrl;
import beans.Member;
import beans.Provider;
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
            		+ ", `current_date`"
            		+ ", occurrence_date"
            		+ ", `comment`) "
            		+ " VALUES "
            		+ " (?, ?, ?, ?, ?, ?)");											// SQL itself being prepared

            pstInserting.setInt(1, serviceProvided.getProvider().getIdUser());				// Replacing each ? with the correct value
            pstInserting.setInt(2, serviceProvided.getService().getIdService());
            pstInserting.setInt(3, serviceProvided.getMember().getFkIdMember());
            pstInserting.setTimestamp(4, serviceProvided.getCurrentDate());
            pstInserting.setDate(5, serviceProvided.getOccurrenceDate());
            pstInserting.setString(6, serviceProvided.getComment());
            pstInserting.executeUpdate();												// SQL being executed

        } catch (SQLException e) {
            System.out.println(e.getMessage());											// Error Treatment
            return User.UNSUCCESSFUL_SQL_QUERY;											// Method finished UNsuccessfully
        }
        
        ConnectionFactory.closeConnection(connection, pstInserting);					// Closing connection to the DBMS
        return User.SUCCESSFUL_SQL_QUERY;												// Method finished successfully
	}
	
	public ArrayList<ServiceProvided> sLastWeek(Member member){
		
		Connection connection = ConnectionFactory.openConnection();
		PreparedStatement pstListing = null;
		ResultSet rsListing = null;
		ProviderCtrl providerCtrl = new ProviderCtrl();
		MemberCtrl memberCtrl = new MemberCtrl();
		ServiceCtrl serviceCtrl = new ServiceCtrl();
		ArrayList<ServiceProvided> serviceProvidedList = new ArrayList<ServiceProvided>();

		try{
			pstListing = connection.prepareStatement(""
					+ "SELECT "
					+ "fk_id_provider, fk_id_service, fk_id_member, `current_date`, occurrence_date, `comment` "
					+ "FROM "
					+ "service_provided "
					+ "WHERE "
					+ "fk_id_member = ? "
					+ "AND occurrence_date >= curdate() - INTERVAL DAYOFWEEK(curdate())+6 DAY "
					+ "AND occurrence_date < curdate() - INTERVAL DAYOFWEEK(curdate())-1 DAY");

			pstListing.setInt(1, member.getFkIdMember());
			rsListing = pstListing.executeQuery();
			
			while(rsListing.next()){
				serviceProvidedList.add
				(
					new ServiceProvided
					(
						providerCtrl.sOne(rsListing.getInt("fk_id_provider")), 
						memberCtrl.sOne(rsListing.getInt("fk_id_member")),
						serviceCtrl.sOne(rsListing.getInt("fk_id_service")),
						rsListing.getTimestamp("current_date"),
						rsListing.getDate("occurrence_date"),
						rsListing.getString("comment")
					)
				);
			}
			
		}catch(SQLException sqle){
			System.err.println(sqle.getMessage());
	        ConnectionFactory.closeConnection(connection, pstListing, rsListing);
			return null;
		}
		
		ConnectionFactory.closeConnection(connection, pstListing, rsListing);			// Closing connection to the DBMS
		
		return serviceProvidedList;
	}
	
	public ArrayList<ServiceProvided> sLastWeek(Provider provider){
		
		Connection connection = ConnectionFactory.openConnection();
		PreparedStatement pstListing = null;
		ResultSet rsListing = null;
		ProviderCtrl providerCtrl = new ProviderCtrl();
		MemberCtrl memberCtrl = new MemberCtrl();
		ServiceCtrl serviceCtrl = new ServiceCtrl();
		ArrayList<ServiceProvided> serviceProvidedList = new ArrayList<ServiceProvided>();

		try{
			pstListing = connection.prepareStatement(""
					+ "SELECT "
					+ "fk_id_provider, fk_id_service, fk_id_member, `current_date`, occurrence_date, `comment` "
					+ "FROM "
					+ "service_provided "
					+ "WHERE "
					+ "fk_id_provider = ? "
					+ "AND occurrence_date >= curdate() - INTERVAL DAYOFWEEK(curdate())+6 DAY "
					+ "AND occurrence_date < curdate() - INTERVAL DAYOFWEEK(curdate())-1 DAY");

			pstListing.setInt(1, provider.getFkIdProvider());
			rsListing = pstListing.executeQuery();
			
			while(rsListing.next()){
				serviceProvidedList.add
				(
					new ServiceProvided
					(
						providerCtrl.sOne(rsListing.getInt("fk_id_provider")), 
						memberCtrl.sOne(rsListing.getInt("fk_id_member")),
						serviceCtrl.sOne(rsListing.getInt("fk_id_service")),
						rsListing.getTimestamp("current_date"),
						rsListing.getDate("occurrence_date"),
						rsListing.getString("comment")
					)
				);
			}
			
		}catch(SQLException sqle){
			System.err.println(sqle.getMessage());
	        ConnectionFactory.closeConnection(connection, pstListing, rsListing);
			return null;
		}
		
		ConnectionFactory.closeConnection(connection, pstListing, rsListing);			// Closing connection to the DBMS
		
		return serviceProvidedList;
	}
	
	public ArrayList<ServiceProvided> sAll(Provider provider){
		
		Connection connection = ConnectionFactory.openConnection();
		PreparedStatement pstListing = null;
		ResultSet rsListing = null;
		ProviderCtrl providerCtrl = new ProviderCtrl();
		MemberCtrl memberCtrl = new MemberCtrl();
		ServiceCtrl serviceCtrl = new ServiceCtrl();
		ArrayList<ServiceProvided> serviceProvidedList = new ArrayList<ServiceProvided>();

		try{
			pstListing = connection.prepareStatement(""
					+ "SELECT "
					+ ""
					+ "fk_id_provider, fk_id_service, fk_id_member, "
					+ "`current_date`, occurrence_date, `comment` "
					+ ""
					+ "FROM "
					+ ""
					+ "service_provided "
					+ ""
					+ "WHERE "
					+ ""
					+ "fk_id_provider = ? ");

			pstListing.setInt(1, provider.getFkIdProvider());
			rsListing = pstListing.executeQuery();
			
			while(rsListing.next()){
				serviceProvidedList.add
				(
					new ServiceProvided
					(
						providerCtrl.sOne(rsListing.getInt("fk_id_provider")), 
						memberCtrl.sOne(rsListing.getInt("fk_id_member")),
						serviceCtrl.sOne(rsListing.getInt("fk_id_service")),
						rsListing.getTimestamp("current_date"),
						rsListing.getDate("occurrence_date"),
						rsListing.getString("comment")
					)
				);
			}
			
		}catch(SQLException sqle){
			System.err.println(sqle.getMessage());
	        ConnectionFactory.closeConnection(connection, pstListing, rsListing);
			return null;
		}
		
		ConnectionFactory.closeConnection(connection, pstListing, rsListing);			// Closing connection to the DBMS
		
		return serviceProvidedList;
	}
	
}
