package functionjava.hw02;

/**
 * Created by xqy on 18/1/22.
 */
public interface Decorator {
    public Decorator encode();
    public Decorator reverse();
    public Decorator toUpperCase();
    public Decorator toLowerCase();
    public Decorator fixed();
    public void end();
}
