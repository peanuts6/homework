package aoptest3;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by xqy on 17/9/20.
 */
@Aspect
@Component
public class EmployeeAspectJoinPoint {

    @Before("execution(public void *.set*(*))")
    public void loggingAdvice(JoinPoint joinPoint){
        System.out.println("Before running loggingAdvice on method="+joinPoint.toString());

        System.out.println("Agruments Passed=" + Arrays.toString(joinPoint.getArgs()));

    }

    @Before("args(name)")
    public void logStringArguments(String name){
        System.out.println("String argument passed="+name);
    }

}
