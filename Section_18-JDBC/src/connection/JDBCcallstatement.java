package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.CallableStatement;

public class JDBCcallstatement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn=JDBCconnection.getJDBCConnection();
		try {
			CallableStatement call1=(CallableStatement) conn.prepareCall("call GetText(?)");
			call1.setInt(1,4);
			ResultSet rs=call1.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("id")+" "+ rs.getString("TestName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
