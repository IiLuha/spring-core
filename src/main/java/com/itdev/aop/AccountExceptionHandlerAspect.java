package com.itdev.aop;

import com.itdev.exception.AccountNotFoundException;
import com.itdev.exception.DeleteFirstAccountException;
import com.itdev.exception.InsufficientFundsException;
import com.itdev.exception.UserNotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AccountExceptionHandlerAspect {

    @Pointcut("target(com.itdev.controller.AccountController)")
    public void isAccountController() {
    }

    @Pointcut("com.itdev.aop.CommonPointcuts.isControllerLayer() && isAccountController() && execution(public * create(*))")
    public void isCreateControllerMethod() {
    }

    @Pointcut("com.itdev.aop.CommonPointcuts.isControllerLayer() && isAccountController() && execution(public * close(*))")
    public void isCloseControllerMethod() {
    }

    @Pointcut("com.itdev.aop.CommonPointcuts.isControllerLayer() && isAccountController() && execution(public * withdraw(..))")
    public void isWithdrawControllerMethod() {
    }

    @Pointcut("com.itdev.aop.CommonPointcuts.isControllerLayer() && isAccountController() && execution(public * deposit(..))")
    public void isDepositControllerMethod() {
    }

    @Pointcut("com.itdev.aop.CommonPointcuts.isControllerLayer() && isAccountController() && execution(public * transfer(..))")
    public void isTransferControllerMethod() {
    }

    @Around("isCreateControllerMethod()")
    public Object addCatchExceptionToCreateMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return handleUserNotFoundException(joinPoint);
    }

    @Around("isCloseControllerMethod()")
    public Object addCatchExceptionToCloseMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return handleAccountExceptions(joinPoint);
    }

    @Around("isWithdrawControllerMethod()")
    public Object addCatchExceptionToWithdrawMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return handleInsufficientFundsExceptionAfterAccountExceptions(joinPoint);
    }

    @Around("isDepositControllerMethod()")
    public Object addCatchExceptionToDepositMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return handleAccountExceptions(joinPoint);
    }

    @Around("isTransferControllerMethod()")
    public Object addCatchExceptionToTransferMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return handleInsufficientFundsExceptionAfterAccountExceptions(joinPoint);
    }

    private Object handleUserNotFoundException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            return proceedingJoinPoint.proceed();
        } catch (UserNotFoundException e) {
            return e.getMessage() +
                    System.lineSeparator() +
                    "Try again with another user id.";
        }
    }

    private Object handleAccountExceptions(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            return proceedingJoinPoint.proceed();
        } catch (AccountNotFoundException | DeleteFirstAccountException e) {
            return e.getMessage() +
                    System.lineSeparator() +
                    "Try again with another account id.";
        }
    }

    private Object handleInsufficientFundsExceptionAfterAccountExceptions(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            return handleAccountExceptions(proceedingJoinPoint);
        } catch (InsufficientFundsException e) {
            return e.getMessage() +
                    System.lineSeparator() +
                    "There are %s funds available.".formatted(e.getAvailableFunds());
        }
    }
}
