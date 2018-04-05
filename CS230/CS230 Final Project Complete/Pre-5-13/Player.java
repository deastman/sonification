/**
 * FILENAME:  Board.java
 * WHO:       
 * WHEN:     
 * WHAT:      
 */

import javafoundations.*;

public class Player {
  private Hand playerHand;
  private String playerName;
  private int playerScore;
  private int numRemainingSwaps;
  private static final int MAX_TILES_ON_HAND = 7;
  private static final int MAX_NUM_SWAPS = 3;
 
 // Constructor
 public Player(String name)
 {
  playerName = name;
  playerHand = new Hand(); 
  playerScore = 0;
  numRemainingSwaps = MAX_NUM_SWAPS;
 }
 
 /**
  * Returns a Player's name.
  * @return String The Player's name
  */
 public String getName()
 {
  return playerName;
 }
 
 /**
  * Returns the Player's current score.
  * @return int player's score
  */
 public int getScore()
 {
  return playerScore;
 }
 
 /**
  * Returns Player's number of remaining swaps out of a maximum of 3. 
  * @return int number of remaining swaps
  */
 public int getRemainingSwaps()
 {
  return numRemainingSwaps;
 }
 
 /**
  * Returns the Tile at a specific index in the Player's hand of Tiles.
  * @return Tile
  */
 public Tile getTile(int index)
 {
  return playerHand.getTile(index);
 }
 
 /**
  * Returns the number of Tiles the player currently has in her hand of a maximum of 7.
  * @return int number of Tiles in player's current hand
  */
 public int getNumTiles()
 {
  return playerHand.getNumTiles();
 }
 
 /**
  * Fills the player's hand with up to 7 Tiles from the Bag
  */
 public void getNewTiles()
 {
   while (playerHand.getNumTiles() < MAX_TILES_ON_HAND && !Bag.isEmpty())
   {
    playerHand.addTile(Bag.getNext());
   }
 }
 
 // increase the player's score
 public void updateScore(int num)
 {
  
 }
 
 // play a tile and remove it from the player's hand
 public void playTile(int index)
 {
   Tile playTile = getTile(index);
   playerHand.removeTile(playTile);
 }
 
 // Method to swap a tile with the other player
 // DE: Need to add some sort of validation here - could throw an index out of bounds exception b/c LL
 public void swapTile(Player other, int indexCaller, int indexOther)
 {
   Tile callerTile = playerHand.getTile(indexCaller);
   Tile otherTile = other.playerHand.getTile(indexOther);
   
   playerHand.setTile(indexCaller, otherTile);
   other.playerHand.setTile(indexOther, callerTile);
   numRemainingSwaps--;
 }
 
 /**
  * Prints out a String representation of a Player object.
  * @return String
  */
 final public String toString(){
   String result ="";
   result += "Player name: " + playerName + "\n";
   result += "Number of remaining swaps: " + numRemainingSwaps + "\n";
   result += "Current score: "  +  playerScore + "\n";
   result +=  playerHand.toString();  
   return result;
 }
 
// Testing method
 public static void main (String[] args)
 {
   Bag testbag = new Bag(); // Will be initialized in the Game class
   Player player1 = new Player("Player 1");
   Player player2 = new Player("Player 2");
   System.out.println(player1);
   player1.playerHand.shuffleTiles();
   System.out.println("After shuffling Player 1's tiles:");
   System.out.println(player1);
   
   System.out.println("Player 1 plays 4 tiles and gets new tiles from the bag.");
   player1.playTile(6);
   player1.playTile(1);
   player1.playTile(4);
   player1.playTile(3);
   player1.getNewTiles();
   
   System.out.println(player1);
   System.out.println(player2);
   Tile player1ToSwap = player1.playerHand.getTile(2);
   Tile player2ToSwap = player2.playerHand.getTile(3);
   System.out.println("Player 1 will swap " + player1ToSwap + " with Player 2's" + player2ToSwap);
   player1.swapTile(player2, 2, 3);
   System.out.println(player1);
   System.out.println(player2);
 }
}
