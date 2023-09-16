import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class DSAStackTest {

   private DSAStack stack = new DSAStack(5);
 
   @Test public void pushTest() //tests that pushing an object onto the stack increases th  count by 1
   {
      stack.push(1);
      assertEquals(1, stack.getCount());
   }
   
   @Test public void popTest() //tests the pop makes the count decrease 
   {
      stack.push(" ");
      stack.pop();
      assertEquals(0, stack.getCount());
   }
   
   @Test (expected = IllegalStateException.class) //tests that trying to pop an empty stack will throw an error
   public void emptyPopTest()
   {
      stack.pop();     
   }
   
   @Test (expected = IllegalStateException.class) //tests that pushing a full queue throws an error
   public void pushFullQueue()
   {
      for(int i = 0; i <= 5; i++)
      {
         stack.push(1);
      }
   }
   
   @Test public void testTop() //test that top() will return the object on top of stack
   {
      stack.push(1);
      stack.push(2);
      stack.push(3);
      stack.push(4);
      
      assertEquals(4, stack.top());
   }
   
   @Test public void testCount() //tests the stack counter
   {
      stack.push(23);
      stack.push(7);
      assertEquals(2, stack.getCount());
      stack.push(0);
      assertEquals(3, stack.getCount());
   }
   
   @Test public void testIsEmpty() //test if isEmpty returns true when empty and false when not
   {
      assertEquals(true, stack.isEmpty());
      stack.push(1);
      assertEquals(false, stack.isEmpty());
   }
   
   @Test public void testisFull() // test if isFull returns false when empty and true when not 
   {
      assertEquals(false, stack.isFull());
      stack.push(1);
      stack.push(1);
      stack.push(1);
      stack.push(1);
      stack.push(1);
      assertEquals(true, stack.isFull());
      
   }
}
