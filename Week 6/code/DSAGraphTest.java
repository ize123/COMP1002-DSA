import java.util.*;

public class DSAGraphTest
{
   public static void main(String[] args)
   {
      DSAGraph graph = new DSAGraph();
      graph.addVertex("F");
      graph.addVertex("G");
      graph.addVertex("A");
      System.out.println(graph.hasVertex("A"));
      System.out.println(graph.hasVertex("G"));
      System.out.println(graph.hasVertex("F"));
      System.out.println(graph.getCount());
      graph.display();
   }
}
