import java.util.*;

public class DSAGraph
{
   //private inner class
   private class DSAGraphVertex
   {
      //class fields
      public String label;
      public DSALinkedList list;
      public boolean visited;
      
      //Constructor
      public DSAGraphVertex(String _label)
      {
         label = _label;
         list = new DSALinkedList();
         visited = false;
      }
      
      //Accessors
      public String getLabel()
      {
         return label;
      }
            
      public DSALinkedList getAdjacent()
      {
           return list;
      }
      
      public void addEdge(DSAGraphVertex vertex)
      {
         list.insertFirst(vertex);
      }

      public boolean getVisisted() {
         return visited;
      }
      
      public void setVisited()
      {
         visited = true;
      }
      
      public void clearVisited()
      {
         visited = false;
      }
      
      public void ToString()
      {
         Iterator iter = list.iterator();
         while(iter.hasNext())
         {
            System.out.println(iter.next().toString());
         }
      }
   }
   
   
   //OUTER CLASS
   //Class Fields
   private DSALinkedList vertices;
   
   //Constructor
   
   public DSAGraph()
   {
      vertices = new DSALinkedList();
   }
   
   public void addVertex(String label)
   {
      if(hasVertex(label))
      {
         throw new IllegalArgumentException("Vertex already exists");
      }
      else
      {
         DSAGraphVertex vertex = new DSAGraphVertex(label);
         vertices.insertLast(vertex);
      }
   }
   
   public void addEdge(String label1, String label2)
   {
      DSAGraphVertex v1 = getVertex(label1);
      DSAGraphVertex v2 = getVertex(label2);
      v1.addEdge(v2);
      //v2.addEdge(v1); // not required if undirected
   }
   
   public boolean hasVertex(String label)
   {
      boolean hasVertex = false;
      Iterator iter = vertices.iterator();
      while(iter.hasNext())
      {
         DSAGraphVertex next = (DSAGraphVertex)iter.next();         
         if(next.getLabel().equals(label))
         {
            hasVertex = true;
         }
      }
      return hasVertex;
   }
   
   public int getCount()
   {
      int count = 0;
      Iterator iter = vertices.iterator();
      while(iter.hasNext())
      {
         iter.next();
         count++;
      }
      return count;
   }
   
   private DSAGraphVertex getVertex(String label)
   {
      DSAGraphVertex vertex = null;
      Iterator iter = vertices.iterator();
      while(iter.hasNext())
      {
         DSAGraphVertex next = (DSAGraphVertex)iter.next();
         if(next.getLabel().equals(label))
         {           
            vertex = next;
         }
      }
      return vertex;
   }
   
   private DSALinkedList getAdjacent(String label)
   {
      DSAGraphVertex vertex = getVertex(label);
      DSALinkedList adjacentList = vertex.getAdjacent();
      return adjacentList;
   }
   
   public boolean isAdjacent(String label1, String label2)
   {
      boolean adjacent = false;
      DSAGraphVertex v1 = getVertex(label1);
      DSAGraphVertex v2 = getVertex(label2);
      DSALinkedList adjacentList = v1.getAdjacent();
      Iterator iter = adjacentList.iterator();
      while(iter.hasNext())
      {
         if(iter.next() == v2)
         {
            adjacent = true;
         }
      }
      return adjacent;
   }
   
   public void display()
   {
      Iterator iter = vertices.iterator();
      System.out.println("GRAPH AS ADJACENCY LIST");
      while(iter.hasNext())
      {
         String label = ((DSAGraphVertex)iter.next()).getLabel();
         System.out.print("[" + label + "] |");
         DSAGraphVertex vertex = getVertex(label);
         DSALinkedList list = vertex.getAdjacent();
         Iterator iterator = list.iterator();
         while(iterator.hasNext())
         {
            System.out.print( " [" +((DSAGraphVertex)iterator.next()).getLabel() + "]");
         }
         System.out.println();     
      }
   }

   public void displayMatrix()
   {
      Iterator iter = vertices.iterator();
      System.out.println("GRAPH AS ADJACENCY MATRIX");
      System.out.print("    ");
      while(iter.hasNext())
      {
         DSAGraphVertex next = (DSAGraphVertex)iter.next();
         System.out.print("[" + next.getLabel() + "] ");
      }
      System.out.println();
      Iterator iter2  = vertices.iterator();
      
      while(iter2.hasNext())
      {
         DSAGraphVertex vert1 = (DSAGraphVertex)iter2.next();
         Iterator iter3 = vertices.iterator();
         System.out.print("[" + vert1.getLabel() +"] " );
         while(iter3.hasNext())
         {
            DSAGraphVertex vert2 = (DSAGraphVertex)iter3.next();
            if(isAdjacent(vert1.getLabel(), vert2.getLabel()))
            {
               System.out.print(" 1  ");
            }
            else
            {
               System.out.print(" 0  ");
            }                     
         }
         System.out.println();  
      }

      System.out.println();
   }

   public DSAGraphVertex returnUnvisited(DSAGraphVertex vertex)
   {
      DSAGraphVertex temp = null;
      if (vertex != null) 
      {
         Iterator iter = vertex.getAdjacent().iterator();

         while (iter.hasNext()) {
            DSAGraphVertex vert1 = (DSAGraphVertex) iter.next();
   
            if (vert1.getVisisted() == false) {
               temp = vert1;
            }
         }
      }

      return temp;  
   }

   public void DFS(String src) 
   {
      int size = getCount();
      DSAStack stack = new DSAStack(size);
      resetVertices();

      DSAGraphVertex start = getVertex(src);
      start.setVisited();
      stack.push(start);
      System.out.print("Depth First Search Path: " + start.getLabel());

      while (!stack.isEmpty()) 
      {
         DSAGraphVertex vert1 = (DSAGraphVertex) stack.top();
         DSAGraphVertex next = returnUnvisited(vert1);
         
         if (next == null) 
         {
            stack.pop();
         }
         else 
         {
            next.setVisited();
            stack.push(next);
            System.out.print(" -> " + next.getLabel());
         }
      }
      System.out.println();
   }

   public void resetVertices() 
   {
      Iterator iter = vertices.iterator();

      while (iter.hasNext()) 
      {
         DSAGraphVertex vertex = (DSAGraphVertex) iter.next();

         vertex.clearVisited();
      }
   }

   public void BFS(String src) 
   {
      int size = getCount();
      DSAQueue queue = new DSAQueue(size);
      resetVertices();

      DSAGraphVertex start = getVertex(src);
      start.setVisited();
      queue.enqueue(start);
      System.out.print("Breadth Firsth Search Path: "+ start.getLabel());

      while (!queue.isEmpty()) {
         DSAGraphVertex vert1 = (DSAGraphVertex) queue.dequeue();

         for (Object o : vert1.getAdjacent()) 
         {
            DSAGraphVertex vert2 = (DSAGraphVertex) o;

            if (vert2.getVisisted() == false) 
            {
               vert2.setVisited();
               queue.enqueue(vert2);
               System.out.print(" -> " + vert2.getLabel());
            }
         }
      }
      System.out.println();
   }
}