package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAndDropTable {
public static void CreateTable() throws SQLException {
	Connection conn=JDBCconnection.getJDBCConnection();
	String sql="CREATE TABLE Demo2(Id INT,text VARCHAR(225))";
	Statement statement=conn.prepareStatement(sql);
	int rs=statement.executeUpdate(sql);
	System.out.print(rs);
	
	sql="INSERT INTO Demo2(id,text)\r\n"
			+ "values(46,'opr')";
	statement=conn.prepareStatement(sql);
	rs=statement.executeUpdate(sql);
	System.out.println(rs);
	
}

	public static void DeleteTable() throws SQLException{
		Connection conn=JDBCconnection.getJDBCConnection();
		String sql="DROP TABLE Demo2";
		Statement statement=conn.prepareStatement(sql);
		int rs=statement.executeUpdate(sql);
		System.out.print(rs);
	}
public static void main(String[] args) {
	try {
		//CreateTable()
		DeleteTable();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
