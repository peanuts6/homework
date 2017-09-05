package xqy.service;

import xqy.domain.AbstractComponent;
import xqy.domain.OneComponent;
import xqy.domain.TwoComponent;
import xqy.util.Autowired;
import xqy.util.MyComponent;

import java.util.Date;

/**
 * Created by xqy on 17/9/5.
 */
@MyComponent(name="helloBean2")
public class HelloBean2 {
    private String myName;

    public HelloBean2()
    {
        System.out.println(new Date()+" created "+ this);
    }

    public void hello() {
        System.out.println(new Date()+" hello "+myName+ " @ "+ this);
        System.out.println("comm say:");

    }

    @Autowired
    public void say(AbstractComponent<OneComponent> component){
        component.hello();
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }
}
