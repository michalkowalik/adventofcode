package p6;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class GridPoint implements Point {
  
  private int x;
  private int y;
  private TreeMap<Integer, Integer> distances;
  private Integer belongsTo;
  
  public Integer getBelongsTo() {
    return belongsTo;
  }
  
  void setBelongsTo(Integer belongsTo) {
    this.belongsTo = belongsTo;
  }
  
  public TreeMap<Integer, Integer> getDistances() {
    return distances;
  }
  
  void setDistances(TreeMap<Integer, Integer> distances) {
    this.distances = distances;
  }
  
  GridPoint(int x, int y) {
    this.x = x;
    this.y = y;
    this.distances = new TreeMap<>();
  }
  
  @Override
  public int getX() {
    return x;
  }
  
  @Override
  public int getY() {
    return y;
  }
  
  int getSumDistances() {
    return this.distances.values().stream().reduce(0, (x, y) -> x + y);
  }
  
  
  int getClosestCoordinate() {
    int minValue = Collections.min(
            this.distances.entrySet(),
            Comparator.comparingInt(Map.Entry::getValue)).getValue();
    
    // more than one closest neighbor?
    int proximityCount = 0;
    int closestCoordinateId = 0;
    
    for(Map.Entry<Integer, Integer> p : this.distances.entrySet()) {
      if (p.getValue() == minValue) {
        closestCoordinateId = p.getKey();
        proximityCount++;
      }
    }
    
    if (proximityCount > 1) {
      return -1;
    }
    return closestCoordinateId;
  }
}
