package xqy.domain;

/**
 * Created by xqy on 17/8/27.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.concurrent.atomic.*;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User{
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private long userId;
    private String userName;
    private String password;
    private boolean enabled;
    private Date regDate;

    public User(String userName,String password,boolean enabled,Date regDate){
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.regDate = regDate;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString(){
        return "User{" +
                "userId="+userId +
                ", userName='"+userName+
                "\', password='"+password+
                "\', enabled="+enabled+
                ", regDate='"+regDate+
                "\'}";
    }
}
