package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.Bill;
import model.Product;

public class BillGetInputlmpl implements BillGetInput {
//Specific class for getting and parsing user input
	@Override
	public Bill getBillInfo() {
		// TODO Auto-generated method stub
		Bill bill=new Bill();
		
		Product product=getBillProduct();
		bill.setProductQuantity(getBillQuantity());
		bill.setProduct(getBillProduct());
		bill.setProductPrice(product.getPrice());
		bill.setBuyTime(getBillBuyTime());
		return bill;
	}

	@Override
	public int getBillQuantity() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Scanner sc1=new Scanner(System.in);
				System.out.println("Bill quantity");
				int quantity=sc1.nextInt();
				if (quantity >0) {
				return quantity;
				}
				else {
					System.out.println("Bill quantity must be larger than 0");
					continue;
				}
			}catch(InputMismatchException i) {
				System.out.println("Bill quantity must be a number");
				continue;
			}
		}
	}

	@Override
	public double getBillPrice(int productId) {
		
		//Get ProductDaolmpl to get productId
		ProductDao productDaolmpl=new ProductDaolmpl();
		double productPrice=productDaolmpl.get(productId).getPrice();
		return productPrice;
	}

	@Override
	public Product getBillProduct() {
		// TODO Auto-generated method stub
		
		//Create productDaolmpl to check if product exist
		ProductDao productDaolmpl=new ProductDaolmpl();
		while (true) {
			try {
				Scanner sc2=new Scanner(System.in);
				System.out.println("Bill productId");
				int productId=sc2.nextInt();
				Product product=productDaolmpl.get(productId);
				if (product != null) {
					return product;
				}
				else {
					System.out.println("Cannot find product");
					continue;
				}
			}catch(InputMismatchException i) {
				System.out.println("Bill quantity must be a number");
				continue;
			}
		}

	}

	@Override
	public Date getBillBuyTime() {
		// TODO Auto-generated method stub
		//Pattern for formatting Date to SQL Date format
		String pattern="YYYY-MM-DD";
		while (true) {
			try {
			Scanner sc3=new Scanner(System.in);
			System.out.println("buy time(YYYY-MM-DD): ");
			String dateStr=sc3.nextLine();
			boolean correctPattern=DateMarcher(dateStr);
			if (correctPattern == true) {
				Date buyTime=Date.valueOf(dateStr);
				return buyTime;
			}else {
				System.out.println("Incorrect format");
				continue;
			}
			}catch (IllegalArgumentException a) {
				System.out.println("Incorrect date inputted");
				continue;
			}
		}
	}

	@Override
	public int getBillId() {
		while(true) {
			try {
			Scanner sc4=new Scanner(System.in);
			System.out.println("Bill Id:");
			return sc4.nextInt();
		}catch(InputMismatchException i) {
			System.out.println("Wrong input type");
			continue;
		}
	
		}	
	}
	public Date[] getDateRange() {
		
		System.out.println("From date");
		Date startDate=getBillBuyTime();
		System.out.println("To date");
		Date endDate=getBillBuyTime();
		
		Date[] dateRange= {startDate,endDate};
		
		return dateRange;
		
	}
	
	//Check if the input date is in correct format
	private boolean DateMarcher(String dateStr) {
		Pattern DATE_PATTERN=Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
		return DATE_PATTERN.matcher(dateStr).matches();
	}
}
