/**
 * Created by leader on 17/8/13.
 */
import java.io.*;
//abstract class ProcessingObject<T> {
//    protected ProcessingObject<T> successor;
//
//    public void setSuccessor(ProcessingObject<T> successor){
//        this.successor = successor;
//    }
//    public T handle(T input){
//        T r = handleWork(input);
//        if(successor != null){
//            return successor.handle(r);
//        }
//        return r;
//    }
//    abstract protected T handleWork(T input);
//}

abstract class PurchaseHandle{
    protected static final double BASE = 500;
    protected PurchaseHandle successor;

    abstract protected double getAllowable();
    abstract protected String getRole();

    public void setSuccessor(PurchaseHandle successor) {
        this.successor = successor;
    }

    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < this.getAllowable()) {
            System.out.println(this.getRole() + " will approve $" + request.getAmount());
        } else if (successor != null) {
            successor.processRequest(request);
        }
    }
}
class ManagerHandle extends PurchaseHandle{
    protected double getAllowable() {
        return BASE * 10;
    }

    protected String getRole() {
        return "Manager";
    }
}
class DirectorHandle extends PurchaseHandle{
    protected double getAllowable() {
        return BASE * 20;
    }

    protected String getRole() {
        return "Director";
    }
}
class VicePresidentHandle extends PurchaseHandle{
    protected double getAllowable() {
        return BASE * 40;
    }

    protected String getRole() {
        return "Vice President";
    }
}
class PresidentHandle extends PurchaseHandle{
    protected double getAllowable() {
        return BASE * 60;
    }

    protected String getRole() {
        return "President";
    }
}
class PurchaseRequest {

    private double amount;
    private String purpose;

    public PurchaseRequest(double amount, String purpose) {
        this.amount = amount;
        this.purpose = purpose;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount)  {
        this.amount = amount;
    }

    public String getPurpose() {
        return this.purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
public class ChainOfResponsibility {
    public static void main(String[] args) {
        ManagerHandle manager = new ManagerHandle();
        DirectorHandle director = new DirectorHandle();
        VicePresidentHandle vp = new VicePresidentHandle();
        PresidentHandle president = new PresidentHandle();
        manager.setSuccessor(director);
        director.setSuccessor(vp);
        vp.setSuccessor(president);

        // Press Ctrl+C to end.
        try {
            while (true) {
                System.out.println("Enter the amount to check who should approve your expenditure.");
                System.out.print(">");
                double d = Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());
                manager.processRequest(new PurchaseRequest(d, "General"));
            }
        }
        catch (Exception exc) {
            System.exit(1);
        }
    }
}
