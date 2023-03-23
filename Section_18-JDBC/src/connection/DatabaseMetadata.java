package connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.DatabaseMetaData;

public class DatabaseMetadata {
public static void main(String[] args) throws SQLException {
	Connection conn=JDBCconnection.getJDBCConnection();
	
	DatabaseMetaData databaseMetaData = (DatabaseMetaData) conn.getMetaData();
	
	System.out.println(databaseMetaData.getDatabaseProductName());
	System.out.println(databaseMetaData.getUserName());
	System.out.println(databaseMetaData.getURL());
}
}
