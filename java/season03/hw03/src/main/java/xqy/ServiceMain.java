package xqy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xqy.config.ServiceConfigTest;

/**
 * Created by xqy on 17/8/28.
 */
public class ServiceMain {
    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("test");
        ctx.register(ServiceConfigTest.class);
        ctx.refresh();
//        UserSession user = (UserSession) ctx.getBean("userSession");
//        user.setUserName("aaa ");
//        System.out.println(user.getUserName());
//        ctx.registerShutdownHook();
    }
}
