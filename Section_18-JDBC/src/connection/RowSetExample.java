package connection;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class RowSetExample {
public static void main(String[] args) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet();
	rowSet.setUrl("jdbc:mysql://localhost:3306/hello");
	rowSet.setUsername("root");
	rowSet.setPassword("pasS912!");
	
	rowSet.setCommand("SELECT * FROM DEMO1");
	rowSet.execute();
	while (rowSet.next()) {
		System.out.println("ID: "+rowSet.getInt(1));
		System.out.println("TestName: "+ rowSet.getString(2));
	}
}
}
