/**
 * FILE NAME: TileHolder.java
 * WHO: 
 * WHEN:
 * WHAT:
 *   
 */

import java.util.*;

public abstract class TileHolder
{
 protected Tile myTiles[][];  // array to hold tiles once in place
 protected Space mySpaces[][]; // array to hold spaces
 protected int rows, cols;
  
 /** 
  * Class constructor.
  */
 public TileHolder(int r, int c, int premiumSpaces[][])
 {
   rows = r;
   cols = c;
   mySpaces = new Space[rows][cols];
   myTiles = new Tile[rows][cols];
   
   for (int x = 0; x < rows; x++) 
   {
     for (int y = 0; y < cols; y++) 
     {
       mySpaces[x][y] = new Space(x, y, premiumSpaces[x][y]);
     }
   }
 }
 
  /** 
  * Class constructor w/out premiumSpaces
  */
 public TileHolder(int r, int c)
 {
   rows = r;
   cols = c;
   mySpaces = new Space[rows][cols];
   myTiles = new Tile[rows][cols];
   
   for (int x = 0; x < rows; x++) 
   {
     for (int y = 0; y < cols; y++) 
     {
       mySpaces[x][y] = new Space(x, y, 1); //all spaces are type 1
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
   Space thisSpace = mySpaces[x][y];
   return thisSpace;
 }
 
 /**
  * Removes the Tile at a particular position (x, y coordinates) on the Board.
  * @param x the x-coordinate (row) of the Tile's location
  * @param y the y-coordinate (column) of the Tile's location
  * 
  */
 public void removeTile(int x, int y)
 {
   Space thisSpace = getSpace(x,y);
   if(!thisSpace.isFree()) 
   {
     myTiles[x][y] = null; //sets tile to null
     thisSpace.removeTile(); //removes it from the space
   }
 }
 
  /**
  * Returns the Tile at a particular index in the TH.
  * @param index the index of the Tile to be returned
  * @return Tile
  */
 public Tile getTile(int r, int c)
 {
  return myTiles[r][c];
 }
 
  // get the number of tile in the TH
 public int getNumTiles()
 {
   int result = 0;
      for (int x = 0; x < rows; x++) 
   {
     for (int y = 0; y < cols; y++) 
     {
       if (!(myTiles[x][y] == null))
       result ++;
     }
   }
  return result;
 }
 
 /**
  * Adds a Tile object to a specific Space in the TH, if the Space is free.
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
     myTiles[x][y] = newTile;
     //System.out.println(newTile + " added");
   }
   //else, don't add the tile
 }
 
 //find current space (there should only be 1) in the TileHolder
  public Space getCurrentSpace()
 {
   Space cs = null;
   for (int x = 0; x < rows; x++) 
   {
     for (int y = 0; y < cols; y++) 
     {
       if (mySpaces[x][y].isCurrent())
         return mySpaces[x][y];
     }
   }
   return cs;
 }
 
 /**
  * Prints out a representation of the TH object.
  * @return String
  **/
 final public String toString(){
   String result ="";
   for (int x = 0; x < rows; x++) 
   {
     result += "\n";
     for (int y = 0; y < cols; y++) 
     {
       result += mySpaces[x][y].toString();
     }
   }
   return result;
 }
 
}
