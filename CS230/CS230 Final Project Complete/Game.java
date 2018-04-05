/**
 * FILENAME:  Game.java
 * WHO: Kaitlin Bohon       
 * WHEN: 5/17/13    
 * WHAT: Game class. Controls moves, checks to see if the game is over, etc.     
 */

import java.util.*;
public class Game 
{
  private GamePanel myGamePanel;
  private Board myBoard;
  private Bag myBag;
  private Player player1;
  private Player player2;
  private Player[] players; 
  private Player currentPlayer, otherPlayer;
  private static final int NUM_PLAYERS = 2;
  private Tile currentTileInPlay;
  private int numPassesInRow; // three consecutive passes for each player = end of game
  private int numTurns; 
  private String winner;
  
  // Constructor
  public Game(String p1name, String p2name)
  {
    myBag = new Bag();
    myBoard = new Board();
    player1 = new Player(p1name);
    player2 = new Player(p2name);
    currentTileInPlay = null;
    currentPlayer = player1;
    otherPlayer = player2;
    //System.out.println(currentPlayer);
    players = new Player[] {player1, player2}; 
    numPassesInRow = 0;
    numTurns = 1;
    myGamePanel = null;
    winner = "Tie!";
  }
  
   /**
   * Submits turn by checking for a valid move, updating scores
   */
  public void playTurn()
  {
    if((myBoard.getActiveSpaces().size() > 0) && myBoard.validateTurn()){ //if tiles have been played and they are valid
      currentPlayer.updateScore(myBoard.getWordScore());
      myBoard.completeTurn();
      this.nextTurn();
    } 
    else removeCurrentTiles();
    this.refreshAll();
  }
  
  
 /**
   * Updates the next turn by changing player, locking tiles, clearing spaces, etc. Called at the end of every turn. 
   */
  public void nextTurn()
  {
    numTurns++; //increments number of passed turns
    currentPlayer.getNewTiles(); //adds new tiles to hand
    Player temp = currentPlayer;
    currentPlayer = otherPlayer; //switches player
    otherPlayer = temp;
    myBoard.lockActiveTiles(); //locks tiles on board
    myBoard.getActiveSpaces().clear(); //updates active tiles list
    //System.out.println(currentPlayer.getName() + "'s turn."); 
  }
  
   /**
   * Sets the status of all tiles on the board to "locked"
   */
  public void lockActiveTiles(){
    for (int i = 0; i < myBoard.getActiveSpaces().size(); i++)
      myBoard.getActiveSpaces().get(i).getTile().lock();
  }
  
   /**
   * Plays out a turn which has been passed, just getting rid of any non-locked tiles on board. 
   */
  public void passTurn(){
    removeCurrentTiles();
    numPassesInRow++;
    nextTurn();
  }
  
   /**
   * Getter for current player
   * @return Player the current player
   */
  public Player getCurrentPlayer()
  {
    return currentPlayer;
  }
  
  //setter for this game's panel
  public void setGamePanel(GamePanel gp)
  {
    myGamePanel = gp;
  }
  
  //Swap tiles
  
  public void swapTiles(){
    if (currentPlayer.getRemainingSwaps() > 0){
      removeCurrentTiles();
      currentPlayer.swapTile(otherPlayer, currentPlayer.getHand().getCurrentSpace().getTile(), otherPlayer.getHand().getCurrentSpace().getTile());
      nextTurn();
      refreshAll();
    }
  }
  
  //play letter by getting the current tile, adding the tile to the board, and removing the tile from the player's hand
  public void playLetter()
  {
    try{ //throws a null pointer exception when you click on the board prior to clicking on a letter
      currentTileInPlay = currentPlayer.getHand().getCurrentSpace().getTile();
      if (currentTileInPlay != null){
        myBoard.addTile(currentTileInPlay, myBoard.getCurrentSpace().getxPos(), myBoard.getCurrentSpace().getyPos());
        myBoard.addActiveSpace(myBoard.getCurrentSpace());
        //System.out.println("LL: " + myBoard.getActiveSpaces());
        currentPlayer.playTile(currentPlayer.getHand().getCurrentSpace().getxPos());
      }
    }catch(NullPointerException ex){}
  }
  
  
  public void refreshAll(){
    //KB: This is so ugly, but I'm not sure how else to get it to update the hands as well as the board..
    if (myGamePanel != null){
      myGamePanel.getBoardPanel().refreshAll();
      myGamePanel.getP1HandPanel().refreshAll();
      myGamePanel.getP2HandPanel().refreshAll();
    }
  }
  
  public void removeCurrentTiles()
  {
    LinkedList<Space> cs = myBoard.getActiveSpaces();
    while (cs.size() > 0){
      Tile activeTile = cs.getLast().getTile();
      myBoard.removeTile(cs.getLast().getxPos(), cs.getLast().getyPos());
      currentPlayer.getHand().addTile(activeTile);
      //System.out.println("Hand with old tiles back: " + currentPlayer.getHand());
      cs.removeLast();
    }
    myBoard.setActiveSpaces(cs);
    refreshAll();
  }
  
  public boolean isOver()
  {
    if (player1.getScore() > player2.getScore())
      winner = player1.getName();
    else if (player2.getScore() > player1.getScore())
      winner = player2.getName();
    else
      winner = "Tie!";
    if (numPassesInRow >= 3)
      return true;
    if (myBag.isEmpty() && (player1.getNumTiles() == 0) && (player2.getNumTiles() == 0))
      return true;
    return false;
  }
  
  public String getWinner()
  {
    return winner;
  }
  
  public Board getBoard()
  {
    return myBoard;
  }
  
  public Player getP1()
  {
    return player1;
  }
  
  public Player getP2()
  {
    return player2;
  }
  
  // Testing method
  public static void main(String[] args) 
  {
    Game test = new Game("Leslie Knope", "Ben Wyatt");
    //test.playGame();
    //System.out.println(mytest);
  }
  
}
