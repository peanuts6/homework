package leader.service;

import leader.bean.LDOrder;
import leader.bean.VIPUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class VIPUserDetector {
    @Autowired
    private ApplicationEventPublisher publisher;

    @EventListener(value = {LDOrder.class}, condition = "#order.price >= 500")
    public void VipUserService(LDOrder order){
        System.out.println("VIPUserService receive order: "+ order.toString());
        VIPUser vipUser = new VIPUser(order.getUserId(),"VIP"+order.getUserId());
        System.out.println("create vip user:" + vipUser.toString());
        //publisher
        publisher.publishEvent(vipUser);
    }
}
