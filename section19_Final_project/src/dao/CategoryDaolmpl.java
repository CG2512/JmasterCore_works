package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import model.Category;

public class CategoryDaolmpl implements CategoryDao {

	@Override
	public Category read(int productId) {
		Category category=new Category();
		Connection conn=JDBC_Connection.getJDBCConnection();
		String sql="SELECT * FROM category WHERE id=?";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1, productId);
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				
				category.setId(rs.getInt("Id"));
				category.setName(rs.getString("Category_Name"));
			}
			return category;
		
		} catch (SQLIntegrityConstraintViolationException e2) {
			return null;
		
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		return null;
	}

}

	@Override
	public Category SearchByName(String name) {
		// TODO Auto-generated method stub
		Category category=new Category();
		Connection conn=JDBC_Connection.getJDBCConnection();
		String sql="SELECT * FROM category WHERE category_Name=?";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				
				category.setId(rs.getInt("Id"));
				category.setName(rs.getString("Category_Name"));
			}
			return category;
		
		} catch (SQLIntegrityConstraintViolationException e2) {
			return null;
		
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		return null;
	}
	}
}
