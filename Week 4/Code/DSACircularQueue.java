import java.util.*;

public class DSACircularQueue extends DSAQueue
{
   //Constructor
   public DSACircularQueue()
   {
      queue = new Object[DEFAULT_CAPACITY];
      front = -1;
      rear = -1;
      count = 0;
   }
   
   //Alternate constructor
   public DSACircularQueue(int value)
   {
      queue = new Object[value];
      front = -1;
      rear = -1;
      count = 0;
   }
   
   //Mutators
   @Override
   public void enqueue(Object value)
   {
      if(isFull())
      {
         throw new IllegalStateException("Queue is already full");
      }
      else if(isEmpty())
      {
         front++;
      }
      rear = (rear + 1) % queue.length;
      queue[rear] = value;
      count++;
      System.out.println(front);
      System.out.println(rear);
   }
   
   @Override
   public Object dequeue()
   {
      
      if(isEmpty())
      {
         throw new IllegalStateException("Queue is already empty");
      }
      Object item = queue[front];
      if(front == rear)
      {
         front = rear = -1;
      }
      else
      {
         front = (front + 1) % queue.length;
      }
      count--;
      System.out.println(front);
      System.out.println(rear);
      return item;
      
   }
}