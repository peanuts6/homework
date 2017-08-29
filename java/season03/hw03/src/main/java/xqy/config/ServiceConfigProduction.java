package xqy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import xqy.domain.*;
import xqy.util.*;
import xqy.service.UserService;

/**
 * Created by xqy on 17/8/29.
 */
@Configuration
@Profile("production")
public class ServiceConfigProduction {
    @Autowired
    private UserList userList;

    @Autowired
    private Account account;

    @Bean(name="userService")
    @Profile("production")
    public UserService userService(){
        return new UserService(userList, account);
    }

}
