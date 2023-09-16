import java.util.*;
import java.io.*;

public class FileIO
{
   public static void main(String[] args)
   {
      DSAGraph graph = new DSAGraph();
      String filename = "prac6_1.al";
      try
      {
         readFileVertex(filename, graph);
         readFileEdges(filename, graph);
      }catch(Exception e)
      {
         System.out.println(e);
      }
      graph.display();
      System.out.println();
      graph.displayMatrix();
      System.out.println();
      graph.BFS("A");
      System.out.println();
      graph.DFS("A");
      System.out.println();
   }

   public static void readFileVertex(String filename, DSAGraph graph) throws IOException
   {
      File file = new File(filename);
      Scanner scan = new Scanner(file);

      while(scan.hasNextLine())
      {
         String line = scan.nextLine();
         String[] arr = line.split(" ");

         try
         {
            graph.addVertex(arr[0]);
         }
         catch(Exception e)
         {

         }

         try
         {
            graph.addVertex(arr[1]);
         }
         catch(Exception e)
         {

         }
      }
      scan.close();  
   }

   public static void readFileEdges(String filename, DSAGraph graph) throws IOException
   {
      File file = new File(filename);
      Scanner scan = new Scanner(file);

      while(scan.hasNextLine())
      {
         String line = scan.nextLine();
         String[] arr = line.split(" ");
         
         graph.addEdge(arr[0], arr[1]);
      }
      scan.close();
   }
}