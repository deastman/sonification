import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameGrid extends JPanel {
  private final int GRID_SIZE = 15;
  private Cell[][] cellArray = new Cell[GRID_SIZE][GRID_SIZE];
  
  private Cell currentCell;
  private JPanel gameGrid;
  private int[][] premiumColorsLayout = {
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

  public GameGrid() {
    gameGrid = new JPanel();
    gameGrid.setPreferredSize(new Dimension(600, 600));
    gameGrid.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

    for (int x = 0; x < GRID_SIZE; x++)
      for (int y = 0; y < GRID_SIZE; y++) {
      currentCell = new Cell(x, y, premiumColorsLayout[x][y]);
      cellArray[x][y] = currentCell;
      gameGrid.add(currentCell);
    }
     add(gameGrid);
  }
  
  public Cell[][] getCellArray() {
    return cellArray;
  }
}

