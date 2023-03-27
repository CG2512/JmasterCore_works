package main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dao.ProductDao;
import dao.ProductDaolmpl;
import dao.ProductGetInput;

import model.Category;
import model.Product;

public class ProductMain {
public static void main(String[] args) {
	menu();
}
private static void menu() {
	int choice=0;
	while(choice !=8) 
	{
	DisplayMenu();
	choice=MenuInput();
	ExecuteChoice(choice);
	}
	//Print this when quitting the program
	System.out.println("Exiting...");
}
private static void DisplayMenu() {
	String menu="\n1.Insert\n"
			+"2.Update\n"
			+"3.Delete\n"
			+"4.Get\n"
			+"5.Search By Name\n"
			+"6.Search in price range\n"
			+"7.Search by category\n"
			+"8.Exit\n";
			
System.out.println(menu);
}
private static int MenuInput() {
	while (true) {
		try {
		Scanner sc1=new Scanner(System.in);
		System.out.println("Input choice:");
		int choice=sc1.nextInt();
		if ( 0<choice && choice<9) {
		return choice;
		}
		else {
			System.out.println("Input between 1 and 8");
			continue;
		}
	}catch (InputMismatchException i) {
		System.out.println("Wrong input, please try again");
		
	}
}
}

private static void ExecuteChoice(int input) {
	switch(input){
	case 1:
		InsertProduct();
		break;
	case 2:
		UpdateProduct();
		break;
	case 3:
		DeleteProduct();
		break;
	case 4:
		GetProduct();
		break;
	case 5:
		SearchProductByName();
		break;
	case 6:
		SearchProductInPriceRange();
		break;
	case 7:
		SearchProductByCategory();
		break;
	}
}
private static void InsertProduct() {
	// TODO Auto-generated method stub
	ProductDaolmpl productDaolmpl=new ProductDaolmpl();
	ProductGetInput ProductGetInput=new ProductGetInput();
	Product product= ProductGetInput.GetProductInfo();
	productDaolmpl.insert(product);
}
private static void UpdateProduct() {
	ProductDaolmpl productDaolmpl=new ProductDaolmpl();
	ProductGetInput ProductGetInput=new ProductGetInput();
	int id=ProductGetInput.GetProductID();
	Product product=ProductGetInput.GetProductInfo();
	product.setId(id);
	productDaolmpl.update(product);
}
 private static void DeleteProduct() {
	 ProductDaolmpl productDaolmpl=new ProductDaolmpl();
	 ProductGetInput ProductGetInput=new ProductGetInput();
	 int id=ProductGetInput.GetProductID();
	 productDaolmpl.delete(id);
 }
 private static void GetProduct() {
	 ProductDaolmpl productDaolmpl=new ProductDaolmpl();
	 ProductGetInput ProductGetInput=new ProductGetInput();
	 int id=ProductGetInput.GetProductID();
	 Product product=productDaolmpl.get(id);
	 ProductInfo(product);
 }
 private static void SearchProductByName() {
	 ProductDaolmpl productDaolmpl=new ProductDaolmpl();
	 ProductGetInput ProductGetInput=new ProductGetInput();
	 String productName=ProductGetInput.GetProductName();
	 List<Product> products=productDaolmpl.SearchByName(productName);
	 ListPrinter(products);
 }
 private static void SearchProductInPriceRange() {
	 ProductDaolmpl productDaolmpl=new ProductDaolmpl();
	 ProductGetInput ProductGetInput=new ProductGetInput();
	 Double[] PriceRange=ProductGetInput.GetPriceRange();
	 List<Product> products=productDaolmpl.SearchInPriceRange(PriceRange[0], PriceRange[1]);
	 ListPrinter(products);
 }
 private static void SearchProductByCategory() {
	 ProductDaolmpl productDaolmpl=new ProductDaolmpl();
	 ProductGetInput ProductGetInput=new ProductGetInput();
	 Category category=ProductGetInput.GetCategoryInfo();
	 List<Product> products=productDaolmpl.SearchByCategory(category);
	 ListPrinter(products);
 }
 private static void ProductInfo(Product product) {
	 System.out.println("Product ID: "+product.getId());
	 System.out.println("Name: "+product.getName());
	 System.out.println("Quantity: "+product.getQuantity());
	 System.out.println("Price: "+product.getPrice());
	 //Display both Category name and ID
	 System.out.println("Category: "+product.getProductCategory().getName()
			 +"("+product.getProductCategory().getId()+")\n");
 }
 private static void ListPrinter(List<Product> products) {
	 if (products.size() >0) {
		 for (Product product: products) {
			 ProductInfo(product);
	 }
	 }
	 else {
		 System.out.println("No product found");
	 }
 }
}
