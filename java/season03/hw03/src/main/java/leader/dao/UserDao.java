package leader.dao;

import leader.domain.User;
import leader.util.DataSource;

import java.util.*;

/**
 * Created by leader on 17/8/30.
 */
public class UserDao {
    private DataSource dataSource;

    public UserDao(DataSource ds){
        this.dataSource = ds;
    }

    public List<User> getUsers(){
        return dataSource.getAll();
    }

    public Map<String,String> getAccount(){
        return dataSource.getAcount();
    }
}
