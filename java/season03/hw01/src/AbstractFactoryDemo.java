/**
 * Created by xqy on 17/8/11.
 */

abstract class AbstractPepperoniPizza{}
class RomePepperoniPizza extends AbstractPepperoniPizza{
    RomePepperoniPizza(){
        System.out.println("create Rome Pepperoni pizza");
    }
}
class MilonPepperoniPizza extends AbstractPepperoniPizza{
    MilonPepperoniPizza(){
        System.out.println("create Milon Pepperoni pizza");
    }
}

abstract class AbstractAchrovyPizza{}
class RomeAchrovyPizza extends AbstractAchrovyPizza{
    RomeAchrovyPizza(){
        System.out.println("create Rome Achrovy pizza");
    }
}
class MilonAchrovyPizza extends AbstractAchrovyPizza{
    MilonAchrovyPizza(){
        System.out.println("create Milon Achrovy pizza");
    }
}

abstract class AbstractDurianPizza {}
class RomeDurianPizza extends AbstractDurianPizza{
    RomeDurianPizza(){
        System.out.println("create Rome Durian pizza");
    }
}
class MilonDurianPizza extends AbstractDurianPizza{
    MilonDurianPizza(){
        System.out.println("create Milon Durian pizza");
    }
}


class RomePizzaFactory extends AbstractPizzaFactory{
    @Override
    public AbstractPepperoniPizza makePeppenoriPizza(){
        return new RomePepperoniPizza();
    }

    @Override
    public AbstractAchrovyPizza makeAchrovyPizza(){
        return new RomeAchrovyPizza();
    }

    @Override
    public AbstractDurianPizza makeDurianPizza(){
        return new RomeDurianPizza();
    }
}
class MilonPizzaFactory extends AbstractPizzaFactory{
    @Override
    public AbstractPepperoniPizza makePeppenoriPizza(){
        return new MilonPepperoniPizza();
    }

    @Override
    public AbstractAchrovyPizza makeAchrovyPizza(){
        return new MilonAchrovyPizza();
    }

    @Override
    public AbstractDurianPizza makeDurianPizza(){
        return new MilonDurianPizza();
    }
}

abstract class AbstractPizzaFactory{
    private static final RomePizzaFactory ROME_FACTORY = new RomePizzaFactory();
    private static final MilonPizzaFactory MILON_FACTORY = new MilonPizzaFactory();

    static AbstractPizzaFactory getFactory(String factoryName){
        AbstractPizzaFactory factory = null;
        if(factoryName.equals("Rome")){
            factory = ROME_FACTORY;
        } else if (factoryName.equals("Milon")){
            factory = MILON_FACTORY;
        }
        return factory;
    }

    public abstract AbstractPepperoniPizza makePeppenoriPizza();

    public abstract AbstractAchrovyPizza makeAchrovyPizza();

    public abstract AbstractDurianPizza makeDurianPizza();
}

public class AbstractFactoryDemo {
    public static void main(String[] args){
        AbstractPizzaFactory romeFactory = AbstractPizzaFactory.getFactory("Rome");
        romeFactory.makeDurianPizza();

        AbstractPizzaFactory milonFactory = AbstractPizzaFactory.getFactory("Milon");
        milonFactory.makePeppenoriPizza();
    }
}

