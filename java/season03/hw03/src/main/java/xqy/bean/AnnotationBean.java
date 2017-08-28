package xqy.bean;

import org.springframework.stereotype.Component;

/**
 * Created by xqy on 17/8/28.
 */
@Component("annotationBean")
public class AnnotationBean {
    private String myName;
    public AnnotationBean(){
        System.out.println("AnnotationBean created");
    }
    public void setMyName(String name){
        this.myName = name;
    }
    public void sayHello(){
        System.out.println("hello annotation bean");
    }
}
