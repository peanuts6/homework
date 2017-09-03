package leader.bean;

import org.springframework.stereotype.Component;

/**
 * Created by leader on 17/8/28.
 */
@Component
public class AnnotationBean {
    private String myName;
    public AnnotationBean(){
        System.out.println("AnnotationBean created");
    }
    public void setMyName(String name){
        this.myName = name;
    }
    public void sayHello(){
        System.out.println("hello annotation domain");
    }

    public void init(){
        System.out.println("Bean is going through init.");
    }
    public void destroy() {
        System.out.println("Bean will destroy now.");
    }
}
