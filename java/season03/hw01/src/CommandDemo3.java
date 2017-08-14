/**
 * Created by xqy on 17/8/14.
 */
import java.util.*;

//receiver
interface FileSystemReceiver {
    void openFile();
    void writeFile();
    void closeFile();
}
class FileReceiver implements FileSystemReceiver{
    @Override
    public void openFile(){
        System.out.println("openFile");
    }
    @Override
    public void writeFile(){
        System.out.println("writeFile");
    }
    @Override
    public void closeFile(){
        System.out.println("closeFile");
    }
}
//abstract command
interface Action {
    public void perform();
}
//concrete command
class OpenFile implements Action {
    private final FileReceiver fileReceiver;
    public OpenFile(FileReceiver fileReceiver) {
        this.fileReceiver = fileReceiver;
    }
    public void perform() {
        fileReceiver.openFile();
    }
}
class CloseFile implements Action {
    private final FileReceiver fileReceiver;
    public CloseFile(FileReceiver fileReceiver) {
        this.fileReceiver = fileReceiver;
    }
    public void perform() {
        fileReceiver.closeFile();
    }
}
class WriteFile implements Action {
    private final FileReceiver fileReceiver;
    public WriteFile(FileReceiver fileReceiver) {
        this.fileReceiver = fileReceiver;
    }
    public void perform() {
        fileReceiver.writeFile();
    }
}
//invoker
class Macro {
    private final List actions;

    public Macro() {
        actions = new ArrayList<>();
    }

    public void record(Action action) {
        actions.add(action);
    }

    public void run() {
        //actions.forEach(Action::perform);
    }
}

public class CommandDemo3 {
    public static void main(String[] args){
        FileReceiver fileReceiver = new FileReceiver();
        Macro macro = new Macro();
        macro.record(fileReceiver::openFile);
        macro.record(fileReceiver::writeFile);
        macro.record(fileReceiver::closeFile);
        macro.run();
    }
}
