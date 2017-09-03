package leader.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import leader.bean.*;

@Configuration
@ComponentScan("leader.bean")
@Profile(value="dev")
@PropertySource(value="classpath:myapp-dev.properties")
public class ConfigForDev {
    public ConfigForDev(){
        System.out.println("@Configuration ConfigForDev created");
    }

    @Autowired
    Environment env;

    @Bean
    MyDataSourceParamBean myDataSourceParam(){
        System.out.println("get store file path:"+env.getProperty("system.storefile"));
        return new MyDataSourceParamBean();
    }
}
