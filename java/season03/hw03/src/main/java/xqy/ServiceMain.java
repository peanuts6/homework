package xqy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xqy.config.*;

/**
 * Created by xqy on 17/8/28.
 */
public class ServiceMain {
    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("test");
        ctx.register(DataConfig.class);
        ctx.register(ServiceCofig.class);
        ctx.refresh();
//        Object obj = ctx.getBean("userSession");
//        user.setUserName("aaa ");
//        System.out.println(user.getUserName());
//        ctx.registerShutdownHook();
    }
}
