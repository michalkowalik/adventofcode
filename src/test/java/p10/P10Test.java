package p10;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class P10Test {
  
  @Test
  void parseLine() throws ParseException {
    P10 p = new P10();
    
    var line = "position=< 3, -2> velocity=<-1,  1>";
    var pattern = Pattern.compile(P10.LINE_PATTERN);
    
    
    Point pp = p.parseLine(line, pattern);
    
    assertEquals(3, pp.getX());
    assertEquals(-2, pp.getY());
    assertEquals(-1, pp.gethVelocity());
    assertEquals(1, pp.getvVelocity());
  }
  
  
  @Test
  void nextView() {
    var points = new ArrayList<Point>();
    
    points.add(new Point(9, 1, 0, 2));
    
    P10 p = new P10();
    
    var nextPoints = p.nextView(points);
    
    var modPoint = nextPoints.get(0);
    
    assertEquals(9, modPoint.getX());
    assertEquals(3, modPoint.getY());
    assertEquals(2, modPoint.getvVelocity());
  }
}