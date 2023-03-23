package connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

import java.sql.ResultSet;



public class ConnectionWhere {
public static void main(String[] args) {
	Connection conn=JDBCconnection.getJDBCConnection();
	try {
		final String sql="Select * from demo1 where id=2";
		Statement statement=conn.prepareStatement(sql);
		ResultSet rs=statement.executeQuery(sql);
		while(rs.next()) {
			int id=rs.getInt("id");
			String name=rs.getString("TestName");
			System.out.println(id+name);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
