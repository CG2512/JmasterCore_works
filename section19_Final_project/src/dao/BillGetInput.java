package dao;

import java.util.Date;

import model.Bill;
import model.Product;

public interface BillGetInput {
	Bill getBillInfo();

	int getBillQuantity();

	double getBillPrice(int productId);

	Product getBillProduct();

	Date getBillBuyTime();

	int getBillId();
}
