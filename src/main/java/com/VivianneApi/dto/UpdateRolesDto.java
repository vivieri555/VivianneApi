package com.VivianneApi.dto;

import com.VivianneApi.security.Role;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record UpdateRolesDto(
        @NotEmpty Set<Role> roles
) {
}
