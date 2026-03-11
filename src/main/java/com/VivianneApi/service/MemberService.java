package com.VivianneApi.service;

import com.VivianneApi.entity.Member;
import com.VivianneApi.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepo;

    public MemberService(MemberRepository memberRepo) {
        this.memberRepo = memberRepo;
    }

    List<Member> members = memberRepo.findByEmail();

//    public List<Member> findMembers() {
//        return memberRepo.findMembers(
//                "Tomas",
//                "Wigell"
//        );
//    }
}
