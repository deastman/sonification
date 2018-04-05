import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

public class GamePanel extends JPanel {
  //instance vars
  private JButton submitButton, passTurnButton, swapLetterButton, shuffleTilesButton;
  private JLabel playerLabel;
  private JLabel lastWordLabel;
  private JLabel lastWord;
  private JLabel lastWordScore;
  private GameGrid gameGrid;
  
  public GamePanel() {
    GameGrid gameGrid = new GameGrid();
    setLayout(new BorderLayout(10, 10)); // hgap, vgap
    setBorder(BorderFactory.createEmptyBorder(10,10,10,30));
    add(makeNorthPanel(), "North");
    add(makeWestPanel(), "West");
    add(makeCenterPanel(gameGrid), "Center");
    add(makeEastPanel(), "East");
    add(makeSouthPanel(), "South");
  }
  
  /**
   * Creates and returns a JPanel. 
   * @return JPanel The Panel that contains the top, status label
   */
  private JPanel makeNorthPanel() {
    JPanel northPanel = new JPanel();
    return northPanel;
  }
  
  /**
   * Creates and returns a JPanel. 
   * @return JPanel The Panel that contains the Quit button
   */
  private JPanel makeEastPanel() {
    JPanel eastPanel = new JPanel();
    GroupLayout layout = new GroupLayout(eastPanel);
    eastPanel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    
    playerLabel = new JLabel("Wendy Wellesley's turn", JLabel.CENTER);
    playerLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
    lastWordLabel = new JLabel("Last word played: net", JLabel.CENTER); //Hard-code this example
    lastWordLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
    lastWord = new JLabel("NET:", JLabel.CENTER);
    lastWord.setFont(new Font("SansSerif", Font.PLAIN, 16));
    lastWordScore = new JLabel("Last word score: 8", JLabel.CENTER); //Hard-code this example
    lastWordScore.setFont(new Font("SansSerif", Font.PLAIN, 14));
    
    submitButton = new JButton("Submit word");
    passTurnButton = new JButton("Pass turn");
    swapLetterButton = new JButton("Swap letter");
    shuffleTilesButton = new JButton("Shuffle tiles");

    //Connect the ButtonListener with the quit button
    //quitButton.addActionListener(new ButtonListener());
    
    //Connect the again button with the ButtonListener
    //againButton.addActionListener(new ButtonListener());
 
    layout.setHorizontalGroup(
    layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
            .addComponent(playerLabel)
            .addComponent(lastWordLabel) 
            .addComponent(lastWordScore) 
            .addComponent(submitButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(passTurnButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(swapLetterButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(shuffleTilesButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    ));
    
    layout.setVerticalGroup(
    layout.createSequentialGroup()
       .addComponent(playerLabel)
       .addComponent(lastWordLabel)
       .addComponent(lastWordScore) 
       .addComponent(submitButton)
       .addComponent(passTurnButton)
       .addComponent(swapLetterButton)
       .addComponent(shuffleTilesButton)
    );
    return eastPanel;
  }
  
  /**
   * Creates and returns a JPanel. 
   * @return JPanel The Panel contains no elements
   */
  private JPanel makeSouthPanel() {
    // create westPanel using BorderLayout manager
    //although it does not contain anything, it adds a bit of breathing space
    JPanel southPanel = new JPanel();
    southPanel.setLayout(new BorderLayout(20, 20));
    return southPanel;
  }
  
  /**
   * Creates and returns a JPanel. 
   * @return JPanel The Panel contains no elements
   */
  private JPanel makeWestPanel() {
    // Create westPanel using BorderLayout manager
    //although it does not contain anything, it adds a bit of breathing space
    JPanel westPanel = new JPanel();
    westPanel.setLayout(new BorderLayout(20, 20));
    return westPanel;
  }
  
  /**
   * Creates and returns a JPanel. 
   * @return JPanel The Panel contains all the 9 buttons
   */
  private JPanel makeCenterPanel(GameGrid g) {
    JPanel centerPanel = new JPanel();
    centerPanel.add(g);
    return centerPanel;
  }
  
  /**
   * ButtonListener is a private class for responding to button push events 
   */ 
  private class ButtonListener implements ActionListener {
    
    /** quits the game if quit button is pushed, changes the text of the buttons of the game to X or O and updates the
      * status label and icon images of the panel when the game is over
      *@param event is the event which causes an action to be performed
      **/
    public void actionPerformed (ActionEvent event) {
      //quit button was pressed
      /*if (event.getSource() == quitButton) System.exit(0); 
      
      if (event.getSource() == againButton)  {
        resetGUIandGame();
      }*/
    } //end actionPerformed
    
    
    //helper method, to be called when the user asks for one more game.
    // It initializes all the gui components as needed, and creates a new instance 
    //of the game to be used in this new round.
    private void resetGUIandGame() {
      //reset buttons
      //reset status label
      //statusLabel.setText("Player X goes first");
      //statusLabel.setIcon(xImg);
      
      //get a new game to start again
      //game = new TicTacToe(); 
    }
  } // end of buttonListener inner class
}  //end TicTacToePanel public class
