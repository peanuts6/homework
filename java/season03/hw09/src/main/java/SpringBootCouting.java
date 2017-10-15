import leader.App_09;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Iterator;

/**
 * Created by xqy on 17/10/15.
 */
public class SpringBootCouting {
    public static void main(String[] args){
        ConfigurableApplicationContext ctx = SpringApplication.run(App_09.class,args);
        Iterator<String> itors = ctx.getBeanFactory().getBeanNamesIterator();
        System.out.println("beans =====");
        while (itors.hasNext()){
            System.out.println(itors.next());
        }
    }
}
