package com.a.sncf.member.controller;

import com.a.sncf.common.response.CommonResponse;
import com.a.sncf.member.request.MemberRegisterRequest;
import com.a.sncf.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResponse<Integer> count() {
        Integer count = memberService.count();
        CommonResponse<Integer> response = new CommonResponse<>(count);
        response.setMessage("Member count retrieved successfully");
        return response;
    }

    @PostMapping("/register")
    public CommonResponse<Long> register(MemberRegisterRequest memberRegisterRequest) {
        long register = memberService.register(memberRegisterRequest);
        CommonResponse<Long> response = new CommonResponse<>(register);
        response.setMessage("Member registered successfully");
        return response;
    }
}
