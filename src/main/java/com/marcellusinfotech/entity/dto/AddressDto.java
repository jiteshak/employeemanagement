package com.marcellusinfotech.entity.dto;

import com.marcellusinfotech.entity.enums.AddressType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class AddressDto {

    private Integer addressId;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @NotBlank(message = "State cannot be blank")
    private String state;

    @NotNull(message = "Pincode cannot be null")
    @Pattern(regexp = "\\d{6}", message = "Pincode must be exactly 6 digits")
    private Long pincode;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Address type cannot be null")
    private AddressType addressType;

}
