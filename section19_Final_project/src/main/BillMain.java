package main;

import model.Product;
import model.Bill;

import dao.BillGetInputlmpl;
import dao.ProductDaolmpl;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dao.BillDao;
import dao.BillDaolmpl;
import dao.BillGetInput;

public class BillMain {
	public static void main(String[] args) {
		menu();
	}

	private static void menu() {
		int choice = 0;
		while (choice != 6) {
			DisplayMenu();
			choice = MenuInput();
			ExecuteChoice(choice);
		}
		// Print this when quitting the program
		System.out.println("Exiting...");
	}

	private static void DisplayMenu() {
		String menu = "\n1.Insert\n" + "2.Update\n" + "3.Delete\n" + "4.Get\n" + "5.Search by date range\n" + "6.Exit";
		System.out.println(menu);
	}

	private static int MenuInput() {
		while (true) {
			try {
				//Get Input
				Scanner sc1 = new Scanner(System.in);
				System.out.println("Input choice:");
				int choice = sc1.nextInt();
				
				//Check if input is in allowed range
				if (0 < choice && choice < 7) {
					return choice;
				} else {
					System.out.println("Input between 1 and 6");
					continue;
				}
			} catch (InputMismatchException i) {
				System.out.println("Wrong input, please try again");

			}
		}
	}

	private static void ExecuteChoice(int input) {
		switch (input) {
		case 1:
			InsertBill();
			break;
		case 2:
			UpdateBill();
			break;
		case 3:
			DeleteBill();
			break;
		case 4:
			GetBill();
			break;
		case 5:
			SearchBillByDate();
			break;
		}
	}

	private static void InsertBill() {
		//Get BillDaolmpl and BillGetInput object
		BillGetInput billGetInputlmpl = new BillGetInputlmpl();
		BillDao billDaolmpl = new BillDaolmpl();
		
		//Get Bill info
		Bill bill = billGetInputlmpl.getBillInfo();
		
		//Insert bill
		billDaolmpl.insert(bill);
	}

	private static void UpdateBill() {
		//Get BillDaolmpl and BillGetInput object
		BillGetInput billGetInputlmpl = new BillGetInputlmpl();
		BillDao billDaolmpl = new BillDaolmpl();
		
		//Get bill ID then Bill info for updating 
		int billId = billGetInputlmpl.getBillId();
		Bill bill = billGetInputlmpl.getBillInfo();
		bill.setId(billId);
		
		//Update bill
		billDaolmpl.update(bill);
	}

	private static void DeleteBill() {
		
		BillGetInput billGetInputlmpl = new BillGetInputlmpl();
		BillDao billDaolmpl = new BillDaolmpl();
		
		// Get Bill id for deleting, no need to fill other Bill properties
		int billId = billGetInputlmpl.getBillId();
		Bill bill = new Bill();
		bill.setId(billId);
		
		// Delete
		billDaolmpl.delete(bill);
	}

	private static void GetBill() {
		BillGetInput billGetInputlmpl = new BillGetInputlmpl();
		BillDao billDaolmpl = new BillDaolmpl();
		
		//Get Id for reading
		int billId = billGetInputlmpl.getBillId();
		
		billDaolmpl.get(billId);
	}

	private static void SearchBillByDate() {
		BillGetInputlmpl billGetInputlmpl = new BillGetInputlmpl();
		BillDaolmpl billDaolmpl = new BillDaolmpl();
		
		//Get Price range
		Date[] dateRange = billGetInputlmpl.getDateRange();
		
		List<Bill> bills = billDaolmpl.searchByDate(dateRange[0], dateRange[1]);
		
		//Print bill info
		for (Bill bill : bills) {
			billDaolmpl.billInfo(bill);
		}
	}
}
