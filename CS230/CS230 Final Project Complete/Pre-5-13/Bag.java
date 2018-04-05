/**
 * FILENAME:  Bag.java
 * WHO:       
 * WHEN:     
 * WHAT:      
 */

import javafoundations.*;
import java.util.*;

public class Bag {
  private static LinkedList<Tile> tileBagList;
  private static LinkedQueue<Tile> tileBagQueue;
        
  /**
   * Constructor. Adds the standard set of tiles (98 in total) to the "Bag" of available
   * tiles. Tiles added to the bag by iterating through tiles array in Tile class (the 
   * 26 tiles in the alphabet) in the outer loop and looping within the inner loop the 
   * number of times the tile tile appears in the bag (e.g., there are 9 As, but only
   * 1 Z in the standard Scrabble bag). The tiles are then randomly shuffled and added to
   * a linked queue.
   */
  public Bag() 
  {                   
    tileBagList = new LinkedList<Tile>();
    tileBagQueue = new LinkedQueue<Tile>();
    for (char i : Tile.letters){ 
      for (int j = 0; j < Tile.getAmountOfTilesInBag(i); j++){
        tileBagList.add(new Tile(i));
      }
    }
    shuffleBag(); //Shuffle the tiles 
    enqueueShuffledBag(); //Enqueue the shuffled tiles into a LinkedQueue
  }
        
  private void shuffleBag() 
  {
    int n = tileBagList.size();  
    Random random = new Random();
    random.nextInt();
                
    for (int i = 0; i < n; i++) {
      int change = i + random.nextInt(n - i);
      swap(tileBagList, i, change);
    }
  }

  private void swap(LinkedList<Tile> bag, int i, int change) 
  {
    Tile helper = bag.get(i);
    bag.set(i, bag.get(change));          
    bag.set(change, helper);
  }
  
  private void enqueueShuffledBag() 
  {
    for (int i = 0; i<tileBagList.size(); i++)
      tileBagQueue.enqueue(tileBagList.get(i));
  }
  
  /**
   * Gets the next tile from the queue ("Bag") if not queue is not empty.
   * @return Tile A random Tile
   */
  public static Tile getNext()
  {
    if(tileBagQueue.isEmpty())
      return null;           
    return(tileBagQueue.dequeue());
  }
  
  /**
   * Gets the next tile from the queue ("Bag") if not queue is not empty.
   * @return Tile A random Tile
   */
  public static boolean isEmpty()
  {
   return tileBagQueue.isEmpty(); 
  }
  
  public String toString()
  {
   String s = "";
   s+="There are " + tileBagList.size() + " tiles in the bag. \n";
   for (int i = 0; i<tileBagList.size(); i++)
      s+=tileBagList.get(i);
   return s; 
  }
  
  // Testing method
  public static void main (String args[]){
   Bag test = new Bag();
   System.out.println(test);
   System.out.println(test.getNext());
  }
}