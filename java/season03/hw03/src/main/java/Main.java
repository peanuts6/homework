/**
 * Created by xqy on 17/8/27.
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.*;
import xqy.bean.HelloWorldBean;

import java.util.*;

public class main {
    public static void main(String[] args) throws InterruptedException{
        System.out.println(new Date() + " begin");
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("application.xml");
        //ApplicationContext ctx =
        // new FileSystemXmlApplicationContext("hw03/src/main/resources/application.xml");

        HelloWorldBean helloWorldBean = (HelloWorldBean) ctx.getBean("helloBean");
        System.out.println(new Date()+" get domain "+helloWorldBean);

        helloWorldBean.sayHello();
    }
}
