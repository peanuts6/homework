package xqy.service;

import org.springframework.web.bind.annotation.*;
import xqy.dao.UserDao;
import xqy.domain.User;
import xqy.domain.UserSession;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by xqy on 17/8/27.
 */
@RestController
public class UserService {
    private UserDao userDao;
    private Map<String,String> admin;
    private List<User> users;
    private final AtomicLong counter = new AtomicLong();
    private short validSeconds = 1;

    public UserService(UserDao userDao){
        this.userDao = userDao;
        this.users = userDao.getUsers();
        this.admin = userDao.getAccount();
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable long userId) {
        for(User user:users){
            if(userId == user.getUserId()){
                return user;
            }
        }
        return null;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public boolean createUser(@RequestBody String userName,@RequestBody String pwd,@RequestBody boolean enabled, @RequestBody Date regDate) {
        User user = new User(userName,pwd,enabled,regDate);
        users.add(user);
        return true;
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable long userId)
    {
        List<User> u = new ArrayList<User>();
        boolean flag = false;
        for(User user:users){
            if(userId != user.getUserId()){
                u.add(user);
            } else {
                flag = true;
            }
        }
        return flag;
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
    public boolean disableUser(@PathVariable long userId, @RequestBody String userName, @RequestBody boolean enabled, @RequestBody String pwd)
    {
        for(User user:users){
            if(user.getUserId() == userId){
                user.setEnabled(enabled);
                user.setPassword(pwd);
                user.setUserName(userName);
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> queryUsers(@RequestParam String userNamePrex,@RequestParam boolean onlyValidUser)
    {
        return users;
    }



    /**
     * 如果密码不对，返回的UserSession对象里sessionId为空，客户端可以依次判断，参照UserSession.isValid方法
     * @param userName
     * @param md5EncodedPassword
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserSession login(@RequestBody String userName, @RequestBody String md5EncodedPassword)
    {
        long userId;
        if(userName.equals(admin.get("username")) && checkMd5Pwd(md5EncodedPassword)){
            userId = counter.incrementAndGet();
        } else {
            userId = 0;
        }
        return new UserSession(userId,userName,new Date().getTime(),validSeconds);
    }

    public boolean checkMd5Pwd(String pwd){
        return pwd.equals(admin.get("password"));
    }

}
