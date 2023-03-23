package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Student;
import model.University;

public class StudentDaolmpl extends JDBC_Uni_conn implements StudentDao {
	
	@Override
	public void create(Student student) {
		// TODO Auto-generated method stub
		Connection conn=super.getJDBCConnection();
		String sql="INSERT INTO STUDENTS(StudentName,Age,UniversityId) VALUES(?,?,?)";
		try {
			PreparedStatement statement=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, student.getName());
			statement.setInt(2, student.getAge());
			statement.setInt(3, student.getUniversity().getId());
			
			statement.executeUpdate();
			ResultSet rs=statement.getGeneratedKeys();
			if (rs.next()) {
				int id=rs.getInt(1);
				student.setId(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		Connection conn=super.getJDBCConnection();
		String sql="UPDATE Students SET StudentName=?,Age=?,UniversityId=? WHERE Id=? ";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1, student.getName());
			statement.setInt(2, student.getAge());
			statement.setInt(3, student.getUniversity().getId());
			statement.setInt(4, student.getId());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Connection conn=super.getJDBCConnection();
		String sql="DELETE FROM Students WHERE id=?";
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
	public Student read(int id) {
		// TODO Auto-generated method stub
		Connection conn=super.getJDBCConnection();
		String sql="Select s.*,u.UniversityName,u.address from students as s"
				+ " INNER JOIN uni as u"
				+ " WHERE s.UniversityId=u.id and s.Id=?";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1,id);
			
			
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				Student student=rowMapper(rs);
				return student;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Student> SearchByAge(int age) {
		// TODO Auto-generated method stub
		List<Student> students=new ArrayList<Student>();
		Connection conn=super.getJDBCConnection();
		String sql="Select s.*,u.UniversityName,u.address from students as s"
				+ " INNER JOIN uni as u"
				+ " WHERE s.UniversityId=u.id and s.age=?";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1, age);
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				Student student= rowMapper(rs);
				students.add(student);
			}
			return students;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Student> SearchByUniversity(String universityName) {
		// TODO Auto-generated method stub
		List<Student> students=new ArrayList<Student>();
		Connection conn=super.getJDBCConnection();
		String sql="Select s.*,u.UniversityName,u.address from students as s"
				+ " INNER JOIN uni as u"
				+ " WHERE s.UniversityId=u.id and u.UniversityName LIKE ?";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1,universityName.toUpperCase());
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				Student student= rowMapper(rs);
				students.add(student);
			}
			return students;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Student> SearchByName(String name) {
		// TODO Auto-generated method stub
		List<Student> students=new ArrayList<Student>();
		Connection conn=super.getJDBCConnection();
		String sql="Select s.*,u.UniversityName,u.address from students as s"
				+ " INNER JOIN uni as u"
				+ " WHERE s.UniversityId=u.id and s.StudentName LIKE ?";
		try {
			PreparedStatement statement =conn.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet rs =statement.executeQuery();
			while (rs.next()){
				Student student= rowMapper(rs);
				students.add(student);
			}
			return students;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private Student rowMapper(ResultSet rs) throws SQLException {
		University university=new University(rs.getString("UniversityName"),rs.getString("address"));
		university.setId(rs.getInt("UniversityId"));
		Student student=new Student(rs.getString("StudentName"),rs.getInt("Age"),university);
		student.setId(rs.getInt("Id"));
		return student;
	}

}
