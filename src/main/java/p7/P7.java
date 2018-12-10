package p7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class P7 {
  
  private static final String INPUT_PATH = "input7.txt";
  
  public static void main(String[] args) {
    P7 p7 = new P7();
    p7.solve();
  }
  
  /**
   * Solve day 7
   */
  private void solve() {
    try {
      List<String[]> input = readInput();
      Graph g = buildGraph(input);
  
      //travelGraph(g);
      parallelAssembly(g);
      
      System.out.println("done");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  // parallel assembly - day 7 puzzle, 2'nd part
  private void parallelAssembly(Graph g) {
    List<Worker> workers = new ArrayList<>();
    workers.add(new Worker("W1"));
    workers.add(new Worker("W2"));
    workers.add(new Worker("W3"));
    workers.add(new Worker("W4"));
    workers.add(new Worker("W5"));
  
    
    StringBuilder res = new StringBuilder();
    
    int currentTime = 0;
    List<String> top = findTop(g);
    Collections.sort(top);

    
    while(!g.getNodes().isEmpty()) {
      
      for(Worker w : workers) {
        w.updateStatus(currentTime);
        if (w.isDone()) {
          System.out.println(
                  "Worker " + w.getName() + " finished with " + w.getCurrentNode().getName() +
                  " at t=" + currentTime);
          res.append(w.getCurrentNode().getName());
          g.removeNode(w.getCurrentNode().getName());
          w.setDone(false);
          w.setWorking(false);
        }
      }
      
      // distribute tasks to workers
      top = findTop(g);
      Collections.sort(top);
  
      for(String node : top) {
        if(!nodeWorkedOn(node, workers)) {
          for(Worker w : workers) {
            if(! w.isWorking()) {
              w.startWork(g.getNode(node).get());
              break;
            }
          }
        }
      }
      
      currentTime++;
    }
    System.out.println(res);
  }
  
  
  private boolean nodeWorkedOn(String nodeName, List<Worker> workers) {
    List<String> currentTasks = workers.stream()
            .filter(Worker::isWorking)
            .map(p -> p.getCurrentNode().getName())
            .collect(Collectors.toList());
    
    return currentTasks.contains(nodeName);
  }
  
  
  // travel graph - part1 of of the day 7 Puzzle
  private void travelGraph(Graph g) {
    while (!g.getNodes().isEmpty()) {
      List<String> top = findTop(g);
      Collections.sort(top);
      System.out.printf("%s", top.get(0));
      g.removeNode(top.get(0));
    }
    System.out.print("\n");
  }
  
  // find top node(s)
  private List<String> findTop(Graph g) {
    List<String> x = g.getNodes().stream().map(Node::getName).collect(Collectors.toList());
    
    for(Node n : g.getNodes()) {
      List<Edge> connections = n.getConnections();
      for(Edge e : connections) {
        String end = e.getEnd().getName();
        x.remove(end);
      }
    }
    return x;
  }
  
  /**
   * build assembly order graph
   * @param input
   * @return
   */
  private Graph buildGraph(List<String[]> input) {
    Graph g = new Graph();
  
    for(String[] line : input) {
      if(! g.nodeExists(line[0])) {
        g.addNode(new Node(line[0]));
      }
      if(! g.nodeExists(line[1])) {
        g.addNode(new Node(line[1]));
      }
      
      // connection:
      Edge e = new Edge();
      e.setStart(g.getNode(line[0]).get());
      e.setEnd(g.getNode(line[1]).get());
      g.getNode(line[0]).get().addConnection(e);
    }
    
    return g;
  }
  
  private List<String[]> readInput() throws IOException {
    return Files.lines(Paths.get(INPUT_PATH))
            .map(this::parseLine).collect(Collectors.toList());
  }
  
  private String[] parseLine(String l) {
    String pattern = "Step ([A-Z]) .* step ([A-Z])";
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(l);
    
    if(m.find()) {
      return new String[] {m.group(1), m.group(2)};
    }
    return new String[]{};
  }
}
