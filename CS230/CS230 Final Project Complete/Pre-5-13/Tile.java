/**
 *
 * FILENAME:  Tile.java
 * WHO:       Kaitlin Bohon
 * WHEN:      May 3, 2013
 * WHAT:      Creates class Tile to store basic information about letter tiles
 */

import javax.swing.*;

public class Tile {
  
  // Instance variables
  private char letter; 
  private int points; 
  private ImageIcon im;
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
    im = new ImageIcon(fp);
    im.setDescription(fp);
  }
  
  /**
   * Second Tile Object Constructor; takes in a character as a parameter and assigns it to letter
   */
  public Tile(char c)
  {
    letter = c;
    points = letterPoints[getIndexOfTile(c)];
  }
  
   public Tile()
  {

  }
  
  /**
   * toString method for class letter
   * @return String
   */
  public String toString() 
  { 
    return " Tile: "+ letter + " (Point value: " + points + ")\n";
     /* " Image: " + im.getDescription();*/
  }
  
  /**
   * Getter for letter
   * @return Char letter
   */
  public char getLetter() 
  { 
    return letter;
  }
  
  /**
   * Getter for points
   * @return int point value of letter
   */
  public int getPoints() 
  { 
    return points;
  }
  
  public static int getAmountOfTilesInBag(char l)
  {
    l = Character.toUpperCase(l); 
    return amountInBag[getIndexOfTile(l)];
  }
  
  /**
   * Getter for research score
   * @return int research score
   */
  public ImageIcon getIm() 
  { 
    return im;
  }
  
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
  
  private String createImgPath(Character l) 
  {
    String p = "\"" + "img"  + "/" + Character.toString(l) + ".png" + "\"";
    return p;
  }
  
  public static void main (String[] args) { 
    Tile l = new Tile('L', 2, "TileImages/L.jpg");
    Tile z = new Tile('Z');
    //System.out.print(l.getAmountOfTilesInBag('l'));
    //System.out.print(z.getAmountOfTilesInBag('z'));
    System.out.println("toString of L: " + l) ;
    System.out.println("Testing getPoints of L (2)" + l.getPoints());
    System.out.println("Testing getTile of L (l)" + l.getLetter());
  }
  
} 
