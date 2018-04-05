/**
 * FILENAME:  GamePanel.java
 * WHO:       Diana Eastman and Kaitlin Bohon
 * WHEN:      May 17, 2013
 * WHAT:      Sets up the main JPanel that contains the Words With Enemies game. Communicates 
 *            with the Game class, which drives the functionality of the game. 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

public class GamePanel extends JPanel {
  //Instance variables
  private JButton submitButton, passTurnButton, swapTilesButton, shuffleTilesButton, quitButton;
  private JLabel playerLabel;
  private JLabel p1ScoreLabel;
  private JLabel p2ScoreLabel;
  private JLabel lastWordScore;
  private JLabel logoLabel;
  private ImageIcon logoImg;
  private BoardPanel myBoardPanel; //The game board is an individual panel
  private HandPanel p1HandPanel, p2HandPanel; //Each player's hands are individual panels
  
  private Game myGame;
    /**
   * Constructor to create a GamePanel. Takes in the Game as a parameter.
   */
  public GamePanel(Game g)
  {
    setLayout(new BorderLayout(10, 10)); 
    setBorder(BorderFactory.createEmptyBorder(10,10,10,30)); //Set layout to BorderLayout
    
    myGame = g;
    myGame.setGamePanel(this);
    
    //Create the BoardPanel
    myBoardPanel = new BoardPanel(myGame);
    
    //Create the two HnadPanels for each Player's hand
    p1HandPanel = new HandPanel(myGame, myGame.getP1());
    p2HandPanel = new HandPanel(myGame, myGame.getP2());
    
    //Adds all panels to the BorderLayout
    add(makeNorthPanel(), "North");
    add(makeWestPanel(), "West");
    add(makeCenterPanel(), "Center");
    add(makeEastPanel(), "East");
    add(makeSouthPanel(), "South");
  }
  
  /**
   * Creates and returns the North JPanel. 
   * @return JPanel with the game's logo
   */
  private JPanel makeNorthPanel() 
  {
    JPanel northPanel = new JPanel();
    logoImg = new ImageIcon ("img/logo.gif"); //Words With Enemies Logo
    logoLabel = new JLabel("", logoImg, JLabel.CENTER);
    northPanel.add(logoLabel);
    return northPanel;
  }
  
  /**
   * Creates and returns a the East JPanel. 
   * @return JPanel with the main action buttons of the game (quit, pass turn, etc.) and labels with 
   * the current player's turn
   */
  private JPanel makeEastPanel() 
  {
    
    JPanel eastPanel = new JPanel();
    GroupLayout layout = new GroupLayout(eastPanel);
    eastPanel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    
    //Create the labels in this panel
    playerLabel = new JLabel(myGame.getCurrentPlayer().getName() + "'s turn", JLabel.CENTER);
    playerLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
    lastWordScore = new JLabel("Last word score:", JLabel.CENTER);
    lastWordScore.setFont(new Font("SansSerif", Font.PLAIN, 16));
    
    //Create the buttons in this panel
    submitButton = new JButton("Submit word");
    passTurnButton = new JButton("Pass turn");
    swapTilesButton = new JButton("Swap tiles");
    shuffleTilesButton = new JButton("Shuffle tiles");
    quitButton = new JButton("Quit Game");
    
    //Adds the listeners for all of the buttons
    submitButton.addActionListener(new ButtonListener());
    shuffleTilesButton.addActionListener(new ButtonListener());
    swapTilesButton.addActionListener(new ButtonListener());
    passTurnButton.addActionListener(new ButtonListener());
    quitButton.addActionListener(new ButtonListener());
    
    //Sets all of the components in the EastPanel within a GroupLayout so the edges line up nicely and the buttons have the same length
    layout.setHorizontalGroup(
                              layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(playerLabel)
                                            .addComponent(lastWordScore) 
                                            .addComponent(submitButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(passTurnButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(swapTilesButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(shuffleTilesButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(quitButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                         ));
    
    layout.setVerticalGroup(
                            layout.createSequentialGroup()
                              .addComponent(playerLabel)
                              .addComponent(lastWordScore) 
                              .addComponent(submitButton)
                              .addComponent(passTurnButton)
                              .addComponent(swapTilesButton)
                              .addComponent(shuffleTilesButton)
                              .addComponent(quitButton)                       
                           );
    return eastPanel;
  }
  
  /**
   * Creates and returns the South JPanel. Contains nothing, but adds space at the bottom. 
   * @return JPanel 
   */
  private JPanel makeSouthPanel() 
  {
    JPanel southPanel = new JPanel();
    southPanel.setLayout(new BorderLayout(20, 20));
    return southPanel;
  }
  
  /**
   * Creates and returns the West JPanel, which contains the two player's hands (individual JPanels)
   * @return JPanel
   */
  private JPanel makeWestPanel() 
  {
    JPanel westPanel = new JPanel();
    JLabel p1Name = new JLabel("Player 1: " + p1HandPanel.getPlayer().getName()); 
    p1ScoreLabel = new JLabel("Score: 0");
    westPanel.add(p1Name);
    westPanel.add(p1ScoreLabel);
    westPanel.add(p1HandPanel);
    JLabel p2Name = new JLabel("Player 2: " + p2HandPanel.getPlayer().getName());
    p2ScoreLabel = new JLabel("Score: 0");
    westPanel.add(p2Name);
    westPanel.add(p2ScoreLabel);
    westPanel.add(p2HandPanel);
    return westPanel;
  }
  
  /**
   * Creates and returns the Center JPanel, which contains the Board's JPanel 
   * @return JPanel
   */
  private JPanel makeCenterPanel() 
  {
    JPanel centerPanel = new JPanel();
    centerPanel.add(myBoardPanel);
    return centerPanel;
  }
  
  /**
   * Returns the Board JPanel
   * @return JPanel
   */
  public BoardPanel getBoardPanel(){
    return myBoardPanel;
  }
  
   /**
   * Returns Player 1's Hand JPanel
   * @return JPanel
   */
  public HandPanel getP1HandPanel(){
    return p1HandPanel;
  }
  
  /**
   * Returns Player 2's Hand JPanel
   * @return JPanel
   */
  public HandPanel getP2HandPanel(){
    return p2HandPanel;
  }
  
  /**
   * ButtonListener is a private class for responding to button push events 
   */ 
  private class ButtonListener implements ActionListener 
  {
    public void actionPerformed (ActionEvent event) 
    {      
      if (event.getSource()== submitButton){
        myGame.playTurn(); // Calls play turn in Game, which validates the word placement
        
      }
      
      if (event.getSource()==shuffleTilesButton){
        myGame.getCurrentPlayer().getHand().shuffleTiles(); // Changes the location of the Tiles in the Hand in the UI
        myGame.refreshAll();
      }
      
      if (event.getSource()== passTurnButton){
        myGame.passTurn(); // Changes the player's turn in the Game
      }
      
      if (event.getSource()== quitButton){
        System.exit(0); // Quits the game
      }
      
      if (event.getSource() == swapTilesButton){
        myGame.swapTiles(); // Allows the calling player to swap the clicked Tile with the clicked Tile in the other player's hand
      }
      
      playerLabel.setText(myGame.getCurrentPlayer().getName() + "'s turn"); //Update current player text
      p1ScoreLabel.setText("Score: " + myGame.getP1().getScore()); //Update player score
      p2ScoreLabel.setText("Score: " + myGame.getP2().getScore()); //Update player score
      lastWordScore.setText("Last word score: " + myGame.getBoard().getWordScore());
      if (myGame.isOver())
        endGame();
      
    }
  } //end actionPerformed
  
  
  private void endGame() 
  {
    playerLabel.setText("Game Over!\n Winner is: " + myGame.getWinner()); 
  }
}  
