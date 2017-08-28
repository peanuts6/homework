import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.*;
import xqy.bean.AnnotationBean;
import xqy.bean.HelloWorldBean;

import java.util.Date;

/**
 * Created by xqy on 17/8/28.
 */
@Configuration
@ComponentScan("xqy.bean")
public class main3 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(new Date() + " begin");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(main3.class);

        AnnotationBean bean = (AnnotationBean) ctx.getBean("annotationBean");
        bean.setMyName("i am annotation bean");
        System.out.println(new Date()+" get bean "+bean);

        bean.sayHello();
    }
}
