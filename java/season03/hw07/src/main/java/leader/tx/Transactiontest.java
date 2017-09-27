package leader.tx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xqy on 17/9/26.
 */
public class Transactiontest {
    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(TransactionConfig.class);
        System.out.println("......context is created...");
        IPersonService personService1 = ctx.getBean("personService1",
                IPersonService.class);
        List<Person> persons = (List<Person>) personService1.queryAll();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
//        for(int i=0;i<5;i++) {
//            int finalI = i;
//            executorService.execute(() -> {
//                List<Person> persons = (List<Person>) personService1.queryAll();
//            });
//        }
//
        System.out.println();
//        List<Person> persons2 = (List<Person>) personService1.queryPerson("Java");
//        System.out.println();
//        PersonService2 personService2 = (PersonService2) ctx.getBean("personService2");
//        personService2.updatePerson(1,"SZ-"+(Math.round(Math.random()*100)),"China");
//        for(int i=0;i<4;i++) {
//            int finalI = i;
//            executorService.execute(() -> {
//                personService2.updatePerson(2,"SZ-"+(Math.round(Math.random()*100)),"China");
//            });
//        }
        executorService.shutdown();
    }
}
