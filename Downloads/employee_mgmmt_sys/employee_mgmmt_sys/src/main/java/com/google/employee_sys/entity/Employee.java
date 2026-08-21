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
    @Email(message = "Enter valid email id")
    @NotBlank(message = "It cannot be null, empty, or spaces")
    private String email;

    @NotBlank(message = "It cannot be null, empty, or spaces")
    private String name;

    @Positive(message = "Salary should be greater than 0")
//    @PositiveOrZero  accepts 0
    private double salary;

    @NotBlank(message = "It cannot be null, empty, or spaces")
    private String department;
}
