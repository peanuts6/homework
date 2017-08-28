package xqy.hw03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.SessionScope;
import xqy.hw03.bean.User;
import xqy.hw03.bean.UserSession;

/**
 * Created by xqy on 17/8/29.
 */
@Configuration
@Profile("production")
public class ServiceConfigProduction {
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
}
