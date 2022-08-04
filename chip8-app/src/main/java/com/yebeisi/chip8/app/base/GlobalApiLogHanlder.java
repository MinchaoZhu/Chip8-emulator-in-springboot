package com.yebeisi.chip8.app.base;

import com.yebeisi.chip8.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class GlobalApiLogHanlder {

    @Autowired
    private HttpServletRequest request;

    @Around(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping) || @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object controllerLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("method: {}, url: {}, query: {}", request.getMethod(), request.getRequestURI(), request.getQueryString());
        return joinPoint.proceed();
    }

}
