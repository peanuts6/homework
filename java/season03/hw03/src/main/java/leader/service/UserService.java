package leader.service;

import leader.dao.UserDao;
import leader.domain.User;
import leader.domain.UserSession;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

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


    public User getUser(long userId) {
        for(User user:users){
            if(userId == user.getUserId()){
                return user;
            }
        }
        return null;
    }


    public boolean createUser(String userName, String pwd, boolean enabled, Date regDate) {
        User user = new User(userName,pwd,enabled,regDate);
        users.add(user);
        return true;
    }


    public boolean deleteUser(long userId)
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


    public boolean disableUser(long userId, String userName, boolean enabled, String pwd)
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


    public List<User> queryUsers(String userNamePrex,boolean onlyValidUser)
    {
        return users;
    }



    /**
     * 如果密码不对，返回的UserSession对象里sessionId为空，客户端可以依次判断，参照UserSession.isValid方法
     * @param userName
     * @param md5EncodedPassword
     * @return
     */

    public UserSession login(String userName, String md5EncodedPassword)
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
