package p8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P8 {
  
  private static final String INPUT_PATH = "input8.txt";
  private int cursor = 0;
  
  
  public static void main(String[] args) {
    P8 p8 = new P8();
    p8.solve();
  }
  
  // solve first part
  private void solve() {
    try {
      List<Integer> data = readInputData();

      Node tree = buildTree(data);
      
      // traverse the tree and get the sum of metadata:
      System.out.printf("Metadata sum: %d\n", getMetadataSum(tree));
      System.out.printf("Root node value: %d\n", calculateRootValue(tree));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private int calculateRootValue(Node n) {
    if (n.childern.isEmpty()) {
      return n.metadata.stream().reduce(0, (x , y) -> x + y);
    }
    
    return n.metadata.stream()
            .map(p -> (p > n.childern.size()) ? 0 : calculateRootValue(n.childern.get(p - 1)))
            .reduce(0, (x, y) -> x + y);
  }
  
  private int getMetadataSum(Node n) {
    int metadataSum = n.metadata.stream().reduce(0, (x, y) -> x + y);
    if (n.childern.isEmpty()) {
      return metadataSum;
    }
    
    List<Integer> childrenSum =
            n.childern.stream().map(this::getMetadataSum).collect(Collectors.toList());
    
    return metadataSum + childrenSum.stream().reduce(0, (x, y) -> x + y);
  }
  
  private Node buildTree(List<Integer> data) {
    if (cursor >= data.size()) {
      return null;
    }
    
    Node n = new Node();
    
    int rootChildren = data.get(this.cursor++);
    int rootMetadataSize = data.get(this.cursor++);
    
    for (int x = 0; x < rootChildren; x++) {
      n.childern.add(buildTree(data));
    }
    
    for (int x = 0; x < rootMetadataSize; x++) {
      n.metadata.add(data.get(this.cursor++));
    }
    
    return n;
  }
  
  
  // read input data into a list
  private List<Integer> readInputData() throws IOException {
    List<String> lines = Files.lines(Paths.get(INPUT_PATH))
            .collect(Collectors.toList());
  
    return Arrays.stream(lines.get(0).split(" "))
            .map(Integer::parseInt).collect(Collectors.toList());
  }
  
}
