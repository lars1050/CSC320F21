import java.util.*;

public class Main {

  private static final Boolean DIRECTED = true;
  private static final Boolean UNDIRECTED = false;

  public static void main(String[] args) {
    Graph graph1 = make_test_graph1();
    System.out.println("\nHERE is your adjacency list for the graph...");
    System.out.println(graph1);
    HashMap<String,AlgoVertex> treeBFS = GraphAlgorithms.BFS(graph1,"A");
    System.out.println("Paths as a result of BFS");
    trace(treeBFS);

    Graph graph2 = make_test_graph2();
    System.out.println("\nHERE is your adjacency list for the graph...");
    System.out.println(graph2);
    HashMap<String,AlgoVertex> treeBFS2 = GraphAlgorithms.BFS(graph2,"A");
    System.out.println("Paths as a result of BFS");
    trace(treeBFS2);

    HashMap<String,AlgoVertex> treeDFS = GraphAlgorithms.DFS(graph1);

    HashMap<String,AlgoVertex> treeDijk = GraphAlgorithms.Dijkstra(graph1);
  }

  public static Graph make_test_graph1() {
    Graph graph = new Graph(UNDIRECTED);
    graph.add("A","B");
    graph.add("A","C");
    graph.add("B","C");
    graph.add("C","D");
    graph.add("D","E");
    return graph;
  }

  public static Graph make_test_graph2() {
    Graph graph = new Graph(DIRECTED);
    graph.add("A","B");
    graph.add("C","A");
    graph.add("B","C");
    graph.add("B","E");
    graph.add("D","B");
    graph.add("E","F");
    graph.add("G");
    return graph;
  }

  public static void trace(HashMap<String,AlgoVertex> searchTree) {
    for (HashMap.Entry<String, AlgoVertex> entry : searchTree.entrySet()) {
      AlgoVertex v = entry.getValue();
      System.out.print(v.label()+": ");
      while (v.parent() != null) {
        System.out.print(v.parent()+" ");
        v = searchTree.get(v.parent());
      }
      System.out.println();
    }
  }
}
