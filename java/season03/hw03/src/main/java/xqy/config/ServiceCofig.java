package xqy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xqy.service.UserService;
import xqy.dao.*;
import xqy.domain.*;
import xqy.util.DataSource;
import java.util.*;

/**
 * Created by xqy on 17/8/30.
 */
@Configuration
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
