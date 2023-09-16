import java.util.*;

public class UnitTestLinkedListIterator
{
   public static void main(String[] args)
   {
      DSALinkedList ll = new DSALinkedList();
      Object nodeValue;
      String testString;
      
      
      System.out.println("TESTING USING ITERATOR IN DSALINKEDLIST");
      try
      {
         ll.insertFirst("abc");
         ll.insertFirst("jkl");
         ll.insertFirst("xyz");
      
         Iterator iterator = ll.iterator();
         testString = iterator.next().toString();
         if(testString == "xyz");
         {
            System.out.println("correct");
         }
         testString = iterator.next().toString();
         if(testString == "jkl");
         {
            System.out.println("correct");
         }
         testString = iterator.next().toString();
         if(testString == "abc");
         {
            System.out.println("correct");
         }
         System.out.println("PASSED");
      }
      catch(Exception e){  System.out.println("FAILED"); }   
   }
}