package com.VivianneApi.dto;

import com.VivianneApi.entity.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MemberCreateDto (
        @NotBlank @Size(max = 50) String firstName,
        @NotBlank @Size(max = 50) String lastName,
        @NotNull @Valid Address address,
        @NotBlank @Email @Size(max = 110) String email,
        @Size(max = 10) String phone,
        @NotBlank @Size(max = 12)String dateOfBirth
){
}
