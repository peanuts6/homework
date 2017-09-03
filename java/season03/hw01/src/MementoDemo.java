import java.util.*;
/**
 * Created by leader on 17/8/8.
 */

class Memento {
    private String state;
    public Memento(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }
}
class Originator {
    private String state;

    public void setState(String state) {
        System.out.println("Set state:" + state);
        this.state = state;
    }
    public Memento save() {
        System.out.println("Saving to Memento.");
        return new Memento(state);
    }
    public void restore(Memento m) {
        state = m.getState();
        System.out.println("Restore state from: " + state);
    }
}
class Caretaker {
    private ArrayList<Memento> mementos = new ArrayList<>();
    public void addMemento(Memento m) {
        mementos.add(m);
    }
    public Memento getMemento(int index) {
        return mementos.get(index);
    }
}
public class MementoDemo {
    public static void main(String[] args) {
        Caretaker caretaker = new Caretaker();
        Originator originator = new Originator();
        originator.setState("State1");
        originator.setState("State2");
        caretaker.addMemento( originator.save() );
        originator.setState("State3");
        caretaker.addMemento( originator.save() );
        originator.setState("State4");
        originator.restore( caretaker.getMemento(0) );
    }
}
