package com.VivianneApi.controller;

import com.VivianneApi.dto.MemberDto;
import com.VivianneApi.dto.MemberListDto;
import com.VivianneApi.dto.MemberUpdateDto;
import com.VivianneApi.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mypages/members")
public class MemberController {

private final MemberService memberService;

public MemberController(MemberService memberService) {
    this.memberService = memberService; }
@GetMapping
    public List<MemberListDto> list() { return memberService.findAll(); }

    @PutMapping("/{id}")
    public MemberDto update(@PathVariable Long id, @RequestBody @Valid MemberUpdateDto dto) {
    return memberService.update(dto, id);
    }
}
