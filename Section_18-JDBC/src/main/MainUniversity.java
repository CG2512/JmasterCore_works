package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import model.University;
import dao.UniversityDaolmpl;
import dao.UniversityDao;
public class MainUniversity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menu();
	}

	private static void menu() {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("1.Create");
			System.out.println("2.Delete");
			System.out.println("3.Update");
			System.out.println("4.Search by Id");
			System.out.println("5.Search by names");
			System.out.println("6.Exit");
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Input: ");
			int rs=sc.nextInt();
			
			if (rs == 1){
				CreateUniversity();
			}
			else if(rs==2) {
				UpdateUniversity();
			}
			else if (rs==3) {
				DeleteUniversity();
			}
			else if (rs==4) {
				ReadById();
			}
			else if (rs==5) {
				SearchByName();
			}
			else if (rs == 6){
				sc.close();
				break;
			}
			else {
				System.out.println("Wrong input");
				continue;
			}
		}
	}

	

	private static void CreateUniversity() {
		// TODO Auto-generated method stub
		University university=GetInput();
		UniversityDao UniversityDao=new UniversityDaolmpl();
		UniversityDao.create(university);
		System.out.println(university.getName());
		System.out.println(university.getAddress());
		System.out.println(university.getId());
	}
	private static void UpdateUniversity() {
		Scanner sc2=new Scanner(System.in);
		System.out.println("Id: ");
		int id=sc2.nextInt();
		University university=GetInput();
		university.setId(id);
		UniversityDao UniversityDao=new UniversityDaolmpl();
		UniversityDao.update(university);
	}
	private static void DeleteUniversity() {
		Scanner sc3=new Scanner(System.in);
		System.out.println("Id: ");
		int id=sc3.nextInt();
		UniversityDao UniversityDao=new UniversityDaolmpl();
		UniversityDao.delete(id);
	}
	private static void ReadById() {
		Scanner sc4=new Scanner(System.in);
		System.out.println("Id: ");
		int id=sc4.nextInt();
		UniversityDao UniversityDao=new UniversityDaolmpl();
		University university=UniversityDao.read(id);
		String UniversityInfo=String.format("Name: %s \nAddress: %s",university.getName(),university.getAddress());
		System.out.println(UniversityInfo);
	}
	private static void SearchByName() {
		Scanner sc5=new Scanner(System.in);
		System.out.println("Search by name: ");
		String name=sc5.nextLine();
		UniversityDao UniversityDao=new UniversityDaolmpl();
		List<University> universities=UniversityDao.search(name);
		
		if (universities.isEmpty()) {
			System.out.println("No match");
		}
		for (University university:universities) {
			String UniversityInfo=String.format("Name: %s \nAddress: %s",university.getName(),university.getAddress());
			System.out.println(UniversityInfo);
		}
	}
	private static University GetInput() {
		Scanner sc1=new Scanner(System.in);
		System.out.println("Name: ");
		String name=sc1.nextLine();
		System.out.println("Address: ");
		String address=sc1.nextLine();
		University university=new University(name,address);
		return university;
	}
	

}
