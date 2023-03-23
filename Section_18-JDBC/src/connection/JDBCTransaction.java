package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTransaction {
public static void main(String[] args) {
	Connection conn= JDBCconnection.getJDBCConnection();
	String sql1="INSERT INTO Demo2(id,text) VALUES(2,'Yosh')";
	String sql2="DELETE FROM Demo2 WHERE Id=5";
	
	try {
		conn.setAutoCommit(false);
		PreparedStatement st1=conn.prepareStatement(sql1);
		PreparedStatement st2=conn.prepareStatement(sql2);
		
		st2.executeUpdate();
		st1.executeUpdate();
		
		
		conn.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
