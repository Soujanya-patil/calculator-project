package com.google.emp_mgmt_sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.emp_mgmt_sys.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
