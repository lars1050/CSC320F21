import java.util.*;

public class GraphAlgorithms {

  public static HashMap<String,AlgoVertex> BFS(Graph graph, String source) {

    HashMap<String,Vertex> vertices = graph.vertices();
    HashMap<String,AlgoVertex> searchTree = new HashMap<String,AlgoVertex>();

    for (HashMap.Entry<String, Vertex> entry : vertices.entrySet()) {
      searchTree.put(entry.getKey(),new AlgoVertex(entry.getValue()));
    }

    if (false) {           // for debugging
      for (HashMap.Entry<String, AlgoVertex> entry : searchTree.entrySet()) {
        System.out.print(entry.getKey()+": ");
        for (Edge e: (entry.getValue()).adjacent()) {
          System.out.print(e.label()+" ");
        }
        System.out.println();
      }
    }

    AlgoVertex s = searchTree.get(source);
    s.discover = 0;
    s.visited = true;
    LinkedList<AlgoVertex> queue = new LinkedList<AlgoVertex>();
    queue.add(s);
    while (queue.size() != 0) {
      // Get the AlgoVertex off the queue
      AlgoVertex u_av = queue.poll();
      // The AlgoVertex contains a vertex
      Vertex u = u_av.self();
      // The vertex has a list of adjacent vertices (edges)
      for (Edge e : u.adjacent()) {
        // Get the AlgoVertex associated with this edge
        AlgoVertex v = searchTree.get(e.label());
        if (!v.visited()) {
          queue.add(v);
          v.discover(u_av.discover()+1);
          v.parent(u_av.label());
          v.visited(true);
        }
      }
    }
    return searchTree;
  }

  public static HashMap<String,AlgoVertex> DFS(Graph graph) {
    return null;

  }

  public static HashMap<String,AlgoVertex> Dijkstra(Graph graph) {
    return null;
  }
}
