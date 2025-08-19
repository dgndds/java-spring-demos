package com.xen0n.aop_demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class CloudLogAspect {

    @Before("Pointcuts.getPackagePointcut()")
    public void logToCloudAdvice(){
        System.out.println("======> Executing Cloud log on AddAccount() <======\n");
    }

}
