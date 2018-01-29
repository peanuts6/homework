/**
 * Created by xqy on 18/1/24.
 */
public class HeapSort {
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int k = N/2; k >= 1; k--)
            sink(a, k, N);
        while (N > 1)
        {
            exch(a, 1, N);
            sink(a, 1, --N);
        }
    }
    private static void sink(Comparable[] a,int k,int N)
    {
        while (2*k <= N)
        {
            int j = 2*k;
            if (j < N && less(a,j, j+1)) j++;
            if (!less(a,k, j)) break;
            exch(a,k, j);
            k = j;
        }
    }
    private static boolean less(Comparable[] pq,int i, int j)
    {
        return pq[i].compareTo(pq[j]) < 0;
    }
    private static void exch(Comparable[] pq,int i, int j)
    {
        Comparable t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
