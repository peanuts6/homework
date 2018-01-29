/**
 * Created by xqy on 18/1/24.
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public MaxPQ(int capacity)
    {
        pq = (Key[]) new Comparable[capacity+1];
    }
    public boolean isEmpty()
    {
        return N == 0;
    }
    public void insert(Key key){}
    public Key delMax(){
        Key max = pq[1];
        ElementarySort.exch(1,N--);
        sink(1);
        pq[N+1] = null;
        return max;
    }
    private void swim(int k){}
    private void sink(int k)
    {
        while (2*k <= N)
        {
            int j = 2*k;
            if (j < N && ElementarySort.less(j, j+1)) j++;
            if (!ElementarySort.less(k, j)) break;
            ElementarySort.exch(k, j);
            k = j;
        }
    }
    private boolean less(int i, int j)
    {
        return pq[i].compareTo(pq[j]) < 0;
    }
    private void exch(int i, int j)
    {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
