package p14;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P14 {
  
  Integer[] scoreBoard;
  
  private static final Integer puzzleInput = 825401;
  private static final Integer inputLen = puzzleInput.toString().length();
  
  public static void main(String[] args) {
    var p14 = new P14();
    p14.scoreBoard = new Integer[200000000];
    p14.scoreBoard[0] = 3;
    p14.scoreBoard[1] = 7;
    p14.solve();
  }
  
  /**
   * Solve the puzzle
   */
  private void solve() {
    var first = 0;
    var second = 1;
    var current_last = 1;
    
    while (true) {
      int next = scoreBoard[first] + scoreBoard[second];
      if (next > 9) {
        scoreBoard[++current_last] = next / 10;
      }
      scoreBoard[++current_last] = next % 10;
      first = (first + 1 + scoreBoard[first]) % (current_last + 1);
      second = (second + 1 + scoreBoard[second]) % (current_last + 1);
      
      if (current_last > inputLen) {
        var z = listToInt(current_last, inputLen);
        if (z  == puzzleInput) {
          break;
        }
      }
    }
   
    var x = listToInt(current_last, 10);
    System.out.printf("Fist part: %s\n",x);
  }
  
  private int listToInt(int cursor, int length) {
    int pow = 0;
    int res = 0;
    for(int i = 0; i < length; i++) {
      res += scoreBoard[cursor - i] * Math.pow(10, pow++);
    }
    return res;
  }
}
