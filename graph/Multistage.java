package graph;

import java.util.Arrays;

public class Multistage {
    final static int INF = 99999, V = 0;

    public static void start() {
        Multistage d = new Multistage();
        d.multistage();
    }

    void multistage() {
        int[][] g = new int[][] {
            { INF, 1, 2, 5, INF, INF, INF, INF },
            { INF, INF, INF, INF, 4, 11, INF, INF },
            { INF, INF, INF, INF, 9, 5, 16, INF },
            { INF, INF, INF, INF, INF, INF, 2, INF },
            { INF, INF, INF, INF, INF, INF, INF, 18 },
            { INF, INF, INF, INF, INF, INF, INF, 13 },
            { INF, INF, INF, INF, INF, INF, INF, 2 }
        };
        int[] spt = new int[g.length];
        Arrays.fill(spt, INF);
        stage(g, spt, 0, 7);
        System.out.println(Arrays.toString(spt));
    }

    int stage(int[][] g, int[] spt, int v, int target) {
        // termination condition. cost of reaching target vertex from self is 0
        if (v == target) {
            return 0;
        }
        // min to track least distance among adjacent possibilities. minV to track vertex 
        int min = INF;
        int minV = 0;
        // Using dynamic programming, if we already have result for given input say M(2, 3)
        // where 2 is stage, 3 is vertex, then use already computed value
        if (spt[v] != INF) return spt[v];
        for (int i = 0; i < g[0].length; i++) {
            // Ingore if there is no edge
            if (g[v][i] == INF) continue;
            System.out.printf("stage: %d -> %d\n", v, i);
            // I think this check is not needed. since target will be returned at termination condition itself
            if (v != target) {
                // Get short path for adjacent vertices
                int dist = stage(g, spt, i, target);
                // Say a vertex has 3 adjacent. that means there are 3 possibilites to reach target vertex from our
                // current vertex. select one that has minimum cost
                if (dist + g[v][i] < min) {
                    min = dist;
                    minV = i;
                }
            }
        }
        // update shortest path array. used for dp
        spt[v] = min + g[v][minV];
        System.out.printf("updating min for %d: %d\n", v, spt[v]);
        // return minimum cost calculated that we can reach target from current vertex
        return spt[v];
    }
}
