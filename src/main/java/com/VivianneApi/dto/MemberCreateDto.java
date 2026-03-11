package com.VivianneApi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberCreateDto (
        @NotBlank @Size(max = 50) String firstName,
        @NotBlank @Size(max = 50) String lastName,
        @NotBlank @Size(max = 100) String address,
        @NotBlank @Email @Size(max = 110) String email,
        @Size(max = 10) String phone,
        @NotBlank @Size(min = 6, max = 12) int dateOfBirth
){
}
