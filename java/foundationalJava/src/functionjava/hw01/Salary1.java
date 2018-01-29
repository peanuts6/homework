package functionjava.hw01;

/**
 * Created by xqy on 18/1/26.
 */
public class Salary1 implements Comparable<Salary1> {
    String name;
    int baseSalary;
    int bonus;
    int totalIncome;
    public Salary1(String name,int baseSalary,int bonus){
        this.name = name;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.totalIncome = baseSalary*13 + bonus;
    }
    @Override
    public int compareTo(Salary1 o) {
        return o.totalIncome - this.totalIncome;
    }
}
