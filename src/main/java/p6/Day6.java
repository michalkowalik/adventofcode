package p6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class Day6 {
  
  private static final String INPUT_PATH = "input6.txt";
  
  // area boundaries.
  private int minx;
  private int maxx;
  private int miny;
  private int maxy;

  // hashmap to keep the points:
  private HashMap<Integer, BasePoint> coordinates;
  
  // grid:
  private List<GridPoint> grid;
  
  public static void main(String[] args) {
    Day6 d6 = new Day6();
    d6.solve();
  }
  
  private void solve() {
    try {
      List<BasePoint> points = this.readInput();
      
      // base points
      this.coordinates = new HashMap<>();
      
      // grid points
      // grid:
      this.grid = new ArrayList<>();
      
      // find boundaries:
      this.maxx = points.get(0).getX();
      this.minx = maxx;
      this.maxy = points.get(0).getY();
      this.miny = maxy;
      coordinates.put(0, points.get(0));
      
      int i = 0;
      for(BasePoint p : points) {
        coordinates.put(i, points.get(i));
        i++;
        if (p.getX() > maxx) {
          maxx = p.getX();
        }
        if (p.getX() < minx) {
          minx = p.getX();
        }
        if (p.getY() > maxy) {
          maxy = p.getY();
        }
        if (p.getY() < miny) {
          miny = p.getY();
        }
      }
      
      // instantiate grid:
      for(int x = minx; x <= maxx; x++) {
        for(int y = miny; y <= maxy; y++) {
          GridPoint gp = new GridPoint(x, y);
          
          // add distances
          gp.setDistances(distances(gp));
          gp.setBelongsTo(gp.getClosestCoordinate());
          grid.add(gp);
        }
      }

      this.part1();
      this.part2();
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
  // create HashMap containing distances between a gridPoint and all the base points:
  private TreeMap<Integer, Integer> distances(Point gp) {
    TreeMap<Integer, Integer> res = new TreeMap<>();
    this.coordinates.forEach((k, v) -> res.put(k, manhattanDistance(gp, v)));
    return res;
  }
  
  
  // part 1:
  private void part1() {
    
    // invalidate infinite areas:
    this.grid.forEach(gp -> {
      if ((gp.getX() == this.minx || gp.getX() == this.maxx) && gp.getBelongsTo() > -1) {
        this.coordinates.get(gp.getBelongsTo()).setInfinite(true);
      }
      if ((gp.getY() == this.miny || gp.getY() == this.maxy) && gp.getBelongsTo() > -1) {
        this.coordinates.get(gp.getBelongsTo()).setInfinite(true);
      }
    });
    
    
    int biggestCount = 0;
    int biggestId = 0;

    for(Map.Entry<Integer, BasePoint> p : this.coordinates.entrySet())  {
      if (p.getValue().isInfinite()) {
        continue;
      }
      int setCount = (int) this.grid.stream().filter(gp -> gp.getBelongsTo() == p.getKey())
              .count();
      if (biggestCount < setCount) {
        biggestCount = setCount;
        biggestId = p.getKey();
      }
    }
  
    System.out.printf("\nP1 : Biggest Count: %d, id: %d\n", biggestCount, biggestId);
    
  }

  
  // part 2:
  private void part2() {
    long res = this.grid.stream().filter(p -> p.getSumDistances() < 10000).count();
    System.out.println("P2 : total area: " + res);
  }
  
  
  /**
   * Read and interpret input file. Return list of points
   * @return List  of points
   * @throws IOException if file can't be read, or misformed
   */
  private List<BasePoint> readInput() throws IOException {
    return Files.lines(Paths.get(INPUT_PATH))
            .map(line -> new BasePoint(line)).collect(Collectors.toList());
  }
  
  // get a Manhattan distance between two points
   int manhattanDistance(Point p1, Point p2) {
    return abs(p1.getX() - p2.getX()) + abs(p1.getY() - p2.getY());
  }
}
