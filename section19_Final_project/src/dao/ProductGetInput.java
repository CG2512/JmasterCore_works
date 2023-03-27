package dao;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.Category;
import model.Product;

//Specialize class for getting input and exception handling 
public class ProductGetInput {
	public Category GetCategoryID() {
		Scanner sc1=new Scanner(System.in);
		CategoryDao categoryDaolmpl=new CategoryDaolmpl();
		while (true) {
		System.out.println("Category ID: ");
		Category category=categoryDaolmpl.read(sc1.nextInt());
		if (category.getName() != null) {
			return category;			
		}
		else {
			System.out.println("Category does not exist, please try again");
			continue;
		}
		}
	}
	public Category GetCategoryByName() {
		Scanner sc8=new Scanner(System.in);
		CategoryDao categoryDaolmpl=new CategoryDaolmpl();
		while (true) {
		System.out.println("Category name: ");
		Category category=categoryDaolmpl.SearchByName(sc8.nextLine());
		if (category.getName() != null) {
			return category;			
		}
		else {
			System.out.println("Category does not exist, please try again");
			continue;
		}
		}
	}
	public Category GetCategoryInfo() {
		Scanner sc9=new Scanner(System.in);
		Category category=new Category();
		CategoryDao categoryDaolmpl=new CategoryDaolmpl();
		while (true) {
			try {
			System.out.println("1.By Name");
			System.out.println("2.By ID");
			System.out.println("Category search by type: ");
			int choice= sc9.nextInt();
			if (choice ==1) {category=GetCategoryByName();}
			else if (choice==2) {category=GetCategoryID();}
			else {
				System.out.println("Pick 1 or 2");
				continue;
			}
			return category;
		}catch (InputMismatchException i) {
			System.out.println("Wrong input,try again");
			continue;
		}
		}
	}
	

	public int GetProductID() {
		Scanner sc2=new Scanner(System.in);
		while (true) {
			try {System.out.println("Product ID: ");
			int ProductId=sc2.nextInt();
			return ProductId;
		}catch (InputMismatchException i) {
			System.out.println("Wrong input, please try again");
			continue;
		}		
	}
	}
	
	
	
	public Product GetProductInfo() {
		Product product=new Product();
		product.setName(GetProductName());
		product.setQuantity(GetQuantity());
		product.setPrice(GetPrice());
		product.setProductCategory(GetCategoryID());
		return product;
		}	
		
	public String GetProductName() {
		
		while (true) {
			Scanner sc3=new Scanner(System.in);
			System.out.println("Product name:");
			String name=sc3.nextLine();
			if (name.length()<1) {
				System.out.print("Name must not be empty");
				continue;
			}
			else if(name.matches("[0-9]+")){
				System.out.print("Name must not contains only number");
				continue;
			}
			return name;			
		}
	}
	private int GetQuantity() {
		
		while (true) {
			try {
				Scanner sc4=new Scanner(System.in);
				System.out.println("Quantity:");
				int quantity=sc4.nextInt();
				return quantity;
				}catch (InputMismatchException i1) {
					System.out.println("Wrong input type for quantity, please try again");
					continue;
				}
		}
	}
	private double GetPrice() {
		
		while (true) {
			try {
				Scanner sc5=new Scanner(System.in);
				System.out.println("Price:");
				double price= sc5.nextDouble();
				return price;
				}catch (InputMismatchException i1) {
					System.out.println("Wrong input type for price , please try again");
					continue;
				}
		}
	}
	public Double[] GetPriceRange(){
		double MinimumPrice;
		double MaximumPrice;
		while (true) {
			try {
				Scanner sc6=new Scanner(System.in);
				System.out.println("Minimum Price:");
				MinimumPrice=sc6.nextDouble();
				break;
				}catch (InputMismatchException i1) {
					System.out.println("Wrong input type for price , please try again");
					continue;
				}
		}
		while (true) {
			try {
				Scanner sc7=new Scanner(System.in);
				System.out.println("Maximum Price:");
				MaximumPrice= sc7.nextDouble();
				break;
				}catch (InputMismatchException i2) {
					System.out.println("Wrong input type for price , please try again");
					continue;
				}	
		} 
		Double[] PriceRange= {MinimumPrice,MaximumPrice};
		return PriceRange;
	}
}
