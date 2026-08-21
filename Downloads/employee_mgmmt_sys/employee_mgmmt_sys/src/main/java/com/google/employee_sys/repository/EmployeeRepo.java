package com.google.employee_sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.employee_sys.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, String>{

}
