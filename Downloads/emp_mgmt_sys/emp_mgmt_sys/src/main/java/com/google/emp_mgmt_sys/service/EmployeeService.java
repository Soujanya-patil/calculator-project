package com.google.emp_mgmt_sys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.google.emp_mgmt_sys.entity.Employee;
import com.google.emp_mgmt_sys.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	public Object getbyid(@PathVariable int id) {
		Optional<Employee> oe= employeeRepository.findById(id);
		if(oe.isPresent()) {
			return oe.get();
		}
		else {
			return "no dat afound with id:"+id;
		}
	}
	public Object getAll() {
		List<Employee> l= employeeRepository.findAll();
		if(l.isEmpty()) {
			return "data not present";
		}
		else {
			return l;
		}
	}
	public String insert(Employee employee) {
		employeeRepository.save(employee);
		return "data inserted";
	}
	public String deleteById(int id) {
		Optional<Employee> oe = employeeRepository.findById(id);
		if(oe.isPresent()) {
			employeeRepository.deleteById(id);
			return "data deleted based on id:"+id;
		}
		else {
			return "no data for this id ";
		}
	}
	public String deleteAll() {
		List<Employee> l = employeeRepository.findAll();
		if(l.isEmpty()) {
			return "no data exist";
		}
		else {
			employeeRepository.deleteAll();
			return "data deleted successfully";
		}
	}
	public String update(Employee employee) {
		employeeRepository.save(employee);
		return "data updated successfully";
	}
	


}
