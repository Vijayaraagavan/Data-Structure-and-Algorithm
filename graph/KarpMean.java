package graph;

import java.util.Arrays;
import java.util.LinkedList;

// A vertex can be involved in one or more cycles. each cycle will have different path. those path may have same or different number of edges and also different weights
// Our aim is to find a cycle that has minimum mean i.e the average weight should be minimum. this is nothing but sum of all weight (or cost of the path) / no of edges in that path
// We can use Karp algo to find not just cycle involving one vertex but for all vertex. which in turn we can a cycle that has minimum mean possible in whole graph

public class KarpMean {
    class Node {
        int vertex, weight;

        Node(int v, int w) {
            vertex = v;
            weight = w;
        }
    }

    final static int INF = 99999;
    LinkedList<Node> adj[];

    public static void start() {
        KarpMean d = new KarpMean();
        d.karpMean();
    }

    void init(int v) {
        adj = new LinkedList[v];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<Node>();
        }
    }

    void addedge(int u, int v, int w) {
        adj[u].add(new Node(v, w));
    }

    Node getEdge(int u, int v) {
        for (Node n : adj[u]) {
            if (n.vertex == v) {
                return n;
            }
        }
        return null;
    }

    void karpMean() {
        int V = 4, E = 6, S = 0;
        init(V);
        addedge(0, 1, 1);
        addedge(0, 2, 10);
        addedge(1, 2, 3);
        addedge(2, 3, 2);
        addedge(3, 1, 0);
        addedge(3, 0, 8);
        int[][] dp = new int[V][E];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[S][0] = 0;
        // Find distance from source to other vertex for each K
        // we iterate in column direction. process all the elements in column k
        // That means we first calculate cost for vertex 0 to reach other vertex 1,2,3,4
        // with zero edges
        // which is impossible. for self vertex cost is 0, for other vertex it is
        // infinity. so we skip first column (i.e for zero edge)
        for (int k = 1; k < E; k++) {
            for (int i = 0; i < dp.length; i++) {
                // say k = 1; i = 1;
                // our main aim is that the path should have exactly 1 edges. Ignoring current
                // edge (any vertex -> i)
                // any previous solution should have distance to source vertex 0 with zero edge
                // so we search all the elements in the previous column. also if a vertex has
                // distance to source vertex with exactly zero edge,
                // check if there is a edge to connect our current vertex to that found vertex
                // by scanning we find only dp[0][0] = 0 is available, others are INF
                // dp[0][0] + w(0, 1) = 0 + 1 = 1
                // say k = 2; i = 2;
                // ignoring current edge, any previous vertex should have distance to source
                // vertex with exactly 1 edge
                // seems dp[2][1] = 1 is available. also there is a between vertex 2 and 3 with
                // weight 2
                // dp[2][2] = dp[2][1] + w(2, 3) = 1 + 3 = 4
                // see the vision. we are using dp to store calculated solution from a given
                // vertex and k. You don't need to traverse again
                // in order to form a path to a destination with fixed edges, you just pick
                // previous vertex with solution for k - 1 edges
                int m = Math.max(k - 1, 0);
                for (int j = 0; j < dp.length; j++) {
                    int adjacentDist = dp[j][m];
                    Node edge = getEdge(j, i);
                    if (adjacentDist == INF || edge == null) {
                        continue;
                    }
                    int dist = dp[j][m] + edge.weight;
                    // There is a possibility of 2 previous vertex existing with exactly k edges to
                    // source vertex. in that case we select minimum
                    if (dist < dp[i][k]) {
                        dp[i][k] = dist;
                    }
                }
                // }
            }
            for (int i = 0; i < dp.length; i++) {
                System.out.println(Arrays.toString(dp[i]));
            }
            System.out.println("==========");
        }
        // Need to choose a cycle with maximum no of edges involved. if there is no path
        // with max edge, reduce no of edge and check
        int maxIdx = E - 1;
        while (true) {
            if (dp[S][maxIdx] != INF) {
                break;
            }
            maxIdx--;
        }
        double minMean = INF;
        for (int i = 0; i < maxIdx; i++) {
            if (dp[S][i] == INF)
                continue;
            double avg = (double) (dp[S][maxIdx] - dp[S][i]) / (maxIdx - i);
            if (avg < minMean) {
                minMean = avg;
            }
        }
        System.out.printf("Karp's minimum for cycles that involve vertex 0: %f", minMean);
    }
}
