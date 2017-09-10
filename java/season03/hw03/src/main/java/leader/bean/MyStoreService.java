package leader.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by leader05 on 17/9/3.
 */
@Component
public class MyStoreService {
    @Autowired
    Environment env;

    public MyStoreService(Environment env){
        System.out.println("MyService created. Use store location "+env.getProperty("system.storeprofile"));
        System.out.println("mydebug "+env.getProperty("mydebug"));
        System.out.println("temp dir "+env.getProperty("java.io.tmpdir"));
        System.out.println("JAVA_HOME "+env.getProperty("JAVA_HOME"));
        System.out.println("BONJOUR_SDK_HOME "+env.getProperty("BONJOUR_SDK_HOME"));
    }
}
