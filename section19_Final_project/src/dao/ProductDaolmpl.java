package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Category;
import model.Product;

public class ProductDaolmpl implements ProductDao{

	@Override
	public void insert(Product product) {
		// TODO Auto-generated method stub
		Connection conn=JDBC_Connection.getJDBCConnection();
		String sql="INSERT INTO Product(Product_Name,Product_Quantity,Product_Price,Category_Id)"
				+ " VALUES(?,?,?,?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement statement=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, product.getName());
			statement.setInt(2, product.getQuantity());
			statement.setDouble(3, product.getPrice());
			statement.setInt(4, product.getProductCategory().getId());
		
			
			ExecuteUpdate(statement);
			
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		Connection conn=JDBC_Connection.getJDBCConnection();
		String sql="UPDATE product SET Product_Name=?,Product_Quantity=?,Product_Price=?,Category_Id=? where Id=?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1,product.getName());
			statement.setInt(2, product.getQuantity());
			statement.setDouble(3, product.getPrice());
			statement.setInt(4, product.getProductCategory().getId());
			statement.setInt(5, product.getId());
			
			ExecuteUpdate(statement);
			conn.commit();
		} catch (SQLException e) {
			System.out.println("Product ID not found");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	@Override
	public void delete(int productId) {
		// TODO Auto-generated method stub
		Connection conn=JDBC_Connection.getJDBCConnection();
		String sql="DELETE FROM product WHERE Id=?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1, productId);
			
			ExecuteUpdate(statement);
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Product get(int productId) {
		// TODO Auto-generated method stub
		Connection conn=JDBC_Connection.getJDBCConnection();
		String sql="SELECT * FROM product WHERE Id=? ";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1, productId);
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				Product product=RowMapper(rs);
				return product;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Product does not exist");
			//e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> SearchByName(String productName) {
		// TODO Auto-generated method stub
		// Create a list of Product for storing
		List<Product> products=new ArrayList<Product>();
		Connection conn=JDBC_Connection.getJDBCConnection();
		String sql="SELECT * FROM product WHERE Product_Name LIKE ?";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1, productName);
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				Product product=RowMapper(rs);
				products.add(product);
				
			}
			return products;
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}


	@Override
	public List<Product> SearchInPriceRange(double MinimumPrice, double MaximumPrice) {
		// TODO Auto-generated method stub
		List<Product> products=new ArrayList<Product>();
		Connection conn=JDBC_Connection.getJDBCConnection();
		String sql="SELECT * FROM product WHERE Product_Price BETWEEN ? AND ?";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setDouble(1, MinimumPrice);
			statement.setDouble(2, MaximumPrice);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				Product product=RowMapper(rs);
				products.add(product);
			}
			return products;		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> SearchByCategory(Category category) {
		// TODO Auto-generated method stub
		List<Product> products =new ArrayList<Product>();
		Connection conn=JDBC_Connection.getJDBCConnection();
		//Note, the whitespace between each command is necessary
		String sql="SELECT p.*,c.Category_Name "
					+"FROM product AS p "
					+"INNER JOIN category AS c "
					+"WHERE p.Category_Id=c.Id "
					+"AND Category_Name=?";
		
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1, category.getName());
			
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				Product product=RowMapper(rs);
				products.add(product);
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private void ExecuteUpdate(PreparedStatement statement) {
		try {
			int rs=statement.executeUpdate();
			if (rs>0) {
				System.out.print("Update product DB success");
			}
			else {
				System.out.print("Update DB fail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Product RowMapper(ResultSet rs) {
		Product product=new Product();
		CategoryDao categoryDao=new CategoryDaolmpl();
		try {
			//Get product attributes from DB
			product.setId(rs.getInt("Id"));
			product.setName(rs.getString("Product_Name"));
			product.setQuantity(rs.getInt("Product_Quantity"));
			product.setPrice(rs.getDouble("Product_Price"));
			//Get category object and set categoryID
			Category category=categoryDao.read(rs.getInt("Category_Id"));
			product.setProductCategory(category);
			return product;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
