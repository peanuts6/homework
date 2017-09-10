package leader05;

import leader05.bean.MyBean;
import leader05.bean.MyBean2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xqy on 17/9/11.
 */
@Configuration
@ComponentScan(basePackages = {"leader05.bean"})
public class MyBeanPostProcessorTest {
    public static void main(String[] args){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyBeanPostProcessorTest.class);
        MyBean myBean = (MyBean) ctx.getBean("myBean");
        myBean.setName("first bean");
        System.out.println(myBean.toString());
        MyBean2 myBean2 = (MyBean2) ctx.getBean("myBean2");
        myBean.setName("second bean");
        System.out.println(myBean2.toString());
    }
}
