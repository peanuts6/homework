import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import leader.bean.MyStoreService;

public class MainEnv1 {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application2.xml");
        MyStoreService storService=ctx.getBean(MyStoreService.class);
        System.out.println("MyStoreService  "+storService);

    }
}
