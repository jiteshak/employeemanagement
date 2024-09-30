package com.marcellusinfotech.service;

import java.util.List;

import com.marcellusinfotech.entity.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto registerEmployee(EmployeeDto employeeDto);
	
	EmployeeDto findByEmployeeId(String employeeId);
	
	List<EmployeeDto> findAllEmployees();
	
	EmployeeDto updateEmployee(String employeeId, EmployeeDto employeeDto);
	
	boolean deleteEmployee(String employeeId);
}
