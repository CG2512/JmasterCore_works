package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Uni_conn {
	public static Connection getJDBCConnection() {
		final String url="jdbc:mysql://localhost:3306/university";
		final String user="root";
		final String password="pasS912!";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		Connection connection=getJDBCConnection();
		if (connection != null) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
	}
}
