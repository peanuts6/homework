import leader.bean.LDOrder;
import leader.service.LdOrderService;
import leader.service.MySpringEvtSender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xqy on 17/9/4.
 */
public class LdOrderServiceTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
        LdOrderService sender=ctx.getBean(LdOrderService.class);
        sender.createOrder();
    }
}
