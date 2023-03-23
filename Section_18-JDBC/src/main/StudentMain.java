package main;

import java.util.List;
import java.util.Scanner;

import dao.StudentDao;
import dao.StudentDaolmpl;
import dao.UniversityDaolmpl;
import model.Student;
import model.University;

public class StudentMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentMain();
	}

	private static void StudentMain() {
		// TODO Auto-generated method stub
		while (true) {
		System.out.println("1.Create \n"
				+ "2.Update \n"
				+ "3.Delete \n"
				+ "4.Read by Id \n"
				+ "5.Search by age \n"
				+ "6.Search by name \n"
				+ "7.Search by university \n"
				+ "8.Exit"
				);
		Scanner sc=new Scanner(System.in);
		System.out.println("Input: ");
		int choice=sc.nextInt();
		if (choice == 8) {
			break;
		}
		else {
			switch(choice) {
			case 1:
				StudentCreate();
				break;
			case 2:
				StudentUpdate();
				break;
			case 3:
				StudentDelete();
				break;
			case 4:
				StudentReadbyId();
				break;
			case 5:
				StudentSearchByAge();
				break;
			case 6:
				StudentSearchByName();
				break;
			case 7:
				StudentSearchByUniversity();
				break;
			}
				
		}
		}
	}

	private static void StudentCreate() {
		// TODO Auto-generated method stub
		Student student=StudentGetInfo();
		StudentDaolmpl studentDaolmpl = new StudentDaolmpl();
		studentDaolmpl.create(student);
		StudentPrintInfo(student);
	}
	private static void StudentUpdate() {
		Scanner sc3 = new Scanner(System.in);
		System.out.println("Update Id: ");
		int id=sc3.nextInt();
		Student student=StudentGetInfo();
		student.setId(id);
		StudentDaolmpl studentDaolmpl = new StudentDaolmpl();
		studentDaolmpl.update(student);
		StudentPrintInfo(student);
	}
	private static void StudentDelete() {
		StudentDaolmpl studentDaolmpl = new StudentDaolmpl();
		Scanner sc4 = new Scanner(System.in);
		System.out.println("Delete Id: ");
		studentDaolmpl.delete(sc4.nextInt());
		System.out.println("Operation success");
	}
	private static void StudentReadbyId() {
		StudentDaolmpl studentDaolmpl = new StudentDaolmpl();
		Scanner sc5 = new Scanner(System.in);
		System.out.println("Read Id: ");
		try {
			Student student=studentDaolmpl.read(sc5.nextInt());
			StudentPrintInfo(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void StudentSearchByAge() {
		// TODO Auto-generated method stub
		
		StudentDaolmpl studentDaolmpl = new StudentDaolmpl();
		Scanner sc6 = new Scanner(System.in);
		System.out.println("Age: ");
		List<Student> students=studentDaolmpl.SearchByAge(sc6.nextInt());
		for (Student student : students) {
			StudentPrintInfo(student);
		}
	}
	private static void StudentSearchByName() {
		// TODO Auto-generated method stub
		StudentDaolmpl studentDaolmpl = new StudentDaolmpl();
		Scanner sc7 = new Scanner(System.in);
		System.out.println("Name: ");
		List<Student> students=studentDaolmpl.SearchByName(sc7.nextLine());
		for (Student student : students) {
			StudentPrintInfo(student);
		}
	}
	private static void StudentSearchByUniversity() {
		StudentDaolmpl studentDaolmpl = new StudentDaolmpl();
		Scanner sc8 = new Scanner(System.in);
		System.out.println("University name: ");
		List<Student> students=studentDaolmpl.SearchByUniversity(sc8.nextLine());
		for (Student student : students) {
			StudentPrintInfo(student);
		}
	}
	
	private static Student StudentGetInfo() {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Student name:");
		String StudentName=sc.nextLine();
		System.out.println("Student age:");
		int StudentAge=sc.nextInt();	
		University university=GetUniversity();
		Student student=new Student(StudentName,StudentAge,university);
		return student;
	}
	
	private static University GetUniversity() {
		// TODO Auto-generated method stub
		UniversityDaolmpl universitydaolmpl=new UniversityDaolmpl();
		University university=new University();
		while (true) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Student's University Id:");
		university=universitydaolmpl.read(sc.nextInt());
		
		if (university != null) {
			break;
		}
		}
		return university;
	}
	private static void StudentPrintInfo(Student student) {
		
		String studentResult=String.format("Student id: %d / Student name: %s "
				+ "/ Student age: %d "
				+"/ Student university : %s \n",student.getId(),
				student.getName(),
				student.getAge(),
				student.getUniversity().getName());
		System.out.println(studentResult);
	}
	
}
