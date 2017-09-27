package util;


import leader.domain.User;

import java.util.*;

/**
 * Created by leader on 17/8/30.
 */
public class ProductionDataSource implements DataSource {

    public List<User> getAll() {
        List<User> users = new ArrayList<User>();
        users.add(new User("pro01","123", true, new Date()));
        users.add(new User("pro02","123",false, new Date()));
        users.add(new User("pro03","123",false,new Date()));
        users.add(new User("pro04","123",true,new Date()));
        return users;
    }

    public Map<String, String> getAcount() {
        Map<String,String> m = new HashMap();
        m.put("username","admin");
        m.put("password","9527");
        return m;
    }
}
