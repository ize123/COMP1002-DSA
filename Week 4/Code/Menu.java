import java.util.*;
import java.io.*;

public class Menu
{
   public static void main(String[] args)
   {
      DSALinkedList list = new DSALinkedList();
      menu(list);
   }
   
   public static void menu(DSALinkedList list)
   {
      String str;
      boolean finished = false;
      
      while(!finished)
      {
         System.out.println("Please select an option:\n (a) insert first\n (b) insert last\n (c) remove first\n (d) remove last\n (e) display the list\n (f) write\n (g) read\n (h) finish");
         Scanner sc = new Scanner(System.in);
         char ch = sc.nextLine().charAt(0);
         
         switch(ch)
         {
            case 'a':
               System.out.println("Please enter what you would like to add first to the list:");
               str = sc.nextLine();
               list.insertFirst(str);
               break;
            case 'b':
               System.out.println("Please enter what you would like to add last to the list:");
               str = sc.nextLine();
               list.insertLast(str);
               break;
            case 'c':
               System.out.println("Removing first item in list.");
               list.removeFirst();
               break;
            case 'd':
               System.out.println("Removing last item in list.");
               list.removeLast();
               break;
            case 'e':
               Iterator iterator = list.iterator();
               while(iterator.hasNext())
               {
                  System.out.println(iterator.next());
               }
               break;
            case 'f':
               save(list, "savedlist.txt");
               break;
            case 'g':
               list = load("savedList.txt");
               break;
            case 'h':
               finished = true;
               break;
            default:
               System.out.println("ERROR INPUT");
         }
      }
   }
   
   public static void save(DSALinkedList listToSave, String filename)
   {
      FileOutputStream fileStrm;
      ObjectOutputStream objStrm;
      
      try
      {
         fileStrm = new FileOutputStream(filename);
         objStrm = new ObjectOutputStream(fileStrm);
         objStrm.writeObject(listToSave);
         
         objStrm.close();
      }
      catch(Exception e)
      {
         System.out.println("Error writing file: " + e);
      }
   }
   
   private static DSALinkedList load(String filename) throws IllegalArgumentException
   {
      FileInputStream fileStrm;
      ObjectInputStream objStrm;
      DSALinkedList inObj = new DSALinkedList();
      try
      {
         fileStrm = new FileInputStream(filename);
         objStrm = new ObjectInputStream(fileStrm);
         inObj = (DSALinkedList)objStrm.readObject();
         objStrm.close();
      }
      catch(ClassNotFoundException e){
         System.out.println("Class DSALinkedList not found" + e.getMessage());
      }
      catch(Exception e)
      {
         throw new IllegalArgumentException("Unable to load object form  file");
      }
      return inObj;
   }
}
