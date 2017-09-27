package leader.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import leader.service.UserService;
import leader.dao.*;
import util.DataSource;

/**
 * Created by leader on 17/8/30.
 */
@Configuration
@ComponentScan
public class ServiceCofig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public UserService UserService(){
        return new UserService(userDao());
    }

    @Bean
    public UserDao userDao(){
        return new UserDao(dataSource);
    }

}
