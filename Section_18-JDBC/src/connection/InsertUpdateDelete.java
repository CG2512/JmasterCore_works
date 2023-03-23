package connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.SQLSyntaxErrorException;

public class InsertUpdateDelete {
	public static void CreateTable() throws SQLException {
		Connection conn=JDBCconnection.getJDBCConnection();
		String sql="CREATE TABLE Demo2(Id INT,text VARCHAR(225))";
		Statement statement=conn.prepareStatement(sql);
		int rs=statement.executeUpdate(sql);
		System.out.print(rs);
	}
	public static void Insert(String Tablename,int id, String text) {
		Connection conn= JDBCconnection.getJDBCConnection();
		String sql=String.format("INSERT INTO %s(id,Content) VALUES(%d,'%s')",Tablename,id,text );
		Statement statement;
		try {
			statement = conn.prepareStatement(sql);
			int rs = statement.executeUpdate(sql);
			System.out.println(rs);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		public static void Delete(String Tablename,int id) {
			Connection conn= JDBCconnection.getJDBCConnection();
			String sql=String.format("DELETE FROM %s WHERE id=%d",Tablename,id);
			Statement statement;
			try {
				statement = conn.prepareStatement(sql);
				int rs = statement.executeUpdate(sql);
				System.out.println(rs);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
	}
		public static void SetValue(String Tablename,int id,String text) {
			Connection conn= JDBCconnection.getJDBCConnection();
			String sql=String.format("UPDATE %s SET Content='%s' WHERE id=%d",Tablename,text,id);
			Statement statement;
			try {
				statement = conn.prepareStatement(sql);
				int rs = statement.executeUpdate(sql);
				System.out.println(rs);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
	}
	public static void main(String[] args) throws SQLSyntaxErrorException {
		
		Insert("Demo1",6,"Hoi");
		Insert("Demo1",4,"Boi");
		SetValue("Demo1",6,"Yosh");
		//Delete("Demo2",2);
		
	}
}
