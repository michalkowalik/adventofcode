package p7;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Graph {
  private List<Node> nodes;
  
  public Graph() {
    this.nodes = new ArrayList<>();
  }
  
  public List<Node> getNodes() {
    return nodes;
  }
  
  public Optional<Node> getNode(String name) {
      return nodes.stream().filter(p -> p.getName().equals(name)).findFirst();
  }
  
  public void addNode(Node n) {
    this.nodes.add(n);
  }
  
  public boolean nodeExists(String name) {
    return nodes.stream().filter(p -> p.getName().equals(name)).count() > 0;
  }
  
  public void removeNode(String name) {
    Node n = this.nodes.stream().filter(p -> p.getName().equals(name)).findFirst().get();
    this.nodes.remove(n);
  }
}
