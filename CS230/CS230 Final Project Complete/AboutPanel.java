
/**  
 * FILE NAME: AboutPanel.java 
 * WHEN: 5/17/2013, 2013 
 * WHAT: Sets up the Panel that gives info about the GUI. 
 * WHO: Kaitlin Bohon
 */ 

import java.awt.*; 
import javax.swing.*; 

public class AboutPanel extends JPanel { 
  //instance vars 
  private JTextArea infoArea;  
  private Game myGame; //Just so we can greet the players by name.
  private Color defaultColor;
  private Color dlcolor = new Color (0, 255, 100);
  private Color tlcolor= new Color (36, 189, 67);
  private Color dwcolor= new Color (255, 192, 203);
  private Color twcolor= new Color (238, 151, 129);
  private Color stcolor = new Color (220, 220, 0);
  
  
  // Constructor
  public AboutPanel(Game g) { 
    setLayout(new BorderLayout()); 
    myGame = g;
    add(makeNPanel(), "North");
    add(makeCenterPanel(), "Center");
    add(makeSouthPanel(), "South");
  } 
  
/** 
   * Creates and returns a JPanel.  
   * @return JPanel The north panel that contains the logo
   */ 
  private JPanel makeNPanel(){
    JPanel northPanel = new JPanel();
    ImageIcon logoImg = new ImageIcon ("img/logo.gif");
    JLabel logoLabel = new JLabel("", logoImg, JLabel.CENTER);
    defaultColor = this.getBackground();
    northPanel.add(logoLabel);
    return northPanel;
  }
  
  /** 
   * Creates and returns a JPanel.  
   * @return JPanel The center panel that contains the general information 
   */ 
  private JPanel makeCenterPanel() { 
    JPanel infoPanel = new JPanel(); 
    infoArea = new JTextArea("Hi " + myGame.getP1().getName() + " and " + myGame.getP2().getName()+"!\n\n"+
                             "Welcome to Words with Enemies! \n"+
                             "\n In this Scrabble-like game, you will build words "+
                             "using your letter tiles. Build the longest words worth the most points to win!\n"+
                             "\nRules: \nEach player will start out with seven letter tiles. " +
                             "The first player will place tiles on the board to build a word worth as many points as possible. " +
                             "The first word played must go through the center of the board. " + 
                             "On each subsequent turn, players have three options:\n" + 
                             "1. Play a word using their letter tiles. " +
                             "Players can only play tiles in a single row or column of the board, and new " + 
                             "words must intersect at least one word already on the board.\n" +
                             "2. Pass turn. This will turn control of the board over to the opponent, with no benefit."+
                             "3. Swap a tile. You can trade one of your tiles with one of your opponents!" +
                             "Players get three chances to swap a tile within the game. To do this, select" + 
                             "your tile that you want to get rid of, and your opponent's tile that you want, and " +
                             "press the swap tiles button.\n\n" +
                             "Scoring: \nThe score a player receives for each turn is the sum of the value for each letter tile. "+
                             "If a letter tile is played on a premium space (see below) your score will get a bonus.");
    infoArea.setLineWrap(true);
    infoArea.setWrapStyleWord(true);
    infoArea.setColumns(50);
    infoArea.setRows(50);
    infoArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
    infoArea.setBackground(defaultColor);
    infoPanel.add(infoArea); 
    return infoPanel; 
  }
  
  /** 
   * Creates and returns a JPanel.  
   * @return JPanel The south Panel that contains the tile examples
   */ 
  private JPanel makeSouthPanel(){
    JPanel examplePanel = new JPanel();
    examplePanel.setPreferredSize(new Dimension(30, 50));
    examplePanel.add(makeSquares(Color.lightGray));
    examplePanel.add(new JLabel("Normal tile. 1x points"));
    examplePanel.add(makeSquares(stcolor));
    examplePanel.add(new JLabel("Starting Tile. 2x points for whole word"));
    examplePanel.add(makeSquares(dlcolor));
    examplePanel.add(new JLabel("Double Letter. 2x points for the letter"));
    examplePanel.add(makeSquares(tlcolor));
    examplePanel.add(new JLabel("Triple Letter. 3x points for the letter"));
    examplePanel.add(makeSquares(dwcolor));
    examplePanel.add(new JLabel("Double Word. 2x points for the word"));
    examplePanel.add(makeSquares(twcolor));
    examplePanel.add(new JLabel("Triple Word. 3x points for the word"));
    return examplePanel ;
  }
  
  /** 
   * Helper method for making squares
   * @return JPanel The Panel that contains the square
   */ 
  private JPanel makeSquares(Color c){
    JPanel square = new JPanel();
    JLabel tileLabel = new JLabel("");
    square.add(tileLabel);
    square.setOpaque(true);
    square.setPreferredSize(new Dimension(30, 30));
    square.setBackground(c);
    return square;
  }
}

