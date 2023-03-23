package dao;

import model.University;

import java.util.ArrayList;
import java.util.List;
public interface UniversityDao {
	void create(University university);
	void update(University university);
	void delete(int id);
	University read(int id);
	
	ArrayList<University> search(String name);
}
