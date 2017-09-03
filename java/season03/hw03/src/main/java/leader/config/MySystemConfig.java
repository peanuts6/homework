package leader.config;

import leader.bean.HelloComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("leader.bean")
@PropertySource(value="classpath:myapp.properties", ignoreResourceNotFound=true)
public class MySystemConfig {

    public MySystemConfig()
    {
        System.out.println("@Configuration MySystemConfig created " );
    }

    @Autowired
    Environment env;

    @Bean
    HelloComponent anotherHelloBean()
    {
        System.out.println("get store file path:"+env.getProperty("system.storefile"));
        return new HelloComponent();
    }
}
