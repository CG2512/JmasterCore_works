package model;

import java.sql.Date;

public class Bill {
	private int Id;
	private int ProductQuantity;
	private double ProductPrice;
	private Product product;
	private Date BuyTime;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getProductQuantity() {
		return ProductQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		ProductQuantity = productQuantity;
	}
	public double getProductPrice() {
		return ProductPrice;
	}
	public void setProductPrice(double productPrice) {
		ProductPrice = productPrice;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product buyProduct) {
		product=buyProduct;
	}
	public Date getBuyTime() {
		return BuyTime;
	}
	public void setBuyTime(Date date) {
		BuyTime = date;
	}
	
	
}
