package functionjava.hw01;

/**
 * Created by xqy on 18/1/16.
 */
public class ByteStore
{
    public static int SIZE = 1000;
    public byte[] storeByteArry = new byte[SIZE*3];
    public void putMyItem(int index, MyItem item){
        storeByteArry[index*3] = item.type;
        storeByteArry[index*3+1] = item.color;
        storeByteArry[index*3+2] = item.price;
    }
    public MyItem getMyItem(int index){
        return new MyItem(storeByteArry[index*3],storeByteArry[index*3+1],storeByteArry[index*3+2]);
    }
}
