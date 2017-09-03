/**
 * Created by leader on 17/8/15.
 */
abstract class Generalization {
    //算法步骤骨架
    void findSolution() {
        stepOne();
        stepTwo();
        stepThr();
        stepFor();
    }
    //通用部分
    private void stepOne() {
        System.out.println("Generalization.stepOne");
    }
    //自定义部分
    abstract void stepTwo();
    abstract void stepThr();
    //通用部分
    void stepFor() {
        System.out.println( "Generalization.stepFor" );
    }
}
abstract class Specialization extends Generalization {
    //替换骨架stepThr部分占位符
    protected void stepThr() {
        step3_1();
        step3_2();
        step3_3();
    }
    //
    private void step3_1() {
        System.out.println("        Specialization.step3_1");
    }
    //占位符
    abstract protected void step3_2();
    //
    private void step3_3() {
        System.out.println("        Specialization.step3_3");
    }
}
class Realization extends Specialization {
    //替换骨架stepTwo部分占位符
    protected void stepTwo() {
        System.out.println("    Realization.stepTwo");
    }
    protected void step3_2() {
        System.out.println("        Realization.step3_2");
    }
    protected void stepFor() {
        System.out.println("    Realization.stepFor");
        super.stepFor();
    }
}
public class TemplateMethodDemo {
    public static void main(String[] args) {
        Generalization algorithm = new Realization();
        algorithm.findSolution();
    }
}
