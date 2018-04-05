/**
 * FILENAME:  Board.java
 * WHO:       
 * WHEN:     
 * WHAT:      
 */

import java.util.*;

public class Board
{
 private Space myGrid[][];     // 2D array representation of the game board
 private LinkedList<Space> activeSpaces; // Spaces on the board that have been occupied during this turn
 private final int GRID_SIZE = 15;
 private int[][] premiumSpaces = {
   { 3, 1, 1, 4, 1, 1, 1, 3, 1, 1, 1, 4, 1, 1, 3 },
   { 1, 2, 1, 1, 1, 5, 1, 1, 1, 5, 1, 1, 1, 2, 1 },
   { 1, 1, 2, 1, 1, 1, 4, 1, 4, 1, 1, 1, 2, 1, 1 },
   { 4, 1, 1, 2, 1, 1, 1, 4, 1, 1, 1, 2, 1, 1, 4 },
   { 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1 },
   { 1, 5, 1, 1, 1, 5, 1, 1, 1, 5, 1, 1, 1, 5, 1 },
   { 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1 },
   { 3, 1, 1, 4, 1, 1, 1, 0, 1, 1, 1, 4, 1, 1, 3 },
   { 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1 },
   { 1, 5, 1, 1, 1, 5, 1, 1, 1, 5, 1, 1, 1, 5, 1 },
   { 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1 },
   { 4, 1, 1, 2, 1, 1, 1, 4, 1, 1, 1, 2, 1, 1, 4 },
   { 1, 1, 2, 1, 1, 1, 4, 1, 4, 1, 1, 1, 2, 1, 1 },
   { 1, 2, 1, 1, 1, 5, 1, 1, 1, 5, 1, 1, 1, 2, 1 },
   { 3, 1, 1, 4, 1, 1, 1, 3, 1, 1, 1, 4, 1, 1, 3 }};
 
 /** 
  * Class constructor.
  */
 public Board()
 {
   activeSpaces = new LinkedList<Space>();
   myGrid = new Space[GRID_SIZE][GRID_SIZE];
   
   for (int x = 0; x < GRID_SIZE; x++) 
   {
     for (int y = 0; y < GRID_SIZE; y++) 
     {
       myGrid[x][y] = new Space(x, y, premiumSpaces[x][y]);
     }
   }
 }
 
 /**
  * Returns the Space at a particular position (x, y coordinates) on the Board.
  * @param x the x-coordinate (row) of the Space's location
  * @param y the y-coordinate (column) of the Space's location
  * @return Space
  */
 public Space getSpace(int x, int y)
 {
   Space thisSpace = myGrid[x][y];
   return thisSpace;
 }
 
 /**
  * Removes the Tile at a particular position (x, y coordinates) on the Board.
  * @param x the x-coordinate (row) of the Tile's location
  * @param y the y-coordinate (column) of the Tile's location
  * @return Tile
  */
 public void removeTile(int x, int y)
 {
   Space thisSpace = getSpace(x,y);
   if(!thisSpace.isFree()) 
   {
     //Remove tile - where should the tile go? Back to the player's rack?
   }
 }
 
 /**
  * Adds a Tile object to a specific Space on the Board, if the Space is free.
  * If the Tile is placed successfully, the Space is added to a Linked List of Spaces
  * that are currently occupied with Tiles during this turn.
  * @param Tile the Tile to be added to the board
  * @param x the x-coordinate (row) of the Tile's destination on the Board
  * @param y the y-coordinate (column) of the Tile's destination on the Board
  */
 public void addTile(Tile newTile, int x, int y)
 {
   Space thisSpace = getSpace(x,y);
   if(thisSpace.isFree()) 
   {
     thisSpace.addTile(newTile);
     activeSpaces.add(thisSpace);
   }
  // else do something?? error?
 }
 
 // Determine if the current tile placement is valid
 public boolean validateTurn()
 {
  // determine if current placement is a valid word
  // make sure placement is valid and connected to existing word
  // special case for first tile (must be a word of at least two letters and placed on x,y[7,7])
  // make sure any incidental words are also valid
  // multiple methods
  return true;
 }
 
 // Compute the score for the current tile placement
 public int computeScore()
 {
  return 0;
 }
 
 /**
  * Prints out a representation of the Board object.
  * @return String
  **/
 final public String toString(){
   String result ="";
   for (int x = 0; x < GRID_SIZE; x++) 
   {
     result += "\n";
     for (int y = 0; y < GRID_SIZE; y++) 
     {
       result += myGrid[x][y].toString();
     }
   }
   return result;
 }
 
 // Complete turn (reset active spaces in the linked list)
 public void completeTurn()
 {
  
 }
 
 // Testing method
 public static void main (String args[])
 {
   Board test = new Board();
   test.addTile(new Tile('D'), 3, 4);
   test.addTile(new Tile('F'), 7, 7);
   test.addTile(new Tile('X'), 2, 2);
   test.addTile(new Tile('D'), 3, 4);
   System.out.println(test);
 }
}
