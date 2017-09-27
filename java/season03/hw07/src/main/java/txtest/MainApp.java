package txtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xqy on 17/9/25.
 */

public class MainApp {

    public static void main( String[] args )
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("txtest.xml");
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        OuterBean testBean = (OuterBean) ctx.getBean("outerBeanImpl");

        User user = new User();
        user.setUsername("johndoe");
        user.setName("John Doe");

        try{
            testBean.testRequired(user);
        } catch(Exception e){
            // catch exception raised from transaction rollback
        }

        testBean.testRequiresNew(user);

    }
}

//
//CREATE TABLE USER (
//        ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
//        USERNAME VARCHAR (32) NOT NULL,
//        NAME VARCHAR (64) NOT NULL,
//        UNIQUE (USERNAME)
//);
