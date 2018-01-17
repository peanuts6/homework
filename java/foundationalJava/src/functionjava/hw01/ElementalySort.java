package functionjava.hw01;

import java.util.Comparator;

/**
 * Created by xqy on 18/1/16.
 */
public class ElementalySort
{
    public static void selectionSort(Comparable[] a){
        int N = a.length;
        for(int i=0;i<N;i++){
            int max = i;
            for(int j=i+1;j<N;j++){
                if(more(a[j],a[max])){
                    max = j;
                }
            }
            exch(a,i,max);
        }
    }
    public static void insertionSort(Comparable[] a){
        int N = a.length;
        for(int i=0;i<N;i++){
            for(int j=i;j>0;j--){
                if(more(a[j],a[j-1]))
                    exch(a,j,j-1);
                else break;
            }
        }
    }
    public static void shellSort(Comparable[] a){
        int N=a.length;
        int h=1;
        //3x+1的递增步长
        while(h<N/3) h=3*h+1;
        while(h>1){
            //插入排序
            for(int i=h;i<N;i++){
                for(int j=i;j>=h && more(a[j],a[j-h]);j-=h)
                    exch(a,j,j-h);
            }
            //移动到下一个步长
            h=h/3;
        }
    }
    public static void merge(Comparable[] a, Comparable[] b,int lo,int mid,int hi){
        //assert isMergeSorted(a,lo,mid);
        //assert isMergeSorted(a,mid+1,hi);
        //复制一个相同大小的数组
        for(int k=lo;k<=hi;k++)
            b[k] = a[k];

        int i = lo, j = mid+1;
        //合并
        for(int k = lo; k <= hi; k++){
            if(i>mid) a[k] = b[j++];
            else if(j>hi) a[k] = b[i++];
            else if(more(b[j],b[i])) a[k] = b[j++];
            else a[k]=b[i++];
        }
         //assert isMergeSorted(a,lo,hi);
    }
    public static void mergeSort(Comparable[] a,Comparable[] b,int lo,int hi){
        if(hi<=lo) return;
        int mid = lo+(hi-lo)/2;
        mergeSort(a,b,lo,mid);
        mergeSort(a,b,mid+1,hi);
        merge(a,b,lo,mid,hi);
    }
    public static void mergeSort(Comparable[] a,Comparable[] b){
        mergeSort(a,b,0,a.length -1);
    }
    public static int partition(Comparable[] a, int lo, int hi){
        int i=lo,j=hi+1;
        while(true){
            //从左边
            while(more(a[++i],a[lo]))
                if(i==hi) break;
            //从右边
            while (more(a[lo],a[--j]))
                if(j==lo) break;
            if(i>=j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }
    public static void quickSort(Comparable[] a){
        quickSort(a,0,a.length-1);
    }
    public static void quickSort(Comparable[] a,int lo,int hi){
        if(hi<=lo) return;
        int j=partition(a,lo,hi);
        quickSort(a,lo,j-1);
        quickSort(a,j+1,hi);
    }

    public static boolean more(Comparable a,Comparable b){
        return a.compareTo(b)<0;
    }
    public static void exch(Comparable[] a, int i, int j){
        if(i==j) return;
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    public static boolean isSoted(Comparable[] a){
        for(int i=0;i<a.length;i++){
            if(more(a[i],a[i-1])){
                return true;
            }
        }
        return false;
    }
}
