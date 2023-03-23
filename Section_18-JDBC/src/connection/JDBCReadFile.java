package connection;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Blob;

public class JDBCReadFile {
public static void main(String[] args) throws SQLException, IOException {
	Connection conn= JDBCconnection.getJDBCConnection();
	String sql="Select * FROM fileTest";
	PreparedStatement st1=conn.prepareStatement(sql);
	
	ResultSet results=st1.executeQuery();
	while (results.next()) {
		String name=results.getString("FileName");
		Blob file=(Blob) results.getBlob("FileContent");
		
		byte[] b=file.getBytes(1, (int) file.length());
		FileOutputStream fileOutputStream = new FileOutputStream(name);
		fileOutputStream.write(b);
		fileOutputStream.close();
		
	}
}
}
