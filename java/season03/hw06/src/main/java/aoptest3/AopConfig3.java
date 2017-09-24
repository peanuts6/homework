package aoptest3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by xqy on 17/9/20.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("aoptest3")
public class AopConfig3 {
    public static void main(String[] args){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AopConfig3.class);
        EmployeeService employeeService = ctx.getBean(EmployeeService.class);
        System.out.println(employeeService.getEmployee().getName());
        employeeService.getEmployee().setName("Peanut");
        employeeService.getEmployee().throwException();

    }
}
