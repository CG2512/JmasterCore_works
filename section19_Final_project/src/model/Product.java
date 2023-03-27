package model;

public class Product {
	private int Id;
	private String Name;
	private int Quantity;
	private double Price;
	private Category ProductCategory;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public Category getProductCategory() {
		return ProductCategory;
	}
	public void setProductCategory(Category productCategory) {
		ProductCategory = productCategory;
	}
	
}
