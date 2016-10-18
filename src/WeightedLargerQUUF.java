import edu.princeton.cs.introcs.StdOut;

/**
 * Created by teocci on 10/18/16.
 */
public class WeightedLargerQUUF
{
    private int[] id;
    private int[] sz;
    private int[] gt;

    public WeightedLargerQUUF(int N)
    {
        id = new int[N];
        sz = new int[N];
        gt = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
            gt[i] = i;
        }
    }

    public int root(int i)
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

    // return the largest element in the connected component containing p
    public int find(int p) {
        return gt[root(p)];
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
        if (gt[oldRoot] > gt[newRoot]) gt[newRoot] = gt[oldRoot];
        else gt[oldRoot] = gt[newRoot];
    }

    public static void main(String[] args) {
        int N = 20;
        WeightedLargerQUUF uf = new WeightedLargerQUUF(N);

        for (int idx = 1; idx <= N/2 + 5; ++idx){

            if (RandomRange.randomInt(0, 1) == 0) {
                int p = RandomRange.randomInt(0, N-1);
                int q = RandomRange.randomInt(0, N-1);
                while (p == q)
                    q = RandomRange.randomInt(0, N-1);
                uf.union(p, q);
                StdOut.println("union(" + p + ", " + q + ")");
            } else {
                int x = RandomRange.randomInt(0, N-1);
                StdOut.println("find(" + x + ") = " + uf.find(x));
            }
        }

        StdOut.println("======");
        for (int idx = 0; idx < N; ++idx){
            StdOut.println("root(" + idx + ") = " + uf.root(idx));
        }


        /*uf.union(0, 2);
        StdOut.println("union(0, 2)");
        uf.union(80, 4);
        StdOut.println("union(80, 4)");
        StdOut.println("find(0) = " + uf.find(0));
        StdOut.println("find(4) = " + uf.find(4));
        uf.union(0, 4);
        StdOut.println("union(0, 4)");
        StdOut.println("find(0) = " + uf.find(0));
        StdOut.println("find(2) = " + uf.find(2));
        uf.union(0, 6);
        StdOut.println("union(0, 6)");
        StdOut.println("find(6) = " + uf.find(6));
        uf.union(1, 93);
        StdOut.println("union(1, 93)");
        uf.union(1, 2);
        StdOut.println("union(1, 2)");
        StdOut.println("find(4) = " + uf.find(4));*/
    }
}
