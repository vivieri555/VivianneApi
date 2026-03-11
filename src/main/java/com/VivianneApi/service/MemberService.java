package com.VivianneApi.service;

import com.VivianneApi.dto.MemberDto;
import com.VivianneApi.dto.MemberWithAccountCreateDto;
import com.VivianneApi.entity.Member;
import com.VivianneApi.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.stream;

@Service
public class MemberService implements MemberServiceInterface {

    private final MemberRepository memberRepo;

    public MemberService(MemberRepository memberRepo) {
        this.memberRepo = memberRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberDto> findAll() {
        return memberRepo.findAll() {
            .stream()
                    .map(MemberMapper::toDto)
                    .toList();
        }
    }

    @Override
    public MemberDto findById(Long id) {
        return null;
    }

    @Override
    public MemberDto create(MemberDto memberDto) {
        return null;
    }

    @Override
    public MemberDto update(MemberDto memberDto, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public MemberDto createWithAccount(MemberWithAccountCreateDto memberDto) {
        return null;
    }

//    public List<Member> findMembers() {
//        return memberRepo.findMembers(
//                "Tomas",
//                "Wigell"
//        );
//    }
}
