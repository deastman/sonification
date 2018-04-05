/**
 * FILENAME:  Space.java
 * WHO:       Diana Eastman    
 * WHEN:      May 17, 2013
 * WHAT:      Represents an individual space on the game board.
 */

import java.awt.*;
import javax.swing.*;

public class Space 
{
 private int xPos; // row
 private int yPos; // column
 private boolean isFree; // no tiles on the space
 private int space; // integer representation of space type 
 private String spaceType;  // triple word, double letter, etc
 private Color spaceColor; 
 private boolean isPremium; // has triple word, double letter, etc.
 private Tile myTile; // tile on the space
 private boolean isCurrent;
 private SpacePanel myPanel;
 
  /**
   * Constructor to create a Space object.
   */
 public Space (int xPos, int yPos, int space)
 {
  this.yPos = yPos;
  this.xPos = xPos;
  this.space = space;
  isFree = true;
  myPanel = null;
  isCurrent = false;
  spaceType = setSpaceType(space);
 }
 
 /**
  * Returns the row of the Space's location on the board
  * @return int row of the Space's location
  */
 public int getxPos()
 {
  return xPos;
 }
 
 /**
  * Returns the column of the Space's location on the board
  * @return int column of the Space's location
  */
 public int getyPos()
 {
  return yPos;
 }
 
 /**
  * Returns true if the Space does not have a Tile on it
  * @return <code>true</code> if no Tile on the Space and false otherwise
  */
 public boolean isFree()
 {
  return isFree;
 }
 
 /**
  * Returns true if the Space is currently selected by the Player (in the UI)
  * @return <code>true</code> if selected by Player and false otherwise
  */
 public boolean isCurrent()
 {
  return isCurrent;
 }
 
 /**
  * Sets the Space Panel associated with this Space
  */
 public void setSpacePanel(SpacePanel sp)
 {
   myPanel = sp;
 }
 
 /**
  * Returns the Space Panel associated with this Space
  * @return SpacePanel
  */
 public SpacePanel getSpacePanel()
 {
   return myPanel;
 }
 
 /**
  * Sets the Space to boolean true if current
  * @param boolean
  */
 public void setCurrent(boolean b)
 {
  isCurrent = b;
 }
 
  /**
  * Returns the premium status of this Space (triple word, double word, default, etc.)
  * @return String the premium status of the Space
  */
 public String getSpecial()
 {
  return spaceType;
 }
 
  /**
  * Returns the Tile currently on this Space
  * @return Tile on the Space or null if no Tile on Space 
  */
 public Tile getTile()
 {
   if (!isFree())
     return myTile;
   else 
     return null;
 }
 
 /**
  * Returns the point value of the Tile on the Space
  * @return int point value of Tile
  */
 public int getValue()
 {
  return myTile.getPoints();
 }
 
  /**
  * Returns the Color associated with this Space (depends on premium status)
  * @return Color of space
  */
 public Color getColor()
 {
   return spaceColor;
 }
 
  /**
  * Adds a Tile to the Space if the Space is free (has no Tile currently occupying it)
  * @param Tile new Tile to add to the Space 
  */
 public void addTile(Tile newTile)
 {
   if (isFree)
   {
     myTile = newTile;
     isFree = false;
   }
 }
 
 /**
  * Removes the Tile from the Space if the Space has a Tile
  */
 public void removeTile()
 {
   if (!isFree)
   {
     myTile = null;
     isFree = true;
   }
 }
 
 /**
  * Sets the Space type of the word and color; each integer corresponds to a specific space type
  * @param int 
  */
 public String setSpaceType(int space)
 {
   String spaceType = "default";
   switch (space) {
     case 0: 
       spaceType = "{}"; // case 0 = double word
       spaceColor = new Color (220, 220, 0);
                     break;
     case 1: 
       spaceType = "[]";
       spaceColor = Color.lightGray; //to be changed when we have icons for premium spaces
                     break;
     case 2: 
       spaceType = "DW";
       spaceColor = new Color (255, 192, 203);
                     break;
     case 3: 
       spaceType = "TW";
       spaceColor = new Color (238, 151, 129);
                     break;
     case 4: 
       spaceType = "DL";
       spaceColor = new Color (0, 255, 100);
                     break;
     case 5: 
       spaceType = "TL";
       spaceColor = new Color (36, 189, 67);
                     break;
   }
   return spaceType;
 }
 
 /**
  * Prints out a String representation of a Space object. If the Space is unoccupied,
  * the Space type is printed (e.g., triple word tile, default). Prints the letter of 
  * a Tile object if the Space is occupied.
  * @return String
  **/
 final public String toString(){
   String result ="";
   if (myTile == null)
     result += space + " ";
   else
     result += myTile.getLetter() + " ";
   return result;
 }
 
 
}
