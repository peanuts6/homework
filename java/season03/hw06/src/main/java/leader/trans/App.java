package leader.trans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by xqy on 17/9/20.
 */
@Configuration
@ComponentScan("leader.trans")
@EnableAspectJAutoProxy
public class App {

    public static void main(String[] args)   {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        EmployeeService employeeService = ctx.getBean(EmployeeService.class);

        employeeService.getEmployee(1);
        employeeService.queryEmployees();

        int empid = (int) (Math.random()*100);
        employeeService.createEmployee(new Employee(empid,"employee"+empid));

        employeeService.updateEmployee(empid, (long) (Math.random()*10000));

        employeeService.saveEmployee(new Employee(empid+1,"employee"+empid+1, (int) (Math.random()*100),(long) (Math.random()*10000)));

//        employeeService.deleteEmployee(empid+1);
        System.out.println("--- 增删改查之后，rollback之前的数据 ---");
        employeeService.queryEmployees();

        employeeService.deleteEmployee(0);

        System.out.println("--- 增删改查之后，rollback之后的数据 ---");
        employeeService.queryEmployees();

    }
}


//create table employee(empid int(4),name varchar(50),age int(2),salary int(11),primary key(empid));
//insert into employee(empid,name,age,salary) values(1,"emp001",22,2200);
//insert into employee(empid,name,age,salary) values(2,"emp002",23,2200);
//insert into employee(empid,name,age,salary) values(3,"emp003",25,3200);