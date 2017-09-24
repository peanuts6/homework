package aoptest3;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by xqy on 17/9/20.
 */
@Aspect
@Component
public class EmployeeAspectPointcut {
    @Before("getNamePointcut()")
    public void loggingAdvice(){
        System.out.println("Executing loggingAdvice on getName()");
    }

    @Before("getNamePointcut()")
    public void secondAdvice(){
        System.out.println("Executing secondAdvice on getName()");
    }

    @Pointcut("execution(public String getName())")
    public void getNamePointcut(){}

    @Before("execution(* aoptest3.*Service.*())")
    public void allServiceMethodsAdvice(){
        System.out.println("Before executing service method");
    }

    @Pointcut("within(aoptest3.*)")
    public void allMethodsPointcut(){}

}
