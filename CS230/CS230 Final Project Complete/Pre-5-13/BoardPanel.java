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

public class BoardPanel extends JPanel {
  private final int GRID_SIZE = 15;
  private SpacePanel[][] spaceArray = new SpacePanel[GRID_SIZE][GRID_SIZE];
  private Board myBoard;  
  private JPanel myBoardPanel;

  public BoardPanel(Board b) {
    myBoard = b;
    myBoardPanel = new JPanel();
    myBoardPanel.setPreferredSize(new Dimension(600, 600));
    myBoardPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

    for (int x = 0; x < GRID_SIZE; x++)
      for (int y = 0; y < GRID_SIZE; y++) {
      SpacePanel current = new SpacePanel(myBoard.getSpace(x,y));
      spaceArray[x][y] = current;
      myBoardPanel.add(current);
    }
     add(myBoardPanel);
  }
}