package com.lx.animation.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by root on 18-7-22.
 */
@Aspect
public class AspectService {
    @AfterReturning(value = "execution(* com.lx.animation.config.BeanClass+.*(..))")
    public void getError(){
        System.out.println("ss");
    }


}
