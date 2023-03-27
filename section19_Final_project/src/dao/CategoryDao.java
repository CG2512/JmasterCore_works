package dao;

import model.Category;

public interface CategoryDao {
	//void insert(Category category);
	//void update(Category category);
	//void delete(Category category);
	Category read(int productId);
	Category SearchByName(String name);
}
