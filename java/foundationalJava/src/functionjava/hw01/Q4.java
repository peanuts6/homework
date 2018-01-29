package functionjava.hw01;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by xqy on 18/1/26.
 */
public class Q4 {
    public static void main(String[] args) {
        Random r = new Random();
        System.out.println(Math.random());
        System.out.println(r.nextInt(10000));
        System.out.println("Random String: "+SortUtil.getRandomString(5));
        System.out.println("Random String2: "+SortUtil.RandomString(5));

        Salary[] arr = SortUtil.makeArray();
        System.out.println("===============乱序");
        Arrays.stream(arr).limit(10).forEach(x->System.out.println(x));
        Arrays.sort(arr, Salary.BY_TOTALINCOME);
        System.out.println("===============按总收入排序(从高到底)");
        Arrays.stream(arr).limit(10).forEach(x->System.out.println(x));
        Arrays.sort(arr, Salary.BY_BASESALARY);
        System.out.println("===============按基本薪资排序(从高到低)");
        Arrays.stream(arr).limit(10).forEach(x->System.out.println(x));
        Arrays.sort(arr, Salary.BY_BONUS);
        System.out.println("===============按奖金排序(从高到低)");
        Arrays.stream(arr).limit(10).forEach(x->System.out.println(x));
        Arrays.sort(arr, Salary.BY_NAME);
        System.out.println("===============按名称(自然排序)");
        Arrays.stream(arr).limit(10).forEach(x->System.out.println(x));
    }
}
