/**
 * FILENAME:  Player.java
 * WHO:       Diana Eastman
 * WHEN:      May 17, 2013
 * WHAT:      Represents a player in the game and encapsulates all player details (hand, name, turn status)
 *           
 */

import javafoundations.*;

public class Player {
  private Hand playerHand; // Tiles available for play during a turn
  private String playerName;
  private int playerScore;
  private int numRemainingSwaps; // The number of times the player can swap a tile with her opponent (out of a possible 3)
  private static final int MAX_TILES_ON_HAND = 7;
  private static final int MAX_NUM_SWAPS = 3;
  private boolean isTurn; 
 
 // Constructor
 public Player(String name)
 {
  playerName = name;
  playerHand = new Hand(); 
  playerScore = 0;
  numRemainingSwaps = MAX_NUM_SWAPS;
  isTurn = false;
 }
 
 /**
  * Checks if it is the Player's turn.
  * @return boolean true if the Player's turn and false otherwise.
  */
 public boolean isTurn()
 {
   return isTurn;
 }
 
 /**
  * Sets the Player's turn to true.
  * @param boolean 
  */
 public void setTurn(boolean b)
 {
   isTurn = b;
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
  * Returns the Player's current hand.
  * @return hand player's hand
  */
 public Hand getHand()
 {
  return playerHand;
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
  return playerHand.getTile(index, 0);
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
  * Fills the player's hand with up to 7 Tiles from the Bag while the bag still has Tiles.
  */
 public void getNewTiles()
 {
   while (playerHand.getNumTiles() < MAX_TILES_ON_HAND && !Bag.isEmpty())
   {
    playerHand.addTile(Bag.getNext());
   }
 }
 
 /**
  * Increases the player's total score.
  * @param int the number of points to add 
  */
 public void updateScore(int num)
 {
  playerScore += num;
 }
 
 /**
  * Player's total score.
  * @param int the number of points to add 
  */
 public void playTile(int index)
 {
   Tile playTile = getTile(index);
   playerHand.removeTile(playTile);
 }
 
 // Method to swap a tile with the other player
 public void swapTile(Player other, Tile callerTile, Tile otherTile)
 {
   playerHand.removeTile(callerTile);
   playerHand.addTile(otherTile);
   other.getHand().removeTile(otherTile);
   other.getHand().addTile(callerTile);
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
   
   System.out.println("Player 1 plays 3 tiles and gets new tiles from the bag.");
   player1.playTile(2);
   player1.playTile(1);
   player1.playTile(4);
   player1.getHand().addTile(Bag.getNext());
   player1.getHand().addTile(Bag.getNext());
   player1.getHand().addTile(Bag.getNext());

   System.out.println(player1);
   
   System.out.println(player2);
   Tile player1ToSwap = player1.getHand().getTile(1,0);
   Tile player2ToSwap = player2.getHand().getTile(3,0);
   System.out.println("Player 1 will swap " + player1ToSwap + " with Player 2's" + player2ToSwap);
   player1.swapTile(player2, player1ToSwap, player2ToSwap);
   System.out.println(player1);
   System.out.println(player2);
   player1.updateScore(30);
   System.out.println("Add points to player 1's score [30]: " + player1.getScore());
   System.out.println("Get player 1's current hand: " + player1.getHand());
   System.out.println("Player 1's turn status [FALSE]: " + player1.isTurn());
   player1.setTurn(true);
   System.out.println("Player 1's turn status after setting to true [TRUE]: " + player1.isTurn());
   Player player3 = new Player("Leslie Knope");
   System.out.println("Get player 3's name: " + player3.getName());
 }
}
