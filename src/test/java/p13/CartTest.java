package p13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
  
  @Test
  void turn() {
    var cart = new Cart(10,10, "^");
    cart.turn();
    
    // first turn - left, was north, needs to be west
    assertEquals(Direction.WEST, cart.getVector());
    
    // second turn - no direction change
    cart.turn();
    assertEquals(Direction.WEST, cart.getVector());
    
    // third time - right
    cart.turn();
    assertEquals(Direction.NORTH, cart.getVector());
    
    // left again
    cart.turn();
    assertEquals(Direction.WEST, cart.getVector());
    
    // and straight again
    cart.turn();
    assertEquals(Direction.WEST, cart.getVector());
  }
  
  @Test
  void turnSouth() {
    var cart = new Cart(10, 10, "V");
    
    cart.turn();
    assertEquals(Direction.EAST, cart.getVector());
  }
}