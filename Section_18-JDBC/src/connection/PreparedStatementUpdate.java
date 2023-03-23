package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementUpdate {

	public static void InsertRecord(int id,String text) {
		Connection conn=JDBCconnection.getJDBCConnection();
		String sql="INSERT INTO Demo2(id,text) VALUES(?,?)";
		try {
			PreparedStatement preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			preparedStatement.setString(2,text);
			int rs=preparedStatement.executeUpdate();
			System.out.println(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void UpdateRecord(int id,String text) {
		Connection conn=JDBCconnection.getJDBCConnection();
		String sql="UPDATE Demo2 SET text=? WHERE id=?";
		try {
			PreparedStatement preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setInt(2,id);
			preparedStatement.setString(1,text);
			int rs=preparedStatement.executeUpdate();
			System.out.println(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void DeleteRecord(int id) {
		Connection conn=JDBCconnection.getJDBCConnection();
		String sql="DELETE FROM Demo2 WHERE id=?";
		try {
			PreparedStatement preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			
			int rs=preparedStatement.executeUpdate();
			System.out.println(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		InsertRecord(4,"Hangman");
		//UpdateRecord(4,"Hangwomen");
		//DeleteRecord(4);
	}
}
