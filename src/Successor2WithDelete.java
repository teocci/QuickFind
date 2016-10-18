import edu.princeton.cs.introcs.StdOut;

/**
 * Created by teocci on 10/18/16.
 *
 * Successor with delete.
 * Given a set of N integers S={0,1,...,N?1} and a sequence of requests of the following form:
 *  - Remove x from S
 *  - Find the successor of x: the smallest y in S such that y>=x.
 * design a data type so that all operations (except construction) should take logarithmic time or better.
 */

public class Successor2WithDelete {

    private int[] id;
    private int[] sz;
    private int[] sr;
    private boolean[] removed; // d[i] == true if removed
    private int N; // N integers in S

    public Successor2WithDelete(int N) {
        id = new int[N];
        sz = new int[N];
        sr = new int[N];
        removed = new boolean[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
            sr[i] = i;
            removed[i] = false;
        }
    }

    private int root(int i)
    {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    private int successor(int i)
    {
        if (i < N)  {
            if (!removed[i]) {
                return i;
            } else {
                return successor(i + 1);
            }
        } else {
            StdOut.println("Error, no successor can be found");
            return -1;
        }
    }

    public void remove(int x) {
        removed[x] = true;
        int lesser = -1, greater = -1;

        if (x > 0 && !removed[x-1])
            lesser = x-1;
        if (x < N - 1 && !removed[x+1])
            greater = x+1;

    }

    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

    // return the largest element in the connected component containing p
    public int find(int p) {
        return sr[root(p)];
    }

    public static void main(String[] args) {
        Successor2WithDelete test = new Successor2WithDelete(10);
        test.remove(2);
        StdOut.println(test.successor(2) == 3);
        test.remove(3);
        StdOut.println(test.successor(2) == 4);
        StdOut.println(test.successor(8) == 8);
        test.remove(8);
        StdOut.println(test.successor(8) == 9);
        test.remove(9);
        StdOut.println(test.successor(8) == -1);
        test.remove(5);
        test.remove(4);
        StdOut.println(test.successor(3) == 6);
    }
}
