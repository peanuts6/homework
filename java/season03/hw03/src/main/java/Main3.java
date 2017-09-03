import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import leader.bean.AnnotationBean;

import java.util.Date;

/**
 * Created by leader on 17/8/28.
 */
@Configuration
@ComponentScan("leader.bean")
public class Main3 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(new Date() + " begin");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Main3.class);

        AnnotationBean bean = (AnnotationBean) ctx.getBean("annotationBean");
        bean.setMyName("i am annotation domain");
        System.out.println(new Date()+" get domain "+bean);

        bean.sayHello();
    }
}
