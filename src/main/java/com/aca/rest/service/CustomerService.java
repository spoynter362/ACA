package com.aca.rest.service;

import java.util.List;
import com.aca.rest.dao.CustomerDao;
import com.aca.rest.model.Customer;


public class CustomerService {

		public static List<Customer> getAllCustomers() {		
			return CustomerDao.getAllCustomers();
		}
		
		public static List<Customer> getByCustomerId(int id) {
			
			return CustomerDao.getByCustomerID(id);
		}

		public static Customer insertCustomer(Customer newCustomer) {
			int rowCount = CustomerDao.insertCustomers(newCustomer);
			System.out.println("number of customer inserts: " + rowCount);
			return newCustomer;
		}

		public static int deleteCustomer(int id) {
			return CustomerDao.deleteCustomers(id);
		}

		public static Customer updateCustomers(Customer customer) {
			CustomerDao.updateCustomers(customer);
			
			return customer;
		}
		
	}


