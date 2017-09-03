import org.springframework.context.ApplicationContext;
import org.springframework.context.support.*;
import leader.bean.ConfigurableBean;

import java.util.Date;

/**
 * Created by leader on 17/8/28.
 */
public class Main2 {
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
