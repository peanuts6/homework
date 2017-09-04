package leader.bean;

import java.util.Date;

/**
 * Created by xqy on 17/9/4.
 */
public class VIPUser {
    private int userId;
    private String userName;
    private Date regDate;

    public VIPUser(int userId,String userName){
        this.userId = userId;
        this.userName = userName;
        this.regDate = new Date();
    }

    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public Date getRegDate(){
        return regDate;
    }
    public void setRegDate(Date regDate){
        this.regDate = regDate;
    }

    @Override
    public String toString(){
        return "VIPUser{userId="+userId+",userName="+userName+",regDate="+regDate.toString()+"}";
    }
}
