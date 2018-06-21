package com.aca.rest.service;

import java.util.List;

//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.Response;

import com.aca.rest.dao.EmployeeDAO;
import com.aca.rest.model.Employee;


public class EmployeeService {

	public static List<Employee> getAllEmployees(){
		return EmployeeDAO.getAllEmployees();
	}

	public static List<Employee> getByEmployeeID(int id) {
		
		return EmployeeDAO.getByEmployeeID(id);
	}

	public static void addEmployees(Employee newEmployee) {
		int rowCount = EmployeeDAO.insertEmployees(newEmployee);
		System.out.println("number of employee inserts: " + rowCount);
	}

	public static Employee deleteEmployee(int id) {
		return EmployeeDAO.deleteEmployee(id);
	}

	public static Employee updateEmployees(Employee employee) {
		EmployeeDAO.updateEmployees(employee);
		
		return employee;
	}

}