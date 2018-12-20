package p13;

import java.util.Arrays;
import java.util.List;

public class Cart {
  private int x;
  private int y;
  private Direction vector;
  private int turnCount;
  private static List<Integer> turnOrder = Arrays.asList(-1, 0, 1);
  private boolean isDead;
  
  
  
  public Cart(int x, int y, String s) {
    this.x = x;
    this.y = y;
    this.vector = toVector(s);
    this.turnCount = 0;
    this.isDead = false;
  }

  public Cart(Cart c) {
    this.x = c.x;
    this.y = c.y;
    this.vector = c.vector;
    this.turnCount = c.turnCount;
  }
  
  
  public boolean isDead() {
    return isDead;
  }
  
  public void setDead(boolean dead) {
    isDead = dead;
  }
  
  
  // it would be nice to actually test it.
  public void turn() {
    int next_turn = turnOrder.get(turnCount % turnOrder.size());
    int x = vector.ordinal() + next_turn;
    if  (x < 0) {
      x = 3;
    }
    this.vector = Direction.values()[x % 4];
    turnCount++;
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
  
  public Direction getVector() {
    return vector;
  }
  
  public void setVector(Direction vector) {
    this.vector = vector;
  }
  
  public int getTurnCount(){
    return turnCount;
  }
  
  public void setTurnCount(int turnCount) {
    this.turnCount = turnCount;
  }
  
  // yes, I know, it's a stupid name.
  private Direction toVector(String s) {
    switch (s) {
      case "<":
        return Direction.WEST;
      case "^":
        return Direction.NORTH;
      case ">":
        return Direction.EAST;
      default:
        return Direction.SOUTH;
    }
  }
}
