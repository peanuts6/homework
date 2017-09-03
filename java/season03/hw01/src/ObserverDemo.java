/**
 * Created by leader on 17/8/14.
 */
import java.util.*;

//pull模式
interface Subject{
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifys();
    boolean isInCompany();
    void setInCompany(boolean b);
}
class Boss implements Subject{
    private List<Observer> staffs = new ArrayList<>();
    private boolean inCompany = true;
    public boolean isInCompany(){
        return this.inCompany;
    }
    public void setInCompany(boolean inCompany){
        this.inCompany = inCompany;
        notifys();
    }
    public void registerObserver(Observer o){
        staffs.add(o);
    }
    public void removeObserver(Observer o){
        this.staffs.remove(o);
    }
    public void notifys(){
        for(Observer staff:staffs){
            staff.update(this.inCompany?
                    "boss is in Company":"boss is not in Company");
        }
    }
}
interface Observer{
    void update(String msg);
}
class Staff implements Observer{
    String name = "";
    public Staff(String name){
        this.name = name;
    }
    public void update(String msg){
        System.out.println(this.name+" receive message:"+msg);
    }
}
public class ObserverDemo {
    public static void main(String[] args){
        Subject boss = new Boss();
        Observer john = new Staff("John");
        boss.registerObserver(john);
        boss.registerObserver(new Staff("Alias"));
        boss.registerObserver(new Staff("Sam"));
        boss.setInCompany(false);
        System.out.println(" --------------------------- ");
        boss.removeObserver(john);
        boss.setInCompany(true);
    }
}
