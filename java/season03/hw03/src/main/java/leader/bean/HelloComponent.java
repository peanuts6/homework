package leader.bean;
/**
 * Created by leader on 17/8/27.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HelloComponent {
    @Value("${system.appname}")
    private String myName;

    public HelloComponent(){
        System.out.println(new Date() + "created "+ this);
    }

    @PostConstruct
    public void init(){
        System.out.println("init... ");
    }

    public void sayHello(){
        System.out.println(new Date() + " hello "+myName +" @ "+this);
    }

    public void setMyName(String myName){
        this.myName = myName;
    }

    @PreDestroy
    public void close(){
        System.out.println("close... ");
    }
}
