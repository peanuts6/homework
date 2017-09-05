package xqy.domain;

import xqy.util.MyComponent;

import java.util.Date;

@MyComponent(name = "twoComponent")
public class TwoComponent implements AbstractComponent<OneComponent> {
    private String myName;

    @Override
    public void hello() {
        System.out.println(new Date()+" TwoComponent "+myName+ " @ "+ this);
    }

    @Override
    public void setMyName(String myName) {
        this.myName = myName;
    }

    @Override
    public String toString(){
        return "TwoComponent@"+this.hashCode();
    }
}
