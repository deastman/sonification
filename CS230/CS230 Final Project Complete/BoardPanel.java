/**
 * FILENAME:  BoardPanel.java
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

public class BoardPanel extends THPanel{
  private final static int GRID_SIZE = 15;
  private Board myBoard;
  private JPanel myBoardPanel;
  
  public BoardPanel(Game g) { //creates and sets up game board
    super(g, g.getBoard(), GRID_SIZE, GRID_SIZE);
    myBoard = g.getBoard();
    add(makeBoardPanel());
    
  }
  
  private JPanel makeBoardPanel(){
    myBoardPanel = super.makeTHPanel();
    return myBoardPanel;
  }
  
  //plays a letter, and then refreshes. Renaming would be a good idea. 
  public void checkPlays(){
    myGame.playLetter();
    super.checkPlays();
  }
}

