import java.util.*;

interface AbstractMachine{
    int mov(int r1,int r2);
    boolean cmp(int r1,int r2);
    void beq();
    int sub(int r1,int r2,int r3);
    void jump();
    void print(int r0);
    void go();
}
class Machine implements AbstractMachine{

    public int mov(int r1,int r2){
        return r1;
    }
    public boolean cmp(int r1,int r2){
        return r1<r2;
    }
    public void beq(){
        System.out.println("beq");
    }
    public int sub(int r1, int r2, int r3) {
        return 0;
    }
    public void jump(){
        System.out.println("jump");
    }
    public void print(int r0){
        System.out.println("print");
    }
    public void go(){
        System.out.println("go");
    }
}
interface Action{
    void execute();
}
class Mov implements Action{
    private final Machine machine;
    public Mov(Machine machine){
        this.machine = machine;
    }
    public void execute(){
        this.machine.mov(0,1);
    }
}
class Cmp implements Action{
    private final Machine machine;
    public Cmp(Machine machine){
        this.machine = machine;
    }
    public void execute(){
        this.machine.cmp(0,1);
    }
}

public class CommandInterpretor{
    public static void main(String[] args){
        Machine machine = new Machine();
        List<Action> actions = new ArrayList<>();
        actions.add(new Mov(machine));
        actions.add(new Cmp(machine));
        for(Action act:actions){
            act.execute();
        }
    }
}
