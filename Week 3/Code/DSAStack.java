import java.util.*;

public class DSAStack
{
   //Class Fields
   private Object[] stack;
   private int count;
   private final int DEFAULT_CAPACITY = 100;
   
   //Default Constructor
   public DSAStack()
   {
      stack = new Object[DEFAULT_CAPACITY];
      count = 0;
   }
   
   //Alternate Constructor
   public DSAStack(int size)
   {
      stack = new Object[size];
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
      if(count == stack.length)
      {
         isFull = true;
      }
      return isFull;
   }
   
   //Mutators
   public void push(Object value)
   {
      if(isFull())
      {
         throw new IllegalStateException("Stack is already full");
      }
      else
      {
         stack[count] = value;
         count++;
      }
   }
   
   public Object pop()
   {
      Object topVal;
      topVal = top();
      count--;
      return topVal;
   }
   
   public Object top()
   {
      Object topVal;
      if(isEmpty())
      {
         throw new IllegalStateException("Stack is empty");
      }
      else
      {
         topVal = stack[count - 1];
      }
      return topVal;
   }
   
   public void display()
   {
      if(isEmpty())
      {
         throw new IllegalStateException("Stack is empty");
      }
      for(int i = 0; i < count; i++)
      {
         System.out.print(stack[i]);
      }
      System.out.println("");
   }
}