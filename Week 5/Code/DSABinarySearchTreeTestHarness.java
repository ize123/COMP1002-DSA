import java.util.*;

public class DSABinarySearchTreeTestHarness
{
   public static void main(String[] args)
   {
      // VARIABLE DECLARATIONS
      int iNumPassed = 0;
      int iNumTests = 0;
      DSABinarySearchTree tree = null;
      
      System.out.println("\n\nTesting Normal Conditions - Constructor");
      System.out.println("=======================================");

      // TEST 1 : CONSTRUCTOR
      try 
      {
         iNumTests++;
         tree = new DSABinarySearchTree();
         System.out.println("Testing creation of DSABinarySearchTree ");
         iNumPassed++;
         System.out.println("passed");
      } catch(Exception e) { System.out.println("FAILED"); }
      
      System.out.println("\nTest inserting, finding and deleting");
      System.out.println("==========================================================");
      
      // TEST 2 : INSERT
      try
      {
         iNumTests++;
         System.out.println("Testing inserting into DSABinarySearchTree ");
         tree.insert("d", 4);
         tree.insert("b", 2);
         tree.insert("f", 6);
         tree.insert("a", 1);
         tree.insert("e", 5);
         tree.insert("g", 7);
         tree.insert("c", 3);
         iNumPassed++;
         System.out.println("passed");
      } catch(Exception e) { System.out.println("FAILED"); }
      
      // TEST 3 : FIND
      try
      {
         iNumTests++;   
         System.out.println("Testing finding a key DSABinarySearchTree ");
         Object key = tree.find("e");
         iNumPassed++;
         System.out.println("passed");
      }catch(Exception e) { System.out.println("FAILED"); }
      
      // TEST 4 : DELETE
      try
      {
         iNumTests++;
         System.out.println("Testing deleting a key DSABinarySearchTree ");
         tree.delete("d");
         try
         {
            tree.find("d");
         }catch(Exception e) { }
         System.out.println(tree.height());
         tree.insert("d", 5);
         iNumPassed++;
         System.out.println("passed");
      }catch(Exception e) { System.out.println("FAILED" + e);} 
      
      System.out.println("\n\nTesting Additional Methods (Height, Min & Max)");
      System.out.println("=======================================");
      
      // TEST 5 : HEIGHT
      try
      {
         iNumTests++;
         System.out.println("Testing .height()");
         if(tree.height() != 4)
            throw new IllegalArgumentException("FAILED");
         iNumPassed++;
         System.out.println("passed");
      }catch(Exception e) { System.out.println("FAILED"); }
      
      // TEST 6 : MIN
      try
      {
         iNumTests++;
         System.out.println("Testing .min()");
         if(tree.min() != "a")
            throw new IllegalArgumentException("FAILED");
         iNumPassed++;
         System.out.println("passed");
      }catch(Exception e) { System.out.println("FAILED"); } 
      
      // TEST 7 : MAX
      try
      {
         iNumTests++;
         System.out.println("Testing .max()");
         if(tree.max() != "g")
            throw new IllegalArgumentException("FAILED");
         iNumPassed++;
         System.out.println("passed");
      }catch(Exception e) { System.out.println("FAILED"); } 
      
      System.out.println("\n\nTesting Traversal Methods");
      System.out.println("=======================================");
      
      // TEST 8 : INORDER
      try
      {
         iNumTests++;
         System.out.println("Testing .inorder()");
         tree.inorder();
         iNumPassed++;
         System.out.println("passed");
      }catch(Exception e) { System.out.println("FAILED"); }
      
      // TEST 9 : PREORDER
      try
      {
         iNumTests++;
         System.out.println("Testing .preorder()");
         tree.preorder();
         iNumPassed++;
         System.out.println("passed");
      }catch(Exception e) { System.out.println("FAILED"); }
      
      // TEST 10 : POSTORDER
      try
      {
         iNumTests++;
         System.out.println("Testing .postorder()");
         tree.postorder();
         iNumPassed++;
         System.out.println("passed");
      }catch(Exception e) { System.out.println("FAILED"); }
      
      // PRINT TEST SUMMARY
      System.out.print("\nNumber PASSED: " + iNumPassed + "/" + iNumTests);
      System.out.print(" -> " + (int)(double)iNumPassed/iNumTests*100 + "%\n");  
   }
}
