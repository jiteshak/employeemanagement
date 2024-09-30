package com.marcellusinfotech.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

	@NotBlank(message = "Name must not be empty")
	private String name;

	@NotBlank(message = "Username must not be empty")
	@Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long")
	private String username;

	@NotBlank(message = "Email must not be empty")
	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Password must not be empty")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;
}
