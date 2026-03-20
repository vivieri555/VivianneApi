package com.VivianneApi.service;

import com.VivianneApi.dto.*;

import java.util.List;

public interface MemberServiceInterface {
    List<MemberListDto> findAll();
    MemberDto update(MemberUpdateDto memberDto, Long id);
}
