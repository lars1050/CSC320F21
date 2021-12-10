import java.util.*;

public class AlgoVertex {
  Vertex self;
  String parent;
  Integer discover;
  Integer finish;
  Boolean visited;
  String label = "None";
  LinkedList<Edge> adjacent = null;

  public AlgoVertex(Vertex v) {
    self = v;
    label = v.label();
    adjacent = v.adjacent();
    parent = null;
    discover = Integer.MAX_VALUE;
    finish = Integer.MAX_VALUE;
    visited = false;
  }

  public String label() { return label; }
  public Vertex self() { return self; }
  public String parent() { return parent; }
  public void parent(String label) {parent = label;}
  public Integer discover() { return discover;}
  public void discover(Integer n) {discover = n;}
  public Integer finish() { return finish;}
  public void finish(Integer n) {finish = n;}
  public LinkedList<Edge> adjacent() { return adjacent; }
  public Boolean visited() { return visited; }
  public void visited(Boolean b) { visited = b;}
}
