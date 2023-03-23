package connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LuuFile {
public static void main(String[] args) throws SQLException, FileNotFoundException {
	File f=new File("text.txt");
	FileInputStream fileInputStream=new FileInputStream(f);
	Connection connection=JDBCconnection.getJDBCConnection();
	
	String sql = "INSERT INTO filetest(FileName,FileContent) VALUES (?,?)";
	PreparedStatement st1=connection.prepareStatement(sql);
	
	st1.setString(1,"text.txt");
	st1.setBinaryStream(2, fileInputStream);
	st1.executeUpdate();
}
}
