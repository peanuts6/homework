import xqy.domain.AbstractComponent;
import xqy.domain.OneComponent;
import xqy.domain.TwoComponent;
import xqy.service.HelloBean;
import xqy.service.HelloBean2;
import xqy.service.MyIocContext;


/**
 * Created by leader05 on 17/9/5.
 */
public class MyIocTest {
    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, ClassNotFoundException {

        MyIocContext ctx = new MyIocContext();

        OneComponent oneComponent = (OneComponent) ctx.getBean(OneComponent.class);
        System.out.println(oneComponent);
        System.out.println("----------------------");
        OneComponent oneComponent1 = (OneComponent) ctx.getBean("oneComponent");
        System.out.println(oneComponent1);
        oneComponent1.setMyName("one");
        oneComponent1.hello();
        System.out.println("----------------------");
        AbstractComponent component = (AbstractComponent) ctx.getBean(TwoComponent.class);
        System.out.println(component);
        System.out.println("----------------------");
        AbstractComponent component2 = (AbstractComponent) ctx.getBean("twoComponent");
        System.out.println(component2);
        component2.setMyName("component2");
        component2.hello();

        System.out.println("====================");

        HelloBean helloBean = (HelloBean) ctx.getBean("helloBean");
        helloBean.setMyName("WiredBean");
        helloBean.hello();

        HelloBean2 helloBean2 = (HelloBean2) ctx.getBean("helloBean2");
        helloBean2.hello();


    }
}
