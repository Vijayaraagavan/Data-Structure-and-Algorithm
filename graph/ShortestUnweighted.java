package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestUnweighted {
    LinkedList<Integer> adj[];

    static public void start() {
        ShortestUnweighted j = new ShortestUnweighted();
        j.shortestPath();
    }

    void init(int v) {
        adj = new LinkedList[v];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    // I thought the problem is to find the minimal cost and not spt only to later
    // find out that we need spt too
    // Finding minimal cost only is simple because of the fact that in unweighted
    // graph the cost of each edge is constant 1
    // So the cost increments by 1 at each depth you traverse
    // Breath First traversal makes it easy to track the depth of a node
    // When we go to next depth level, we increment the tracker
    // In order to find spt, create an spt array to track distance. But this time we
    // need an additional array to keep
    // the path information i.e parent of current node. only then we can print the
    // shortest path since we are using bfs
    void shortestPath() {
        // int V = 8, E = 10, S = 0, D = 7;
        // int edges[][] = {{0, 1}, {1, 2}, {0, 3}, {3, 4}, {4, 7}, {3, 7}, {6, 7}, {4,
        // 5}, {4, 6}, {5, 6}};
        int V = 8, E = 10, S = 2, D = 6;
        int edges[][] = { { 0, 1 }, { 1, 2 }, { 0, 3 }, { 3, 4 }, { 4, 7 }, { 3, 7 }, { 6, 7 }, { 4, 5 }, { 4, 6 },
                { 5, 6 } };
        init(V);
        for (int i = 0; i < edges.length; i++) {
            addEdge(edges[i][0], edges[i][1]);
        }
        // 3 arrays
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[V];
        int[] parents = new int[V];
        // Initialize
        int depth = 0;
        q.offer(S);
        int limit = q.size();

        boolean found = false;
        int[] spt = new int[V];
        Arrays.fill(spt, Integer.MAX_VALUE);
        spt[S] = 0;
        Arrays.fill(parents, Integer.MAX_VALUE);
        // we don't need full spt, stop when target is found
        while (!q.isEmpty() && !found) {
            limit--;
            int current = q.poll();
            visited[current] = true;
            System.out.printf("depth: %d, node: %d\n", depth, current);
            if (current == D) {
                found = true;
                continue;
            }
            for (int i = 0; i < adj[current].size(); i++) {
                int neighbhor = adj[current].get(i);
                if (!visited[neighbhor]) {
                    q.offer(neighbhor);
                }
                int dist = spt[current] + 1;
                if (dist < spt[neighbhor]) {
                    spt[neighbhor] = dist;
                    parents[neighbhor] = current;
                }
            }
            // we have processed current depth, increment depth tracker
            if (limit == 0) {
                limit = q.size();
                depth++;
            }
        }
        int h = D;
        while (true) {
            int par = parents[h];
            System.out.printf("%d -> ", h);
            if (par == Integer.MAX_VALUE) {
                break;
            }
            h = par;
        }
        System.out.println();
        System.out.println(depth);
        System.out.println(Arrays.toString(spt));
    }
}

// vijay@vijays-MacBook-Air java % ./run.sh
// Note: Some input files use unchecked or unsafe operations.
// Note: Recompile with -Xlint:unchecked for details.
// depth: 0, node: 2
// depth: 1, node: 1
// depth: 2, node: 0
// depth: 3, node: 3
// depth: 4, node: 4
// depth: 4, node: 7
// depth: 5, node: 7
// depth: 5, node: 5
// depth: 5, node: 6
// 6 -> 4 -> 3 -> 0 -> 1 -> 2 -> 
// 5
// [2, 1, 0, 3, 4, 5, 5, 4]
