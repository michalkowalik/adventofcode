package p10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class P10 {

  private static final String INPUT_FILE = "input10.txt";
  static final String LINE_PATTERN =
          ".*<([ \\-\\d]+), ([ \\-\\d]+)>.*<([ \\-\\d]+), ([ \\-\\d]+)";
  
  public static void main(String[] args) {
    P10 p = new P10();
    p.solve();
  }
  
  // solve the puzzle
  private void solve() {
    try {
      var pattern = Pattern.compile(LINE_PATTERN);
      var points = readInput(pattern);
      
      var prevPoints = new ArrayList<Point>();
      int area = getArea(points);
      int modArea;
      int iteration = 0;
      
      while (true) {
        prevPoints = (ArrayList<Point>) points.stream().collect(Collectors.toList());
        points = nextView(points);
        modArea = getArea(points);
        if (modArea > area) {
          System.out.printf("Solution at iteration %d\n", iteration);
          break;
        }
        area = modArea;
        iteration++;
      }
      
      System.out.println(visualise(prevPoints));
      
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
  private int getArea(List<Point> points) {
    var miny = points.stream().map(p -> p.getY()).min((a, b) -> a > b ? 1 : -1);
    var maxy = points.stream().map(p -> p.getY()).max((a, b) -> a > b ? 1 : -1);
    return maxy.get() - miny.get();
  }
  
  
  // calculate next view:
   List<Point> nextView(List<Point> p) {
    return  p.stream().map(a -> new Point(
            a.getX() + a.gethVelocity(),
            a.getY() + a.getvVelocity(),
            a.gethVelocity(),
            a.getvVelocity())).collect(Collectors.toList());
  }
  
  private String visualise(List<Point> points) {
    var minx = points.stream().map(p -> p.getX()).min((a, b) -> a > b ? 1 : -1);
    var maxx = points.stream().map(p -> p.getX()).max((a, b) -> a > b ? 1 : -1);
    var miny = points.stream().map(p -> p.getY()).min((a, b) -> a > b ? 1 : -1);
    var maxy = points.stream().map(p -> p.getY()).max((a, b) -> a > b ? 1 : -1);
    
    // table [row][column] ([y][x]) - easier to print!
    String[][] table = new String[maxy.get() - miny.get() + 1][maxx.get() - minx.get() + 1];
    
    for (int row = 0; row < table.length; row++){
      for (int col = 0; col < table[row].length; col++) {
        table[row][col] = " ";
      }
    }
    points.forEach(p -> table[p.getY() - miny.get()][p.getX() - minx.get()] = "*");
  
    List<String> x = Arrays.stream(table).map(p -> String.join("", p)).collect(Collectors.toList());
    return String.join("\n", x);
  }
  
  private List<Point> readInput(Pattern pattern) throws IOException {
      return Files.lines(Paths.get(INPUT_FILE))
              .map(p -> {
                try {
                  return parseLine(p, pattern);
                } catch (ParseException pe) {
                  pe.printStackTrace();
                }
                return null;
              }).collect(Collectors.toList());
  }
  
   Point parseLine(String p, Pattern pattern) throws ParseException {
    var matcher = pattern.matcher(p);
    if (matcher.find()) {
      return new Point(
              Integer.parseInt(matcher.group(1).strip()),
              Integer.parseInt(matcher.group(2).strip()),
              Integer.parseInt(matcher.group(3).strip()),
              Integer.parseInt(matcher.group(4).strip()));
    } else throw new ParseException("can't parse" , 0);
  }
}
