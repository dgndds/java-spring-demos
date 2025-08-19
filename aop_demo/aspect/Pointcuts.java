package com.xen0n.aop_demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Pointcuts {
    @Pointcut("execution(* com.xen0n.aop_demo.dao.*.*(..))")
    public void packagePointcut() {}

    @Pointcut("execution(* com.xen0n.aop_demo.dao.*.get*(..))")
    public void getMethodPointcut() {}

    @Pointcut("execution(* com.xen0n.aop_demo.dao.*.set*(..))")
    public void setMethodPointcut() {}

    @Pointcut("packagePointcut() && !(getMethodPointcut() || setMethodPointcut())")
    public void getPackagePointcut() {}
}
