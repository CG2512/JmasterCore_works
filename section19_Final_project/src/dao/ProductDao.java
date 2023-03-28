package dao;

import model.Product;
import model.Category;

import java.util.List;

public interface ProductDao {
	void insert(Product product);

	void update(Product prodcut);

	void delete(int productId);

	Product get(int productId);

	List<Product> searchByName(String productName);

	List<Product> searchInPriceRange(double mininumPrice, double maximumPrice);

	List<Product> searchByCategory(Category category);

}
