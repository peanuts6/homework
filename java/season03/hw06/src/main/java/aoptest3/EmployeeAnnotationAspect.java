package aoptest3;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by xqy on 17/9/20.
 */
@Aspect
@Component
public class EmployeeAnnotationAspect {
    @Before("@annotation(aoptest3.Loggable)")
    public void myAdvice(){
        System.out.println("Executing myAdvice!!");
    }
}
