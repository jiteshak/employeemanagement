package com.marcellusinfotech.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotBlank(message = "Username or email must not be empty")
    private String usernameOrEmail;

    @NotBlank(message = "Password must not be empty")
    private String password;

    @Email(message = "Email should be valid if provided")
    private String email; // Optional: Only if you want to separate email as a field
}
