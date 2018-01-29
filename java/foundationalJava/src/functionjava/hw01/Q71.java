package functionjava.hw01;

import java.util.Arrays;

/**
 * Created by xqy on 18/1/26.
 */
public class Q71 {

    public static void main(String[] args){
        //        7-1
        Salary1[] arr2 = SortUtil.makeArray2();
        ElementalySort.selectionSort(arr2);
        Arrays.stream(arr2).limit(100).forEach(x->System.out.println(x.name+":"+x.totalIncome/10000+"万"));

        Salary1[] arr3 = SortUtil.makeArray2();
//        Salary1[] arr4 = new Salary1[arr3.length];
//        ElementalySort.mergeSort(arr3,arr4);
        ElementalySort.quickSort(arr3);
        Arrays.stream(arr3).limit(100).forEach(x->System.out.println(x.name+":"+x.totalIncome/10000+"万"));
    }
}
