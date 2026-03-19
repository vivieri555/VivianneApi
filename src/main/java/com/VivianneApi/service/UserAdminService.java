package com.VivianneApi.service;

import com.VivianneApi.dto.AdminDto;
import com.VivianneApi.dto.MemberDto;
import com.VivianneApi.dto.UpdateRolesDto;
import com.VivianneApi.dto.UserRolesDto;
import com.VivianneApi.exception.MemberNotFoundException;
import com.VivianneApi.mapper.MemberMapper;
import com.VivianneApi.repository.AppUserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAdminService {

    private final AppUserRepository appUserRepo;

    public UserAdminService(AppUserRepository appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void updateRoles(String username, UpdateRolesDto dto) {
        var user = appUserRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Inte hittat användaren: " + username));
        user.setRoles(dto.roles());
        appUserRepo.save(user);
    }
    public UserRolesDto getRoles(String username) {
        var user = appUserRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserRolesDto(user.getUsername(), user.getRoles());
    }
    //Göra om lista medlemmar
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public List<AdminDto> findAllForAdmin() {
        return appUserRepo.findAll()
                .stream()
                .map(MemberMapper::toDtoAdmin)
                .toList();
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public AdminDto findById(Long id) {
        return appUserRepo.findById(id)
                .map(MemberMapper::toDtoAdmin)
                .orElseThrow(()-> new MemberNotFoundException(id));
    }
}
