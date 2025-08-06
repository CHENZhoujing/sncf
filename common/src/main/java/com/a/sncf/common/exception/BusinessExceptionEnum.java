package com.a.sncf.common.exception;

public enum BusinessExceptionEnum {
    MEMBER_MOBILE_EXIST("Member mobile already registered"),
    MEMBER_MOBILE_NOT_EXIST("Get validation first"),
    MEMBER_MOBILE_CODE_ERROR("Incorrect verification code");

    private String description;

    BusinessExceptionEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BusinessExceptionEnum{" +
                "description='" + description + '\'' +
                '}';
    }

}
