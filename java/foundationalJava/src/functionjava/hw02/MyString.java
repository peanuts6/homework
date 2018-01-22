package functionjava.hw02;

import java.util.Arrays;
import java.util.Base64;

/**
 * Created by xqy on 18/1/22.
 */
public class MyString implements Decorator {
    public String str;
    public MyString(String _str){
        str = _str;
    }
    @Override
    public Decorator encode() {
        str = Base64.getEncoder().encodeToString(str.getBytes());
        return this;
    }

    @Override
    public Decorator reverse() {
        str = new StringBuilder(str).reverse().toString();
        return this;
    }

    @Override
    public Decorator toUpperCase() {
        str = str.toUpperCase();
        return this;
    }

    @Override
    public Decorator toLowerCase() {
        str = str.toLowerCase();
        return this;
    }

    @Override
    public Decorator fixed() {
        int len = 10-str.length();
        if(len>0){
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=0;i<len;i++){
                stringBuilder.append("!");
            }
            str = str.concat(stringBuilder.toString());
        }else{
            str = str.substring(0,9);
        }
        return this;
    }

    @Override
    public void end() {
        System.out.println(str);
    }
}
