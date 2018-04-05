/**
 * FILENAME:  Hand.java
 * WHO:       
 * WHEN:     
 * WHAT:      
 */

import java.util.*;

public class Hand extends TileHolder {

  private static final int NUM_LETTERS_TO_START = 7; // DE: might not need this as an instance here - in player class too
  
  /** 
   * Class constructor. Initializes the Hand to the maximum number of tiles (7).
   */
  public Hand()
  {
    super(NUM_LETTERS_TO_START, 1); //calls on TileHolder to make myTiles and mySpaces
    //System.out.println("Hand initiated");
    while (this.getNumTiles() < NUM_LETTERS_TO_START)
      this.addTile(Bag.getNext());
  }
  
  
  /**
   * Adds a new Tile to the player's Hand. Called by <code>getNewTiles</code> in Player.
   * @param newTile the Tile to be added
   */
  public void addTile(Tile newTile)
  {
    int i = this.getFirstFreeSpace();
    super.addTile(newTile, i, 0);
  }
  
  /**
   * Adds a new Tile to the player's Hand. Called by <code>getNewTiles</code> in Player.
   * @param newTile the Tile to be added
   */
  public void addTile(int i, Tile newTile)
  {
    super.addTile(newTile, i,0);
  }
  
  /**
   * Finds the first free space in the hand and returns its index. Returns -1 if hand is full
   * @return freeSpace the index of the first free space
   */
  public int getFirstFreeSpace()
  {
    int i = 0;
    while (getTile(i,0) !=null && (i < NUM_LETTERS_TO_START -1)) //loops through array until you find the first open spot
      i++;
    if (i>=NUM_LETTERS_TO_START)
      return -1;
    else
      return i;
  }
  
  
  /**
   * Returns the index of a particular tile in the hand
   * @return index
   */
  public int getIndex(Tile t)
  {
    for (int x = 0; x < NUM_LETTERS_TO_START; x++) {
      //System.out.println(x);
      if (myTiles[x][0] != null){
      if (myTiles[x][0].getLetter() == t.getLetter())
        return x;
      }
    }
    return -1;
  }
  
  /**
   * Removes a Tile from the player's Hand.
   * @param tile the Tile to be removed
   */
  public void removeTile(Tile tile)
  {
    //System.out.println("Hand before: " + this);
    int i = getIndex(tile);
    if (i > -1){
      myTiles[i][0] = null;
      super.removeTile(i, 0);
    }
    //System.out.println("Hand after: " + this);
    //System.out.println("IsFree: " + mySpaces[i][0].isFree());
  }
  
  /**
   * Sets a Tile at a specified index in the player's Hand. Called by <code>swapTile</code> in Player.
   * @param tile the Tile to be added
   * @param index the index location of the Tile to add
   */
  public void setTile(int index, Tile tile)
  {
    myTiles[index][0] = tile;
  }
  
  /**
   * Shuffles the Tiles in the Player's Hand to new indices. For each Tile in the Hand, a random number is 
   * generated.
   */
  public void shuffleTiles()
  {
    int n = getNumTiles();  
    Random random = new Random();
    random.nextInt();
    
    for (int i = 0; i < NUM_LETTERS_TO_START; i++) {
      int change = i + random.nextInt(NUM_LETTERS_TO_START - i);
      swap(myTiles, i, change);
    }
  }
  
  // helper method for shuffle
  private void swap(Tile[][] hand, int i, int change) {
    Tile orig = hand[i][0];
    Tile replacement = hand[change][0];
    removeTile(i, 0);
    removeTile(change, 0);
    addTile(replacement, i, 0);
    addTile(orig, change, 0);
  }
  
  // Testing method
  public static void main (String args[])
  {
    Bag b = new Bag();
    Hand test = new Hand();
//    test.addTile(new Tile('D'), 0, 4);
//    test.addTile(new Tile('F'), 0, 6);
//    test.addTile(new Tile('X'), 0, 2);
//    test.addTile(new Tile('E'), 0, 4);
//    test.addTile(new Tile('R'));
//    test.addTile(new Tile('R'));
    //System.out.println(test);
//    //System.out.println("Testing getTile. Should be null: " + test.getTile(0,1));
//    //System.out.println("Testing getTile. Should be X: " + test.getTile(0, 2));
    //System.out.println("Testing getNumTiles. Should be 7: " + test.getNumTiles());
    //System.out.println("Remove tile R.");
    test.removeTile(new Tile('R'));
    //System.out.println(test);
    //System.out.println("Remove tile E.");
    test.removeTile(new Tile('E'));
    //System.out.println(test);
    //System.out.println("Testing shuffle");
    test.shuffleTiles();
    //System.out.println(test);
  }
}