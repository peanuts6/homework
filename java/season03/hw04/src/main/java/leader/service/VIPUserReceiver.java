package leader.service;

import leader.bean.VIPUser;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class VIPUserReceiver {
    @EventListener(value={VIPUser.class})
    public void handlerEvent(Object vipuser)
    {
        System.out.println("you have upgraded to VIP user.");
        System.out.println("send mail to: " + vipuser);
    }
}
