import java.util.*;
import java.io.*;

public class DSABinarySearchTreeMenu
{
   public static void main(String[] args)
   {
      DSABinarySearchTree tree = new DSABinarySearchTree();
      tree.insert("d", 4);
      tree.insert("b", 2);
      tree.insert("f", 6);
      tree.insert("a", 1);
      tree.insert("e", 5);
      tree.insert("g", 7);
      tree.insert("c", 3);
      
      menu(tree);

   }
   
   public static void menu(DSABinarySearchTree tree)
   {
      boolean finished = false;
      while(!finished)
      {
         System.out.println("Please select an option:\n (a) read csv file\n (b) read serialized file\n (c) display tree\n (d) write csv file\n (e) write serialized file\n (f) finish");
         Scanner sc = new Scanner(System.in);
         char ch = sc.nextLine().charAt(0);
         
         switch(ch)
         {
            case 'a':
               //readCSV();
               break;
            case 'b':
               tree = null;
               tree = readSerialized("Serialized.txt");
               break;
            case 'c':
               displayTree(tree);
               break;
            case 'd':
               //writeCSV();
               break;
            case 'e':
               writeSerialized(tree, "serialized.txt");
               break;
            case 'f':
               finished = true;
               break;
            default:
               System.out.println("ERROR INPUT");
          }
      }
   }
   
   public static void writeCSV()
   {
      
   }
   
   public static void writeSerialized(DSABinarySearchTree treeToSave, String filename)
   {
      FileOutputStream fileStrm;
      ObjectOutputStream objStrm;
      
      try
      {
         fileStrm = new FileOutputStream(filename);
         objStrm = new ObjectOutputStream(fileStrm);
         objStrm.writeObject(treeToSave);
         
         objStrm.close();
      }
      catch(Exception e)
      {
         System.out.println("Error writing file: " + e);
      }
   }
   
   public static DSABinarySearchTree readSerialized(String filename)
   {
      FileInputStream fileStrm;
      ObjectInputStream objStrm;
      DSABinarySearchTree inObj = new DSABinarySearchTree();
      try
      {
         fileStrm = new FileInputStream(filename);
         objStrm = new ObjectInputStream(fileStrm);
         inObj = (DSABinarySearchTree)objStrm.readObject();
         objStrm.close();
      }
      catch(ClassNotFoundException e){
         System.out.println("Class DSABinarySearchTree not found" + e.getMessage());
      }
      catch(Exception e)
      {
         throw new IllegalArgumentException("Unable to load object form  file");
      }
      return inObj;
   }
   
   public static void displayTree(DSABinarySearchTree tree)
   {
      System.out.println("Please select an option:\n (a) inorder\n (b) prerder\n (c) postorder\n");
      Scanner sc = new Scanner(System.in);
      char ch = sc.nextLine().charAt(0);
      
      switch(ch)
      {
         case 'a':
            tree.inorder();
            break;
         case 'b':
            tree.preorder();
            break;
         case 'c':
            tree.postorder();
            break;
         default:
            System.out.println("Error input");
      }
   }
   
   
}