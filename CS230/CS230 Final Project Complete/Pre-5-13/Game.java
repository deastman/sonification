/**
 * FILENAME:  Game.java
 * WHO:       
 * WHEN:     
 * WHAT:      
 */

public class Game 
{
 private Board myBoard;
 private Bag myBag;
 private Player player1;
 private Player player2;
 private Player[] players; 
 private Player currentPlayer;
 private static final int NUM_PLAYERS = 2;
 private Tile currentTileInPlay;
 private int numPassedTurns; // three consecutive passes for each player = end of game - need to increment this or zero it out
 
 // Constructor
 public Game(String p1name, String p2name)
 {
   myBag = new Bag();
   myBoard = new Board();
   player1 = new Player(p1name);
   player2 = new Player(p2name);
   currentTileInPlay = new Tile();
   players = new Player[] {player1, player2}; // would be better not to hardcode this?
   numPassedTurns = 0;
 }
 
 public void playGame()
 {
   int i = 0;
   while(!isOver()) {
     i++;
     currentPlayer = players[i%NUM_PLAYERS];
     System.out.println(currentPlayer.getName() + "'s turn."); 
     //3 ways to exit a turn: 
     // 1) Submit a word
     // 2) Pass turn
     // 3) Swap letter
   }
 }
 
 private boolean isOver()
 {
   //3 ways to exit the game:
   // 1) Choose "exit"
   // 2) 3 consecutive turns where neither player plays a letter.
   // 3) Bag of letter tiles is empty && both players have no letters
   return false;
 }
 
 public Board getBoard()
 {
   return myBoard;
 }

 // Testing method
 public static void main(String[] args) 
 {
  Game test = new Game("Leslie Knope", "Ben Wyatt");
  //test.playGame();
  //System.out.println(mytest);
 }

}
