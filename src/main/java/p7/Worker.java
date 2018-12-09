package p7;

public class Worker {
  private String name;
  private boolean isWorking;
  private boolean isDone;
  private int startTime;
  private int currentTime;
  private Node currentNode;

  public String getName() {
    return name;
  }
  
  public boolean isWorking() {
    return isWorking;
  }
  
  public void setWorking(boolean b) {
    this.isWorking = b;
  }
  
  public void setDone(boolean b) {
    this.isDone = b;
  }
  
  public boolean isDone() {
    return isDone;
  }
  
  public Node getCurrentNode() {
    return currentNode;
  }
  
  
  public Worker(String name) {
    this.name = name;
    this.isWorking = false;
    this.isDone = false;
  }
  
  public void updateStatus(int currentTime) {
    this.currentTime = currentTime;
    //!!!!
    if (isWorking && (currentNode != null)
            && currentTime - startTime >= (int)currentNode.getName().charAt(0) - 4) {
      this.isDone = true;
      this.isWorking = false;
    }
    // nothing to see here yet
  }
  
  public void startWork(Node n) {
    System.out.printf("Worker %s starting on %s at t=%d\n", this.name, n.getName(), currentTime);
    this.startTime = currentTime;
    this.currentNode = n;
    this.isWorking = true;
    this.isDone = false;
  }
}
