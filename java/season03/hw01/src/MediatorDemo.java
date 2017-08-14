/**
 * Created by xqy on 17/8/14.
 */
import java.util.*;

interface AbstractMediator{
    public void send(String msg, Colleague colleague);
}
class ApplicationMediator implements AbstractMediator{
    private List<Colleague> colleagueList;
    public ApplicationMediator(){
        colleagueList = new ArrayList<>();
    }
    public void addColleague(Colleague colleague){
        colleagueList.add(colleague);
    }
    public void send(String msg, Colleague originator){
        System.out.println(originator.toString()+" send: "+msg);
        for(Colleague colleague:colleagueList){
            if(colleague != originator){
                colleague.receive(msg);
            }
        }
    }
}
abstract class Colleague{
    private AbstractMediator mediator;
    public Colleague(AbstractMediator m){this.mediator = m;}
    public void send(String msg){
        mediator.send(msg, this);
    }
    public AbstractMediator getMediator(){return mediator;}
    abstract void receive(String msg);
}
class ConcreteColleague extends Colleague{
    public ConcreteColleague(AbstractMediator m) {super(m);}
    public void receive(String msg){
        System.out.println("Colleague received: "+msg);
    }
}
class MobileColleague extends Colleague{
    public MobileColleague(AbstractMediator m) {super(m);}
    public void receive(String msg){
        System.out.println("Mobile received: "+msg);
    }
}
class LatoColleague extends Colleague{
    public LatoColleague(AbstractMediator m) {super(m);}
    public void receive(String msg){
        System.out.println("Lato received: "+msg);
    }
}
public class MediatorDemo {
    public static void main(String[] args){
        ApplicationMediator mediator = new ApplicationMediator();
        ConcreteColleague desktop = new ConcreteColleague(mediator);
        MobileColleague mobile = new MobileColleague(mediator);
        LatoColleague lato = new LatoColleague(mediator);
        mediator.addColleague(desktop);
        mediator.addColleague(mobile);
        mediator.addColleague(lato);
        desktop.send("hello! i am desktop");
        mobile.send("hi! i am mobile");
    }
}
