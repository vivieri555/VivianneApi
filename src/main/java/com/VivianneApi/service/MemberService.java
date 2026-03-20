package com.VivianneApi.service;

import com.VivianneApi.dto.*;
import com.VivianneApi.entity.Member;
import com.VivianneApi.exception.MemberNotFoundException;
import com.VivianneApi.mapper.MemberMapper;
import com.VivianneApi.repository.AppUserRepository;
import com.VivianneApi.repository.MemberRepository;
import com.VivianneApi.security.AppUser;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MemberService implements MemberServiceInterface {

    private final MemberRepository memberRepo;
    private final AppUserRepository appUserRepo;

    public MemberService(MemberRepository memberRepo,
                         AppUserRepository appUserRepo) {
        this.memberRepo = memberRepo;
        this.appUserRepo = appUserRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberListDto> findAll() {
        return memberRepo.findMembers()
            .stream()
                    .map(MemberMapper::toDtoList)
                    .toList();
    }

    @Override
    @Transactional
    public MemberDto update(MemberUpdateDto memberDto, Long id) {
        String currentUser = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        AppUser owner = appUserRepo.findById(id)
                .orElseThrow(() -> new AccessDeniedException("Ingen ägare"));
        //om det ej matchar m username
        if(!owner.getUsername().equals(currentUser)) {
        throw new AccessDeniedException("Får endast uppdatera dina egna uppgifter");
        }
        Member member = memberRepo.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        member.setFirstName(memberDto.firstName());
        member.setLastName(memberDto.lastName());
        member.setEmail(memberDto.email());
        member.setPhone(memberDto.phone());
        member.setAddress(memberDto.address());
        member.setDateOfBirth(memberDto.dateOfBirth());

        return MemberMapper.toDto(member);
    }
}
