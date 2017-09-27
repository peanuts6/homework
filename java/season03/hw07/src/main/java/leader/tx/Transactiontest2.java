package leader.tx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by xqy on 17/9/27.
 */
public class Transactiontest2 {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(TransactionConfig2.class);
        EmployeeService employeeService = ctx.getBean("employeeService", EmployeeService.class);
        List<Employee> list = employeeService.queryEmployees("");
        int empid = 0;
        for(int i=0;i<2;i++){
            empid = Math.toIntExact(Math.round(Math.random() * 100));
            employeeService.createEmployee(new Employee(empid,"CPP-"+empid,33,empid*1000));
        }
        employeeService.queryEmployees("CPP");
        employeeService.updateEmployee(16,25000);
        employeeService.queryEmployees("CPP");
        employeeService.deleteEmployee(empid);
        employeeService.queryEmployees("CPP");
        employeeService.deleteEmployee(1);
    }

}
