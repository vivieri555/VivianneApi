package com.VivianneApi.controller;

import com.VivianneApi.dto.AdminDto;
import com.VivianneApi.dto.MemberDto;
import com.VivianneApi.dto.UpdateRolesDto;
import com.VivianneApi.dto.UserRolesDto;
import com.VivianneApi.repository.AppUserRepository;
import com.VivianneApi.service.UserAdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminController {

    private final UserAdminService userAdminService;
    private final AppUserRepository appUserRepo;
    public AdminController(UserAdminService userAdminService, AppUserRepository appUserRepo) {
        this.userAdminService = userAdminService;
        this.appUserRepo = appUserRepo;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserRolesDto> listAll() {
        return appUserRepo.findAll()
                .stream()
                .map(user -> new UserRolesDto(user.getUsername(), user.getRoles()))
                .toList();
    }
    @GetMapping
    public List<AdminDto> list() { return userAdminService.findAllForAdmin(); }

    @GetMapping("/{id}")
    public AdminDto get(@PathVariable Long id) { return userAdminService.findById(id); }

    @PatchMapping("/{username}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateRoles(@PathVariable String username,
                                            @RequestBody @Valid UpdateRolesDto dto) {
        userAdminService.updateRoles(username, dto);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{username}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserRolesDto> getRoles(@PathVariable String username) {
        return ResponseEntity.ok(userAdminService.getRoles(username));
    }
}
