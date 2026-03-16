package com.VivianneApi.controller;

import com.VivianneApi.service.MemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MemberController {

private final MemberService memberService;

public MemberController(MemberService memberService) {
    this.memberService = memberService; }


}
