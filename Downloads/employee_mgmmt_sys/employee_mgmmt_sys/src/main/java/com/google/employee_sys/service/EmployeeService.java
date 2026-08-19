package com.google.employee_sys.service;

import org.springframework.stereotype.Service;


import com.google.employee_sys.entity.Employee;
import com.google.employee_sys.exception.UserNotFoundException;
import com.google.employee_sys.repository.EmployeeRepo;

@Service
public class EmployeeService {
	private EmployeeRepo employeeRepo;

	public EmployeeService(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	public String insert(Employee employee) {
		employeeRepo.save(employee);
		return "employee inserted successfully";
	}
	
	public Object fechById(Long id) {

        return employeeRepo.findById(id)
                .orElseThrow(() ->
                    new UserNotFoundException("Employee not found with id: " + id));
  	}
	
	public Object fetchAll() {
		return employeeRepo.findAll();
	}
	
	public String update(Employee employee) {
		employeeRepo.save(employee);
		return "employee updated successfully";
	}
	
	public String deleteById(Long id) {
		 employeeRepo.deleteById(id);
		 return "employee deleted successfully based on id";
	}
	
	public String delete() {
		employeeRepo.deleteAll();
		return "employees deleted successfully";
		
	}
	

}
