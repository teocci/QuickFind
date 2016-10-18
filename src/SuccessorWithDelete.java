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

public class SuccessorWithDelete {
    private boolean data[]; // data[i] == false if removed
    public WeightedLargerQUUF uf; // used to find largest un-removed element
    private int N; // N integers in S

    public SuccessorWithDelete(int N) {
        this.N = N;
        data = new boolean[N];
        for (int i = 0; i < N; ++i)
            data[i] = true;
        uf = new WeightedLargerQUUF(N);
    }

    public void remove(int x) {
        data[x] = false;
        if (x > 0 && !data[x-1])
            uf.union(x, x-1);
        if (x < N - 1 && !data[x+1])
            uf.union(x, x+1);
    }

    public int successor(int x) {
        if (data[x]) {
            return x;
        } else {
            int res = uf.find(x) + 1;
            if (res >= N) {
                //StdOut.println("Error, no successor can be found");
                return -1;
            } else {
                return res;
            }
        }
    }

    public static void main(String[] args) {
        int N = 20;
        SuccessorWithDelete swd = new SuccessorWithDelete(N);

        for (int idx = 1; idx <= N*2; ++idx){

            if (RandomRange.randomInt(0, 1) == 0) {
                int p = RandomRange.randomInt(0, N-1);
                swd.remove(p);
                StdOut.println("remove(" + p + ")");
            } else {
                int x = RandomRange.randomInt(0, N-1);
                StdOut.println("successor(" + x + ") = " + swd.successor(x));
            }
        }

        StdOut.println("======");
        for (int idx = 0; idx < N; ++idx){
            int successor = swd.successor(idx);
            StdOut.println("successor(" + idx + ") = " + (successor == -1 ? "Eliminated" : successor));
        }
    }
}
