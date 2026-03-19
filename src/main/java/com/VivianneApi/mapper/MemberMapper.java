package com.VivianneApi.mapper;

import com.VivianneApi.dto.AdminDto;
import com.VivianneApi.dto.MemberCreateDto;
import com.VivianneApi.dto.MemberDto;
import com.VivianneApi.entity.Member;
import com.VivianneApi.security.AppUser;

public final class MemberMapper {
    private MemberMapper() {}

    public static MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getFirstName(), member.getLastName(),
                member.getAddress(), member.getEmail(), member.getPhone());
    }
    //ha en till för admin doDto admin
    public static AdminDto toDtoAdmin(AppUser appUser) {
        return new AdminDto(appUser.getId(), appUser.getMember().getFirstName(), appUser.getMember().getLastName(), appUser.getMember().getAddress(),
               appUser.getMember().getEmail(), appUser.getMember().getPhone(), appUser.getMember().getDateOfBirth());
    }
    public static Member fromCreate(MemberCreateDto dto) {
        return new Member(dto.firstName(), dto.lastName(), dto.address(), dto.email(), dto.phone(), dto.dateOfBirth());
    }
}
