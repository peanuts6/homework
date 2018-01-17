package functionjava.hw01;

import java.util.*;

/**
 * Created by xqy on 18/1/15.
 */
public class SortDemo {
    public static Random r = new Random();
    public static final int ARR_SIZE = 1000;

    public static void main(String[] args){

//        4
//        System.out.println(Math.random());
//        System.out.println(r.nextInt(10000));
//        System.out.println("Random String: "+getRandomString(5));
//        System.out.println("Random String2: "+RandomString(5));
//
//        Salary[] arr = makeArray();
//        System.out.println("===============乱序");
//        Arrays.stream(arr).limit(10).forEach(x->System.out.println(x));
//        Arrays.sort(arr, Salary.BY_TOTALINCOME);
//        System.out.println("===============按总收入排序(从高到底)");
//        Arrays.stream(arr).limit(10).forEach(x->System.out.println(x));
//        Arrays.sort(arr, Salary.BY_BASESALARY);
//        System.out.println("===============按基本薪资排序(从高到低)");
//        Arrays.stream(arr).limit(10).forEach(x->System.out.println(x));
//        Arrays.sort(arr, Salary.BY_BONUS);
//        System.out.println("===============按奖金排序(从高到低)");
//        Arrays.stream(arr).limit(10).forEach(x->System.out.println(x));
//        Arrays.sort(arr, Salary.BY_NAME);
//        System.out.println("===============按名称(自然排序)");
//        Arrays.stream(arr).limit(10).forEach(x->System.out.println(x));


//        5
//        ByteStore byteStore = new ByteStore();
//        byteStore.putMyItem(0,new MyItem((byte) 1,(byte) 110,(byte) 12));
//        byteStore.putMyItem(1,new MyItem((byte) 1,(byte) 120,(byte) 14));
//        System.out.println(byteStore.getMyItem(0).toString());
//        System.out.println(byteStore.getMyItem(1).toString());
//        System.out.println("==========");
//
//        ByteStore byteStore2 = new ByteStore();
//        for(int i=0;i<1000;i++){
//            MyItem item = new MyItem((byte) r.nextInt(127),(byte) r.nextInt(127), (byte) (r.nextInt(127)));
//            byteStore2.putMyItem(i,item);
//            //System.out.println(byteStore2.getMyItem(i).toString());
//        }
//        System.out.println("==type== ==color== ==price==");
//        // 索引
//        Integer[] index = new Integer[1000];
//        for(int i=0;i<1000;i++){
//            index[i] = i;
//        }
//        Arrays.sort(index,Comparator.comparingInt(x->byteStore2.storeByteArry[3* (Integer) x+2]).reversed());
//        for(int i=0;i<100;i++){
//            System.out.println(byteStore2.getMyItem(index[i]));
//        }

//        7-1
        SortDemo sortDemo = new SortDemo();
//        Salary1[] arr2 = sortDemo.makeArray2();
//        ElementalySort.selectionSort(arr2);
//        Arrays.stream(arr2).limit(100).forEach(x->System.out.println(x.name+":"+x.totalIncome+"万"));

//        Salary1[] arr3 = sortDemo.makeArray2();
////        Salary1[] arr4 = new Salary1[arr3.length];
////        ElementalySort.mergeSort(arr3,arr4);
//        ElementalySort.quickSort(arr3);
//        Arrays.stream(arr3).limit(100).forEach(x->System.out.println(x.name+":"+x.totalIncome+"万"));

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

    public static String RandomString(int len){
        StringBuilder s = new StringBuilder();
        r.ints(0,128)
                .filter(i->Character.isAlphabetic(i))
                .limit(len)
                .forEach(i-> s.append((char) i));
        return s.toString();
    }

    public static String getRandomString(int len){
        char[] a = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            };

        String str = "";
        for(int i=0;i<len;i++){
            str += a[r.nextInt(a.length)];
        }
        return str;
    }

    public static Salary[] makeArray(){
        int ARR_SIZE=10000;
        Salary[] arr = new Salary[ARR_SIZE];
        for(int i=0;i<ARR_SIZE;i++){
            arr[i] = new Salary(RandomString(5),r.nextInt(1000000-50000+1)+50000,r.nextInt(100000));
        }
        return arr;
    }
    public Salary1[] makeArray2(){
        int ARR_SIZE=10000;
        Salary1[] arr = new Salary1[ARR_SIZE];
        for(int i=0;i<ARR_SIZE;i++){
            arr[i] = new Salary1(RandomString(5),r.nextInt(1000000-50000+1)+50000,r.nextInt(100000));
        }
        return arr;
    }

    public class Salary1 implements Comparable<Salary1>{
        String name;
        int baseSalary;
        int bonus;
        int totalIncome;
        public Salary1(String name,int baseSalary,int bonus){
            this.name = name;
            this.baseSalary = baseSalary;
            this.bonus = bonus;
            this.totalIncome = baseSalary*13 + bonus;
        }

        @Override
        public int compareTo(Salary1 o) {
            return o.totalIncome - this.totalIncome;
        }
    }


}
