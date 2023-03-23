package model;

public class University {
	private int Id;
	private String Name;
	private String Address;
	
	public University() {}
	
	public University(int id) {
		this.Id=id;
	}
	public University(String name, String address) {
		// TODO Auto-generated constructor stub
		this.Name=name;
		this.Address=address;
	}
	
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		this.Address = address;
	}
	
	
}
