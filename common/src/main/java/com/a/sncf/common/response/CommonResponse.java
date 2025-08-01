package com.a.sncf.common.response;

public class CommonResponse<T> {
    private Boolean success = true;
    private String message;
    private T content;

    public CommonResponse(T content) {
        this.content = content;
    }

    public CommonResponse() {
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
