package leader.trans;

/**
 * Created by xqy on 17/9/20.
 */
//@Component
public class Employee {
    private int empid = 0;
    private String name;
    private int age;
    private long salary;

    public Employee(int empid,String name){
        this.empid = empid;
        this.name = name;
    }
    public Employee(int empid,String name,int age,long salary){
        this.empid = empid;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String toString(){
        return "EMPLOYEE{empid- "+this.empid+" name- "+this.name+
                " age- "+this.age+" salary- "+this.salary+"}";
    }


}
