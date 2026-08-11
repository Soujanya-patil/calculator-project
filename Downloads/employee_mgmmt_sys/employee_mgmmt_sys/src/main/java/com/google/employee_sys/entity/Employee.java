package com.google.employee_sys.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
@Table(name = "employees")
public class Employee {
	@Id
	@Email
	private String email;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String department;
	
	@Positive
	private double salary;
}
