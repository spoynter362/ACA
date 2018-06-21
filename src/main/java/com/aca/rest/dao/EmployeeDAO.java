package com.aca.rest.dao;

//import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import javax.sql.rowset.serial.SerialBlob;
import javax.xml.bind.DatatypeConverter;
import com.aca.rest.model.Employee;

public class EmployeeDAO {
	private final static String allEmployeeColumns = 
			"   TitleId, LastName, FirstName, BirthDate, Hiredate, Address, City, StateId, Salary, ZipCode, PersonalPhone, Extension, Picture, Notes, ReportsToEmployeeId, TitleOfCourtesyId ";
	
	private final static String sqlSelectAllEmployees = 
			"   SELECT EmployeeId, TitleId, LastName, FirstName, BirthDate, Hiredate, Address, City, StateId, Salary, ZipCode, PersonalPhone, Extension, Picture, Notes, ReportsToEmployeeId, TitleOfCourtesyId " + 
			"   FROM employee ";
	
	private final static String sqlSelectByEmployeeID = 
			"   SELECT EmployeeId, TitleId, LastName, FirstName, BirthDate, Hiredate, Address, City, StateId, Salary, ZipCode, PersonalPhone, Extension, Picture, Notes, ReportsToEmployeeId, TitleOfCourtesyId " + 
			"   FROM employee " +
			"   WHERE EmployeeID = ?";
	
	private final static String sqlInsertEmployee = 
			"   Insert INTO employee ( " + allEmployeeColumns + " ) " +
			"   VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	
	private final static String sqlUpdateEmployee = 
			"   UPDATE employee " + 
			"   SET TitleId = ? , LastName = ? , FirstName = ? , BirthDate = ? , Hiredate = ? , Address = ? , City = ? , StateId = ? , Salary = ? , ZipCode = ? , PersonalPhone = ? , Extension = ? , Picture = ? , Notes = ? , ReportsToEmployeeId = ? , TitleOfCourtesyId = ? " + 
			"   WHERE EmployeeId = ?";
	
	private final static String sqlDeleteStatement = 
			"   DELETE " +
			"   FROM employee " +
			"   WHERE EmployeeID = ?";

	public static List<Employee> getAllEmployees() {
		List <Employee> employees = new ArrayList<Employee>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
		statement = conn.createStatement();
		result = statement.executeQuery(sqlSelectAllEmployees);
		
		while(result.next()) {
			Employee employee = makeEmployee(result);
			
			employees.add(employee);
		}
	}catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			result.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return employees;
  }

	public static ArrayList<Employee> getByEmployeeID(int id) {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
		statement = conn.prepareStatement(sqlSelectByEmployeeID);
		statement.setInt(1, id);
		
		result = statement.executeQuery();
		
		while(result.next()) {
			Employee employee = makeEmployee(result);
			
			employees.add(employee);
		}
	}catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			result.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return employees;
		
	}

	public static int insertEmployees(Employee newEmployee) {
		int rowCount = 0;
		
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlInsertEmployee);
			
			statement.setInt(1, newEmployee.getTitleId());
			statement.setString(2, newEmployee.getLastName());
			statement.setString(3, newEmployee.getFirstName());
			statement.setObject(4, newEmployee.getBirthdate());
			statement.setObject(5, newEmployee.getHiredate());
			statement.setString(6, newEmployee.getAddress());
			statement.setString(7, newEmployee.getCity());
			statement.setString(8, newEmployee.getStateId());
			statement.setBigDecimal(9, newEmployee.getSalary());
			statement.setString(10, newEmployee.getZipCode());
			statement.setString(11, newEmployee.getPersonalPhone());
			statement.setString(12, newEmployee.getExtension());
			
//			byte[] picture = DatatypeConverter.parseBase64Binary(newEmployee.getPicture());
//			Blob pictureBlob = new SerialBlob(picture);
//			statement.setBlob(13, pictureBlob);
			statement.setString(13, newEmployee.getPicture());
			
			statement.setString(14, newEmployee.getNotes());
			statement.setInt(15, newEmployee.getReportsToId());
			statement.setInt(16, newEmployee.getTitleOfCourtesyId());
			
			rowCount = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rowCount;
	}
	
	
	private static Employee makeEmployee (ResultSet result) throws SQLException{
		Employee employee = new Employee();
		
		employee.setId(result.getInt("EmployeeId"));
		employee.setFirstName(result.getString("FirstName"));
		employee.setLastName(result.getString("LastName"));
		employee.setBirthdate(result.getObject("BirthDate", LocalDate.class));
		employee.setHiredate(result.getObject("HireDate", LocalDate.class));
		employee.setCity(result.getString("City"));
		employee.setAddress(result.getString("Address"));
		employee.setStateId(result.getString("StateID"));
		employee.setSalary(result.getBigDecimal("Salary"));
		employee.setTitleId(result.getInt("TitleId"));
		employee.setTitleOfCourtesyId(result.getInt("TitleOfCourtesyId"));
		employee.setReportsToId(result.getInt("ReportsToEmployeeId"));
		employee.setZipCode(result.getString("ZipCode"));
		employee.setPersonalPhone(result.getString("PersonalPhone"));
		employee.setExtension(result.getString("Extension"));
		employee.setNotes(result.getString("Notes"));
		
		byte[] picture = result.getBytes("Picture");
		String pictureString = DatatypeConverter.printBase64Binary(picture);
		employee.setPicture(pictureString);
		
		return employee;
	}

	public static Employee deleteEmployee(int id) {
		List<Employee> employees = EmployeeDAO.getByEmployeeID(id);
		
		Employee employeeToDelete = employees.get(0);
		int rowCount = 0;
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		try {
			statement = conn.prepareStatement(sqlDeleteStatement);
			statement.setInt(1, id);
			rowCount = statement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("number of employees deleted: " + rowCount);
		return employeeToDelete;
	}

	public static Employee updateEmployees(Employee employee) {
		int rowCount = 0;
		
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlUpdateEmployee);
			
			statement.setInt(1, employee.getTitleId());
			statement.setString(2, employee.getLastName());
			statement.setString(3, employee.getFirstName());
			statement.setObject(4, employee.getBirthdate());
			statement.setObject(5, employee.getHiredate());
			statement.setString(6, employee.getAddress());
			statement.setString(7, employee.getCity());
			statement.setString(8, employee.getStateId());
			statement.setBigDecimal(9, employee.getSalary());
			statement.setString(10, employee.getZipCode());
			statement.setString(11, employee.getPersonalPhone());
			statement.setString(12, employee.getExtension());
			
//			byte[] picture = DatatypeConverter.parseBase64Binary(employee.getPicture());
//			Blob pictureBlob = new SerialBlob(picture);
//			statement.setBlob(13, pictureBlob);
			statement.setString(13, employee.getPicture());
			statement.setString(14, employee.getNotes());
			statement.setInt(15, employee.getReportsToId());
			statement.setInt(16, employee.getTitleOfCourtesyId());
			
			statement.setInt(17, employee.getId());	
			
			rowCount = statement.executeUpdate();
			System.out.println(rowCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return employee;
		
	}
}


