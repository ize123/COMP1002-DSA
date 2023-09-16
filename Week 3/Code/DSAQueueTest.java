import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class DSAQueueTest {

   private DSAQueue queue = new DSAQueue(5);
   
   @Test public void enqueueTest() //tests that enqueuing adds to the queue by checking the count
   {
      queue.enqueue(4);
      queue.enqueue(4);
      assertEquals(2, queue.getCount());
   }
   
   @Test public void dequeueTest() //tests that dequeuing removes from the queue by checking the count
   {
      queue.enqueue(4);
      queue.enqueue(4);
      queue.dequeue();
      assertEquals(1, queue.getCount());
   }

   @Test (expected = IllegalStateException.class)//tests that dequeuing an empty queue will throw an exception
   public void emptyQueueDedqueueTest()
   {
      queue.dequeue();
   }
   
   @Test (expected = IllegalStateException.class)//tests that enqueuing full queue will throw an exception
   public void fullenqueueTest()
   {
      for(int i = 0; i <= 5; i++)
      {
         queue.enqueue(1);
      }
   }
   
   @Test public void testPeek() //tests that the peek function looks at the first item in the queue after adding and removing items
   {
      queue.enqueue(1);
      queue.enqueue(6);
      queue.enqueue(3);
      queue.enqueue(8); 
      assertEquals(1, queue.peek());
      queue.dequeue();
      assertEquals(6, queue.peek());
      
   }
   
   @Test public void testCount() //Tests that the count is correct after adding and removing items
   {
      queue.enqueue(1);
      queue.enqueue(6);
      assertEquals(2, queue.getCount());
      queue.dequeue();
      assertEquals(1, queue.getCount());
   }
   
   @Test public void testIsEmpty() //Tests if the queue is empty function returns the right values
   {
      queue.enqueue(1);
      assertEquals(false, queue.isEmpty());
      queue.dequeue();
      assertEquals(true, queue.isEmpty());
   }
   
   @Test public void testIsFull() //Tests if the queue isEmpty function returns the right values
   {
      for(int i = 0; i < 5; i++)
      {
         queue.enqueue(1);
      }
      assertEquals(true, queue.isFull()); //Tests that the queue isFull function returns correct values when full and empty
      for(int i = 0; i < 5; i++)
      {
         queue.dequeue();
      }
      assertEquals(false, queue.isFull());
   }
}
