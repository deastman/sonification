/**
 * FILENAME:  Space.java
 * WHO:       
 * WHEN:     
 * WHAT:      
 */

public class Space 
{
 private int xPos;
 private int yPos;
 private boolean isFree;
 private int space; // integer representation of space type 
 private String spaceType;  // triple word, double letter, etc
 private boolean isPremium;
 private Tile myTile;
 
 // constructor
 public Space (int xPos, int yPos, int space)
 {
  this.yPos = yPos;
  this.xPos = xPos;
  this.space = space;
  isFree = true;
  spaceType = setSpaceType(space);
 }
 
 // Get row
 public int getxPos()
 {
  return xPos;
 }
 
 // Get col
 public int getyPos()
 {
  return yPos;
 }
 
 // is the space empty?
 public boolean isFree()
 {
  return isFree;
 }
 
 // return special status of tile, else null
 public String getSpecial()
 {
  return spaceType;
 }
 
 // Get the tile currently occupied in the space or null
 public Tile getTile()
 {
   if (!isFree())
     return myTile;
   else 
     return null;
 }
 
 // Return point value of tile (not including special value of space)
 public int getValue()
 {
  return myTile.getPoints();
 }
 
 // Add a tile to the space - else error??
 public void addTile(Tile newTile)
 {
   if (isFree)
   {
     myTile = newTile;
     isFree = false;
   }
 }
 
 // Set type of space - triple word, double word, etc.
 public String setSpaceType(int space)
 {
   String spaceType = "default";
   switch (space) {
     case 0: spaceType = "{}"; // case 0 = double word
                     break;
     case 1: spaceType = "[]";
                     break;
     case 2: spaceType = "DW";
                     break;
     case 3: spaceType = "TW";
                     break;
     case 4: spaceType = "DL";
                     break;
     case 5: spaceType = "TL";
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
   if (isFree)
     result += space + " ";
   else
     result += myTile.getLetter() + " ";
   return result;
 }
 
 
}
