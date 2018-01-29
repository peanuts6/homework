/**
 * Created by xqy on 18/1/24.
 */
public class UnorderedMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;   // pq[i] = ith element on pq
    private int N;      // number of elements on pq

    public UnorderedMaxPQ(int capacity)
    {
        pq = (Key[]) new Comparable[capacity]; //no generic array creation
    }
    public boolean isEmpty()
    {
        return N == 0;
    }
    public void insert(Key x)
    {
        pq[N++] = x;
    }
    public Key delMax()
    {
        int max = 0;
        for (int i = 1; i < N; i++)
            if (ElementarySort.less(max, i)) max = i;
        ElementarySort.exch(max, N-1);
        return pq[--N];
    }

}
