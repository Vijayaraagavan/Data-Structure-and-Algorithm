package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySpt {
    class Node {
        int vertex, w;

        Node(int u, int w) {
            this.vertex = u;
            this.w = w;
        }
    }

    LinkedList<Node> adj[];
    static int INF = 99999;
    int vertices;

    public static void start() {
        BinarySpt d = new BinarySpt();
        d.binarySpt();
    }

    void init(int v) {
        vertices = v;
        adj = new LinkedList[v];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<Node>();
        }
    }

    void addEdge(int u, int v, int w) {
        Node un = new Node(u, w);
        Node vn = new Node(v, w);
        adj[u].add(vn);
        adj[v].add(un);
    }

    void binarySpt() {
        int V = 9;
        init(V);
        addEdge(0, 1, 0);
        addEdge(0, 7, 1);
        addEdge(1, 7, 1);
        addEdge(1, 2, 1);
        addEdge(2, 3, 0);
        addEdge(2, 5, 0);
        addEdge(2, 8, 1);
        addEdge(3, 4, 1);
        addEdge(3, 5, 1);
        addEdge(4, 5, 1);
        addEdge(5, 6, 1);
        addEdge(6, 7, 1);
        addEdge(7, 8, 1);
        int src = 0;// source node
        shortestPath(src, V);
    }

    void shortestPath(int src, int V) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(src);
        int[] spt = new int[V];
        Arrays.fill(spt, INF);
        spt[src] = 0;
        boolean[] visited = new boolean[V];
        visited[src] = true;
        while (!q.isEmpty()) {
            int n = q.poll();
            for (int i = 0; i < adj[n].size(); i++) {
                Node neighbor = adj[n].get(i);
                if (!visited[neighbor.vertex]) {
                    q.offer(neighbor.vertex);
                    visited[neighbor.vertex] = true;
                    int dist = spt[n] + neighbor.w;
                    if (dist < spt[neighbor.vertex]) {
                        spt[neighbor.vertex] = dist;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(spt));
    }
}
