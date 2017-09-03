package leader.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by leader on 17/8/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSession {

    private String sessionId;
    private long userId;
    private String userName;
    private long createTime;
    private short validSeconds;

    public UserSession(long userId,String userName,long createTime,short validSeconds){
        this.userId = userId;
        this.userName = userName;
        this.createTime = createTime;
        this.validSeconds = validSeconds;
        if(userId == 0){
            sessionId = "";
        } else{
            sessionId = sign();
        }
    }
    public String sign(){
        return "";
    }

    public String getSessionId() {
        return sessionId;
    }
    public boolean isValid()
    {
        return sessionId!=null && ((createTime+validSeconds*1000)<System.currentTimeMillis());
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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
    public long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    public short getValidSeconds() {
        return validSeconds;
    }
    public void setValidSeconds(short validSeconds) {
        this.validSeconds = validSeconds;
    }

    public void init(){
        System.out.println("Bean is going through init.");
    }
    public void destroy() {
        System.out.println("Bean will destroy now.");
    }


    @Override
    public String toString(){
        return "UserSession{" +
                "sessionId='"+sessionId +
                "\', userId="+userId +
                ", userName='"+userName+
                "\', validSeconds='"+validSeconds+
                ", createTime='"+createTime+
                "\'}";
    }
}

