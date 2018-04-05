/**
 * FILENAME:  GamePanel.java
 * WHO:       
 * WHEN:     
 * WHAT:      
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

public class GamePanel extends JPanel {

  private JButton submitButton, passTurnButton, swapLetterButton, shuffleTilesButton;
  private JLabel playerLabel;
  private JLabel lastWordLabel;
  private JLabel lastWord;
  private JLabel lastWordScore;
  private JLabel logoLabel;
  private ImageIcon logoImg;

  private Game myGame;
  
  public GamePanel(Game g) 
  {
    setLayout(new BorderLayout(10, 10)); 
    setBorder(BorderFactory.createEmptyBorder(10,10,10,30));
    
    myGame = g;
    BoardPanel myBoardPanel = new BoardPanel(myGame.getBoard());
    
    add(makeNorthPanel(), "North");
    add(makeWestPanel(), "West");
    add(makeCenterPanel(myBoardPanel), "Center");
    add(makeEastPanel(), "East");
    add(makeSouthPanel(), "South");
  }
  
  /**
   * Creates and returns a JPanel. 
   * @return JPanel 
   */
  private JPanel makeNorthPanel() 
  {
    JPanel northPanel = new JPanel();
    logoImg = new ImageIcon ("img/logo.gif");
    logoLabel = new JLabel("", logoImg, JLabel.CENTER);
    northPanel.add(logoLabel);
    return northPanel;
  }
  
  /**
   * Creates and returns a JPanel. 
   * @return JPanel 
   */
  private JPanel makeEastPanel() 
  {
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
   * @return JPanel 
   */
  private JPanel makeSouthPanel() 
  {
    JPanel southPanel = new JPanel();
    southPanel.setLayout(new BorderLayout(20, 20));
    return southPanel;
  }
  
  /**
   * Creates and returns a JPanel. 
   * @return 
   */
  private JPanel makeWestPanel() 
  {
    //Player hands to go here - both on the same panel, or each on their own panel?
    JPanel westPanel = new JPanel();
    westPanel.setLayout(new BorderLayout(20, 20));
    return westPanel;
  }
  
  /**
   * Creates and returns a JPanel. 
   * @return JPanel
   */
  private JPanel makeCenterPanel(BoardPanel myBoardPanel) 
  {
    JPanel centerPanel = new JPanel();
    centerPanel.add(myBoardPanel);
    return centerPanel;
  }
  
  /**
   * ButtonListener is a private class for responding to button push events 
   */ 
  private class ButtonListener implements ActionListener 
  {
    public void actionPerformed (ActionEvent event) 
    {
      //quit button was pressed
      /*if (event.getSource() == quitButton) System.exit(0); 
      
      if (event.getSource() == againButton)  {
        resetGUIandGame();
      }*/
    } //end actionPerformed
    
    
    private void resetGUIandGame() 
    {
      
    }
  } 
}  
