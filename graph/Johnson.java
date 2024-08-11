package graph;

import java.util.Arrays;

public class Johnson {
    static int INF = 99999;

    static public void start() {
        Johnson j = new Johnson();
        j.johnson();
    }

    void johnson() {
        // int graph[][] = {
        //         { 0, 0, 5, 0, 0 },
        //         { -4, 0, 0, 0, -1 },
        //         { 0, 3, 0, 15, 0 },
        //         { 0, 0, 0, 0, 0 },
        //         { 0, 0, 1, -2, 0 }
        // };
        int graph[][] = {
        {0, 0, 0, 1},
        {2, 0, 0, 0},
        {3, 0, 0, 0},
        {0, 0, 5, 0}
        };
        // int graph[][] = {
        // {0, -5, 2, 3},
        // {0, 0, 4, 0},
        // {0, 0, 0, 1},
        // {0, 0, 0, 0}
        // };
        int l = graph.length;
        // attach new node
        // for (int i = 0; i < graph.length; i++) {
        // graph[i] = push(graph[i], 0);
        // }
        int[][] attachedGraph = push(graph);
        // for (int i = 0; i < attachedGraph.length; i++) {
        // System.out.println(Arrays.toString(attachedGraph[i]));
        // }
        System.out.println("bellman ford");
        int[] spt = bellmanFord(attachedGraph);
        System.out.println("convert negatives");

        // convert negative edges
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                int edge = attachedGraph[i][j];
                if (attachedGraph[i][j] == 0)
                    continue;
                int newEdge = edge + spt[i] - spt[j];
                attachedGraph[i][j] = newEdge;
            }
        }
        for (int i = 0; i < attachedGraph.length; i++) {
            System.out.println(Arrays.toString(attachedGraph[i]));
        }
        System.out.println("djikstras");

        int[][] spts = new int[l][l];
        for (int i = 0; i < spts.length; i++) {
            int[] dspt = djikstra(graph, attachedGraph, i);
            spts[i] = dspt;
        }
        System.out.println("full spt");
        for (int i = 0; i < spts.length; i++) {
            System.out.println(Arrays.toString(spts[i]));
        }

        // for (int i = 0; i < l; i++) {
        // for (int j = 0; j < l; j++) {
        // int edge = graph[i][j];
        // if (graph[i][j] == 0) continue;
        // int newEdge = edge + spt[i] - spt[j];
        // graph[i][j] = newEdge;
        // }
        // }

        // from reweight to original
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if (spts[i][j] == Integer.MAX_VALUE)
                    continue;
                attachedGraph[i][j] = spts[i][j] - (spt[i] - spt[j]);
            }
        }
        System.out.println("original graph");
        for (int i = 0; i < attachedGraph.length; i++) {
            System.out.println(Arrays.toString(attachedGraph[i]));
        }
    }

    int[] push(int[] arr, int a) {
        int[] result = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        result[arr.length] = a;
        return result;
    }

    int[][] push(int[][] arr) {
        int l = arr.length + 1;
        int[][] result = new int[l][l];
        for (int i = 0; i < l - 1; i++) {
            for (int j = 0; j < l - 1; j++) {
                result[i][j] = arr[i][j];
            }
            result[i][l - 1] = 0;
        }
        result[l - 1] = new int[l];
        return result;
    }

    int[] bellmanFord(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int target = m - 1;
        int max = Integer.MAX_VALUE;
        // Same shorted path tree (spt) as djikstra
        int[] spt = new int[n];
        for (int i = 0; i < spt.length; i++) {
            spt[i] = max;
        }
        spt[target] = 0;

        // relax for vertices - 1 times
        for (int k = 1; k < m; k++) {
            // use inner and outer loop to iterate each edge with their weight
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.println(Arrays.toString(spt));
                    if (spt[i] == max || (arr[i][j] == 0 && i != target)) {
                        continue;
                    }
                    int dist = spt[i] + arr[i][j];
                    if (dist < spt[j]) {
                        spt[j] = dist;
                    }
                }
            }
        }
        return spt;
    }

    int[] djikstra(int[][] graph, int[][] attachedGraph, int target) {

        // track nodes that are added to shortest path tree (spt)
        boolean[] sptset = new boolean[graph.length];
        // spt means an array of length vertices that maintains cost
        int[] spt = new int[graph.length];
        // From which vertex we need to calculate cost to all other vertices
        // Initialize spt with maximum value but distance of source vertex from itself
        // is always 0
        for (int i = 0; i < spt.length; i++) {
            if (i == target) {
                spt[i] = 0;
            } else {
                spt[i] = Integer.MAX_VALUE;
            }
        }

        return solvDjikstra(graph, attachedGraph, sptset, spt);
    }

    int[] solvDjikstra(int[][] graph, int[][] attachedGraph, boolean[] sptset, int[] spt) {
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
            int neighbor = attachedGraph[node][i];
            // neighbhor == 0 means there is no edge between target node and other
            // we ignore if either neighbor or target node cost is max value. since any more
            // addition will case overflow
            // if (spt[node] == Integer.MAX_VALUE) {
            // spt[node] = 0;
            // continue;
            // }
            if (graph[node][i] == 0 || neighbor == Integer.MAX_VALUE || spt[node] == Integer.MAX_VALUE)
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
            if (tmp < spt[i] && !sptset[i]) {
                spt[i] = tmp;
            }
        }
        // sptset[min[1]] = true;
        System.out.println(Arrays.toString(spt));
        // this is the base case for recursion. loop until sptset array is filled with
        // true

        if (!isSptComplete(spt, sptset)) {
            solvDjikstra(graph, attachedGraph, sptset, spt);
        }
        return spt;

    }

    boolean isSptComplete(int[] spt, boolean[] sptset) {
        for (int i = 0; i < spt.length; i++) {
            // I don't know if we should check for cost as maximum value. spt is formed if
            // sptset is full
            if (i == Integer.MAX_VALUE || !sptset[i])
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
