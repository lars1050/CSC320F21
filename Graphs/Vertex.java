import java.util.*;

public class Vertex {
  String label = "None";
  LinkedList<Edge> adjacent = null;

  public Vertex(String s) {
    label = s;
    adjacent = new LinkedList<>();
  }

  public void addEdge(String label) {
    adjacent.add(new Edge(label, 1));
  }
  public void addEdge(String label, Integer weight) {
    adjacent.add(new Edge(label, weight));
  }

  public LinkedList<Edge> adjacent() { return adjacent; }
  public String label() { return label; }
}
