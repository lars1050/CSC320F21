import java.util.*;

public class Graph {

  HashMap<String, Vertex> vertices = null;

  Boolean directed = false;
  Boolean weighted = false;

  public Graph(Boolean dir) {
    directed = dir;
    vertices = new HashMap<String, Vertex>();
  }

  private void addVertex(String label) {
    if (!vertices.containsKey(label)) {
      Vertex new_v = new Vertex(label);
      vertices.put(label,new_v);
    }
  }

  public void add(String label) {
    addVertex(label);
  }

  public void add(String label1, String label2) {
    add(label1,label2,1);
  }

  public void add(String label1, String label2, Integer weight) {
    addVertex(label1);
    addVertex(label2);
    (vertices.get(label1)).addEdge(label2,weight);
    if (!directed) {
      (vertices.get(label2)).addEdge(label1,weight);
    }
  }

  public String toString() {
    String output = "";
    for (HashMap.Entry<String, Vertex> entry : vertices.entrySet()) {
      output += entry.getKey()+": ";
      for (Edge e: (entry.getValue()).adjacent()) {
        output += e.label()+" ";
      }
      output += "\n";
    }
    return output;
  }

  public HashMap<String,Vertex> vertices() { return vertices; }
}
