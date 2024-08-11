import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {
    private int vertices = 0;
    private LinkedList<Integer>[] adjList;
    private Queue<Integer> queue;
    private Stack<Integer> stack;
    @SuppressWarnings("unchecked")
    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjList = (LinkedList<Integer>[]) new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            this.adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int u, int v) {
        this.adjList[u].add(v);
    }

    public void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[this.vertices];
        // check if node exist in graph
        boolean exist = this.adjList[node] != null;
        if (!exist) {
            return;
        }
        // System.out.println(node);
        visited[node] = true;
        queue.add(node);
        while (!queue.isEmpty()) {
            // System.out.println(queue);
            int current = queue.poll();
            System.out.println(current);
            for (int neighbor : this.adjList[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    public int level(int node) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[this.vertices];
        int maxVertex = 0;
        for (int j = 0; j < this.adjList.length; j++) {
            LinkedList<Integer> l = this.adjList[j];
            for (int i = 0; i < l.size(); i++) {
                maxVertex = Math.max(maxVertex, Math.max(j, l.get(i)));
            }
        }
        if (node > maxVertex || this.adjList[node] == null)
            return -1;
        queue.offer(0);
        visited[0] = true;
        int level = 0;
        while (!queue.isEmpty()) {
            System.out.println(queue);
            int sz = queue.size();
            while (sz-- > 0) {
                int current = queue.poll();
                if (current == node)
                    return level;
                for (int neighbor : this.adjList[current]) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    public static void start() {
        int vertices = 5;

        // Create a graph
        Graph graph = new Graph(vertices);

        // Add edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);

        // Perform BFS traversal starting from vertex 0
        // System.out.print(
        //         "Breadth First Traversal starting from vertex 0: ");
        // for (int j = 0; j < graph.adjList.length; j++) {
        //     LinkedList<Integer> l = graph.adjList[j];
        //     for (int i = 0; i < l.size(); i++) {
        //         System.out.printf("%d -> %d\n", j, l.get(i));
        //     }
        // }
        // graph.bfs(0);
        // int target = 4;
        // int level = graph.level(target);
        // System.out.printf("found level for %d: %d\n", target, level);
        // minimizeMaxAdj();
        // graph.findenergy();
        // graph.djikstra();
    }

    public static void minimizeMaxAdj() {
        int[][] arr = { { 1, 2, 1 }, { 2, 8, 7 }, { 2, 3, 2 } };
        int m = 3, n = 3;
        Graph g = new Graph(5);
        g.stack = new Stack<Integer>();
        // g.minAdj(arr, m, n);
    }
    public void minAdj(int[][] arr, int m, int n) {
        if (m > 1) {
            // System.out.printf(" m side: %d\n", arr[m-1][n-1]);
            this.stack.add(arr[m-1][n-1]);
            minAdj(arr, m-1, n);
        }
        if (n > 1) {
            // System.out.printf(" n side: %d\n", arr[m-1][n-1]);
            this.stack.add(arr[m-1][n-1]);
            minAdj(arr, m, n-1);
        }
        if (m == 1 && n == 1) {
            System.out.printf("reached: %d ", arr[0][0]);
            while (!this.stack.isEmpty()) {
                System.out.printf("%d ", this.stack.pop());
            }
            System.err.printf(" \n");
            return;
        }
    }
    public void findenergy() {
        int[][] arr = { { 1, 2, 1 }, { 2, 8, 7 }, { 2, 3, 2 } };
        int m = 3, n = 3;
        // Graph g = new Graph(5);
        this.stack = new Stack<Integer>();
        int start = 0;
        int end = 10000000;
        int minimumEnergy = arr[0][0];
        while (start < end) {
        System.out.println(this.stack);
            this.stack.clear();
            int mid = (int)Math.ceil((start + end) / 2.0);
            boolean[][] visited = new boolean[m][3];

            if (isVisited(0, 0, mid, visited, arr, arr[0][0])) {
                minimumEnergy = mid;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }

        System.out.println(this.stack);
        System.out.println(minimumEnergy);
    }

    boolean isVisited(int u, int v, int x, boolean[][] visited, int[][] arr, int parent) {
        int m = arr.length;
        int n = arr[0].length;
        if (u < 0 || u >= m || v < 0 || v >= n || visited[u][v] || Math.abs(parent - arr[u][v]) > x) {
            return false;
        }
        if (u == m-1 && v == n-1) {
            return true;
        }
        visited[u][v] = true;
        if (isVisited(u+1, v, x, visited, arr, arr[u][v])) {
            this.stack.push(arr[u][v]);
            return true;
        }
        if (isVisited(u-1, v, x, visited, arr, arr[u][v])) {
            this.stack.push(arr[u][v]);
            return true;
        }
        if (isVisited(u, v+1, x, visited, arr, arr[u][v])) {
            this.stack.push(arr[u][v]);
            return true;
        }
        if (isVisited(u, v-1, x, visited, arr, arr[u][v])) {
            this.stack.push(arr[u][v]);
            return true;
        }
        return false;
    }
}


