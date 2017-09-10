import leader.bean.AnnotationBean;
import leader.bean.HelloComponent;
import leader.bean.MyStoreService;
import leader.config.MySystemConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

/**
 * Created by leader05 on 17/9/3.
 */
public class MainEnv2 {
    public static void main(String[] args){
        System.out.println(new Date() + " begin");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MySystemConfig.class);

        MyStoreService storeService=ctx.getBean(MyStoreService.class);
        System.out.println("MyStoreService  "+storeService);

        HelloComponent h = (HelloComponent) ctx.getBean("anotherHelloBean");
//        HelloComponent h = (HelloComponent) ctx.getBean(HelloComponent.class);
        h.sayHello();
    }
}
