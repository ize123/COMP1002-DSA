import java.util.*;

public class DSAGraph
{
   //private inner class
   private class DSAGraphVertex
   {
      //class fields
      private String label;
      private DSALinkedList list;
      private boolean visited;
      
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
         list.insertLast(vertex);
      }

      public void setLabel(String inLabel)
      {
         label = inLabel;
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
   
   //private inner class
   private class DSAGraphEdge
   {
      //class Fields
      private DSAGraphVertex from;
      private DSAGraphVertex to;
      private int value;
      private DSALinkedList security;
      private DSALinkedList barriers;

      //Constructor
      public DSAGraphEdge(DSAGraphVertex fromVertex, DSAGraphVertex toVertex, int inValue, DSALinkedList inSecurity, DSALinkedList inBarrier)
      {
         from = fromVertex;
         to = toVertex;
         value = inValue;
         security = inSecurity;
         barriers = inBarrier;
      }

      //Accessors
      public int getValue()
      {
         return value;
      }

      public DSAGraphVertex getFrom()
      {
         return from;
      }

      public DSAGraphVertex getTo()
      {
         return to;
      }

      public DSALinkedList getSecuirty()
      {
         return security;
      }

      public DSALinkedList getBarriers()
      {
         return barriers;
      }
   }
   
   //OUTER CLASS
   //Class Fields
   private DSALinkedList vertices;
   private DSALinkedList edges;
   
   //Constructor
   public DSAGraph()
   {
      vertices = new DSALinkedList();
      edges = new DSALinkedList();
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
   
   public void addEdge(String label1, String label2, int inValue, DSALinkedList security, DSALinkedList barriers)
   {
      DSAGraphVertex v1 = getVertex(label1);
      DSAGraphVertex v2 = getVertex(label2);
      v1.addEdge(v2);
      //v2.addEdge(v1); // not required if undirected

      //New code adding edges
      DSAGraphEdge edge = new DSAGraphEdge(v1, v2, inValue, security, barriers);
      edges.insertLast(edge);
   }
   
   public void removeVertex(String label)
   {
      DSAGraphVertex v1 = getVertex(label);
      Iterator iter = vertices.iterator();
      while(iter.hasNext())
      {
         if((DSAGraphVertex)iter.next() == v1)
         {
            vertices.removeAt(v1);
         }
      }

      Iterator iter2 = edges.iterator();
      while(iter2.hasNext())
      {
         DSAGraphEdge edge = (DSAGraphEdge)iter2.next();
         if(edge.getFrom() == v1 || edge.getTo() == v1)
         {
            edges.removeAt(edge);
         }
      }
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
   
   public void updateNode(String oldValue, String newValue)
   {
      Iterator iter = vertices.iterator();
      while(iter.hasNext())
      {
         DSAGraphVertex vert = (DSAGraphVertex)iter.next();
         if(vert.getLabel().equals(oldValue))
         {
            vert.setLabel(newValue);
         }
      }
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
   
   //Prints out the details of all edges of a specified vertex
   public void getEdgeDetails(String label)
   {
      System.out.println("Finding all edges connected to this node");
      DSALinkedList edgeList = getEdges(label);
      for(Object o : edgeList)
      {
         DSAGraphEdge edge = (DSAGraphEdge) o;
         Iterator iter = edge.security.iterator();
         String secString = "";
         while(iter.hasNext())
         {
            secString += iter.next().toString() + " ";
         }

         Iterator iter2 = edge.barriers.iterator();
         String barString = "";
         while(iter2.hasNext())
         {
            barString += iter2.next().toString() + " ";
         }
         System.out.println("FROM:\t  " + ((DSAGraphVertex)edge.getFrom()).getLabel() + "\nTO:\t  " + ((DSAGraphVertex)edge.getTo()).getLabel() + "\nSecurity: " + secString + "\nBarriers: " + barString);
         System.out.println();
      }
   }

   //Gets all edges that are associated with a vertex and returns a full list of them 
   private DSALinkedList getEdges(String label)
   {
      DSALinkedList list = new DSALinkedList();
      Iterator iter = edges.iterator();
      while(iter.hasNext())
      {
         DSAGraphEdge next = (DSAGraphEdge)iter.next();
         if(((DSAGraphVertex)next.getFrom()).getLabel().equals(label) || ((DSAGraphVertex)next.getTo()).getLabel().equals(label))
         {
            list.insertLast(next);
         }
      }
      return list;
   }

   private DSAGraphEdge getEdge(String label)
   {
      DSAGraphEdge edge = null;
      Iterator iter = edges.iterator();
      while(iter.hasNext())
      {
         DSAGraphEdge next = (DSAGraphEdge)iter.next();
         if(((DSAGraphVertex)next.getFrom()).getLabel().equals(label))
         {
            edge = next;
         }
      }
      return edge;
   }

   private DSALinkedList getAdjacent(String label)
   {
      DSAGraphVertex vertex = getVertex(label);
      DSALinkedList adjacentList = vertex.getAdjacent();
      return adjacentList;
   }
   
   private DSALinkedList getAdjacentE(String label)
   {
      DSAGraphEdge edge = getEdge(label);
      DSALinkedList adjacentList = new DSALinkedList();
      Iterator iter = edges.iterator();
      while(iter.hasNext())
      {
         DSAGraphEdge edge2 = (DSAGraphEdge)iter.next();
         if(edge.getFrom() == edge2.getFrom())
         {
            adjacentList.insertFirst(edge2);
         }
      }
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
      System.out.println("DISPLAYING GRAPH AS ADJACENCY LIST: ");
      while(iter.hasNext())
      {
         String label = ((DSAGraphVertex)iter.next()).getLabel();
         System.out.print("" + label + "\t| ");
         DSAGraphVertex vertex = getVertex(label);
         DSALinkedList list = vertex.getAdjacent();
         Iterator iterator = list.iterator();
         while(iterator.hasNext())
         {
            System.out.print( ""+ ((DSAGraphVertex)iterator.next()).getLabel() + "\t");
         }
         System.out.println();     
      }
   }

   public void displayAsMatrix()
   {
      Iterator iter = vertices.iterator();
      System.out.println("DISPLAYING GRAPH AS MATRIX: ");
      System.out.print("\t\t\t");
      while(iter.hasNext())
      {
         String label = ((DSAGraphVertex)iter.next()).getLabel();
         System.out.print(label + "\t");
      }
      System.out.println();

      Iterator iter2 = vertices.iterator();      

      while(iter2.hasNext())
      {
         Iterator iter3 = vertices.iterator();
         DSAGraphVertex vert1 = (DSAGraphVertex)iter2.next();
         System.out.print(vert1.getLabel() + "\t");
         while(iter3.hasNext())
         {
            DSAGraphVertex vert2 = (DSAGraphVertex)iter3.next();
            if(isAdjacent(vert1.getLabel(), vert2.getLabel()))
            { 
               Iterator edgeIter = edges.iterator();
               String edgeValue = "";
               while(edgeIter.hasNext())
               {
                  DSAGraphEdge edge = (DSAGraphEdge)edgeIter.next();
                  if(edge.getFrom() == vert1 && edge.getTo() == vert2)
                  {
                     edgeValue += Integer.toString(edge.getValue()) + ",";
                  }
               }
               String newString = edgeValue.substring(0, edgeValue.length() -1);
               String formatString = String.format("\t%s\t", newString);
               System.out.print(formatString);
            }
            else
            {
               System.out.print("\t0\t");
            }
         }
         System.out.println();
      }
      System.out.println();
   }

   public DSAGraphVertex returnUnvisited(DSAGraphVertex vertex) {
      
      DSAGraphVertex temp = null;

      if (vertex != null) {
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

   public void DFS(String src) {
      int size = getCount();
      DSAStack stack = new DSAStack(size);
      resetVertices();

      DSAGraphVertex start = getVertex(src);
      start.setVisited();
      stack.push(start);
      System.out.print(start.getLabel());

      while (!stack.isEmpty()) {
         DSAGraphVertex vert1 = (DSAGraphVertex) stack.top();
         DSAGraphVertex next = returnUnvisited(vert1);         
         
         if (next == null) {
            stack.pop();
         }
         else {
            next.setVisited();
            stack.push(next);
            System.out.print(" -> " + next.getLabel());
         }
      }
      System.out.println();
   }

   public void resetVertices() {
      Iterator iter = vertices.iterator();

      while (iter.hasNext()) {
         DSAGraphVertex vertex = (DSAGraphVertex) iter.next();

         vertex.clearVisited();
      }
   }

   public void BFS(String src) {
      int size = getCount();
      DSAQueue queue = new DSAQueue(size);
      resetVertices();

      DSAGraphVertex start = getVertex(src);
      start.setVisited();
      queue.enqueue(start);
      System.out.print(start.getLabel());

      while (!queue.isEmpty()) {
         DSAGraphVertex vert1 = (DSAGraphVertex) queue.dequeue();

         for (Object o : vert1.getAdjacent()) {
            DSAGraphVertex vert2 = (DSAGraphVertex) o;

            if (vert2.getVisisted() == false) {
               vert2.setVisited();
               queue.enqueue(vert2);
               System.out.print(" -> " + vert2.getLabel());
            }
         }
      }
      System.out.println();
   }
}