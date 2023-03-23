package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCStatement {
public static void main(String[] args) {
	try {
		@SuppressWarnings("rawtypes")
		Connection con=JDBCconnection.getJDBCConnection();
		
		
		String sql="Select * from demo1 where id=2 or 1=1";
		Statement statement=con.prepareStatement(sql);
		ResultSet rs= statement.executeQuery(sql);
		
		while(rs.next()) {
			int id=rs.getInt("id");
			String name=rs.getString("TestName");
			System.out.println(id + name);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
