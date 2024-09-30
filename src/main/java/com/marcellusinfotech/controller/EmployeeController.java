package com.marcellusinfotech.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcellusinfotech.entity.dto.EmployeeDto;
import com.marcellusinfotech.exception.ResourceNotFoundException;
import com.marcellusinfotech.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // Endpoint to register a new employee
    @PostMapping("/employee")
    public ResponseEntity<EmployeeDto> registerEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        try {
            EmployeeDto createdEmployee = employeeService.registerEmployee(employeeDto);
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get an employee by ID
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable String employeeId) {
        EmployeeDto employeeDto = employeeService.findByEmployeeId(employeeId);
        if (employeeDto == null) {
            throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
        }
        return ResponseEntity.ok(employeeDto);
    }

    // Endpoint to get all employees
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.findAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Endpoint to update an existing employee
    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable String employeeId,
                                                      @Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(employeeId, employeeDto);
        if (updatedEmployeeDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(updatedEmployeeDto);
    }

    // Endpoint to delete an employee
    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId) {
        boolean isDeleted = employeeService.deleteEmployee(employeeId);
        if (isDeleted) {
            return ResponseEntity.ok("Employee deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID: " + employeeId);
        }
    }
}
