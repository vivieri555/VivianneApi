package com.VivianneApi.dto;

import com.VivianneApi.entity.Address;

public record AdminDto(Long id, String firstName, String lastName, Address address, String email, String phone, String dateOfBirth) {
}
