package aoptest3;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by xqy on 17/9/20.
 */
@Component
@Aspect
public class EmployeeAspect {
    @Before("execution(public String getName())")
    public void getNameAdvice(){
        System.out.println("getName() 之前执行getNameAdvice()");
    }

    @Before("execution(* aoptest3.*Service.get*())")
    public void getAllAdvice(){
        System.out.println("调用Service结尾以get开始的方法");
    }
}
