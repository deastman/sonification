/**
 * FILENAME: THPanel.java
 * WHO:       
 * WHEN:     
 * WHAT:      
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class THPanel extends JPanel
{
  protected int rows, cols;
  protected SpacePanel[][] spaceArray;
  protected TileHolder myTH;
  protected JPanel myTHPanel;
  protected Game myGame;
  
  
  //Constructor. Pass in the game it's referring to, the TH, and the dims
  public THPanel(Game g, TileHolder t, int r, int c) { //creates and sets up THPanel
    myGame = g;
    myTH = t;
    rows = r;
    cols = c;
    spaceArray = new SpacePanel[rows][cols];
  }
  
   //Out of game constructor. Pass in the TH, and the dims
  public THPanel(TileHolder t, int r, int c) { //creates and sets up THPanel
    myTH = t;
    rows = r;
    cols = c;
    spaceArray = new SpacePanel[rows][cols];
  }
  
   /**
  * Gets the current selected space in the THPanel (there should only be 1)
  */
  public SpacePanel getSelected(){
    {
      SpacePanel noSelected = null;
      for (int x = 0; x < rows; x++) 
      {
        for (int y = 0; y < cols; y++) 
        {
          if (spaceArray[x][y].getSelected()) //loop through all spaces in THPanel, check if they're selected
            return spaceArray[x][y];
        }
      }
      return noSelected;
    }
  }
  
  /**
  * Getter for TH
  * @return TH associated with Panel
  */
  public TileHolder getTH()
  {
    return myTH;
  }
  
  
   /**
  * Refresh the display for every space in the THPanel
  */
  public void refreshAll(){
    for (int x = 0; x < rows; x++){
      for (int y = 0; y < cols; y++) {
      spaceArray[x][y].refreshDisplay(); //by looping through
    }
  }
    //System.out.println("Refreshed");
  }
  
  /**
  * Checks if there are plays. There won't be unless it's in a board panel, but refresh all nonetheless. 
  */
 public void checkPlays()
 {
    myGame.refreshAll();
 } 
  
 
  /**
  * Make a JPanel representation of a TileHolder with the specifications
  * @return JPanel THPanel created
  */
  protected JPanel makeTHPanel(){
    myTHPanel = new JPanel();
    myTHPanel.setPreferredSize(new Dimension(cols*40, rows*40)); //40 is size of one square
    myTHPanel.setLayout(new GridLayout(rows, cols)); 
    for (int x = 0; x < rows; x++) //adds an individual SpacePanel for each square
      for (int y = 0; y < cols; y++) {
      SpacePanel current = new SpacePanel(this, x, y);
      spaceArray[x][y] = current;
      myTHPanel.add(current);
    }
    return myTHPanel;
  }
}