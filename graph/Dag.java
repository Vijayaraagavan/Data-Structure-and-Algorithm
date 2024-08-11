package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

class Adjacent {
    protected int u, v, w;
    Adjacent(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

public class Dag {
    LinkedList<Adjacent> adj[];
    public static void start() {
        Dag d = new Dag();
        d.dag();
    }
    void init(int v) {
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Adjacent>();
        }
    }
    void addEdge(int u, int v, int w) {
        adj[u].add(new Adjacent(u, v, w));
    }
    void dag() {
        int v = 6;
        init(v);
        addEdge(0, 1, 5);
        addEdge(0, 2, 3);
        addEdge(1, 3, 6);
        addEdge(1, 2, 2);
        addEdge(2, 4, 4);
        addEdge(2, 5, 2);
        addEdge(2, 3, 7);
        addEdge(3, 4, -1);
        addEdge(4, 5, -2);
        int target = 1;
        boolean[] visited = new boolean[v];
        Stack<Integer> s = new Stack<>();
        sorting(0, visited, s);
        int[] spt = new int[v];
        for (int i = 0; i < spt.length; i++) {
            spt[i] = Integer.MAX_VALUE;
        }
        spt[target] = 0;
        System.out.println(s);

        while (!s.empty()) {
            int currentIdx = s.pop();
            if (spt[currentIdx] == Integer.MAX_VALUE) continue;
            for (int i = 0; i < adj[currentIdx].size(); i++) {
                Adjacent neighbhor = adj[currentIdx].get(i);
                int dist = spt[neighbhor.u] + neighbhor.w;
                if (dist < spt[neighbhor.v]) {
                    spt[neighbhor.v] = dist;
                }
            }
            System.out.println(Arrays.toString(spt));
        }
    }
    void sorting(int v, boolean[] visited, Stack<Integer> s) {
        LinkedList<Adjacent> node = adj[v];
        for (int i = 0; i < node.size(); i++) {
            int inner = node.get(i).v;
            if (!visited[inner]) {
                visited[inner] = true;
                sorting(inner, visited, s);
            }
        }
        s.push(v);
    }
}
