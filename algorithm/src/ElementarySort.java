import java.util.Random;

/**
 * Created by xqy on 18/1/24.
 */
public class ElementarySort
{
    public static void selectionSort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
        {
            int min = i;
            for (int j = i+1; j < N; j++)
                if (less(a[j], a[min]))
                    min = j;
            exch(a, i, min);
        }
    }

    public static void insertionSort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
            for (int j = i; j > 0; j--)
                if (less(a[j], a[j-1]))
                    exch(a, j, j-1);
                else break;
    }

    public static void shellSort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, ... 3X+1 increment sequence
        while (h >= 1) {  // h-sort the array.
            for (int i = h; i < N; i++) //insertion sort
            {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                    exch(a, j, j-h);
            }
            h = h/3; //move to next increment
        }
    }

    /*
     * MergeSort
     */
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
    {
        for (int k = lo; k <= hi; k++)  // copy
            aux[k] = a[k];

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++)
        {
            if      (i > mid)               a[k] = aux[j++];
            else if (j > hi)                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))  a[k] = aux[j++];
            else                            a[k] = aux[i++];
        }
    }
    private static void mergeSort(Comparable[] a, Comparable[] aux, int lo, int hi)
    {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid+1, hi);
        if (!less(a[mid+1], a[mid])) return;
        merge(a, aux, lo, mid, hi);
    }
    public static void mergeSort(Comparable[] a)
    {
        Comparable[] aux = new Comparable[a.length];
        mergeSort(a, aux, 0, a.length - 1);
    }

    /*
     * QuickSort
     */
    private static int partition(Comparable[] a, int lo, int hi)
    {
        int i = lo, j = hi+1;
        while (true)
        {
            while (less(a[++i], a[lo])) //从左往右
                if (i == hi) break;
            while (less(a[lo], a[--j])) //从右往左
                if (j == lo) break;
            if (i >= j) break;          //到中间交叉点
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
    public static void quickSort(Comparable[] a)
    {
        shuffle(a);
        quickSort(a, 0, a.length - 1);
    }
    private static void quickSort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        quickSort(a, lo, j-1);
        quickSort(a, j+1, hi);
    }
    //quicksort会产生大量数组，可以拆分成多个小块数据插入排序
    private static void cutoffQuickSortToInsertionSort(Comparable[] a, int lo, int hi){
        final int CUTOFF = 10;
        if (hi <= lo + CUTOFF - 1){
            insertionSort(a);
//            insertionSort(a,lo,hi);
            return;
        }
        int j = partition(a, lo, hi);
        cutoffQuickSortToInsertionSort(a, lo, j-1);
        cutoffQuickSortToInsertionSort(a, j+1, hi);
    }
    //选择拆分点，左边元素<=拆分点<=右边元素，一般都不用，选择第一个作为拆分点就行
    public static Comparable select(Comparable[] a, int k)
    {
        shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo)
        {
            int j = partition(a, lo, hi);
            if      (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else            return a[k];
        }
        return a[k];
    }
    //解决重复元素问题，3-way quick sort
    private static void threeWayQuickSort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        threeWayQuickSort(a, lo, lt - 1);
        threeWayQuickSort(a, gt + 1, hi);
    }



            //取0～数组下标的随机数，然后排序
    public static void shuffle(Comparable[] a){
        int N = a.length;
        Random random = new Random();
        for (int i = 0; i < N; i++)
        {
            int r = random.nextInt(i); //0~i的随机数
            exch(a, i, r);
        }
    }

    public static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    public static void exch(int i, int j){
        int swap = i;
        i=j;
        j=swap;
    }

    private static boolean isSorted(Comparable[] a)
    {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

}
