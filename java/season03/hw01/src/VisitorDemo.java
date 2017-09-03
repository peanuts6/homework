/**
 * Created by leader on 17/8/14.
 */
interface Element{
    void accept(Visitor v);
}
class One implements Element{
    public void accept(Visitor v){v.visit(this);}
}
class Two implements Element{
    public void accept(Visitor v){v.visit(this);}
}
class Three implements Element{
    public void accept(Visitor v){v.visit(this);}
}
interface Visitor{
    void visit(One one);
    void visit(Two two);
    void visit(Three three);
}
class UpVisitor implements Visitor{
    public void visit(One one){
        System.out.println("do Up on "+one.toString());
    }
    public void visit(Two two){
        System.out.println("do Up on "+two.toString());
    }
    public void visit(Three three){
        System.out.println("do Up on "+three.toString());
    }
}
class DownVisitor implements Visitor{
    public void visit(One one){
        System.out.println("do Down on "+one.toString());
    }
    public void visit(Two two){
        System.out.println("do Down on "+two.toString());
    }
    public void visit(Three three){
        System.out.println("do Down on "+three.toString());
    }
}
public class VisitorDemo {
    public static void main(String[] args){
        Element[] elements = {new One(),new Two(),new Three()};
        UpVisitor up = new UpVisitor();
        DownVisitor down = new DownVisitor();
        for(Element elm:elements){
            elm.accept(up);
            elm.accept(down);
        }
    }
}
