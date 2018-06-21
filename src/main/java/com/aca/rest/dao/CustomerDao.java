package com.aca.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.aca.rest.model.Customer;

public class CustomerDao {
		private final static String allCustomerColumns = 
				"   id, name, city ";
		
		private final static String sqlSelectAllCustomers = 
				"   SELECT id, name, city " + 
				"   FROM cust ";
		
		private final static String sqlSelectByCustomerID = 
				"   SELECT id, name, city " + 
				"   FROM cust " +
				"   WHERE id = ?";
		
		private final static String sqlInsertCustomer = 
				"   Insert INTO cust ( " + allCustomerColumns + " ) " +
				"   VALUES (?,?,?) ";
		
		private final static String sqlUpdateCustomer = 
				"   UPDATE cust " + 
				"   SET name = ? , city = ? " + 
				"   WHERE id = ?";
		
		private final static String sqlDeleteStatement = 
				"   DELETE " +
				"   FROM cust " +
				"   WHERE id = ?";

		public static List<Customer> getAllCustomers() {
			List <Customer> customers = new ArrayList<Customer>();
			
			ResultSet result = null;
			Statement statement = null;
			
			Connection conn = MariaDbUtil.getConnection();
			
			try {
			statement = conn.createStatement();
			result = statement.executeQuery(sqlSelectAllCustomers);
			
			while(result.next()) {
				Customer customer = makeCustomer(result);
				
				customers.add(customer);
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
			return customers;
	  }

		public static ArrayList<Customer> getByCustomerID(int id) {
			ArrayList<Customer> customers = new ArrayList<Customer>();
			
			ResultSet result = null;
			PreparedStatement statement = null;
			
			Connection conn = MariaDbUtil.getConnection();
			
			try {
			statement = conn.prepareStatement(sqlSelectByCustomerID);
			statement.setInt(1, id);
			
			result = statement.executeQuery();
			
			while(result.next()) {
				Customer customer = makeCustomer(result);
				
				customers.add(customer);
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
			return customers;
			
		}

		public static int insertCustomers(Customer newCustomer) {
			int rowCount = 0;
			
			PreparedStatement statement = null;
			Connection conn = MariaDbUtil.getConnection();
			
			try {
				statement = conn.prepareStatement(sqlInsertCustomer);
				
				statement.setInt(1, newCustomer.getId());
				statement.setString(2, newCustomer.getName());
				statement.setString(3, newCustomer.getCity());
				
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
		
		
		private static Customer makeCustomer (ResultSet result) throws SQLException{
			Customer customer = new Customer();
			
			customer.setId(result.getInt("id"));
			customer.setName(result.getString("Name"));
			customer.setCity(result.getString("City"));
			
			
			return customer;
		}

		public static int deleteCustomers(int id) {
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
			System.out.println("number of customers deleted: " + rowCount);
			return rowCount;
		}

		public static Customer updateCustomers(Customer customer) {
			int rowCount = 0;
			
			PreparedStatement statement = null;
			Connection conn = MariaDbUtil.getConnection();
			
			try {
				statement = conn.prepareStatement(sqlUpdateCustomer);
				
				statement.setInt(3, customer.getId());
				statement.setString(1, customer.getName());
				statement.setString(2, customer.getCity());
				
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
			
			return customer;
			
		}
//	}
}
