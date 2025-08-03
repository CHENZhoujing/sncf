package com.a.sncf.common.exception;

public enum BusinessExceptionEnum {
    MEMBER_MOBILE_EXIST("Member mobile already exists");

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
