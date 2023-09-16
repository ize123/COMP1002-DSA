import java.util.*;

public class DSAQueue
{
   //Class Fields
   protected Object[] queue;
   protected int count;
   protected int front;
   protected int rear;
   protected final int DEFAULT_CAPACITY = 100;
   
   //Default Constructor
   public DSAQueue()
   {
      queue = new Object[DEFAULT_CAPACITY];
      front = 0;
      rear = 0;
      count = 0;
   }
   
   //Alternate Constructor
   public DSAQueue(int size)
   {
      queue = new Object[size];
      front = 0;
      rear = 0;
      count = 0;
   }
   
   //Accessors
   public int getCount()
   {
      return count;
   }
   
   public boolean isEmpty()
   {
      boolean isEmpty = false;
      if(count == 0)
      {
         isEmpty = true;
      }
      return isEmpty;
   }
   
   public boolean isFull()
   {
      boolean isFull = false; 
      if(count == queue.length)
      {
         isFull = true;
      }
      return isFull;
   }
   
   //Mutators
   public void enqueue(Object value)
   {
      if(isFull())
      {
         throw new IllegalStateException("Queue is already full");
      }
      else 
      {
         if(count < queue.length)
         {
            queue[rear] = value;
            count++;
            rear++;
            
            if((rear == queue.length) && (front > 0))
            {
               for(int i = 0; i < count; i++)
               {
                  queue[i] = queue[front + i];
                  front = 0;
                  rear = count;
               }
            }
         }
      }
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
         item = queue[front];
         count--;
         front++;
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
         topVal = queue[front];
      }
      return topVal;
   }
}