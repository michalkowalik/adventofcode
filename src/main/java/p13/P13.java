package p13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class P13 {
  
  private static final String INPUT_PATH = "input13.txt";
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
      System.out.printf("initialized %d carts\n", carts.size());
      
      var i = 0;
      // loop while true
      while(step()) {
        System.out.println("Iteration: " + i++);
        carts = carts.stream().filter(p -> !p.isDead()).collect(Collectors.toList());
        if (carts.size() < 2) {
          break;
        }
      }
      
      System.out.printf("Last cart location: x: %d, y: %d \n",
              carts.get(0).getX(), carts.get(0).getY());
      
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
      for (int x = 0; x < line.length; x++) {
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
   * sadly, there's no elegant way of doing it, carts need to be iterated over
   */
  private boolean step() {
    for(Cart c : this.carts) {
      var tc = moveCart(c);
      c.setX(tc.getX());
      c.setY(tc.getY());
      c.setTurnCount(tc.getTurnCount());
      c.setVector(tc.getVector());
      
      
      // ugly! - but works.
      var collided = detectCollision(c);
      if (collided.isPresent()) {
        System.out.printf(
                "collision detected at x: %d, y: %d\n",
                collided.get().get(0).getX(), collided.get().get(0).getY());
        
        for(Cart cart : collided.get()) {
          System.out.printf("marking as dead cart at x: %d, y: %d\n", cart.getX(), cart.getY());
          cart.setDead(true);
        }
      }
      
    }
    return true;
  }
  
  
  /**
   * detect a collision
   *
   * @return empty if no collision, cart taking part in collision if any:
   */
  private Optional<List<Cart>> detectCollision(Cart c) {
    
      var collided =
              this.carts.stream()
                      .filter(p -> p.getX() == c.getX() && p.getY() == c.getY() )
                      .collect(Collectors.toList());
      if (collided.size() > 1) {
        return Optional.of(collided);
      }
    
    return Optional.empty();
  }
  
  /**
   * move single cart
   *
   * @param c cart to be moved
   * @return modified cart
   */
  private Cart moveCart(Cart c) {
    var m = new Cart(c);
    
    var possibleDirections = this.map[m.getX()][m.getY()].getConnections();
    if (possibleDirections.isEmpty()) {
      // no possible moves? -> not on tracks?, don't
      System.out.println("Cart out of tracks?");
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
