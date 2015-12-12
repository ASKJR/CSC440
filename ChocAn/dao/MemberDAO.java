package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;
import beans.Member;
import util.ConnectionFactory;

public class MemberDAO {

	public int iOne(Member member){

        Connection connection = ConnectionFactory.openConnection();			// Connection to the database
        ResultSet rsInserting = null;
        PreparedStatement pstInserting = null;								// PreparedStatement to process the SQL
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
            		, Statement.RETURN_GENERATED_KEYS);						// SQL itself being prepared


            pstInserting.setString(1, member.getStAddr());					// Replacing each ? with the correct value
            pstInserting.setString(2, member.getAddrComp());
            pstInserting.setString(3, member.getCity());
            pstInserting.setString(4, member.getState());
            pstInserting.setString(5, member.getZipCode());
            pstInserting.setString(6, member.getLstName());
            pstInserting.setString(7, member.getFstName());
            pstInserting.setString(8, member.getCellPhone());
            pstInserting.setString(9, member.getHomePhone());
            pstInserting.setString(10, member.getWorkPhone());
            pstInserting.setString(11, member.getEmail());
            
            pstInserting.executeUpdate();									// SQL being executed
            rsInserting = pstInserting.getGeneratedKeys();					// Generated ID being retrieved

            if (rsInserting.next()) {
            	generatedId = rsInserting.getInt(1);						// Assigning generated ID to the returning variable

            	pstInserting = connection.prepareStatement(""
                		+ " INSERT INTO `member`("
                		+ " fk_id_member, `status`) "
                		+ " VALUES "
                		+ " (?, ?)");											// SQL itself being prepared

                pstInserting.setInt(1, generatedId);						// Replacing each ? with the correct value
                pstInserting.setInt(2, member.getStatus());
                pstInserting.executeUpdate();								// SQL being executed
            }
            else
            {
            	System.out.println("No Insert to the Database happened.");
            	return Member.UNSUCCESSFUL_SQL_QUERY;
            }
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());								// Error Treatment
            return Member.UNSUCCESSFUL_SQL_QUERY;								// Method finished UNsuccessfully
        }
        
        ConnectionFactory.closeConnection(connection, pstInserting);		// Closing connection to the DBMS
        return Member.SUCCESSFUL_SQL_QUERY;									// Method finished successfully
	}

	public int uOne(Member member){

        Connection connection = ConnectionFactory.openConnection();			// Connection to the database
        PreparedStatement pstUpdating = null;								// PreparedStatement to process the SQL

        try {
            
        	pstUpdating = connection.prepareStatement(""
            		+ "UPDATE `user` "
            		+ "SET "
            		+ "st_addr = ?, "
            		+ "addr_comp = ?, "
            		+ "city = ?, "
            		+ "state = ?, "
            		+ "zip_code = ?, "
            		+ "lst_name = ?, "
            		+ "fst_name = ?, "
            		+ "cell_phone = ?, "
            		+ "home_phone = ?, "
            		+ "work_phone = ?, "
            		+ "email = ? "
            		+ "WHERE "
            		+ "user.id_user = ?", Statement.RETURN_GENERATED_KEYS);									// SQL itself being prepared


        	pstUpdating.setString(1, member.getStAddr());					// Replacing each ? with the correct value
        	pstUpdating.setString(2, member.getAddrComp());
        	pstUpdating.setString(3, member.getCity());
        	pstUpdating.setString(4, member.getState());
        	pstUpdating.setString(5, member.getZipCode());
        	pstUpdating.setString(6, member.getLstName());
        	pstUpdating.setString(7, member.getFstName());
        	pstUpdating.setString(8, member.getCellPhone());
        	pstUpdating.setString(9, member.getHomePhone());
        	pstUpdating.setString(10, member.getWorkPhone());
        	pstUpdating.setString(11, member.getEmail());
        	pstUpdating.setInt(12, member.getFkIdMember());
            
            pstUpdating.executeUpdate();									// SQL being executed
            pstUpdating.getGeneratedKeys();



        } catch (SQLException e) {
            System.out.println(e.getMessage());	
            System.out.println("oiiii");// Error Treatment
            return Member.UNSUCCESSFUL_SQL_QUERY;								// Method finished UNsuccessfully
        }
        
        ConnectionFactory.closeConnection(connection, pstUpdating);			// Closing connection to the DBMS
        return Member.SUCCESSFUL_SQL_QUERY;									// Method finished successfully
	}

	public Member sOne(Member member){

        Connection connection = ConnectionFactory.openConnection();			// Connection to the database
        ResultSet rsSelecting = null;
        PreparedStatement pstSelecting = null;								// PreparedStatement to process the SQL

        try {
            
        	pstSelecting = connection.prepareStatement(""
            		+ "SELECT * "
            		+ "FROM user "
            		+ "WHERE id_user = ?");							// SQL itself being prepared


            pstSelecting.setInt(1, member.getFkIdMember());					// Replacing each ? with the correct value

            rsSelecting = pstSelecting.executeQuery();						// SQL being executed
        	
            if (rsSelecting.next()) {
            	member.setStAddr(rsSelecting.getString("st_addr"));
            	member.setAddrComp(rsSelecting.getString("addr_comp"));
            	member.setCity(rsSelecting.getString("city"));
            	member.setState(rsSelecting.getString("state"));
            	member.setZipCode(rsSelecting.getString("zip_code"));
            	member.setLstName(rsSelecting.getString("lst_name"));
            	member.setFstName(rsSelecting.getString("fst_name"));
            	member.setCellPhone(rsSelecting.getString("cell_phone"));
            	member.setHomePhone(rsSelecting.getString("home_phone"));
            	member.setWorkPhone(rsSelecting.getString("work_phone"));
            	member.setEmail(rsSelecting.getString("email"));
            	
            	pstSelecting = connection.prepareStatement(""
                		+ "SELECT * "
                		+ "FROM member as m, user as u "
                		+ "WHERE fk_id_member = ? "
                		+ "AND m.status = " + User.STATUS_VALID + " "
                		+ "AND m.fk_id_member = u.id_user");							// SQL itself being prepared


                pstSelecting.setInt(1, member.getFkIdMember());					// Replacing each ? with the correct value
    
                rsSelecting = pstSelecting.executeQuery();						// SQL being executed
                
                if(rsSelecting.next()){
                	member.setStatus(rsSelecting.getInt("status"));
                	member.setFkIdMember(rsSelecting.getInt("fk_id_member"));
                }else{
                	return null;
                }
            }
            else
            {
            	return null;
            }

        } catch (SQLException e) {
            return null;														// Method finished UNsuccessfully
        }
        
        ConnectionFactory.closeConnection(
        		connection, pstSelecting, rsSelecting);							// Closing connection to the DBMS
        
        return member;															// Method finished successfully
	}
	
}
