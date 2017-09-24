package jdbctest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by xqy on 17/9/18.
 */
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans2.xml");
        StudentService studentService = (StudentService)ctx.getBean("studentService");

        System.out.println("------Records creation--------" );
        studentService.create("Zara", 11, 99, 2010, 0);
        studentService.create("Nuha", 20, 97, 2010, 1);
        studentService.create("Ayan", 25, 100, 2011, 2);

        System.out.println("------Listing all the records--------" );
        List<StudentMarks> studentMarks = studentService.queryAll();

        for (StudentMarks record : studentMarks) {
            System.out.print("ID : " + record.getId() );
            System.out.print(", Name : " + record.getName() );
            System.out.print(", Marks : " + record.getMarks());
            System.out.print(", Year : " + record.getYear());
            System.out.println(", Age : " + record.getAge());
        }
    }
}
