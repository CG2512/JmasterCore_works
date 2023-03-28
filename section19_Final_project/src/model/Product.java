package model;

public class Product {
	private int id;
	private String name;
	private int quantity;
	private double price;
	private Category productCategory;

	public int getId() {
		return id;
	}

	public void setId(int productId) {
		id = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String productName) {
		name = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int productQuantity) {
		quantity = productQuantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double productPrice) {
		price = productPrice;
	}

	public Category getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(Category category) {
		productCategory = category;
	}

}
