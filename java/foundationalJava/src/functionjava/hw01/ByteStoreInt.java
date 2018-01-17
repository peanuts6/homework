package functionjava.hw01;

/**
 * Created by xqy on 18/1/17.
 */
public class ByteStoreInt {
    public static int SIZE = 1000;
    public int[] storeByteArry = new int[SIZE];

    public void putMyItem(int index, MyItem item){
        storeByteArry[index] = item.type<<4 & item.color<<2 & item.price<<0;
    }
    public MyItem getMyItem(int index){
        byte price = (byte) (0x000000ff & storeByteArry[index]);
        byte color = (byte) (0x0000ff00 & storeByteArry[index]);
        byte type = (byte) (0x00ff0000 & storeByteArry[index]);
        return new MyItem(type,color,price);
    }
}
