package xqy.util;

import org.springframework.context.annotation.Profile;
import xqy.domain.User;

import java.util.*;

/**
 * Created by xqy on 17/8/30.
 */
public class TestDataSource implements UserList,Account{

    public List<User> getAll() {
        List<User> users = new ArrayList<User>();
        users.add(new User("test01","456", true, new Date()));
        users.add(new User("test02","456",false, new Date()));
        users.add(new User("test03","456",true,new Date()));
        users.add(new User("test04","456",true,new Date()));
        return users;
    }

    public Map<String, String> getAcount() {
        Map<String,String> m = new HashMap();
        m.put("username","test");
        m.put("password","3125");
        return m;
    }
}
