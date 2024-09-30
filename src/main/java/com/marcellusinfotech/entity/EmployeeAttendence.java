package com.marcellusinfotech.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee_attendence_info")
public class EmployeeAttendence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer attendenceId;
	private LocalDate attendenceDate;
	private boolean attendenceMorning;
	private boolean attendenceNoon;
}

