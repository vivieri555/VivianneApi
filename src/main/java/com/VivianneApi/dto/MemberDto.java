package com.VivianneApi.dto;


public record MemberDto (
       Long id, String firstName, String lastName, String address, String email, String phone, int dateOfBirth
){
}
