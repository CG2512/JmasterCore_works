package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class JDBCmetadata {
public static void main(String[] args) throws SQLException {
	Connection conn=JDBCconnection.getJDBCConnection();
	
	String sql="SELECT * FROM DEMO2";
	PreparedStatement st1=conn.prepareStatement(sql);
	
	ResultSet resultset=st1.executeQuery(sql);
	ResultSetMetaData metaData= (ResultSetMetaData) resultset.getMetaData();
	
	System.out.println(metaData.getColumnCount());
	System.out.println(metaData.getColumnName(1));
	System.out.println(metaData.getColumnName(2));
	System.out.println(metaData.getColumnTypeName(1));
	System.out.println(metaData.getTableName(2));
}
}
