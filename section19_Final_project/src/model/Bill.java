package model;

import java.sql.Date;

public class Bill {
	private int id;
	private int productQuantity;
	private double productPrice;
	private Product product;
	private Date buyTime;

	public int getId() {
		return id;
	}

	public void setId(int productId) {
		id = productId;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int quantity) {
		productQuantity = quantity;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double price) {
		productPrice = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product buyProduct) {
		product = buyProduct;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date date) {
		buyTime = date;
	}

}
