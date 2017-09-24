/**
 * Created by xqy on 17/9/17.
 */
public class Employee {
    String employeeName;
    int employeeNumber;

    public Employee(String employeeName, int employeeNumber){
        this.employeeName = employeeName;
        this.employeeNumber = employeeNumber;
    }

    public void storeData(String employeeName, int employeeNumber){
        System.out.println("store");
    }
}
