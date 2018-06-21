package com.aca.rest.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aca.rest.model.Customer;
import com.aca.rest.model.Employee;
import com.aca.rest.service.CustomerService;
import com.aca.rest.service.EmployeeService;

@Path("/customers")
public class CustomerController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAllCustomers() {	
		
		return CustomerService.getAllCustomers();
	}		
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Customer updateCustomer(Customer updateCustomer) {	
		return CustomerService.updateCustomers(updateCustomer);
	}	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Customer insertCustomer(Customer insertCustomer) {	
		return CustomerService.insertCustomer(insertCustomer);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public int deleteCustomers(@PathParam ("id")int id){
		
		System.out.println("delete customer: " + id);
		int customer = CustomerService.deleteCustomer(id);
		
		return customer;
	}
	
}


