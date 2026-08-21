package com.google.employee_sys.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.employee_sys.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	Optional<User> findByEmail(String email);
	Optional<User> deleteByEmail(String email);
}
