package graph;

import java.util.Arrays;

public class Floyd {
    final static int INF = 99999, V = 4;

    public static void start() {
        Floyd d = new Floyd();
        d.floyd();
    }

    public void floyd() {
        int graph[][] = { { 0, 5, INF, 10 },
        { INF, 0, 3, INF },
        { INF, INF, 0, 1 },
        { INF, INF, INF, 0 } };

        for (int i = 0; i < graph.length; i++) {
            int intermediate = i;
            for (int j = 0; j < graph.length; j++) {
                for (int j2 = 0; j2 < graph.length; j2++) {
                    int el = graph[j][j2];
                    int sum = graph[j][intermediate] + graph[intermediate][j2];
                    if (sum < el) {
                        graph[j][j2] = sum;
                    }
                }
            }
        }
        for ( int[] i: graph) {
            System.out.println(Arrays.toString(i));
        }
    }
}
