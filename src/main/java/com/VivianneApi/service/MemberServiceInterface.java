package com.VivianneApi.service;

import com.VivianneApi.dto.MemberCreateDto;
import com.VivianneApi.dto.MemberDto;
import com.VivianneApi.dto.MemberWithAccountCreateDto;

import java.util.List;

public interface MemberServiceInterface {
    List<MemberDto> findAll();
    MemberDto findById(Long id);
    MemberDto create(MemberCreateDto memberDto);
    MemberDto update(MemberDto memberDto, Long id);
    void delete(Long id);
    MemberDto createWithAccount(MemberWithAccountCreateDto memberDto);
}
