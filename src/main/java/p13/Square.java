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
  
  public void addConnection(Direction d) {
    this.connections.add(d);
  }
}
