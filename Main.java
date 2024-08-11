import graph.BellmanFord;
import graph.Dag;
import graph.Dial;
import graph.Dijkstra;
import graph.Floyd;
import graph.Johnson;
import graph.Multistage;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] in = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        // System.out.println(s.evalRPN(in));
        // s.calculate("1-(-2)");
        // Graph.start();
        // Dijkstra.start();
        // BellmanFord.start();
        // Floyd.start();
        // Johnson.start();
        // Dag.start();
        // Dial.start();
        Multistage.start();
    }
}
