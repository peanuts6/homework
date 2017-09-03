import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import leader.bean.*;

/**
 * Created by leader on 17/9/2.
 */
@Configuration
@ComponentScan
public class MainTest {
    @Bean(name="helloBean")
    public HelloBean helloBean(){
        return new HelloBean();
    }
    @Bean
    public AutoWiredBean autoWiredBean(){
        return new AutoWiredBean();
    }
    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        HelloComponent hc = ctx.getBean(HelloComponent.class);
        System.out.println(hc);

        HelloBean hb = (HelloBean) ctx.getBean("helloBean");

    }
}
