public class Edge {
  String label;
  Integer weight;

  public Edge(String l) {
    label = l;
    weight = 1;
  }
  public Edge(String l, Integer w) {
    label = l;
    weight = w;
  }

  public String label() { return label; }
  public Integer weight() { return weight; }
}
