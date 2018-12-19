package p13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P13 {
  
  private static final String INPUT_PATH="input13.test1.txt";
  private Square[][] map;
  private List<Cart> carts;
  
  public static void main(String[] args) {
    var p13 = new P13();
    p13.solve();
  }
  
  // solve the day 13 puzzle
  private void solve() {
    try {
      carts = new ArrayList<>();
      map = this.readInput();
    
      step();
      step();
      step();
      step();
      step();
      
      System.out.println("done");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private Square[][] readInput() throws IOException {
    List<String[]> lines =
            Files.lines(Paths.get(INPUT_PATH))
                    .map(p -> p.split("")).collect(Collectors.toList());
    List<String> cartSymbols = Arrays.asList("<", "^", ">", "v");
    
    
    var res = new Square[lines.get(0).length][lines.size()];
    int y = 0;
    
    for (String[] line : lines) {
      for(int x = 0; x < line.length; x++) {
        var s = new Square(x, y);
        s.addConnections(line[x], res);
        res[x][y] = s;
        // add cart together with it's vector:
        if (cartSymbols.contains(line[x])) {
          this.carts.add(new Cart(x, y, line[x]));
        }
        
      }
      y++;
    }
    
    return res;
  }
  
  /**
   * single emulation step. Do not modify, carts in place, replace it with a new list.
   */
  private void step() {
    this.carts = this.carts.stream()
            .map(this::moveCart).collect(Collectors.toList());
  }
  
  
  /**
   * move single cart
   * @param c cart to be moved
   * @return modified cart
   */
  private Cart moveCart(Cart c) {
    var m = new Cart(c);
    
    var possibleDirections = this.map[m.getX()][m.getY()].getConnections();
    if (possibleDirections.isEmpty()) {
      // no possible moves? -> not on tracks?, don't move
      return m;
    }
  
    // change direction if on crossroads:
    if (possibleDirections.size() == 4) {
      m.turn();
    } else if (m.getVector().ordinal() % 2 == 0) {
      var newDirection = possibleDirections.stream().filter(x -> x.ordinal() % 2 == 1).findFirst();
      newDirection.ifPresent(m::setVector);
    } else {
      var newDirection = possibleDirections.stream().filter(x -> x.ordinal() % 2 == 0).findFirst();
      newDirection.ifPresent(m::setVector);
    }
    
    // move along if possible
    if (possibleDirections.contains(m.getVector())) {
      switch (m.getVector()) {
        case NORTH:
          m.setY(m.getY() - 1);
          break;
        case SOUTH:
          m.setY(m.getY() + 1);
          break;
        case WEST:
          m.setX(m.getX() - 1);
          break;
        default:
          m.setX(m.getX() + 1);
      }
    }
    return m;
  }
  
}
