package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

class Node {
    int vertex, w;
    Node(int u, int w) {
        this.vertex = u;
        this.w = w;
    }
}

public class Dial {
    LinkedList<Node> adj[];
    int vertices;
    public static void start() {
        Dial d = new Dial();
        d.dial();
    }
    void init(int v) {
        vertices = v;
        adj = new LinkedList[v];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<Node>();
        }
    }

    void AddEdge(int u, int v, int w) {
        Node un = new Node(u, w);
        Node vn = new Node(v, w);
        adj[u].add(vn);
        adj[v].add(un);
    }

    void dial() {
        int V = 9;
        init(V);
 
        // making above shown graph
        AddEdge(0, 1, 4);
        AddEdge(0, 7, 8);
        AddEdge(1, 2, 8);
        AddEdge(1, 7, 11);
        AddEdge(2, 3, 7);
        AddEdge(2, 8, 2);
        AddEdge(2, 5, 4);
        AddEdge(3, 4, 9);
        AddEdge(3, 5, 14);
        AddEdge(4, 5, 10);
        AddEdge(5, 6, 2);
        AddEdge(6, 7, 1);
        AddEdge(6, 8, 6);
        AddEdge(7, 8, 7);
        for (int i = 0; i < adj.length; i++) {
            // System.out.printf("%d -> ", i);
            // for (Node n : adj[i]) {
            //     System.out.printf("%d ", n.vertex);
            // }
            // System.out.println();
        }
        // maximum weighted edge - 14
        shortestPath(0, 14);
    }

    void shortestPath(int v, int maxW) {
        int bucketSize = this.vertices * maxW + 1;
        ArrayList<Integer>[] b = new ArrayList[bucketSize];
        Arrays.fill(b, new ArrayList<Integer>());
        int[] spt = new int[this.vertices];
        Arrays.fill(spt, Integer.MAX_VALUE);

        b[0].add(v);
        spt[v] = 0;

        int tracker = 0;
        while(true) {
            System.out.println(Arrays.toString(spt));
            while (b[tracker].size() == 0 && tracker < bucketSize - 1) {
                tracker++;
            }
            if (tracker == bucketSize-1) {
                break;
            }
            int current = b[tracker].get(0);
            b[tracker].remove(0);
            for (Node n : adj[current]) {
                int dist = spt[current] + n.w;
                if (dist < spt[n.vertex]) {
                    spt[n.vertex] = dist;
                    b[dist].add(0, n.vertex);
                }
            }
        }
    }
}
