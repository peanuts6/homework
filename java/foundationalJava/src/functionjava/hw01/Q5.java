package functionjava.hw01;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by xqy on 18/1/26.
 */
public class Q5 {
    public static void main(String[] args){
        Random r = new Random();
        //        5
        ByteStore byteStore = new ByteStore();
        byteStore.putMyItem(0,new MyItem((byte) 1,(byte) 110,(byte) 12));
        byteStore.putMyItem(1,new MyItem((byte) 1,(byte) 120,(byte) 14));
        System.out.println(byteStore.getMyItem(0).toString());
        System.out.println(byteStore.getMyItem(1).toString());
        System.out.println("==========");

        ByteStore byteStore2 = new ByteStore();
        for(int i=0;i<1000;i++){
            MyItem item = new MyItem((byte) r.nextInt(127),(byte) r.nextInt(127), (byte) (r.nextInt(127)));
            byteStore2.putMyItem(i,item);
            //System.out.println(byteStore2.getMyItem(i).toString());
        }
        System.out.println("==type== ==color== ==price==");
        // 索引
        Integer[] index = new Integer[1000];
        for(int i=0;i<1000;i++){
            index[i] = i;
        }
        Arrays.sort(index, Comparator.comparingInt(x->byteStore2.storeByteArry[3* (Integer) x+2]).reversed());
        for(int i=0;i<100;i++){
            System.out.println(byteStore2.getMyItem(index[i]));
        }
    }
}
