package com.a.sncf.common.controller;

import com.a.sncf.common.exception.BusinessException;
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
    public CommonResponse<Void> exceptionHandler(Exception e){
        LOG.error("System Exception occurred, please contact the administrator");
        CommonResponse<Void> commonResponse = new CommonResponse<>();
        commonResponse.setSuccess(false);
        commonResponse.setMessage("An unexpected error occurred: " + e.getMessage());
        return commonResponse;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonResponse<Void> exceptionHandler(BusinessException e) {
        LOG.error("Business Exception occurred: ", e);
        CommonResponse<Void> commonResponse = new CommonResponse<>();
        commonResponse.setSuccess(false);
        commonResponse.setMessage(e.getBusinessExceptionEnum().getDescription());
        return commonResponse;
    }
}
