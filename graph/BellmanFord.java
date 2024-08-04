package graph;

import java.util.Arrays;

public class BellmanFord {
    final static int FIND_NEGATIVE_CYCLE = 1;

    public static void start() {
        BellmanFord d = new BellmanFord();
        d.bellmanFord(FIND_NEGATIVE_CYCLE);
    }

    void bellmanFord(int option) {
        int arr[][] = new int[][] {
                { 0, -1, 4, Integer.MAX_VALUE, Integer.MAX_VALUE },
                { Integer.MAX_VALUE, 0, 3, 2, 2 },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE },
                { Integer.MAX_VALUE, 1, 5, 0, Integer.MAX_VALUE },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -3, 0 }
        };
        int m = arr.length;
        int n = arr[0].length;
        int target = 0;
        // Same shorted path tree (spt) as djikstra
        int[] spt = new int[n];
        for (int i = 0; i < spt.length; i++) {
            spt[i] = Integer.MAX_VALUE;
        }
        spt[target] = 0;

        // relax for vertices - 1 times
        for (int k = 1; k < m; k++) {
            // use inner and outer loop to iterate each edge with their weight
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == 0 || arr[i][j] == Integer.MAX_VALUE)
                        continue;
                    int dist = spt[i] + arr[i][j];
                    if (dist < spt[j]) {
                        spt[j] = dist;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(spt));
        if (option == FIND_NEGATIVE_CYCLE) {
            findNegativeCycle(arr, spt);
        }
    }
    void findNegativeCycle(int[][]arr, int[] spt) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 0)
                    continue;
                int dist = spt[i] + arr[i][j];
                if (dist < spt[j]) {
                    System.out.println("Negative cycle exist");
                    return;
                }
            }
        }
    }
}

// Input 1:
// new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
// { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
// { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
// { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
// { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
// { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
// { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
// { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
// { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
// Output:
// [0, 4, 12, 19, 21, 11, 9, 8, 14]

// Input 2:
// new int[][] { { 0, 2, 4 }, { 0, 0, 0 }, { 0, -3, 0 } };
// Output:
// [0, 1, 4]
// I feel like this algorithm is a brute force type. since we are exploring all
// the possibilites by
// processing each all the Edges for V-1 times. In first iteration itself when
// we process all the edges we get
// somewhat good cost to all vertex. By repeatedly doing this for V-1 iteration
// we arrive at accurate solution

// Input 3:
// new int[][] {
//     { 0, -1, 4, Integer.MAX_VALUE, Integer.MAX_VALUE },
//     { Integer.MAX_VALUE, 0, 3, 2, 2 },
//     { Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE },
//     { Integer.MAX_VALUE, 1, 5, 0, Integer.MAX_VALUE },
//     { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -3, 0 }
// };
// Output:
// [0, -1, 2, -2, 1]
// Negative cycle exist

// the above spt is wrong. reason is bellman ford algorithm can be used to detect negative edge cycle but not the
// shortest path if negative cycle exist that the source can reach
// It seems I have wrong understanding of whether this algorithm can solve negative edge cycle