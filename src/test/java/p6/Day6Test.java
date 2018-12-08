package p6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {
  
  @Test
  void manhattanDistance() {
    
    Day6 d = new Day6();
    
    BasePoint p1 = new BasePoint(0, 0);
    BasePoint p2 = new BasePoint(1, 1);
    
    assertEquals(2, d.manhattanDistance(p1, p2));
  }
}