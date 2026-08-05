package com.google.emp_mgmt_sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.emp_mgmt_sys.entity.Employee;
import com.google.emp_mgmt_sys.service.EmployeeService;


@RestController
@RequestMapping("/emp")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/id/{id}")
	public Object getbyid(@PathVariable int id) {
		return employeeService.getbyid(id);
	}
	
	@GetMapping("/all")
	public Object getAll() {
		return employeeService.getAll();
	}
	
	@PostMapping("/inserts")
	public String insert(@RequestBody Employee employee) {
		return employeeService.insert(employee);
	}
	
	@DeleteMapping("/deleteid/{id}")
	public String deleteById(@PathVariable int id) {
		return employeeService.deleteById(id);
	}
	
	@DeleteMapping("/delete")
	public String deleteAll() {
		return employeeService.deleteAll();
	}
	
	@PutMapping("/updates")
	public String update(@RequestBody Employee employee) {
		return employeeService.update(employee);
	}
	


}
