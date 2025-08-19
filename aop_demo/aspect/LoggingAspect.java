package com.xen0n.aop_demo.aspect;

import com.xen0n.aop_demo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

    @Before("Pointcuts.getPackagePointcut()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("======> Executing Before Advice on AddAccount() <======\n");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + signature + "\n");

        Object[] args = joinPoint.getArgs();

        for(Object arg: args){
            if(arg instanceof Account){
                System.out.println("Arg Name " + ((Account) arg).getName() + "\n" );
            }
        }
    }

    @AfterReturning(pointcut = "Pointcuts.packagePointcut()",returning = "result")
    public void afterAddAccountAdvice(JoinPoint joinPoint, List<Account> result) {
        System.out.println("======> Executing AfterReturning() <======\n");
        System.out.println("Method: " + joinPoint.getSignature() + "\n");
        System.out.println("Result: " + result);
        
        convertAccountNamesUpperCase(result);

        System.out.println("Result: " + result);

    }

    @AfterThrowing(
            pointcut = "execution(* com.xen0n.aop_demo.dao.AccountDAO.getAccounts(..))",
            throwing = "exp"
    )
    public void afterThrowingGetAccountAdvice(JoinPoint joinPoint, Exception exp) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("======> Executing AfterThrowing() for " + methodName + " <======\n");

        System.out.println("Exception from advice is: " + exp + "\n");
    }

    @After("execution(* com.xen0n.aop_demo.dao.AccountDAO.getAccounts(..))")
    public void afterFinallyGetAccountAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("\n======> Executing AfterFinally() for " + methodName + " <======\n");
    }

    @Around("execution(* com.xen0n.aop_demo.service.*.getTrafficFortune(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("\n======> Executing Around() for " + methodName + " <======\n");

        long start = System.currentTimeMillis();
        Object result = null;

        try{
            result = joinPoint.proceed();
        }catch (Exception e){
            System.out.println("Exception: " + e + "\n");
            result = "Unable to retrieve results for the traffic fortune";

        }

        long end = System.currentTimeMillis();

        System.out.println("\n======> Executing Around() time: " + (end - start) + "ms\n");

        return result;
    }

    private void convertAccountNamesUpperCase(List<Account> result) {

        for (Account account : result){
            String accountName = account.getName().toUpperCase();
            account.setName(accountName);
        }
    }

}
