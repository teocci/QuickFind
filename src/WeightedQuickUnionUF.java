/**
 * Created by teocci on 10/18/16.
 */
public class WeightedQuickUnionUF
{
    private int[] id;
    private int[] sz;
    public WeightedQuickUnionUF(int N)
    {
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
    }

    private int root(int i)
    {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        int newRoot;
        int oldRoot;
        if (i == j) return;
        if (sz[i] < sz[j]) {
            newRoot = j; oldRoot = i;
        } else {
            newRoot = i; oldRoot = j;
        }

        id[oldRoot] = newRoot;
        sz[newRoot] += sz[oldRoot];
    }
}