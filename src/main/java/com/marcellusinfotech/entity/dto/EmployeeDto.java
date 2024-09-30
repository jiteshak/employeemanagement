package com.marcellusinfotech.entity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.marcellusinfotech.entity.enums.BloodGroup;
import com.marcellusinfotech.entity.enums.Degree;
import com.marcellusinfotech.entity.enums.EmployeeStatus;
import com.marcellusinfotech.entity.enums.Gender;
import com.marcellusinfotech.entity.enums.Nationality;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDto {

    @NotBlank(message = "Employee ID cannot be blank")
    private String employeeId;

    @NotBlank(message = "Employee name cannot be blank")
    @Size(max = 100, message = "Employee name must not exceed 100 characters")
    private String employeeName;

    @NotBlank(message = "Employee email cannot be blank")
    @Email(message = "Employee email must be valid")
    private String employeeEmail;

    @NotNull(message = "Employee date of birth cannot be null")
    private LocalDate employeeDateOfBirth;

    @NotNull(message = "Employee date of joining cannot be null")
    private LocalDate employeeDateOfJoining;

    private LocalDateTime employeeLastLogin;

    @NotBlank(message = "Employee designation cannot be blank")
    private String employeeDesignation;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Employee nationality cannot be null")
    private Nationality employeeNationality;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Employee gender cannot be null")
    private Gender employeeGender;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Employee blood group cannot be null")
    private BloodGroup employeeBloodGroup;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Employee degree cannot be null")
    private Degree employeeDegree;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Employee status cannot be null")
    private EmployeeStatus employeeStatus;

    @NotNull(message = "Address list cannot be null")
    private Set<AddressDto> addressList;

}
