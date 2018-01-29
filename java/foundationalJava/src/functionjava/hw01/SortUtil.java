package functionjava.hw01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xqy on 18/1/26.
 */
public class SortUtil {
    public static Random r = new Random();

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
            arr[i] = new Salary(RandomString(5),r.nextInt(100000-50000)+50000,r.nextInt(100000));
        }
        return arr;
    }
    public static Salary[] makeArray(int size){
        Salary[] arr = new Salary[size];
        for(int i=0;i<size;i++){
            arr[i] = new Salary(RandomString(5),r.nextInt(100000-50000)+50000,r.nextInt(100000));
        }
        return arr;
    }
    public static List<Salary> makeList(int size){
        List<Salary> arr = new ArrayList<>();
        for(int i=0;i<size;i++){
            arr.add(new Salary(RandomString(5),r.nextInt(100000-50000)+50000,r.nextInt(100000)));
        }
        return arr;
    }
    public static Salary1[] makeArray2(){
        int ARR_SIZE=10000;
        Salary1[] arr = new Salary1[ARR_SIZE];
        for(int i=0;i<ARR_SIZE;i++){
            arr[i] = new Salary1(RandomString(5),r.nextInt(1000000-50000)+50000,r.nextInt(10));
        }
        return arr;
    }
}
