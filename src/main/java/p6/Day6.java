package p6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class Day6 {
  
  private static final String INPUT_PATH = "input6.txt";
  
  public static void main(String[] args) {
    Day6 d6 = new Day6();
    d6.solve();
  }
  
  private void solve() {
    try {
      List<Point> points = this.readInput();
      System.out.println("here");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
  private List<Point> readInput() throws IOException {
    return Files.lines(Paths.get(INPUT_PATH))
            .map(line -> new Point(line)).collect(Collectors.toList());
  }
  
  // get a Manhattan distance between two points
   int manhattanDistance(Point p1, Point p2) {
    return abs(p1.getX() - p2.getX()) + abs(p1.getY() - p2.getY());
  }
  
  // is the area defined by a point finite (closed by other points)?
   boolean isFiniteArea(Point p1) {
    return false;
  }
}
