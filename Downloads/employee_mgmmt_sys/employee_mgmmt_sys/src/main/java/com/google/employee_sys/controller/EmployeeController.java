package com.google.employee_sys.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.employee_sys.entity.Employee;
import com.google.employee_sys.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping
	public String insert(@Valid @RequestBody Employee employee) {
		return employeeService.insert(employee);
	}
	
	@GetMapping("/id/{id}")
	public Object fechById(@PathVariable Long id) {
		return employeeService.fechById(id);
	}
	
	@GetMapping("/all")
	public Object fetchAll() {
		return employeeService.fetchAll();
	}
	
	@PutMapping("/update")
	public String update(@RequestBody Employee employee) {
		return employeeService.update(employee);
	}
	
	@DeleteMapping("/id{id}")
	public String deleteById(@PathVariable Long id) {
		return employeeService.deleteById(id);
	}
	
	@DeleteMapping
	public String delete() {
		return employeeService.delete();
	}
}
