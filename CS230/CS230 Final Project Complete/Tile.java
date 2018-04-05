/**
 *
 * FILENAME:  Tile.java
 * WHO:       Diana Eastman
 * WHEN:      May 3, 2013
 * WHAT:      Creates class Tile to store basic information about letter tiles, including the point value of
 *            each letter, the image path to the letter's icon, and the amount of each letter in the "bag" of tiles.
 */

import javax.swing.*;

public class Tile {
  
  // Instance variables
  private char letter; 
  private int points; 
  private ImageIcon im;
  private boolean locked;
  public static final char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 
                                             'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
  private static final int[] letterPoints = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
                                           /* A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q,  R, S, T, U, V, W, X, Y, Z */
  private static final int[] amountInBag  = { 9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1 };
  
  /**
   * Constructor to create a Tile object.
   */
  public Tile(char l, int p, String fp) 
  { 
    this.letter = l;
    this.points = p;
    locked = false;
    im = new ImageIcon(fp);
    im.setDescription(fp);
  }
  
  /**
   * Second Tile Object Constructor; takes in a character as a parameter and assigns it to letter and gets the associated point value
   */
  public Tile(char c)
  {
    letter = c;
    points = letterPoints[getIndexOfTile(c)];
  }
  
  /**
   * Returns the letter of a Tile.
   * @return Char letter
   */
  public char getLetter() 
  {
      return letter;
  }
  
  /**
   * Returns the point value associated with a Tile.
   * @return int point value of letter
   */
  public int getPoints() 
  { 
    return points;
  }
  
  /**
   * Returns the number of Tiles of a particular letter that are contained within the Bag.
   * @return int number of Tiles
   */
  public static int getAmountOfTilesInBag(char l)
  {
    l = Character.toUpperCase(l); 
    return amountInBag[getIndexOfTile(l)];
  }
  
  /**
   * Returns the ImageIcon of a Tile.
   * @return ImageIcon Tile image
   */
  public ImageIcon getIm() 
  { 
    return im;
  }
  
   /**
   * Returns the index of a particular char in the array of letters in the alphabet
   * @return int index or -1 if not found
   */
  private static int getIndexOfTile(char l) 
  {
    char getletter = Character.toUpperCase(l);
    for(int i=0; i<letters.length; i++)
    {
      if(letters[i]==getletter)
        return i;
    }
    return -1; //Default not found
  }
  
  /**
   * Creates and returns the file path to the image thumbnail of a particular letter
   * @return String file path to image of letter
   */
  public String createImgPath() 
  {
    String p = "thumbnails/" + Character.toString(letter) + ".png";
    return p;
  }
  
  public boolean isLocked(){
   return locked; 
  }
  
  public void lock(){
   locked = true; 
  }
  
  public void unlock(){
   locked = false; 
  }
  
  /**
   * toString method for class letter
   * @return String
   */
  public String toString() 
  { 
    return " Tile: "+ letter + " (Point value: " + points + ")\n";
  }
  
  //Testing method
  public static void main (String[] args) { 
    Tile l = new Tile('L', 2, "TileImages/L.jpg");
    Tile z = new Tile('Z');
    System.out.println("String representation of L tile: " + l) ;
    System.out.println("Testing getPoints of L [2]: " + l.getPoints());
    System.out.println("Testing getTile of L [l]: " + l.getLetter());
    System.out.println("Testing get number of Z tiles in Bag [1]: " + getAmountOfTilesInBag('Z'));
    System.out.println("Testing get number of E tiles in Bag [12]: " + getAmountOfTilesInBag('E'));
    l.lock();
    System.out.println("Testing lock tiles [true]:" + l.isLocked());
    l.unlock();
    System.out.println("Testing unlock tiles [true]:" + l.isLocked());
  }
  
} 
