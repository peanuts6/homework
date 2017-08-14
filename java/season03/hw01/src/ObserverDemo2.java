/**
 * Created by xqy on 17/8/14.
 */
/**
 * Created by xqy on 17/8/14.
 */
import java.util.*;

//push模式
interface Subject2{
    public void registerObserver(Observer2 o);
    public void removeObserver(Observer2 o);
    boolean isInCompany();
    void setInCompany(boolean b);
    public List<Observer2> getStaff();
}
class Boss2 implements Subject2{
    private List<Observer2> staffs = new ArrayList<>();
    private boolean inCompany = true;
    @Override
    public boolean isInCompany(){
        return this.inCompany;
    }
    @Override
    public void setInCompany(boolean inCompany){
        this.inCompany = inCompany;
    }
    @Override
    public void registerObserver(Observer2 o){
        staffs.add(o);
    }
    @Override
    public void removeObserver(Observer2 o){
        this.staffs.remove(o);
    }
    public List<Observer2> getStaff(){
        return this.staffs;
    }
}
interface Observer2{
    public void update(String msg);
    public void notifys();
}
class Staff2 implements Observer2{
    protected Subject2 subject;
    String name = "";
    public Staff2(Subject2 s,String name){
        this.subject = s;
        this.name = name;
    }
    public void update(String msg){
        System.out.println(this.name+" receive message:"+msg);
    }
    public void subscribe(){
        subject.registerObserver(this);
    }
    public void unSubscribe(){
        subject.removeObserver(this);
    }
    @Override
    public void notifys(){
        String str;
        for(Observer2 s:this.subject.getStaff()){
            str = this.subject.isInCompany()?"Boss is in company":"Boss is not in company";
            if(s != this){
                s.update(this.name+" receive message:" + str);
            }
        }
    }
}
public class ObserverDemo2 {
    public static void main(String[] args){
        Subject2 boss = new Boss2();
        boss.registerObserver(new Staff2(boss,"Jonh"));
        boss.registerObserver(new Staff2(boss,"Alias"));
        boss.registerObserver(new Staff2(boss,"Sam"));
        boss.setInCompany(false);
    }
}
