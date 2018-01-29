package functionjava.hw04;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by xqy on 18/1/26.
 */
public class Salary implements Serializable {
    public static final int BASE_MONTH=13;
    public String name;
    public int baseSalary;
    public int bonus;
    public int totalIncome;

    public static final Comparator<Salary> BY_NAME = (o1, o2)->o1.name.compareTo(o2.name);
    public static final Comparator<Salary> BY_BASESALARY = (o1, o2)->o2.baseSalary - o1.baseSalary;
    public static final Comparator<Salary> BY_BONUS = (o1, o2)->o2.bonus - o1.bonus;
    public static final Comparator<Salary> BY_TOTALINCOME = (o1, o2)->o2.totalIncome - o1.totalIncome;

    public Salary(String name,int baseSalary,int bonus) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.totalIncome = this.baseSalary*BASE_MONTH+this.bonus;
    }
    public String getName(){
        return name;
    }
    public String getName2(){
        return name.substring(0,2);
    }
    public void setName(String _name){
        name=_name;
    }
    public int getBonus(){
        return bonus;
    }
    public int getTotalIncome(){
        return totalIncome;
    }

    @Override
    public String toString(){
        return this.name+","+this.baseSalary +"," + this.bonus;
    }
}
