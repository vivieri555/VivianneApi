package com.VivianneApi.mapper;

import com.VivianneApi.dto.MemberCreateDto;
import com.VivianneApi.dto.MemberDto;
import com.VivianneApi.entity.Member;

public final class MemberMapper {
    private MemberMapper() {}

    public static MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getFirstName(), member.getLastName(),
                member.getAddress(), member.getEmail(), member.getPhone(), member.getDateOfBirth());
    }
    public static Member fromCreate(MemberCreateDto dto) {
        return new Member(dto.firstName(), dto.lastName(), dto.address(), dto.email(), dto.phone(), dto.dateOfBirth());
    }
}
