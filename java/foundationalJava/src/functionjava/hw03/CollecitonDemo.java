package functionjava.hw03;

import java.util.*;

/**
 * Created by xqy on 18/1/23.
 */
public class CollecitonDemo {
    public static void main(String[] args){
//        Integer[] a = {1,2,3,4,5,6,7};
//        List<Integer> b = new ArrayList<>();
//        b = Arrays.asList(a);
//        System.out.println("Array to list: ");
//        b.stream().forEach(x->System.out.println(x));
//        System.out.println("List to array: ");
//        Object[] d = b.toArray();
//        for(Object bb:d){
//            System.out.println(bb);
//        }

        Map map = new IdentityHashMap();
        Integer a=5;
        Integer b=5;
        map.put(a,"100");
        map.put(b,"100");
        System.out.println(map.size());
        map.clear();
        Integer c=Integer.MAX_VALUE-1;
        Integer d=Integer.MAX_VALUE-1;
        map.put(c,"100");
        map.put(d,"100");
        System.out.println(map.size());
    }
}
