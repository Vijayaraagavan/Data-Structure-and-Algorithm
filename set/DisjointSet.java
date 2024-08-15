package set;

import java.util.Arrays;

// docs link: https://docs.google.com/document/d/1zL5bKlZLAv98Ix6lIxWwrLg0l0jAV4bMm7kL9RuaGPo/edit#heading=h.vdpypldpqp9m
public class DisjointSet {
    static public void start() {
        DisjointSet d = new DisjointSet();
        d.startAlgo();
    }

    private int[] dset;
    private int[] rank;

    void init(int n) {
        this.dset = new int[n];
        this.rank = new int[n];

        // Initially each item belongs to its own unique tree/set
        // when we start to union 2 items, one move under another
        // also height of each tree is 1 at start
        for (int i = 0; i < dset.length; i++) {
            dset[i] = i;
            rank[i] = 1;
        }
    }

    int find(int k) {
        int p = dset[k];
        if (p == k)
            return k;
        return find(p);
    }

    void union(int i, int j) {
        int parenti = find(i);
        int parentj = find(j);
        if (parenti == parentj) {
            return;
        }
        // move head of one tree to another (not i or j but their root node.) It
        // essentialy means we are moving whole tree
        dset[parenti] = parentj;
    }

    void startAlgo() {
        init(11);
        // a: 1, b: 2, c: 3, d: 4, e: 5, f: 6, g: 7, h: 8, i: 9, j: 10
        // I have used index 1 instead of 0
        unionByRank(1, 2);
        unionByRank(2, 4);
        unionByRank(3, 6);
        unionByRank(3, 9);
        unionByRank(10, 5);
        unionByRank(7, 10);

        System.out.printf("parent set: %s\n", Arrays.toString(dset));
        test();
    }

    void test() {
        if (find(5) == find(1))
            System.out.println("Yes");
        else
            System.out.println("No");

        // Check if 1 is a friend of 0
        if (find(2) == find(1))
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    void unionByRank(int i, int j) {
        int parenti = find(i);
        int parentj = find(j);
        if (parenti == parentj) {
            return;
        }
        int ranki = rank[i];
        int rankj = rank[j];
        if (ranki == rankj) {
            dset[parenti] = parentj;
            rank[i]++;
        } else if (ranki > rankj) {
            dset[parentj] = parenti;
        } else {
            dset[parenti] = parentj;
        }
    }
}

// Following are relationships to be added:
// a <-> b
// b <-> d
// c <-> f
// c <-> i
// j <-> e
// g <-> j
