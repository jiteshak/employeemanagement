package com.marcellusinfotech.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.marcellusinfotech.entity.enums.BloodGroup;
import com.marcellusinfotech.entity.enums.Degree;
import com.marcellusinfotech.entity.enums.EmployeeStatus;
import com.marcellusinfotech.entity.enums.Gender;
import com.marcellusinfotech.entity.enums.Nationality;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_primary_info")
public class Employee {

    @Id
    private String employeeId;
    private String employeeName;
    private String employeeEmail;
    private LocalDate employeeDateOfBirth;
    private LocalDate employeeDateOfJoining;
    private String employeeDesignation;
    private LocalDateTime employeeLastLogin;

    // Enums
    @Enumerated(EnumType.STRING)
    private Nationality employeeNationality;
    @Enumerated(EnumType.STRING)
    private Gender employeeGender;
    @Enumerated(EnumType.STRING)
    private BloodGroup employeeBloodGroup;
    @Enumerated(EnumType.STRING)
    private Degree employeeDegree;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Address> addressList = new HashSet<>(); 

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "batch_fk")
    private Batch inBatch;

    @OneToMany(cascade = CascadeType.ALL)
    private List<EmployeeAttendence> employeeAttendence;
}
