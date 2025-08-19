package com.xen0n.aop_demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class AnalyticsAspect {
    @Before("Pointcuts.getPackagePointcut()")
    public void analyticsAdvice(){
        System.out.println("======> Executing Analytics on AddAccount() <======\n");
    }
}
