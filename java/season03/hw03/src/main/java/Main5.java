import leader.bean.HelloComponent;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main5 {
    public static void main(String[] args) throws InterruptedException {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("application2.xml");
        HelloComponent bean=ctx.getBean(HelloComponent.class);
        bean.sayHello();
        ctx.close();
    }
}
