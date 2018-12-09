package p7;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph node
 */
public class Node {
  private String name;
  private List<Edge> connections;
  
  public Node(String name) {
    this.name = name;
    this.connections = new ArrayList<>();
  }
  
  public String getName() {
    return this.name;
  }
  
  public void addConnection(Edge e) {
    this.connections.add(e);
  }
}
