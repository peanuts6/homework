package xqy.dao;

import xqy.domain.User;

import java.util.*;

/**
 * Created by xqy on 17/8/30.
 */
public class UserDao {
    private DataSource dataSource;

    public UserDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<User> userList(){
        return dataSource.getAll();
    }
}
