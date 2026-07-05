package com.controller.ordermanagement.aspect;

import com.controller.ordermanagement.controller.ItemController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger=LoggerFactory.getLogger(ItemController.class);

    @Before("execution(* com.controller.ordermanagement.controller.ItemController.*(..))")
    public void logBefore(JoinPoint joinPoint){
        logger.info("Before entering :{}",joinPoint.getSignature().getName());
    }
    @After("execution(* com.controller.ordermanagement.controller.ItemController.*(..))")
    public void logAfter(JoinPoint joinPoint){
        logger.info("Returning from method :{}",joinPoint.getSignature().getName());
    }
    @AfterThrowing(
            pointcut = "execution(* com.controller.ordermanagement.service.ItemImpl.*.*(..))"
            ,throwing = "ex"
    )
    public void throwingException(Exception ex){
        logger.info("Exception thrown for :{}",ex.getMessage());
    }
}

//.. represnet the number of parameters
// *  anu return type
//insated of getAllItems(..) if we replace it with * then it would work for all the methods not only getallitems
//first star is for any return type wnd star for any calss 3rd star for any method