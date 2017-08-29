package xqy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import xqy.domain.*;
import xqy.service.UserService;
import xqy.util.Account;
import xqy.util.UserList;

/**
 * Created by xqy on 17/8/28.
 */
@Configuration
@Profile("test")
public class ServiceConfigTest {
    @Autowired
    private UserList userList;

    @Autowired
    private Account account;

    @Bean(name="userService")
    @Profile("test")
    public UserService userService(){
        return new UserService(userList, account);
    }
}
