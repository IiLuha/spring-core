package com.itdev.aop;

import com.itdev.exception.LoginAlreadyExistException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserExceptionHandlerAspect {

    @Pointcut("target(com.itdev.controller.UserController)")
    public void isUserController() {
    }

    @Pointcut("com.itdev.aop.CommonPointcuts.isControllerLayer() && isUserController() && execution(public * create(*))")
    public void isCreateControllerMethod() {
    }

    @Around("isCreateControllerMethod()")
    public Object addCatchExceptionToCreateMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (LoginAlreadyExistException e) {
            return e.getMessage() +
                    "Try again with another login.";
        }
    }
}
