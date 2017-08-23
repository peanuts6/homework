/**
 * Created by xqy on 17/8/21.
 */
interface Strategy{
    boolean validate(String val);
}
class IsNotNull implements Strategy{
    public boolean validate(String val){
        return !val.equals("");
    }
}
class IsNumber implements Strategy{
    public boolean validate(String val){
        return true;
    }
}
//wrapper
class Validator{
    private Strategy strategy;
    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }
    public boolean validate(String val){
        return strategy.validate(val);
    }
}
public class StrategyValidator {
    public static void main(String[] args){
        Validator validator = new Validator();
        validator.setStrategy(new IsNotNull());
        System.out.println("assert 1000 is not null: "+(validator.validate("1000")?"true":"false"));
    }
}
