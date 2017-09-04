import leader.domain.MyOrder;
import leader.service.HelloWorldBean2;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by xqy on 17/9/4.
 */
public class NonSpringBean {
    public static void main(String[] args) throws InterruptedException {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("applicationContext2.xml");
        MyOrder myOrder = new MyOrder();
        HelloWorldBean2 h2 = myOrder.getHellBean();
        h2.hello();
        System.out.println(" isAopProxy  " + AopUtils.isAopProxy(myOrder));
        System.out.println(" isCglibProxy  " + AopUtils.isCglibProxy(myOrder));
        System.out.println(" isJdkDynamicProxy  " + AopUtils.isJdkDynamicProxy(myOrder));
        ctx.registerShutdownHook();

    }
}
