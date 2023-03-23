package model;


public class Student {
	private int Id;
	private String Name;
	private int Age;
	private University university;
	public Student(String studentName, int studentAge, University Studentuniversity) {
		this.Name=studentName;
		this.Age=studentAge;
		this.university=Studentuniversity;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University Studentuniversity) {
		university=Studentuniversity;
	}
	
	
}
