package p13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class P13 {
  
  private static final String INPUT_PATH="input13.test1.txt";
  private Square[][] map;
  
  public static void main(String[] args) {
    var p13 = new P13();
    p13.solve();
  }
  
  // solve the day 13 puzzle
  private void solve() {
    try {
      map = this.readInput();
      
      System.out.println("done");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private Square[][] readInput() throws IOException {
    List<String[]> lines =
            Files.lines(Paths.get(INPUT_PATH))
                    .map(p -> p.split("")).collect(Collectors.toList());
    
    var res = new Square[lines.get(0).length][lines.size()];
    int y = 0;
    
    for (String[] line : lines) {
      for(int x = 0; x < line.length; x++) {
        var s = new Square(x, y);
        s.addConnections(line[x], res);
        res[x][y] = s;
        // add cart together with
      }
      y++;
    }
    
    return res;
  }
}
