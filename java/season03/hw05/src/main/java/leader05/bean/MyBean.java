package leader05.bean;

import org.springframework.stereotype.Component;

/**
 * Created by xqy on 17/9/11.
 */
@Component
public class MyBean implements AbstractCom,Welcome{
    private int id;
    private String name;

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format("MyBean[id=%d, name=%s]@%s",id,name,this.hashCode());
    }

    @Override
    public String getWelcome() {
        return "welcome: "+name;
    }

    @Override
    public void sayHello(String msg) {
        System.out.println("say hello: "+msg);
    }
}
