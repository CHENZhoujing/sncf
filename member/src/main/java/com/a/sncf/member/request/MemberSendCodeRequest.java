package com.a.sncf.member.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MemberSendCodeRequest {

    @NotBlank
    @Pattern(regexp = "^1\\d{10}$", message = "Invalid mobile number format")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberSendCodeRequest{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
