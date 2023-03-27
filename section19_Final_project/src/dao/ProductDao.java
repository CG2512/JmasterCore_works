package dao;

import model.Product;
import model.Category;

import java.util.List;
public interface ProductDao {
	void insert(Product product);
	void update(Product prodcut);
	void delete(int productId);
	Product get(int productId);
	
	List<Product> SearchByName(String productName);
	List<Product> SearchInPriceRange(double MininumPrice,double MaximumPrice);
	List<Product> SearchByCategory(Category category);
	
	
	
}
