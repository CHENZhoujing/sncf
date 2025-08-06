package com.a.sncf.member.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MemberLoginRequest {

    @NotBlank(message = "Mobile number cannot be blank")
    @Pattern(regexp = "^1\\d{10}$", message = "Invalid mobile number format")
    private String mobile;
    @NotBlank(message = "Code cannot be blank")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberLoginRequest{" +
                "mobile='" + mobile + '\'' +
                ", code='" + code + '\'' +
                '}';
    }


}
