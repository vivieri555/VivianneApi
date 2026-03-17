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
@RequestMapping("/api/member")
public class MemberController {

private final MemberService memberService;

public MemberController(MemberService memberService) {
    this.memberService = memberService; }
@GetMapping
    public List<MemberDto> list() { return memberService.findAll(); }

    @GetMapping("/{id}")
    public MemberDto get(@PathVariable Long id) { return memberService.findById(id); }

    @PostMapping
    public ResponseEntity<MemberDto> create(@RequestBody @Valid MemberCreateDto dto) {
    MemberDto saved = memberService.create(dto);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(saved.id())
            .toUri();
    return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public MemberDto update(@PathVariable Long id, @RequestBody @Valid MemberUpdateDto dto) {
    return memberService.update(dto, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    memberService.delete(id);
    return ResponseEntity.noContent().build();
    }
}
