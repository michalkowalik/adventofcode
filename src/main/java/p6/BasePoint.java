package p6;

public class BasePoint implements Point {
  private  int x;
  private  int y;
  private boolean isInfinite;
  
  public BasePoint(int x, int y) {
    this.x = x;
    this.y = y;
    this.isInfinite = false;
  }
  
  public BasePoint(String line) {
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
  
  public boolean isInfinite() {
    return isInfinite;
  }
  
  public void setInfinite(boolean infinite) {
    isInfinite = infinite;
  }
}
