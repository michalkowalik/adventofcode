package p10;

public class Point {
  private int x;
  private int y;
  private int hVelocity;
  private int vVelocity;
  
  
  public Point(int x, int y, int hv, int vv) {
    this.x = x;
    this.y = y;
    this.hVelocity = hv;
    this.vVelocity = vv;
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
  
  public int gethVelocity() {
    return hVelocity;
  }
  
  public void sethVelocity(int hVelocity) {
    this.hVelocity = hVelocity;
  }
  
  public int getvVelocity() {
    return vVelocity;
  }
  
  public void setvVelocity(int vVelocity) {
    this.vVelocity = vVelocity;
  }
}
