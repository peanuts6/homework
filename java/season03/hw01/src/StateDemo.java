/**
 * Created by xqy on 17/8/15.
 */
//wrapper
class FSM {
    private State[] states  = {new A(), new B(), new C()};
    private int currentState = 0;

    public void on()  {
        states[currentState].on(this);
    }
    public void off() {
        states[currentState].off(this);
    }
    public void ack() {
        states[currentState].ack(this);
    }
    public void changeState(int index) {
        currentState = index;
    }
}
abstract class State {
    public void on(FSM fsm) {
        System.out.println("error");
    }
    public void off(FSM fsm) {
        System.out.println("error");
    }

    public void ack(FSM fsm) {
        System.out.println("error");
    }
}
class A extends State {
    public void on(  FSM fsm ) {
        System.out.println("A + on");
        fsm.changeState(2);
    }
    public void off(FSM fsm) {
        System.out.println("A + off");
        fsm.changeState(1);
    }
    public void ack(FSM fsm) {
        System.out.println("A + ack");
        fsm.changeState(0);
    }
}
class B extends State {
    public void on(FSM fsm) {
        System.out.println("B + on");
        fsm.changeState(0);
    }
    public void off(FSM fsm) {
        System.out.println("B + off");
        fsm.changeState(2);
    }
}
class C extends State {
    //callback to wrapper
    public void on(FSM fsm) {
        System.out.println("C + on");
        fsm.changeState(1);
    }
}
public class StateDemo {
    public static void main(String[] args) {
        FSM fsm  = new FSM();
        int[] msgs = {2, 1, 2, 1, 0, 2, 0, 0};
        for (int msg : msgs) {
            if (msg == 0) {
                fsm.on();
            } else if (msg == 1) {
                fsm.off();
            } else if (msg == 2) {
                fsm.ack();
            }
        }
    }
}
