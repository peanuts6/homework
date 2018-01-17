package functionjava.hw01;

import java.io.Serializable;

/**
 * Created by xqy on 18/1/16.
 */
public class MyItem implements Serializable{
    byte type;
    byte color;
    byte price;
    public MyItem(byte type,byte color,byte price){
        this.type = type;
        this.color = color;
        this.price = price;
    }
    public String toString(){
        return " "+this.type+" \t\t"+this.color+" \t\t"+this.price+" ";
    }
}
