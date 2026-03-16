package com.VivianneApi.dto;

import com.VivianneApi.security.Role;

import java.util.Set;

public record UserRolesDto(
        String username, Set<Role> roles
) {
}
