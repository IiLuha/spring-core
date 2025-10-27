package com.itdev.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommonPointcuts {

    @Pointcut("within(com.itdev.controller.*Controller)")
    public void isControllerLayer() {
    }
}
