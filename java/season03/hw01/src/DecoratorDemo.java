/**
 * Created by xqy on 17/8/12.
 */

interface Widget {
    void draw();
}
class TextField implements Widget {
    private int width, height;

    public TextField(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public void draw() {
        System.out.println("TextField: " + width + ", " + height);
    }
}
abstract class Decorator implements Widget {
    private Widget widget;

    public Decorator(Widget widget) {
        this.widget = widget;
    }

    public void draw() {
        widget.draw();
    }
}
class BorderDecorator extends Decorator {
    public BorderDecorator(Widget widget) {
        super(widget);
    }
    public void draw() {
        super.draw();
        System.out.println("Add border");
    }
}
class ScrollDecorator extends Decorator {
    public ScrollDecorator(Widget widget) {
        super(widget);
    }
    public void draw() {
        super.draw();
        System.out.println("Add scrollbar");
    }
}

public class DecoratorDemo {
    public static void main(String[] args) {
        Widget widget = new BorderDecorator(
                new BorderDecorator(
                        new ScrollDecorator(
                                new TextField(80, 24))));
        widget.draw();
    }
}
