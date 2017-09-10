package leader05.bean;

import org.springframework.stereotype.Component;

/**
 * Created by xqy on 17/9/11.
 */
@Component
public class MyBean2 {
    private int id;
    private String firstname;
    private String lastname;
    private int age;
    private int mark;

    public void setName(String name){
        this.firstname = name;
    }

    @Override
    public String toString(){
        return String.format("MyBean2[id=%d, fistname=%s, lastname=%s, age=%d, mark=%d]@%s",id,firstname,lastname,age,mark,this.hashCode());
    }
}
