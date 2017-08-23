/**
 * Created by xqy on 17/8/21.
 */
import java.lang.reflect.*;

class SimpleCommand{
    private int state;
    public SimpleCommand(int state){
        this.state = state;
    }
    public void add(Integer value){
        state += value.intValue();
    }
    public void addTwoOperands(Integer value1, Integer value2){
        state = state + value1.intValue() + value2.intValue();
    }
    public int getState(){
        return state;
    }
}
class ReflectCommand{
    private Object receiver;
    private Method action;
    private Object[] args;

    public ReflectCommand(Object obj,String methodName,Object[] arguments){
        this.receiver = obj;
        this.args = arguments;
        Class cls = obj.getClass();
        Class[] argTypes = new Class[args.length];
        for(int i=0;i<args.length;i++) {
            argTypes[i] = args[i].getClass();
        }
        try{
            action = cls.getMethod(methodName, argTypes);
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
    }
    public Object execute(){
        try{
            action.invoke(receiver, args);
            return receiver.getClass().getMethod("getState").invoke(receiver);
        }catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
            e.printStackTrace();
        }
        return null;
    }

}
public class CommandDemo {
    public static void main(String[] args){
        SimpleCommand[] simpleCommands = {new SimpleCommand(0),new SimpleCommand(2)};

        ReflectCommand[] reflectCommands = {
                new ReflectCommand(simpleCommands[0],"add", new Integer[]{3}),
                new ReflectCommand(simpleCommands[1],"addTwoOperands", new Integer[]{4,5})
        };
        System.out.println("commands:");
        for(ReflectCommand r:reflectCommands){
            System.out.println(r.execute()+" ");
        }
        System.out.println(" ");
    }
}
