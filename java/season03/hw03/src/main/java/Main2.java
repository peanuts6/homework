import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.*;
import xqy.bean.ConfigurableBean;

import java.util.Date;

/**
 * Created by xqy on 17/8/28.
 */
public class main2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(new Date() + " begin");
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("application2.xml");

        ConfigurableBean bean = (ConfigurableBean) ctx.getBean("configurableBean");
        bean.setMyName("i am configurable domain");
        System.out.println(new Date()+" get domain "+bean);

        bean.sayHello();
    }
}
