/** Simulates a Dice Poker game played between the computer and user. 
 * This class definition contains a main() method that assumes 
 * that the user enters a name and an integer in the command line, for example: 
 * java PlayDice Wendy 7
 * If the user does not enter these arguments, however, default values are
 * provided. The default name is "Dave" and the default number of rounds is 5.
 * @author   Takis Metaxas 
 * @version     %I%, %G%
 */

/* 
 DiceGame.java
 CS230 Homework Assignment P4
 Modified by: Diana Eastman
 Modified date: February 11, 2013
*/

import java.util.*; 

public class DiceGame {
 
  /** Creates a game object that contains the variables associated with a game.
  */
  String name; // Dave is supplied vy default
  int numRounds; // 5 rounds by default
  int pwin; //round wins of the player
  int cwin; //round wins of the computer
  private DicePlayer computerplayer;
  private DicePlayer humanplayer;
  //Provide an array of strings to map the ranks to the names of the hands
  private static String[] ranks = {"Nothing", "One Pair", "Two Pair", "Three of a Kind", "Full House", "Four of a Kind", "Five of a Kind" };

  public DiceGame(String name) 
  {
    name = "Dave";
    numRounds = 5;
    pwin = 0;
    cwin = 0;
    humanplayer = new DicePlayer(name);
    computerplayer = new DicePlayer("Hal");
  }
 
  /** Counts how many distinct values appear in the input array
  * and stores each count onto the diceResults array.
  * PRE-CONDITION: diceResults[] should have enough length 
  * to accomodate the values found in the input array.
  *
  * @param input   the input array
  * @param diceResults  holds the multiplicity of values found in input.
  *       Note that this is actually the output parameter
  */
  private void accumulateValues(int[] input, int[] diceResults) 
  {
    int count[] = new int[6];
    for(int i = 0; i < input.length; i++) 
    { 
      int value = input[i]; 
      count[value-1]++;
    }
    for(int i = 0; i< count.length; i++)
    {
      //An array that accumulates the number of occurrences mapped to the array's position 
      //(e.g., pairs are at position 2, three of a kind is at position 3, and so forth)
      diceResults[count[i]]+=1; 
    }
  } 
  
  /** Given an input array storing five dice values, 
   * determines the rank of the set of values 
   * @param input  the array with the five dice values
   * @return    the rank: an integer between 0 and 6
   */
  private int getRank (int[] input) 
  { 
    int[] diceResults = new int[6];
    accumulateValues(input, diceResults); 
    int rank = 0;
    if (diceResults[2]==1 && diceResults[3]==0) {
     rank = 1; 
    }
    else if (diceResults[2]==2) {
     rank = 2; 
    }
    else if (diceResults[3]==1 &&diceResults[2]==0) {
      rank = 3;
    }
    else if (diceResults[3]==1 && diceResults[2]==1) {
      rank = 4;
    }
    else if (diceResults[4]==1) {
      rank = 5;
    }
    else if (diceResults[5]==1) {
     rank =6;
    }
    else {
    rank = 0;
    }
    rankToString(rank);
    return rank;  
  }
  
  public static String rankToString (int rank) 
  { 
    return ranks[rank]; 
  }
  
  /** Plays one round of the game: First the computer's turn, 
   * then the player's turn
   * @param name the player's name
   * @return   0 if computer wins the round, 1 if player wins, 2 if a tie
  */
  private int playOneRound(String name) {
    int compRank = 0;
    String compRankString = "";
    int humanRank = 0;
    String humanRankString = "";
    compRank = getRank(computerplayer.getValues());
    humanRank = getRank(humanplayer.getValues());
    compRankString = (computerplayer.toString() + " " + rankToString(compRank));
    humanRankString = (humanplayer.toString() + " " + rankToString(humanRank));
    System.out.println(compRankString);
    System.out.println(humanRankString);
    if (compRank > humanRank) 
      return 0;
    if (humanRank > compRank)
      return 1;
    else return 2;
  }
  
/**  Simulates the playing of numRounds of the Dice Poker game between 
 * HAL and player name, and prints the winner at the end
 * @param name   the player's name
 * @param numRounds  the number of rounds to play
*/
  public void playDiceGame (String name, int numRounds) {
    
    
    System.out.println("Good evening, " + name + ". Everything's running smoothly. And you?" + ".");
    System.out.println("I'm completely operational and all my circuits are functioning perfectly.");
    System.out.println("Would you like to play a game of Dice Poker? I play very well.");
    
    int rounds=1;
    do{ 
      System.out.println("*** ROUND " + rounds);
      int winner = playOneRound(name); 
      if(winner == 0) cwin++; 
      if(winner == 1) pwin++; 
    } while(rounds++ < numRounds);
 
    // After all rounds played, determine the final winner of the game and print the results
    if (pwin>cwin) System.out.print("The game was won by "+ name + " with a score of " + pwin + " to " + cwin);
    else if (cwin>pwin) System.out.print("The game was won by the HAL with a score of " + cwin + " to " + pwin);
    else System.out.print("The game was tied with a score of " + cwin + " to " + pwin);
    System.out.println(" in " + numRounds + " rounds.");
    System.out.println("Thank you for a very enjoyable game!");
  }

  public static void main (String args[]) {
    
 // Create an instance of a new game and play the rounds
    String name = (args.length >  0)? args[0] : "Dave";
    DiceGame game = new DiceGame(name);
    
 
 // 5 rounds by default
    int numRounds = (args.length >  1)? Integer.parseInt(args[1]) : 5; 
    game.playDiceGame(name, numRounds);
  }
}
