package com.yebeisi.chip8.core.advice;

import com.yebeisi.chip8.core.annotation.ArrayLen;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class ArrayLenAdvice {

    @Around(value = "@annotation(arrayLen)")
    public Object checkArrayLen(ProceedingJoinPoint joinPoint, ArrayLen arrayLen) throws Throwable {
        List<Integer> lenLimits = Arrays.stream(arrayLen.limits()).boxed().collect(Collectors.toList());
        if( -1 != arrayLen.value()) {
            lenLimits.add(arrayLen.value());
        }

        Object[] args = joinPoint.getArgs();
        if(!isArray(args[0])) {
            throw new RuntimeException("[array check] object: " + args[0] + " is not an array");
        }

        Integer size = Array.getLength(args[0]);
        if(!lenLimits.contains(size)) {
            throw new RuntimeException("[array check] length: " + size + "  of object: " + args[0] + " is not allowed");
        }
        return joinPoint.proceed();
    }

    public static boolean isArray(Object obj)
    {
        return obj!=null && obj.getClass().isArray();
    }

}
