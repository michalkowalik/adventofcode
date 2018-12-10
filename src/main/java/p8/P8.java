package p8;

import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P8 {
  
  public static final String INPUT_PATH = "input8.txt";
  
  public static void main(String[] args) {
    P8 p8 = new P8();
    p8.solve1();
  }
  
  // solve first part
  private void solve1() {
    try {
      List<Integer> data = readInputData();
      
      // build the tree:
      Node tree = buildTree(data);
      
      // traverse the tree and get the sum of metadata:
      
      
      System.out.println("done");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private Node buildTree(List<Integer> data) {
    return null;
  }
  
  
  // read input data into a list
  private List<Integer> readInputData() throws IOException {
    List<String> lines = Files.lines(Paths.get(INPUT_PATH))
            .collect(Collectors.toList());
  
    return Arrays.asList(lines.get(0).split(" ")).stream()
            .map(l -> Integer.parseInt(l)).collect(Collectors.toList());
  }
  
}
