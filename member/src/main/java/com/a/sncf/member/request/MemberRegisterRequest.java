package com.a.sncf.member.request;

import jakarta.validation.constraints.NotBlank;

public class MemberRegisterRequest {

    @NotBlank(message = "Mobile number cannot be blank")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberRegisterRequest{" +
                "mobile='" + mobile + '\'' +
                '}';
    }

}
