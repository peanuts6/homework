/**
 * Created by leader on 17/8/27.
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.*;
import leader.bean.HelloComponent;

import java.util.*;

public class Main1 {
    public static void main(String[] args) throws InterruptedException{
        System.out.println(new Date() + " begin");
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("application.xml");
        //ApplicationContext ctx =
        // new FileSystemXmlApplicationContext("hw03/src/Main1/resources/application.xml");

        HelloComponent helloWorldBean = (HelloComponent) ctx.getBean("helloBean");
        System.out.println(new Date()+" get domain "+helloWorldBean);

        helloWorldBean.sayHello();
    }
}
