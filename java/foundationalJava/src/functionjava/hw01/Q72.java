package functionjava.hw01;

import java.util.*;

/**
 * Created by xqy on 18/1/15.
 */
public class Q72 {
    public static final int ARR_SIZE = 1000;

    public static void main(String[] args){
        Random r = new Random();
//        7-2
        System.out.println("==type== ==color== ==price==");
        ByteStoreInt byteStoreInt = new ByteStoreInt();
//        byteStore.putMyItem(0,new MyItem((byte) 1,(byte) 2,(byte) 13));
//        System.out.println(byteStore.getMyItem(0));
        for(int i=0;i<ARR_SIZE;i++){
            MyItem item = new MyItem((byte) r.nextInt(127),(byte) r.nextInt(127), (byte) (r.nextInt(127)));
            byteStoreInt.putMyItem(i,item);
            //System.out.println(byteStoreInt.getMyItem(i));
        }
        Integer[] index = new Integer[ARR_SIZE];
        for(int i=0;i<ARR_SIZE;i++){
            index[i] = i;
        }
        System.out.println("==type== ==color== ==price==");
        Arrays.sort(index,Comparator.comparingInt(x->(byteStoreInt.storeByteArry[(Integer) x] & 0x000000ff)).reversed());
        for(int i=0;i<100;i++){
            System.out.println(byteStoreInt.storeByteArry[i]+" == ");
            System.out.println(byteStoreInt.getMyItem(index[i]));
        }
    }


}
