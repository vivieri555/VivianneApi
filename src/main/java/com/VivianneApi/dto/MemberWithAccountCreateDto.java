package com.VivianneApi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

public record MemberWithAccountCreateDto(
        @NotBlank @Size(max = 50) String firstName,
        @NotBlank @Size(max = 50) String lastName,
        @NotBlank @Size(max = 100) String address,
        @NotBlank @Email @Size(max = 110) String email,
        @Size(max = 10) String phone,
        @NotBlank @Size(max = 12) String dateOfBirth,
        @NotBlank @Size(max = 100) String username,
        @NotBlank @Size(max = 80) String password
) {
}
