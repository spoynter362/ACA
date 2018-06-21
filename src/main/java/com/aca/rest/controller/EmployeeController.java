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
import com.aca.rest.model.Employee;
import com.aca.rest.service.EmployeeService;


@Path("/employees")
public class EmployeeController {
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Employee> getAllEmployees(){
			return EmployeeService.getAllEmployees();
		}
		
		@GET
		@Path("/{value}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Employee> getByEmployeeID(@PathParam("value") int id){
			return EmployeeService.getByEmployeeID(id);
		}
		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Employee addEmployees(Employee newEmployee){
			
			EmployeeService.addEmployees(newEmployee);
			
			return newEmployee;
		}
		
		
		
		@DELETE
		@Path("/{employeeID}")
		@Produces(MediaType.APPLICATION_JSON)
		public Employee deleteEmployees(@PathParam ("employeeID")int id){
			
			System.out.println("delete employee: " + id);
			Employee employee = EmployeeService.deleteEmployee(id);
			
			return employee;
		}

		
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Employee updateEmployees(Employee employee){
			
			EmployeeService.updateEmployees(employee);
			
			return employee;
		}
}
