package com.VivianneApi.controller;

import com.VivianneApi.dto.*;
import com.VivianneApi.service.UserAdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/members")
public class AdminController {

    private final UserAdminService userAdminService;
    public AdminController(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AdminDto> list() { return userAdminService.findAllForAdmin(); }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AdminDto findListById(@PathVariable Long id) { return userAdminService.findById(id); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AdminDto update(@PathVariable Long id, @RequestBody @Valid MemberUpdateDto dto) {
    return userAdminService.update(dto, id);
    }
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminDto> updateMember(@PathVariable Long id, @RequestBody MemberUpdateDto dto) {
        AdminDto updated = userAdminService.updateMember(id, dto);
        return ResponseEntity.ok(updated);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MemberDto> create(@RequestBody @Valid MemberCreateDto dto) {
        MemberDto saved = userAdminService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.id())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userAdminService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
