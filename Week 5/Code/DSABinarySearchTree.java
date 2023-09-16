import java.util.*;
import java.io.Serializable;

public class DSABinarySearchTree implements Serializable
{
   private class DSATreeNode implements Serializable
   {
      private String m_key;
      private Object m_value;
      private DSATreeNode m_leftChild;
      private DSATreeNode m_rightChild;
      
      public DSATreeNode(String inKey, Object inVal)
      {
         if(inKey == null) 
         {
            throw new IllegalArgumentException("Key cannot be null");
         }
         
         m_key = inKey;
         m_value = inVal;
         m_leftChild = null;
         m_rightChild = null;
      }
      
      public String getKey() { return m_key; }
      public Object getValue() { return m_value; }
      public DSATreeNode getLeft() { return m_leftChild; }
      public void setLeft(DSATreeNode newLeft) { m_leftChild = newLeft; }
      public DSATreeNode getRight() { return m_rightChild; }
      public void setRight(DSATreeNode newRight) { m_rightChild = newRight; }
   }
   
   private DSATreeNode m_root;
   
   public DSABinarySearchTree()
   {
      m_root = null; //start with an emtpy tree
   }
   
   //Wrapper - Find
   public Object find(String key)
   {
      return findRec(key, m_root);
   }
   //Function - Find
   private Object findRec(String key, DSATreeNode currNode)
   {
      Object value = null;
      
      if(currNode == null)
      {
         throw new NoSuchElementException("Key " + key + " not found");
      }
      else if(key.equals(currNode.getKey()))
      {
         value = currNode.getValue();
      }
      else if(key.compareTo(currNode.getKey()) < 0)
      {
         value = findRec(key, currNode.getLeft());
      }
      else
      {
         value = findRec(key, currNode.getRight());
      }
      return value;
   }
   
   //Wrapper - insert
   public void insert(String key, Object value)
   {
      m_root = insertRec(key, value, m_root);
   }
   //Function - insert
   private DSATreeNode insertRec(String key, Object data, DSATreeNode currNode)
   {
      DSATreeNode updateNode = currNode;
      if(currNode == null)
      {
         DSATreeNode newNode = new DSATreeNode(key, data);
         updateNode = newNode;
      }
      else if(key.equals(currNode.getKey()))
      {
         throw new IllegalArgumentException("Key already exists");
      }
      else if(key.compareTo(currNode.getKey()) < 0)
      {
         currNode.setLeft(insertRec(key, data, currNode.getLeft()));
      }
      else
      {
         currNode.setRight(insertRec(key, data, currNode.getRight()));
      }
      return updateNode;
   }
   
   //Wrapper - delete
   public void delete(String key)
   {  
      m_root = deleteRec(key, m_root);
   }
   //Function - delete
   private DSATreeNode deleteRec(String key, DSATreeNode currNode)
   {
      DSATreeNode updateNode = currNode;
      if(currNode == null)
      {
         throw new NoSuchElementException("Key " + key + " not found");
      }
      else if(key.equals(currNode.getKey()))
      {
         updateNode = deleteNode(key,currNode);
      }
      else if(key.compareTo(currNode.getKey()) < 0)
      {
         currNode.setLeft(deleteRec(key, currNode.getLeft()));
      }
      else
      {
         currNode.setRight(deleteRec(key, currNode.getRight()));
      }
      return updateNode;
   }
   
   private DSATreeNode deleteNode(String key, DSATreeNode delNode)
   {
      DSATreeNode updateNode = null;
      if(delNode.getLeft() == null && delNode.getRight() == null)
      {
         updateNode = null; // no children
      }
      else if(delNode.getLeft() != null && delNode.getRight() == null)
      {
         updateNode = delNode.getLeft(); // one child - left
      }
      else if(delNode.getLeft() == null && delNode.getRight() != null)
      {
         updateNode = delNode.getRight(); // one child - right
      }
      else
      {
         updateNode = promoteSuccessor(delNode.getRight()); // two children
         if(updateNode != delNode.getRight()) // no cycles
         {
            updateNode.setRight(delNode.getRight()); // update right
         }
         updateNode.setLeft(delNode.getLeft()); // and left
      }
      return updateNode;
   }
   
   private DSATreeNode promoteSuccessor(DSATreeNode cur)
   {   
      DSATreeNode successor = cur;
      if(cur.getLeft() != null)
      {
         successor = promoteSuccessor(cur.getLeft());
         if(successor == cur.getLeft()) // parent of the successor
         {
            cur.setLeft(successor.getRight()); // needs to adopt right child
         }
      }
      return successor;
   }
   
   //Wrapper - min
   public String min()
   {
      String minKey = null;
      minKey = minRec(m_root);
      return minKey;
   }
   //Function - min
   private String minRec(DSATreeNode currNode)
   {
      String minKey;
      if(currNode.getLeft() != null)
      {
         minKey = minRec(currNode.getLeft());
      }
      else
      {
         minKey = currNode.getKey();
      }
      return minKey;
   }
   
   //Wrapper - max
   public String max()
   {
      String maxKey = null;
      maxKey = maxRec(m_root);
      return maxKey;
   }
   //Function - max
   private String maxRec(DSATreeNode currNode)
   {
      String maxKey;
      if(currNode.getRight() != null)
      {
         maxKey = maxRec(currNode.getRight());
      }
      else
      {
         maxKey = currNode.getKey();
      }
      return maxKey;
   }
   
   //Wrapper - height
   public int height()
   {
      return heightRec(m_root);
   }
   //Function - height
   private int heightRec(DSATreeNode currNode)
   {
      int htSoFar, iLeftHt, iRightHt;
      
      if(currNode == null)
      {
         htSoFar = 0; // base case - no more along this branch 
      }
      else
      {
         iLeftHt = heightRec(currNode.getLeft());
         iRightHt = heightRec(currNode.getRight());
         
         //Get height of left vs right branches
         if(iLeftHt > iRightHt)
         {
            htSoFar = iLeftHt + 1;
         }
         else
         {
            htSoFar = iRightHt + 1;
         }       
      }
      return htSoFar;
   }
   
   //Traversal Methods
   public DSAQueue inorder()
   {
      DSAQueue queue = new DSAQueue();
      InorderRec(m_root, queue);
      return queue;
   }
   private void InorderRec(DSATreeNode currNode, DSAQueue queue)
   { 
      if(currNode != null)
      {             
         InorderRec(currNode.getLeft(), queue);
         System.out.println(currNode.getKey());
         queue.enqueue(currNode);
         InorderRec(currNode.getRight(), queue);         
      }
   }
   
   public void preorder()
   {
      PreorderRec(m_root);
   }
   private void PreorderRec(DSATreeNode currNode)
   {
      if(currNode != null)
      {   
         System.out.println(currNode.getKey());    
         PreorderRec(currNode.getLeft());
         PreorderRec(currNode.getRight());         
      }
   }
   
   public void postorder()
   {
      PostorderRec(m_root);
   }
   private void PostorderRec(DSATreeNode currNode)
   {
      if(currNode != null)
      {      
         PostorderRec(currNode.getLeft());
         PostorderRec(currNode.getRight());
         System.out.println(currNode.getKey());          
      }
   }
}