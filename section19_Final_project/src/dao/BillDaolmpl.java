package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import model.Bill;
import model.Product;

public class BillDaolmpl implements BillDao{

	@Override
	public void insert(Bill bill) {
		// TODO Auto-generated method stub
		//Get new ProductDaolmpl to check if there's enough item
		
		ProductDao productDaolmpl=new ProductDaolmpl();
		Connection conn=JDBC_Connection.getJDBCConnection();
		String sql="INSERT INTO bill(quantity,price,productId,buyDate) VALUES(?,?,?,?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement statement=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			Product product=productDaolmpl.get(bill.getProduct().getId());
			// Check if there's still enough products left for bill
			if (product.getQuantity() >= bill.getProductQuantity()) {
			//Insert bill
				statement.setInt(1,bill.getProductQuantity());
				statement.setDouble(2, bill.getProductPrice());
				statement.setInt(3,bill.getProduct().getId());
				statement.setDate(4, (java.sql.Date) bill.getBuyTime());
				
				//Update product quantity
				UpdateProductQuantity(product,bill.getProductQuantity());
				//Execute update
				statement.executeUpdate();
				conn.commit();
			}else {
				System.out.println("Product doesn't have enough items");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Bill bill) {
		
		ProductDao productDaolmpl=new ProductDaolmpl();
		Connection conn=JDBC_Connection.getJDBCConnection();
		String sql="UPDATE bill SET quantity=?,price=?,productId=?,buyDate=? WHERE Id=?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement statement=conn.prepareStatement(sql);
			Product product=productDaolmpl.get(bill.getProduct().getId());
			// Check if there's still enough products left for bill
			if (product.getQuantity() >= bill.getProductQuantity()) {
			//Insert bill
				statement.setInt(1,bill.getProductQuantity());
				statement.setDouble(2, bill.getProductPrice());
				statement.setInt(3,bill.getProduct().getId());
				statement.setDate(4,  bill.getBuyTime());
				statement.setInt(5, bill.getId());
				
				//Update product quantity
				UpdateProductQuantity(product,bill.getProductQuantity());
				//Execute update
				statement.executeUpdate();
				conn.commit();
			
				
				}else {
				System.out.println("Product doesn't have enough items");
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Bill bill) {
		// TODO Auto-generated method stub
		Connection conn=JDBC_Connection.getJDBCConnection();
		String sql="DELETE FROM bill WHERE id=?";
		try {
			PreparedStatement statement =conn.prepareStatement(sql);
			statement.setInt(1, bill.getId());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Bill get(int billId) {
		// TODO Auto-generated method stub
		Connection conn=JDBC_Connection.getJDBCConnection();
		String sql="SELECT * FROM bill WHERE id=?";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1, billId);
			
			ResultSet rs=statement.executeQuery();
			
			while(rs.next()) {
				Bill bill=RowMapper(rs);
				BillInfo(bill);
				return bill;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Bill> searchByDate(Date startDate,Date endDate) {
		List<Bill> bills=new ArrayList<Bill>();
		Connection conn=JDBC_Connection.getJDBCConnection();
		String sql="SELECT b.*,p.Product_Name FROM bill AS b "
				+ "INNER JOIN product AS p "
				+"ON b.productId=p.Id "
				+"WHERE b.buyDate BETWEEN ? AND ? "
				+"ORDER BY buyDate ASC";
		try {
			
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setDate(1, startDate);
			statement.setDate(2, endDate);
			
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				Bill bill=RowMapper(rs);
				bills.add(bill);
			}
			return bills;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private void UpdateProductQuantity(Product product,int billQuantity) {
		ProductDaolmpl productDaolmpl=new ProductDaolmpl();
		int remainsQuantity=product.getQuantity()-billQuantity;
		product.setQuantity(remainsQuantity);
		productDaolmpl.update(product);
	}
	private Bill RowMapper(ResultSet rs) throws SQLException {
		
		ProductDao productDaolmpl=new ProductDaolmpl();
		Bill bill=new Bill();
		bill.setId(rs.getInt("id"));
		bill.setProductQuantity(rs.getInt("quantity"));
		bill.setProductPrice(rs.getDouble("price"));
		Product product=productDaolmpl.get(rs.getInt("productId"));
		bill.setProduct(product);
		bill.setBuyTime(rs.getDate("buyDate"));
		
		return bill;
	}
	public void BillInfo(Bill bill) {
		System.out.println("Bill id: "+bill.getId());
		System.out.println("Bill quantity: "+bill.getProductQuantity());
		System.out.println("Bill price: "+bill.getProductPrice());
		System.out.println("Bill product Id: "+bill.getProduct().getPrice());
		System.out.println("Bill buy time: "+bill.getBuyTime().toString());
		//Print empty line to split between info
		System.out.println("");
	}

	
}
