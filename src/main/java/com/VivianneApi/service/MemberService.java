package com.VivianneApi.service;

import com.VivianneApi.dto.MemberCreateDto;
import com.VivianneApi.dto.MemberDto;
import com.VivianneApi.dto.MemberWithAccountCreateDto;
import com.VivianneApi.entity.Member;
import com.VivianneApi.exception.MemberNotFoundException;
import com.VivianneApi.mapper.MemberMapper;
import com.VivianneApi.repository.AppUserRepository;
import com.VivianneApi.repository.MemberRepository;
import com.VivianneApi.security.AppUser;
import com.VivianneApi.security.Role;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.stream;

@Service
public class MemberService implements MemberServiceInterface {

    private final MemberRepository memberRepo;
    private final AppUserRepository appUserRepo;
    private final PasswordEncoder encoder;

    public MemberService(MemberRepository memberRepo,
                         AppUserRepository appUserRepo, PasswordEncoder encoder) {
        this.memberRepo = memberRepo;
        this.appUserRepo = appUserRepo;
        this.encoder = encoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberDto> findAll() {
        return memberRepo.findAll()
            .stream()
                    .map(MemberMapper::toDto)
                    .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDto findById(Long id) {
        return memberRepo.findById(id)
                .map(MemberMapper::toDto)
                .orElseThrow(()-> new MemberNotFoundException(id));
    }

    @Override
    @Transactional
    public MemberDto create(MemberCreateDto memberDto) {
        Member entity = MemberMapper.fromCreate(memberDto);
        Member saved = memberRepo.save(entity);
        return MemberMapper.toDto(saved);
    }

    @Override
    @Transactional
    public MemberDto update(MemberDto memberDto, Long id) {
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

        return MemberMapper.toDto(member);
    }

    @Override
    public void delete(Long id) {
if(!memberRepo.existsById(id)) {
    throw new MemberNotFoundException(id);
}
memberRepo.deleteById(id);
    }

    @Override
    @Transactional
    public MemberDto createWithAccount(MemberWithAccountCreateDto memberDto) {
        if (memberRepo.existsByEmail(memberDto.email())) {
            throw new IllegalArgumentException("Användarnamnet med emailen är upptaget");
            if(appUserRepo.existsByUsername(memberDto.username())) {
                throw new IllegalArgumentException("Användarnamnet är upptaget");
            }
Member member = new Member(memberDto.firstName(), memberDto.lastName(), memberDto.address()
,memberDto.email(), memberDto.phone(), memberDto.dateOfBirth());
            memberRepo.save(member);
            AppUser appUser = new AppUser(memberDto.username(), encoder.encode(memberDto.password()),
                    java.util.Set.of(Role.USER), member);
            appUserRepo.save(appUser);
        }
    }

//    public List<Member> findMembers() {
//        return memberRepo.findMembers(
//                "Tomas",
//                "Wigell"
//        );
//    }
}
