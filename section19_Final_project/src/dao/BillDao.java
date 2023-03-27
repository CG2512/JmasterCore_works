package dao;

import java.sql.Date;
import java.util.List;

import model.Bill;

public interface BillDao {
	void insert(Bill bill);
	void update(Bill bill);
	void delete(Bill bill);
	Bill get(int billId);
	
	List<Bill> searchByDate(Date startDate,Date endDate);
}
