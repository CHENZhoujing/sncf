package com.a.sncf.member.controller;

import com.a.sncf.common.response.CommonResponse;
import com.a.sncf.member.request.MemberLoginRequest;
import com.a.sncf.member.request.MemberRegisterRequest;
import com.a.sncf.member.request.MemberSendCodeRequest;
import com.a.sncf.member.response.MemberLoginResponse;
import com.a.sncf.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
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
    public CommonResponse<Long> register(@Valid MemberRegisterRequest request) {
        long register = memberService.register(request);
        CommonResponse<Long> response = new CommonResponse<>(register);
        response.setMessage("Member registered successfully");
        return response;
    }

    @PostMapping("/send-code")
    public CommonResponse<Void> sendCode(@Valid MemberSendCodeRequest request) {
        memberService.sendCode(request);
        return new CommonResponse<>(null);
    }

    @PostMapping("/login")
    public CommonResponse<MemberLoginResponse> login(@Valid MemberLoginRequest request) {
        MemberLoginResponse response = memberService.login(request);

        return new CommonResponse<>(response);
    }
}
