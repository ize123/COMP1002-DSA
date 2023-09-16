import java.util.*;
import java.io.Serializable;

public class DSALinkedList implements Iterable, Serializable
{
   public Iterator iterator()
   {
      return new DSALinkedListIterator(this);
   }
   
   //private inner class
   private class DSALinkedListIterator implements Iterator
   {
      private DSAListNode iterNext;
      public DSALinkedListIterator(DSALinkedList theList)
      {
         iterNext = theList.head;
      }
      //Iterator interface implementation
      public boolean hasNext() { return (iterNext != null); }
      public Object next()
      {
         Object value;
         if(iterNext == null)
         {
            value = null;
         }
         else
         {
            value = iterNext.getValue();
            iterNext = iterNext.getNext();
         }
         return value;
      }
      public void remove() { throw new UnsupportedOperationException("Not supported"); } 
   }
   
   //private inner class
   private class DSAListNode implements Serializable
   {
      //class fields
      public Object m_value;
      public DSAListNode m_next;
      public DSAListNode m_prev;
      
      //constructor
      public DSAListNode(Object inValue)
      {
         m_value = inValue;
         m_next = null;
         m_prev = null;
      }
      
      //class methods
      public Object getValue()
      {
         return m_value;
      }
      
      public void setValue(Object inValue)
      {
         m_value = inValue;
      }
      
      public DSAListNode getNext()
      {
         return m_next;
      }
      
      public void setNext(DSAListNode newNext)
      {
         m_next = newNext;
      }
      
      public DSAListNode getPrev()
      {
         return m_prev;
      }
      
      public void setPrev(DSAListNode newPrev)
      {
         m_prev = newPrev;
      }
   }
   
   //class fields
   public DSAListNode head;
   public DSAListNode tail;
   
   //constructor
   public DSALinkedList()
   {
      head = null;
      tail= null; 
   }
   
   //class methods 
   public void insertFirst(Object newValue)
   {
      DSAListNode newNd = new DSAListNode(newValue);
      if(isEmpty())
      {
         head = newNd;
         tail = newNd;
      }
      else
      {
         head.setPrev(newNd);
         newNd.setNext(head);
         head = newNd;
      }
   }
   
   public void insertLast(Object newValue)
   {
      DSAListNode newNd = new DSAListNode(newValue);
      if(isEmpty())
      {
         head = newNd;
         tail = newNd;
      }
      else
      {
         tail.setNext(newNd);
         newNd.setPrev(tail);
         tail = newNd;
      }
   }
   
   public boolean isEmpty()
   {
      boolean empty = (head == null);
      return empty;
   }
   
   public Object peekFirst()
   {
      Object nodeValue;
      if(isEmpty())
      {
         throw new IllegalStateException("List is empty");
      }
      else
      {
         nodeValue = head.getValue();
      }
      return nodeValue;
   }
   
   public Object peekLast()
   {
      Object nodeValue;
      if(isEmpty())
      {
         throw new IllegalStateException("List is empty");
      }
      else
      {
         nodeValue = tail.getValue();
      }
      return nodeValue;
   }
   
   public Object removeFirst()
   {
      Object nodeValue;
      if(isEmpty())
      {
         throw new IllegalStateException("List is empty");
      }
      else
      {
         nodeValue = head.getValue();
         if(head.getNext() == null)
         {
            head = null; 
         }
         else
         {
            head = head.getNext();
            head.setPrev(null);
         }
      }
      return nodeValue;
   }
   
   public Object removeLast()
   {
      Object nodeValue;
      if(isEmpty())
      {
         throw new IllegalStateException("List is empty");
      }
      else if(head.getNext() == null)
      {
         nodeValue = head.getValue();
         head = null;
      }
      else
      {
         nodeValue = tail.getValue();
         tail = tail.getPrev();
         tail.setNext(null);
      }
      return nodeValue;
   }

   public void removeAt(Object nodeValue)
   {
      if(isEmpty())
      {
         throw new IllegalStateException("List is empty");
      }
      else if(head.getNext() == null)
      {
         if(head.getValue() == nodeValue)
         {
            head = null;
         }
      }
      else
      {
         DSAListNode temp = head;
         while(temp != null)
         {
            if(temp.getValue() == nodeValue)
            {
               if(temp == head)
               {
                  head = head.getNext();
                  head.getPrev().setPrev(null);
               }
               else if(temp == tail)
               {
                  temp.getPrev().setNext(null);
               }
               else
               {
                  temp.getPrev().setNext(temp.getNext());
                  temp.getNext().setPrev(temp.getPrev());
               }         
               temp = null;
            }   
            if(temp != null)
            {
               temp = temp.getNext(); 
            }             
         }
      }
   }
}