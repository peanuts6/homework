package edu.ldcollege;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by xqy on 17/10/15.
 */
@SpringBootApplication(scanBasePackages = "edu.ldcollege.**")
@MapperScan("edu.ldcollege.mapping")
public class App_09demo extends SpringBootServletInitializer {
    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App_09demo.class);
    }

    public static void main(String[] args) throws Exception {
//        Enumeration<URL> resouces;
//        try {
//            resouces = Thread.currentThread().getContextClassLoader().getResources("leader");
//            while(resouces.hasMoreElements())
//            {
//                System.out.println("$$$$$$$$$ "+resouces.nextElement());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ConfigurableApplicationContext ctx = SpringApplication.run(App_09demo.class, args);

        SpringApplication.run(App_09demo.class, args);
    }
}
