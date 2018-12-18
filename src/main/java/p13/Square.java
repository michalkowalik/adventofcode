package p13;

import java.util.ArrayList;
import java.util.List;

enum Direction {
  NORTH, EAST, SOUTH, WEST;
}

/**
 * Map Square.
 *  - location
 *  - connects what with what
 */
public class Square {
  private int x;
  private int y;
  private List<Direction> connections;
  
  
  public Square(int x, int y) {
    this.x = x;
    this.y = y;
    this.connections = new ArrayList<>();
  }
  
  public int getX() {
    return x;
  }
  
  public void setX(int x) {
    this.x = x;
  }
  
  public int getY() {
    return y;
  }
  
  public void setY(int y) {
    this.y = y;
  }
  
  public List<Direction> getConnections() {
    return connections;
  }
  
  public void setConnections(List<Direction> connections) {
    this.connections = connections;
  }
  
  // Adding reference to the map array
  public void addConnections(String c, Square[][] map) {
    switch(c) {
      case "|":
        this.connections.add(Direction.NORTH);
        this.connections.add(Direction.SOUTH);
        break;
      case "-":
        this.connections.add(Direction.EAST);
        this.connections.add(Direction.WEST);
        break;
      case "/":
        if(this.x > 0 && map[this.x - 1][this.y].connections.contains(Direction.EAST)) {
          this.connections.add(Direction.WEST);
          this.connections.add(Direction.NORTH);
        } else {
          this.connections.add(Direction.EAST);
          this.connections.add(Direction.SOUTH);
        }
        break;
      case "\\":
        if(this.y > 0 && map[this.x][this.y -1].connections.contains(Direction.SOUTH)){
          this.connections.add(Direction.EAST);
          this.connections.add(Direction.NORTH);
        } else {
          this.connections.add(Direction.WEST);
          this.connections.add(Direction.SOUTH);
        }
        break;
      case "+":
        this.connections.add(Direction.SOUTH);
        this.connections.add(Direction.NORTH);
        this.connections.add(Direction.EAST);
        this.connections.add(Direction.WEST);
      default:
        break;
    }
  }
}
