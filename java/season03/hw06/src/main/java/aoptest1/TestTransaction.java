package aoptest1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xqy on 17/9/21.
 */
@Configuration
@ComponentScan("aoptest1")
@EnableAspectJAutoProxy
public class TestTransaction {

    public static void main(String args[]){

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        BuyProduct buy  = (BuyProduct)context.getBean("buyProduct");
        buy.buyProduct("user1", 101);
        buy.displayState("user1", 101);
        buy.buyProduct("user2", 102);
        buy.displayState("user2", 102);

        PurchaseProduct purchase  = (PurchaseProduct)context.getBean("purchaseProduct");
        purchase.buyProduct("user1", 101);
        purchase.displayState("user1", 101);
        purchase.buyProduct("user2", 102);
        purchase.displayState("user2", 102);
    }
}
