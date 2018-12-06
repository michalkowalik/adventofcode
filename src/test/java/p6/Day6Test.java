package p6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {
  
  @Test
  void manhattanDistance() {
    
    Day6 d = new Day6();
    
    Point p1 = new Point(0, 0);
    Point p2 = new Point(1, 1);
    
    assertEquals(2, d.manhattanDistance(p1, p2));
  }
  
  @Test
  void isFiniteArea() {
  }
}