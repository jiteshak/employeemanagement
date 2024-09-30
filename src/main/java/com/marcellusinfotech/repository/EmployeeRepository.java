package com.marcellusinfotech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcellusinfotech.entity.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, String>{

	Optional<Employee> findByEmployeeId(String employeeId);
	
}
