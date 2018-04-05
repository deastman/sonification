/** CODE TEMPLATE
* Creates the "hand" of a player by creating sn object of 5 random dice
* @author   Takis Metaxas 
* @version     %I%, %G%
*/

/* 
 DicePlayer.java
 CS230 Homework Assignment P4
 Modified by: Diana Eastman
 Modified date: February 11, 2013
*/

import java.util.*;

public class DicePlayer {

final private int hand = 5;
private Die[] FiveDice = new Die[hand];
String playerName;

    /** Constructor: Creates a player's hand by creating and rolling dice. 
     */   
 public DicePlayer(String name) 
 {
   for (int i = 0; i < hand; i++)
   {
     FiveDice[i] = new Die();
   }
   
   playerName = name;
 }
    /** Prints the contents of the hand that a player holds. 
    */
 public String toString()  {
   String s = "";
   int [] valueArray = new int[hand];
   for (int i = 0; i < hand; i++)
   {
     valueArray[i] = FiveDice[i].getFaceValue();
   }
   s = playerName + " rolled: " + Arrays.toString(valueArray);
   return  s;
 }

    /** Returns an array the contents of the hand that a player holds. 
     * @return integer array of @param hand values corresponding to dice
    */
 public int[] getValues() {
   int [] values = new int[hand];
   int i = 0;
   for (i = 0; i< values.length; i++) 
   {
     values[i]=FiveDice[i].roll();
   }
   
   return values;
 }

  /** Testing method. 
  */
  public static void main (String args[]) {
    DicePlayer hal = new DicePlayer("Hal");
    hal.getValues();
    System.out.println(hal);
    DicePlayer dave = new DicePlayer("Dave");
    dave.getValues();
    System.out.println(dave);
  }
}
