package p8;

import java.util.ArrayList;
import java.util.List;

public class Node {
  public List<Integer> metadata;
  public List<Node> childern;
  
  public Node() {
    this.metadata = new ArrayList<>();
    this.childern = new ArrayList<>();
  }
}
