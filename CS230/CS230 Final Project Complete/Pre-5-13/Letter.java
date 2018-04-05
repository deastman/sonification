/**
 *
 * FILENAME:  Letter.java
 * WHO:       Kaitlin Bohon
 * WHEN:      May 3, 2013
 * WHAT:      Creates class Letter to store basic information about letter tiles
 */

import javax.swing.*;


public class Letter {
  
  // Instance variables
  private char letter; //name of school
  private int points; //various ratings of the school
  private ImageIcon im;
  
  /**
   *  Constructor to create a School object
   * */
  public Letter(char l, int p, String fp) { 
    this.letter = l;
    this.points = p;
    im = new ImageIcon(fp);
    im.setDescription(fp);
  }
  
  /**
   * toString method for class letter
   * @return String
   * */
  public String toString() { 
    return " Letter: "+this.letter+ "\n"+
      " Points: " +this.points+ "\n"+
      " Image: " + im.getDescription();
  }
  
  
  /**
   * Getter for letter
   * @return Char letter
   * */
  public char getLetter() { 
    return letter;
  }
  
  /**
   * Getter for points
   * @return int point value of letter
   * */
  public int getPoints() { 
    return points;
  }
  
  /**
   * Getter for research score
   * @return int research score
   * */
  public ImageIcon getIm() { 
    return im;
  }
  
  
  public static void main (String[] args) { 
    
    Letter l = new Letter('L', 2, "LetterImages/L.jpg");
    
    System.out.println("toString of L: " + l) ;
    System.out.println("Testing getPoints of L (2)" + l.getPoints());
    System.out.println("Testing getLetter of L (l)" + l.getLetter());
  }
  
} 
