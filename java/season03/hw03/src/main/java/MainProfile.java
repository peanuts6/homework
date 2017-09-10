
import leader.bean.MyDataSourceParamBean;
import leader.config.ConfigForDev;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

/**
 * Created by leader05 on 17/9/3.
 */
public class MainProfile {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(new Date() + " begin");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigForDev.class);

        MyDataSourceParamBean p = (MyDataSourceParamBean) ctx.getBean("myDataSourceParam");
        System.out.println(p);
    }
}
