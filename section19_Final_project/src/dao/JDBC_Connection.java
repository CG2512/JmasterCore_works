package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connection {
	public static Connection getJDBCConnection() {
		final String url = "jdbc:mysql://localhost:3306/back_end_ecommerce";
		final String user = "root";
		final String password = "pasS912!";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
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
		Connection conn = JDBC_Connection.getJDBCConnection();
		if (conn != null) {
			System.out.println("Work");
		} else {
			System.out.println("Not work");
		}
	}
}
