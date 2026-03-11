package com.VivianneApi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberDto (
       Long id, String firstName, String lastName, String address, String email, String phone, int dateOfBirth
){
}
