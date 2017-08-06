package xqy.season03.hw01;

import java.util.*;

/**
 * Created by xqy on 17/8/7.
 */
interface Command {
    void executed();
}

class DomesticEngineer implements Command {
    public void execute() {
        System.out.println("take out the trash");
    }
}

class Politician implements Command {
    public void execute() {
        System.out.println("take money from the rich, take votes from the poor");
    }
}

class Programmer implements Command {
    public void execute() {
        System.out.println("sell the bugs, charge extra for the fixes");
    }
}

public class CommandDemo1 {
    public static List produceRequests() {
        List<Command> queue = new ArrayList<>();
        queue.add(new DomesticEngineer());
        queue.add(new Politician());
        queue.add(new Programmer());
        return queue;
    }

    public static void workOffRequests(List queue) {
        for (Object command : queue) {
            ((Command)command).execute();
        }
    }

    public static void main( String[] args ) {
        List queue = produceRequests();
        workOffRequests(queue);
    }
}
