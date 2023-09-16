import java.util.*;

public class DSAQueue implements Iterable
{
   //Class Fields
   protected DSALinkedList queue;
   protected int front;
   protected int rear;
   
   //Default Constructor
   public DSAQueue()
   {
      queue = new DSALinkedList();
      front = 0;
      rear = 0;
   }
   
   public Iterator iterator()
   {
      return queue.iterator();
   }
   
   //Accessors
   public boolean isEmpty()
   {
      boolean isEmpty = false;
      if(queue.isEmpty())
      {
         isEmpty = true;
      }
      return isEmpty;
   }
   
   //Mutators
   public void enqueue(Object value)
   {
      queue.insertLast(value);
   }
   
   public Object dequeue()
   {
      Object item;
      if(isEmpty())
      {
         throw new IllegalStateException("Queue is empty");
      }
      else
      {
         item = queue.peekFirst();
         queue.removeFirst();
         
      }
      return item;
   }
   
   public Object peek()
   {
      Object topVal;
      if(isEmpty())
      {
         throw new IllegalStateException("Nothing in queue");
      }
      else
      {
         topVal = queue.peekFirst();
      }
      return topVal;
   }
}