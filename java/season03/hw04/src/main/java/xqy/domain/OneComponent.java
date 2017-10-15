package xqy.domain;

import xqy.util.MyComponent;

import java.util.Date;

/**
 * Created by xqy on 17/9/5.
 */
@MyComponent(name = "oneComponent")
public class OneComponent implements AbstractComponent<OneComponent> {
    private String myName;

    @Override
    public void hello() {
        System.out.println(new Date()+" OneComponent "+myName+ " @ "+ this);
    }

    @Override
    public void setMyName(String myName) {
        this.myName = myName;
    }

    @Override
    public String toString(){
        return "OneComponent@"+this.hashCode();
    }
}
