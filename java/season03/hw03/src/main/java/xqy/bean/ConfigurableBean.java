package xqy.bean;


import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by xqy on 17/8/28.
 */
@Component("configurableBean")
public class ConfigurableBean {
    private String myName;
    public ConfigurableBean(){
        System.out.println("ConfigurableBean");
    }
    public void sayHello(){
        System.out.println(new Date() + " hellow "+myName +" @ "+this);
    }
    public void setMyName(String myName){
        this.myName = myName;
    }
}
