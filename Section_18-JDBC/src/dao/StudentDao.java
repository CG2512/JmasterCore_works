package dao;

import model.Student;
import model.University;

import java.util.List;
import java.util.ArrayList;
public interface StudentDao {
	void create(Student student);
	void update(Student student);
	void delete(int id);
	Student read(int id);
	
	List<Student> SearchByAge(int age);
	List<Student> SearchByName(String name);
	List<Student> SearchByUniversity(String universityName);
}
