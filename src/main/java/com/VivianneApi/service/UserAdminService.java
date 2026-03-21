package com.VivianneApi.service;

import com.VivianneApi.dto.*;
import com.VivianneApi.entity.Member;
import com.VivianneApi.exception.EmailAlreadyExists;
import com.VivianneApi.exception.MemberNotFoundException;
import com.VivianneApi.mapper.MemberMapper;
import com.VivianneApi.repository.AppUserRepository;
import com.VivianneApi.repository.MemberRepository;
import com.VivianneApi.security.AppUser;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAdminService {

    private final AppUserRepository appUserRepo;
    private final MemberRepository memberRepo;

    public UserAdminService(AppUserRepository appUserRepo, MemberRepository memberRepo) {
        this.appUserRepo = appUserRepo;
        this.memberRepo = memberRepo;
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public AdminDto updateMember(Long id, MemberUpdateDto dto) {
        var user = appUserRepo.findById(id)
        .orElseThrow(() -> new MemberNotFoundException(id));
       if(dto.firstName() != null) {
           user.getMember().setFirstName(dto.firstName());
       }
       if(dto.lastName() != null) {
           user.getMember().setLastName(dto.lastName());
       }
       if(dto.email() != null) {
           user.getMember().setEmail(dto.email());
       }
       if(dto.address() != null) {
           user.getMember().setAddress(dto.address());
       }
       if(dto.phone() != null) {
           user.getMember().setPhone(dto.phone());
       }
        AppUser savedUser = appUserRepo.save(user);
        return MemberMapper.toDtoAdmin(savedUser);
    }

    //Göra om lista medlemmar
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public List<AdminDto> findAllForAdmin() {
        return appUserRepo.findAll()
                .stream()
                .filter(user -> user.getMember() != null)
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
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public AdminDto update(MemberUpdateDto dto, Long id) {
        String currentUser = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        AppUser owner = appUserRepo.findById(id)
                .orElseThrow(() -> new AccessDeniedException("Ingen ägare"));
        //om det ej matchar m username
//        if(!owner.getUsername().equals(currentUser)) {
//            throw new AccessDeniedException("Får endast uppdatera dina egna uppgifter");
//        }
        AppUser member = appUserRepo.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        member.getMember().setFirstName(dto.firstName());
        member.getMember().setLastName(dto.lastName());
        member.getMember().setEmail(dto.email());
        member.getMember().setPhone(dto.phone());
        member.getMember().setAddress(dto.address());
        member.getMember().setDateOfBirth(dto.dateOfBirth());

        return MemberMapper.toDtoAdmin(member);
    }
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public MemberDto create(MemberCreateDto memberDto) {
//        if(appUserRepo.existsByEmail(memberDto.email())) {
//            throw new EmailAlreadyExists(memberDto.email());
//        }

        Member entity = MemberMapper.fromCreate(memberDto);
        Member saved = memberRepo.save(entity);

        return MemberMapper.toDto(saved);
    }
  @Transactional
  @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        if(!appUserRepo.existsById(id)) {
            throw new MemberNotFoundException(id);
        }
        appUserRepo.deleteById(id);
    }
}
