//*******************************************************************
//  PriorityQueueNode.java       Java Foundations
//
//  Represents a node in a priority queue.
//*******************************************************************

package javafoundations;

public class PriorityQueueNode<T> implements Comparable<PriorityQueueNode>
{
   private static int nextorder = 0;
   private int priority;
   private int order;
   private T element;

   //-----------------------------------------------------------------
   //  Creates a new node with the specified element.
   //-----------------------------------------------------------------
   public PriorityQueueNode (T obj, int prio) 
   {
      element = obj;
      priority = prio;
      order = nextorder;
      nextorder++;
   }
   
   //-----------------------------------------------------------------
   //  Returns the element in this node.
   //-----------------------------------------------------------------
   public T getElement() 
   {
      return element;
   }
   
   //-----------------------------------------------------------------
   //  Returns the piority value for the element in this node.
   //-----------------------------------------------------------------
   public int getPriority() 
   {
      return priority;
   }

   //-----------------------------------------------------------------
   //  Returns the order for this node (to break priority ties).
   //-----------------------------------------------------------------
   public int getOrder() 
   {
      return order;
   }
   
   //-----------------------------------------------------------------
   //  Returns a string representation of this node.
   //-----------------------------------------------------------------
   public String toString() 
   {
      String temp = (element.toString() + priority + order);
      return temp;
   }
   
   //-----------------------------------------------------------------
   //  Compares this node to the parameter, based on priority. Uses
   //  the order in which the elements were added to break ties.
   //-----------------------------------------------------------------
   public int compareTo(PriorityQueueNode obj) 
   {
      int result;

      if (priority < obj.getPriority())
         result = -1;
      else if (priority > obj.getPriority())
         result = 1;
      else if (order > obj.getOrder())  // break tied priorities
         result = -1;
      else
         result = 1;

      return result;
   }
}


