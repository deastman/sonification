/**
 * FILENAME:  Hand.java
 * WHO:       
 * WHEN:     
 * WHAT:      
 */

import java.util.*;

public class Hand {
 
 LinkedList<Tile> playerHand;  // linked list of tiles
 private static final int NUM_LETTERS_TO_START = 7; // DE: might not need this as an instance here - in player class too
 
 /** 
  * Class constructor. Initializes the Hand to the maximum number of tiles (7).
  */
 public Hand()
 {
   playerHand = new LinkedList<Tile>();
   while (playerHand.size() < NUM_LETTERS_TO_START)
     playerHand.add(Bag.getNext());
 }
 
 /**
  * Returns the Tile at a particular index in the Hand.
  * @param index the index of the Tile to be returned
  * @return Tile
  */
 public Tile getTile(int index)
 {
  return playerHand.get(index);
 }
 
 /**
  * Adds a new Tile to the player's Hand. Called by <code>getNewTiles</code> in Player.
  * @param newTile the Tile to be added
  */
 public void addTile(Tile newTile)
 {
  playerHand.add(newTile);
 }
 
 /**
  * Adds a new Tile to the player's Hand at a specific index.
  * @param newTile the Tile to be added
  */
 public void addTile(int i, Tile newTile)
 {
  playerHand.add(i, newTile);
 }
 
 /**
  * Removes a Tile from the player's Hand.
  * @param tile the Tile to be removed
  */
 public void removeTile(Tile tile)
 {
  playerHand.remove(tile);
 }
 
 /**
  * Sets a Tile at a specified index in the player's Hand. Called by <code>swapTile</code> in Player.
  * @param tile the Tile to be added
  * @param index the index location of the Tile to add
  */
 public void setTile(int index, Tile tile)
 {
  playerHand.set(index, tile);
 }
 
 /**
  * Shuffles the Tiles in the Player's Hand to new indices. For each Tile in the Hand, a random number is 
  * generated.
  * @param tile the Tile to be added
  * @param index the index location of the Tile to add
  */
 public void shuffleTiles()
 {
   int n = playerHand.size();  
   Random random = new Random();
   random.nextInt();
                
   for (int i = 0; i < n; i++) {
     int change = i + random.nextInt(n - i);
     swap(playerHand, i, change);
   }
 }
 
 // helper method for shuffle
 private void swap(LinkedList<Tile> hand, int i, int change) {
    Tile helper = hand.get(i);
    hand.set(i, hand.get(change));          
    hand.set(change, helper);
  }
 
 // get the number of tile in the hand
 public int getNumTiles()
 {
  return playerHand.size();
 }
 
 /**
   * Prints out a Hand object.
   * @return String
   **/
  final public String toString() 
  {
    String result ="";
    result += "Tiles currently in the player's hand: \n" ;  
    for (int i = 0; i < playerHand.size(); i++) {
      result += playerHand.get(i).toString();
    }
    return result;
  }
}
