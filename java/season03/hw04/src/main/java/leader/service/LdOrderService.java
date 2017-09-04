package leader.service;

import leader.bean.LDOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LdOrderService {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void createOrder(){
        LDOrder order = new LDOrder();
        order.setUserId(1);
        order.setPrice(200);
        order.setCreateDate(new Date());
        System.out.println("send order1: "+order.toString());
        publisher.publishEvent(order);
        LDOrder order2 = new LDOrder();
        order2.setUserId(2);
        order2.setPrice(600);
        order2.setCreateDate(new Date());
        System.out.println("send order2: "+order2.toString());
        publisher.publishEvent(order2);
    }
}
