package com.a.sncf.common.controller;

import com.a.sncf.common.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResponse<Void> exceptionHandler(Exception e) throws Exception {
        LOG.error("Exception occurred: ", e);
        CommonResponse<Void> commonResponse = new CommonResponse<>();
        commonResponse.setSuccess(false);
        commonResponse.setMessage(e.getMessage());
        return commonResponse;
    }
}
