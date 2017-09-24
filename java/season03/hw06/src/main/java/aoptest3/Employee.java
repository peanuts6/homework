package aoptest3;

import org.springframework.stereotype.Component;

/**
 * Created by xqy on 17/9/20.
 */
@Component
public class Employee {
    private String name;

    public String getName() {
        return name;
    }

    @Loggable
    public void setName(String nm) {
        this.name=nm;
    }

    public void throwException(){
        throw new RuntimeException("Dummy Exception");
    }
}
