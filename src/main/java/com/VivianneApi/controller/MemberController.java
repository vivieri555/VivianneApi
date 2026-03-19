package com.VivianneApi.controller;

import com.VivianneApi.dto.MemberCreateDto;
import com.VivianneApi.dto.MemberDto;
import com.VivianneApi.dto.MemberUpdateDto;
import com.VivianneApi.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/mypages/members")
public class MemberController {

private final MemberService memberService;

public MemberController(MemberService memberService) {
    this.memberService = memberService; }
@GetMapping
    public List<MemberDto> list() { return memberService.findAll(); }

    @GetMapping("/{id}")
    public MemberDto get(@PathVariable Long id) { return memberService.findById(id); }

    @PutMapping("/{id}")
    public MemberDto update(@PathVariable Long id, @RequestBody @Valid MemberUpdateDto dto) {
    return memberService.update(dto, id);
    }
}
