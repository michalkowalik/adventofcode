package p10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class P10 {

  private static final String INPUT_FILE = "input10.test.txt";
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
      
      
      
      System.out.println("done");
    } catch (IOException e) {
      e.printStackTrace();
    }
    
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
