package graph;

import java.util.Arrays;

// call Dijkstra.start();
public class Dijkstra {
    public static void start() {
        Dijkstra d = new Dijkstra();
        d.djikstra();
    }

    void djikstra() {
        int graph[][] = new int[][] { {0, 0, 3, 3},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0} };

        // track nodes that are added to shortest path tree (spt)
        boolean[] sptset = new boolean[graph.length];
        // spt means an array of length vertices that maintains cost
        int[] spt = new int[graph.length];
        // From which vertex we need to calculate cost to all other vertices
        int target = 0;
        // Initialize spt with maximum value but distance of source vertex from itself is always 0
        for (int i = 0; i < spt.length; i++) {
            if (i == target) {
                spt[i] = 0;
            } else {
                spt[i] = Integer.MAX_VALUE;
            }
        }

        solvDjikstra(graph, sptset, spt);
    }

    void solvDjikstra(int[][] graph, boolean[] sptset, int[] spt) {
        // Find smallest node from spt tree. One important thing is the cost of a vertex
        // is not finalized
        // until they are marked as true in sptset array
        // we mark a vertex as added to spt if they are the smallest in a iteration
        // eg: at start target node is the smallest (cost: 0). In next iteration, either
        // node 1 or 7 will be smallest
        // A node in sptset tree won't be choosen as start for next iteration because
        // they are cost is finalized
        int node = findSmallestNode(spt, sptset);
        sptset[node] = true;
        // update cost of adjacent vertices (for node 0 it is node 1 and 7)
        for (int i = 0; i < spt.length; i++) {
            int neighbor = graph[node][i];
            // neighbhor == 0 means there is no edge between target node and other
            // we ignore if either neighbor or target node cost is max value. since any more
            // addition will case overflow
            if (neighbor == 0 || neighbor == Integer.MAX_VALUE || spt[node] == Integer.MAX_VALUE)
                continue;
            // get updated cost
            int tmp = neighbor + spt[node];
            // System.out.printf("row: %d, distance of %d is %d\n", node, i, spt[i]);
            // Here there is contradiction. Once a node is marked true in sptset it only
            // can't be picked to start
            // next iteration. But it does not mean its cost should be fixed. if the new
            // value is lower we use it
            // eg: both node 2 and 6 can reach node 8. path that uses node 6's cost is 15.
            // But later we find that
            // the path that uses node 2's cost is only 14
            if (tmp < spt[i]) {
                spt[i] = tmp;
            }
        }
        // sptset[min[1]] = true;
        System.out.println(Arrays.toString(spt));
        System.out.println(Arrays.toString(sptset));
        // this is the base case for recursion. loop until sptset array is filled with
        // true
        if (!isSptComplete(spt, sptset)) {
            solvDjikstra(graph, sptset, spt);
        }
    }

    boolean isSptComplete(int[] spt, boolean[] sptset) {
        for (int i = 0; i < spt.length; i++) {
            // I don't know if we should check for cost as maximum value. spt is formed if
            // sptset is full
            if (!sptset[i])
                return false;
        }
        return true;
    }

    int findSmallestNode(int[] spt, boolean[] sptset) {
        int result = 0;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < spt.length; i++) {
            if (spt[i] <= minValue && !sptset[i]) {
                minValue = spt[i];
                result = i;
            }
        }
        return result;
    }

}
/*
 * input 1:
 * int[][] graph = {
 * {0, 10, 3, Integer.MAX_VALUE, Integer.MAX_VALUE},
 * {Integer.MAX_VALUE, 0, 1, 2, Integer.MAX_VALUE},
 * {Integer.MAX_VALUE, 4, 0, 8, 2},
 * {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 7},
 * {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 9, 0}
 * };
 * output:
 * Shortest distances from source 0: [0, 7, 3, 9, 5]
 * 
 * input 2:
 * int graph[][]
 * = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
 * { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
 * { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
 * { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
 * { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
 * { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
 * { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
 * { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
 * { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
 * output:
 * Vertex Distance from Source
 * 0 0
 * 1 4
 * 2 12
 * 3 19
 * 4 21
 * 5 11
 * 6 9
 * 7 8
 * 8 14
 * console:
 * [0, 4, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 8,
 * 2147483647]
 * [0, 4, 12, 2147483647, 2147483647, 2147483647, 2147483647, 8, 2147483647]
 * [0, 4, 12, 2147483647, 2147483647, 2147483647, 9, 8, 15]
 * [0, 4, 12, 2147483647, 2147483647, 11, 9, 8, 15]
 * [0, 4, 12, 25, 21, 11, 9, 8, 15]
 * [0, 4, 12, 19, 21, 11, 9, 8, 14]
 * [0, 4, 12, 19, 21, 11, 9, 8, 14]
 * [0, 4, 12, 19, 21, 11, 9, 8, 14]
 * [0, 4, 12, 19, 21, 11, 9, 8, 14]
 */
// Why Dijkstra’s Algorithms fails for the Graphs having Negative Edges ?
// The problem with negative weights arises from the fact that Dijkstra’s algorithm assumes that once a node is added to the set of visited nodes, its distance is finalized and will not change. However, in the presence of negative weights, this assumption can lead to incorrect results.

// Consider the following graph for the example:

// Failure-of-Dijkstra-in-case-of-negative-edges
// input:
// int graph[][] = new int[][] { { 0, 2, 4 }, { 0, 0, 0 }, { 0, -3, 0 } };
// output: 
// [0, 2, 4]
// But in my solution the negative value is correctly accounted
// my output is [0, 1, 4]
// Because remember even if the cost of a vertex is fixed i change if a new lower is found
// if (tmp < spt[i]) {
//     spt[i] = tmp;
// }
// so thats the reason
// there is a problem with my approach. Assume that i have modified node 8 cost from 15 to 14 even though it is 
// added to sptset. if there is vertex that has parent as node 8, then its cost too must be changed for new value 14. 
// so its cost is too reduced. Unfortunately we don't update children recursively. so child vertex still uses old cost
// That's why djikstra algorithm doesn't update a vertex cost if it is finalized