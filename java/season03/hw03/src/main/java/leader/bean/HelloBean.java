package leader.bean;

import java.util.Date;

/**
 * Created by leader on 17/9/2.
 */
public class HelloBean {
    private String myName;
    public HelloBean(){
        System.out.println(new Date() + "created "+ this);
    }
    public void sayHello(){
        System.out.println(new Date() + " hellow "+myName +" @ "+this);
    }
    public void setMyName(String myName){
        this.myName = myName;
    }
}
