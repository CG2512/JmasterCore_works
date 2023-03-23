package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.University;

public class UniversityDaolmpl extends JDBC_Uni_conn implements UniversityDao {

	@Override
	public void create(University university) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO uni(UniversityName,address) VALUES(?,?)";
		Connection conn=super.getJDBCConnection();
		try {
			PreparedStatement statement=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, university.getName());
			statement.setString(2, university.getAddress());
			
			statement.executeUpdate();
			
			ResultSet rs=statement.getGeneratedKeys();
			
			while (rs.next()) {
				int id=rs.getInt(1);
				university.setId(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	@Override
	public void update(University university) {
		// TODO Auto-generated method stub
		String sql="UPDATE uni SET UniversityName=?,Address=? WHERE id=?";
		Connection conn=super.getJDBCConnection();
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1, university.getName());
			statement.setString(2, university.getAddress());
			statement.setInt(3, university.getId());
			
			statement.executeUpdate();
			}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		}
		
	

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM uni WHERE id=?";
		Connection conn=super.getJDBCConnection();
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public University read(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM uni WHERE id=?";
		Connection conn=super.getJDBCConnection();
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs=statement.executeQuery();
		//Get read result
			while (rs.next()) {
				University university=getUni(rs);
				
				return university;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public ArrayList<University> search(String name) {
		
		ArrayList<University> universities=new ArrayList<University>();
		String sql="SELECT * FROM uni WHERE UniversityName LIKE ?";
		Connection conn=super.getJDBCConnection();
		
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1,name);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next()) {
				University university=getUni(rs);
				universities.add(university);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return universities;
	}
	private University getUni(ResultSet rs) throws SQLException {
		String name=rs.getString(2);
		String address=rs.getString(3);
		
		University university=new University(name,address);
		university.setId(rs.getInt(1));
		return university;
	}
}
