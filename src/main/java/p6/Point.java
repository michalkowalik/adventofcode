package p6;

public class Point {
  private  int x;
  private  int y;
  
  
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public Point(String line) {
    String[] parts = line.split(", ");
    this.x = Integer.parseInt(parts[0]);
    this.y = Integer.parseInt(parts[1]);
    
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
}
