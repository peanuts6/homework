package leader.bean;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by leader on 17/9/2.
 */
public class AutoWiredBean {
    private String myName;
    @Autowired
    private HelloBean helloBean;
    public AutoWiredBean(){
        System.out.println(new Date() + "created "+ this);
    }
    public void sayHello(){
        System.out.println(new Date() + " hellow "+myName +" @ "+this);
    }
    public void setMyName(String myName){
        this.myName = myName;
    }
}
