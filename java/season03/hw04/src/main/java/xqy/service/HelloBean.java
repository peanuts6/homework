package xqy.service;

import xqy.domain.AbstractComponent;
import xqy.domain.TwoComponent;
import xqy.util.Autowired;
import xqy.util.MyComponent;

import java.util.Date;

/**
 * Created by xqy on 17/9/5.
 */
@MyComponent(name="helloBean")
public class HelloBean {
    public String myName;

    @Autowired
    private HelloBean2 component;

    public HelloBean()
    {
        System.out.println(new Date()+" created "+ this);
    }

    public void hello() {
        System.out.println(new Date()+" hello "+myName+ " @ "+ this);
        System.out.println("comm say:");
        component.hello();
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

}
