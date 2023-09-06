package org.jsp.employeeproj.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.employeeproj.dao.EmployeeDao;
import org.jsp.employeeproj.dto.Employees;

public class EmployeesController {
	static Scanner s = new Scanner(System.in);
	static EmployeeDao dao = new EmployeeDao();

	public static void main(String[] args) {

		System.out.println("1.Save Employee");
		System.out.println("2.Update Employee");
		System.out.println("3.Find Employee By Id");
		System.out.println("4.Verify Employee By Phone or Password");
		System.out.println("5.Verify Employee Id and password");
		System.out.println("6.Delete Employee By Id");
		System.out.println("7.Find Employee By Desg");
		int choice = s.nextInt();

		switch (choice) {
		case 1: {
			save();
			break;
		}
		case 2: {
			update();
			break;
		}
		case 3: {
			findById();
			break;
			
		}
		case 4: {
			verifyByphone();
			break;
		}
		case 5: {
			verifyById();
			break;
			
		}
		case 6: {
			delete();
			break;
		}
		case 7: {
			findByDesg();
			break;
		}
		default:
		{
			System.err.println("Invalid Choice");
		}
			break;
		}
	}

	public static void save() {
		System.out.println("Enter the Employee name,desg,salary,phone and password");
		Employees e = new Employees();
		e.setName(s.next());
		e.setDesg(s.next());
		e.setSalary(s.nextDouble());
		e.setPhone(s.nextLong());
		e.setPassword(s.next());
		e = dao.saveEmployee(e);
		System.out.println("Employee saved with Id: " + e.getId());
	}

	public static void update() {
		System.out.println("Enter Your Id to update");
		int id = s.nextInt();
		System.out.println("Enter the Employee name,desg,salary,phone and password to update");
		Employees e = new Employees();
		e.setId(id);
		e.setName(s.next());
		e.setDesg(s.next());
		e.setSalary(s.nextDouble());
		e.setPhone(s.nextLong());
		e.setPassword(s.next());
		e = dao.updateEmployee(e);
		System.out.println("Employee Updated with Id:" + e.getId());

	}

	public static void verifyByphone() {
		System.out.println("Enter your phone number and password to vrify");
		long phone = s.nextLong();
		String password = s.next();
		Employees e = dao.verifyEmployee(phone, password);
		if (e != null) {
			System.out.println("Employee Id: " + e.getId());
			System.out.println("Employee name: " + e.getName());
			System.out.println("Employee Designation " + e.getDesg());
			System.out.println("Employee Salary: " + e.getSalary());
			System.out.println("Employee Phone Number: " + e.getPhone());
		} else {
			System.err.println("Invalid Phone number or Password");
		}
	}

	public static void verifyById() {
		System.out.println("Enter yOUR Id and password to verify");
		int id = s.nextInt();
		String password = s.next();
		Employees e = dao.verifyEmployee(id, password);
		if (e != null) {
			System.out.println("Employee Id: " + e.getId());
			System.out.println("Employee Name: " + e.getName());
			System.out.println("Designtaion: " + e.getDesg());
			System.out.println("Salary: " + e.getSalary());
			System.out.println("Phone Number: " + e.getPhone());
		} else {
			System.err.println("invalid  Id or password");
		}
	}

	public static void delete() {
		System.out.println("Enter Employee Id to delete");
		int id = s.nextInt();
		boolean deleted = dao.deleteEmployee(id);
		if (deleted) {
			System.out.println("Employee deleted");
		} else {
			System.err.println("You have entered an Invalid Id");
		}
	}

	public static void findById() {
		System.out.println("Enter the Employee id to display details");
		int id = s.nextInt();
		Employees e = dao.findById(id);
		if (e != null) {
			System.out.println("Employee Id:" + e.getId());
			System.out.println("Employee Name:" + e.getName());
			System.out.println("Designtaion:" + e.getDesg());
			System.out.println("Salary:" + e.getSalary());
			System.out.println("Phone Number:" + e.getPhone());
		} else {
			System.err.println("You have entered an Invalid Id");
		}
	}

	public static void findByDesg() {
		System.out.println("Enter the Employee Desg to display details");
		String desg = s.next();
		List<Employees> emps = dao.findEmployeeByDesg(desg);
		if (emps.size() > 0) {
			for (Employees e : emps) {
				System.out.println("Employee Id:" + e.getId());
				System.out.println("Employee Name:" + e.getName());
				System.out.println("Designtaion:" + e.getDesg());
				System.out.println("Salary:" + e.getSalary());
				System.out.println("Phone Number:" + e.getPhone());
			}
		} else {
			System.err.println("No employee found with entered designtaion");
		}
	}
}
