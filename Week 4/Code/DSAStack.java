import java.util.*;

public class DSAStack implements Iterable
{
   //Class Fields
   private DSALinkedList stack;
   
   //Default Constructor
   public DSAStack()
   {
      stack = new DSALinkedList();
   }
   
   public Iterator iterator()
   {
      return stack.iterator();
   }
   
   //Accessors
   public boolean isEmpty()
   {
      boolean isEmpty = false;
      if(stack.isEmpty())
      {
         isEmpty = true;
      }
      return isEmpty;
   }
   
   //Mutators
   public void push(Object value)
   {
      stack.insertFirst(value);
   }
   
   public Object pop()
   {
      Object topVal;
      topVal = stack.peekFirst();
      stack.removeFirst();
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
         topVal = stack.peekFirst();
      }
      return topVal;
   }
   
   public void display()
   {
      if(isEmpty())
      {
         throw new IllegalStateException("Stack is empty");
      }
      while(stack.iterator().hasNext())
      {
         System.out.println(stack.iterator().next());
      }
      System.out.println("");
   }
}