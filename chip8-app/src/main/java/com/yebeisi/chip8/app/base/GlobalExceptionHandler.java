package com.yebeisi.chip8.app.base;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public BaseResponse handleException(Exception e) {
        e.printStackTrace();
        return BaseResponse.newFailResponse().errorMsg(e.getMessage()).build();
    }

}
