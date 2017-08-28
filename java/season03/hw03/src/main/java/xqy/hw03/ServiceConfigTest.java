package xqy.hw03;

import org.springframework.context.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import xqy.hw03.bean.*;
import xqy.hw03.service.*;

/**
 * Created by xqy on 17/8/28.
 */
@Configuration
@Profile("test")
public class ServiceConfigTest {
    @Bean
    public User user(){
        return new User();
    }
    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Scope("prototype")
    @SessionScope
    public UserSession userSession(){
        return new UserSession();
    }
//    @Beanß
//    public UserService userService(){
//
//    }
}
