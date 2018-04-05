//*******************************************************************
//  PriorityQueue.java       Java Foundations
//
//  Represents a priority queue that uses a underlying heap.
//*******************************************************************

package javafoundations;

public class PriorityQueue<T> extends LinkedMaxHeap<PriorityQueueNode<T>>
{
   //-----------------------------------------------------------------
   //  Creates a new, empty priority queue.
   //-----------------------------------------------------------------
   public PriorityQueue() 
   {
      super();
   }
   
   //-----------------------------------------------------------------
   //  Adds the specified element to this priority queue.
   //-----------------------------------------------------------------
   public void addElement (T object, int priority) 
   {
      PriorityQueueNode<T> node = new PriorityQueueNode<T> (object, priority);
      super.addElement(node);
   }
   
   //-----------------------------------------------------------------
   //  Removes the next highest priority element from this queue and
   //  returns a reference to it.
   //-----------------------------------------------------------------
   public T removeNext() 
   {
      PriorityQueueNode<T> temp = (PriorityQueueNode<T>)super.removeMin();
      return temp.getElement();
   }
}


