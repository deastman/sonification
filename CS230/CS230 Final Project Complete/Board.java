/**
 * FILENAME:  Board.java
 * WHO: Diana Eastman      
 * WHEN: May 17, 2013
 * WHAT: Represents Words With Enemies game board as a 2D array of Space objects (a TileHolder), including 
 * Spaces (with letter tiles) in play during this turn and the layout (e.g., bonus fields and attending point values)      
 */

import java.util.*;

public class Board extends TileHolder
{
 // Instance variables
 private LinkedList<Space> activeSpaces; // Spaces on the board that have been occupied during this turn
 private Space currentSpace; //space on board currently selected
 private static final int GRID_SIZE = 15;
 private String wordOnBoard; // the word formed on the board during the current turn
 private int wordScore; // the score of the word
 private Word word;
 private LinkedList<Space> wordSpaces; // Spaces that make up the primary word submitted
 private HashDictionary dictionary;
     
 private static int[][] premiumSpaces = {
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
 
 /** 
  * Class constructor.
  */
 public Board()
 {
   super(GRID_SIZE, GRID_SIZE, premiumSpaces); //TileHolder creates myTiles (array of tiles) and mySpaces (array of spaces)
   activeSpaces = new LinkedList<Space>();
   dictionary = new HashDictionary("dictionary.txt");
 }
 
 /**
  * Returns the Space at a particular position (x, y coordinates) on the Board.
  * @param x the x-coordinate (row) of the Space's location
  * @param y the y-coordinate (column) of the Space's location
  * @return Space
  */
 public Space getSpace(int x, int y)
 {
   Space thisSpace = mySpaces[x][y];
   return thisSpace;
 }

 /**
  * Determines if the word played on the board during this turn is valid. 
  * 
  * There are two checks for valid placement: 
  * (1) Check if the tiles were placed in one direction during this turn: <code>getWordDirection()</code>
  * (2) Check if the word is a solitaire, i.e., isolated on the board. 
  * All words--save the first play of the game--must be anchored to an existing word: <code>checkIfNotSolitare()</code>
  * 
  * There are two checks for a valid word:
  * (1) Check that the word formed from the player's tiles (known as primary word) exists 
  * in our dictionary. Note that this will also take care of the case in which the player
  * puts her tiles all in one direction, but there are gaps (e.g., "B_KE"): <code>wordAcross()</code> and <code>wordDown()</code>
  * (2) Check that any words formed incidentally from the placement of the player's tiles
  * (known as secondary words) are also in our dictionary: <code>secondaryWordAcross</code> and <code>secondaryWordDown</code>
  * @return <code>true</code> if the the word played passes all validity checks and false otherwise.
  */
 public boolean validateTurn()
 {
   word = new Word(activeSpaces);
   
   if(word.getWordDirection() == Word.WordDirection.UNKNOWN) // Unknown means tiles were placed vertically and horizontally
     return false;
   
   else if (checkIfNotSolitare())
     return false;

   else if(word.getWordDirection() == Word.WordDirection.ACROSS) 
   {
     if (!wordAcross())
       return false;
     else {
       computeScore(); // Compute the score of the primary word
       return secondaryWordDown(); // Make sure any secondary words also exist in the dictionary (NOTE: Not added to score)
     }
   }
   else if(word.getWordDirection() == Word.WordDirection.DOWN) 
   {
     if (!wordDown())
       return false;
      else {
       computeScore();
       return secondaryWordAcross();
     }
   }
  return true;
 }
 
 /**
  * Determines if this turn is the first of the game.
  * @return <code>true</code> if no tiles are yet locked in place (Spaces are locked at the end of each turn) and false otherwise
  */
 private boolean firstMove() 
 {
  if(getNumLockedTiles()==0)
    return true;
  return false;
 }
 
 /**
  * Returns the number of locked spaces on the board. If no Spaces are locked, 
  * no Tiles have been placed on the Board and this is the first turn of the game.
  * @return the number of Spaces that are locked, i.e., contain Tiles
  */
 private int getNumLockedTiles()
 {
   int result = 0;
   for (int x = 0; x < rows; x++) 
   {
     for (int y = 0; y < cols; y++) 
     {
       if(!mySpaces[x][y].isFree())
         if (myTiles[x][y].isLocked() == true)
       result ++;
     }
   }
   return result; 
 }

 /**
  * Determines if the word placed on the Board is a solitaire, i.e., unanchored to 
  * any existing Spaces occupied by Tiles.
  * @return the number of Spaces that are locked, i.e., contain Tiles
  */
 private boolean checkIfNotSolitare()
 {
   if(firstMove()) // if first move, don't check if it's isolated--check only that there is a tile on (7,7) and that 2 tiles have been placed
   {
     if(!mySpaces[7][7].isFree() && activeSpaces.size() > 1)
       return false;
     else 
       return true;
   }
   
   int start = word.getStart(); // Returns the smallest position (vertically or horizontally as the case may be) from the Linked List of active spaces
   int end = word.getEnd(); // Returns the largest position
   int p = word.getSharedPos(); // Returns row or column that the word is on
   
   if((start-1) > 0) // Extend start position by 1 if this doesn't put us off of the board
     start = start - 1; 
   if ((end+1) < GRID_SIZE) // Extend end position by 1 if this doesn't put us off of the board
     end = end + 1;

   if(word.getWordDirection()==Word.WordDirection.ACROSS)
   {
     for(int i=start; i <= end; i++)
     {
        /**
         * Checks if new word includes any old letters, e.g.
         *  Board before:
         *  v - board edge
         * |S| |O|R|E| |
         *
         * Board after we added T and S:
         *  v - start (we don't extend before S because we'd be off of the board)
         * |S|T|O|R|E|S| |
         *              ^ - end (because we extended the end position to check if there are any letters beyond S)
         *
         * Check from start to end
         */
        if(!mySpaces[p][i].isFree()) {// If the space has a tile on it...
          if(myTiles[p][i].isLocked()) //...check if the tile was already on the board before this turn
          return false;
        }
     }
   }
   if(word.getWordDirection()==Word.WordDirection.DOWN)
   {
     for(int i=start; i <= end; i++)
     {
        if(!mySpaces[i][p].isFree()) {// If the space has a tile on it...
          if(myTiles[i][p].isLocked())  //...check if the tile was already on the board before this turn
          return false;
        }
      }         
   }
   return true;  
 }
 
 /**
  * Creates a word from Tiles placed horizontally on the Board and determines if
  * this word exists in the dictionary. Also checks if any horizontal words are created by a vertically
  * oriented placed word. In the latter case, this method is called by <code>secondaryWordAcross()</code> for
  * each Space in the Linked List activeSpaces.
  * @return <code>true</code> if the horizontally placed word exists in the dictionary
  */
 private boolean wordAcross() 
 {
   int start = word.getStart(); // The leftmost space in our Linked List of activeSpaces
   int end   = word.getEnd(); // The rightmost space in our Linked List of activeSpaces
   int x = word.getSharedPos(); // Moving across within the same row

   for (int i = start; i >= 0; i--) { // Step to the left from start to determine if there Spaces occupied by letter Tiles to the left
     if(!mySpaces[x][i].isFree()) // Found a Space with a letter Tile - this is our new leftmost space
       start = i;
     else // Hit an empty space or the board's edge - break out of the loop
       break;
   }
   for (int i = end; i< GRID_SIZE; i++)  // Step to the right from end to determine if there Spaces occupied by letter Tiles to the right
   {
     if(!mySpaces[x][i].isFree()) // Found a Space with a letter Tile - this is our new rightmost space
       end = i;
     else // Hit an empty space or the board's edge - break out of the loop
       break;
   }
   
   wordSpaces = new LinkedList<Space>();
   wordOnBoard = "";
   for (int i = start; i <=end; i++) // Traverse the row from starting position to end and build up the word played
   {
     if (!mySpaces[x][i].isFree()) {
       wordSpaces.add(mySpaces[x][i]); // Also add to a Linked List of Spaces (contains premium tile status) to assist in calculating score
       wordOnBoard += mySpaces[x][i].getTile().getLetter();
     }
     else {
       wordSpaces.add(null); // This accounts for someone placing tiles in one row with gaps - the word will contain a space and not be valid in our dictionary
       wordOnBoard += " ";
     }
   }
   System.out.println("The word is : " + wordOnBoard);
   if (wordSpaces.size()==1) //wordAcross() also used to determine secondary words formed -- if no secondary words exist, a LL of 1 results
     return true;
   else if (wordSpaces.size()>1) // Validate the word in our dictionary
     return (isWord(wordOnBoard));
   else
     return false; // Word doesn't exist in the dictionary and returns false
 }
 
 /**
  * Creates a word from Tiles placed vertically on the Board and determines if
  * this word exists in the dictionary. Also checks if any vertical words are created by a horizontally
  * oriented played word. In the latter case, this method is called by <code>secondaryWordDown()</code> for
  * each Space in the Linked List activeSpaces.
  * @return <code>true</code> if the vertically placed word exists in the dictionary
  */
 private boolean wordDown() 
 {
   int start = word.getStart();
   int end   = word.getEnd();
   int y = word.getSharedPos(); // all in the same column
     
   for (int i = start; i >= 0; i--) 
   {
     if(!mySpaces[i][y].isFree()) 
       start = i;
     if(mySpaces[i][y].isFree())
       break;
   }
   
   for (int i = end; i< GRID_SIZE; i++) 
   {
     if(!mySpaces[i][y].isFree()) 
       end = i;
     if(mySpaces[i][y].isFree())
       break;
   }
   
   wordSpaces = new LinkedList<Space>();
   wordOnBoard = "";
   for (int i = start; i <=end; i++) 
   {
     if (!mySpaces[i][y].isFree()) {
       wordSpaces.add(mySpaces[i][y]);
       wordOnBoard += mySpaces[i][y].getTile().getLetter();
     }
     else {
       wordSpaces.add(null);
       wordOnBoard += " ";
     }
   }
   System.out.println("The word is : " + wordOnBoard);
   if (wordSpaces.size()==1)
     return true;
   else if(wordSpaces.size()>1)
     return (isWord(wordOnBoard));
   else
     return false;
 }
 
 /**
  * Getter for wordOnBoard
  * @return last word played
  */
 
 public String getWordOnBoard(){
   return wordOnBoard;
 }
 
 /**
  * Determines if the horizontal word played is parallel to a word and incidentally creates one or 
  * more vertical words.
  * Example:
  * 
  * |N|O|T|E| < NOTE is the existing word
  *   |N|O| < NO is the word played parallel, forming ON and TO incidentally
  * 
  * Goes through the Tiles we played in this turn (from LL activeSpaces), determines what column the Tile is 
  * placed on, and sets the start and end point of our search to the location of the Tile. In <code>wordDown()</code>
  * the start and end points are expanded (within the column we set) if Spaces above or below the Tile we 
  * placed also contain Tiles. This returns true if the secondary word is valid, or none exists. The method returns
  * false if the secondary word is not in our dictionary. 
  * 
  * @return <code>true</code> if the secondary word is valid or none exists.
  */
 private boolean secondaryWordDown() 
 {
   for (int i = 0; i < activeSpaces.size(); i++) {
     int y = activeSpaces.get(i).getyPos();
     word.setSharedPos(y);
     word.setStart(activeSpaces.get(i).getxPos());
     word.setEnd(activeSpaces.get(i).getxPos());
     if(!wordDown())
       return false;
   }
   return true;
 }
 
  /**
  * Determines if the horizontal word played is parallel to a word and incidentally creates one or 
  * more vertical words.
  * Goes through the Tiles we played in this turn (from LL activeSpaces), determines what row the Tile is 
  * placed on, and sets the start and end point of our search to the location of the Tile. In <code>wordDown()</code>
  * the start and end points are expanded (within the column we set) if Spaces to the left or right of the Tile we 
  * placed also contain Tiles. This returns true if the secondary word is valid, or none exists. The method returns
  * false if the secondary word is not in our dictionary. 
  * 
  * @return <code>true</code> if the secondary word is valid or none exists.
  */
 private boolean secondaryWordAcross() 
 {
   for (int i = 0; i < activeSpaces.size(); i++) {
     int x = activeSpaces.get(i).getxPos();
     word.setStart(activeSpaces.get(i).getyPos());
     word.setEnd(activeSpaces.get(i).getyPos());
     word.setSharedPos(x);
     if(!wordAcross())
       return false;
   }
   return true;
 }
 
 public boolean isWord(String w)
 {
   if(dictionary.searchWord(w))
     return true;
   else return false;
 }
 
 /**
  * Computes the score of our primary word placed on the Board.
  */
 private void computeScore()
 {
   int score = 0;
   int dwCount = 0;
   int twCount = 0;
   int letterValue = 0;
   for (int i = 0; i < wordSpaces.size(); i++) 
   {
     letterValue = wordSpaces.get(i).getValue(); // The number of points the letter is worth
     String special = wordSpaces.get(i).getSpecial(); // The premium status of the Space the letter is occupying
     if(special=="DL") {
       letterValue = letterValue * 2;
     }
     if(special=="TL") {
       letterValue = letterValue * 3;
     }
     if(special=="DW" || special == "{}") {
       dwCount++;
     }
     if(special=="TW") {
       twCount++;
     }
     score += letterValue;
   }
   if(dwCount > 0)
     score = score * (2*dwCount);
   else if(twCount > 0)
     score = score * (3*twCount);
   else
     score = score;
   wordScore = score;
 }
 
 /**
  * Returns the score of the word played.
  * @return score of the word
  */
 public int getWordScore()
 {
   return wordScore;
 }
 
 // Complete turn (reset active spaces in the linked list)
 public void completeTurn()
 {
  
 }
 
 /**
  * Returns the Spaces that were occupied by tiles from the current player's hand during this turn
  * @return active spaces
  */
 public LinkedList<Space> getActiveSpaces(){
  return activeSpaces; 
 }
 
 /**
  * Adds a Space from the current player's hand to the list of activeSpaces
  */
 public void addActiveSpace(Space s){
   activeSpaces.add(s);
 }
 
 /**
  * Sets activeSpaces to a particular Spaces
  */
 public void setActiveSpaces(LinkedList<Space> s){
  activeSpaces = s;
 }
 
 /**
  * Sets <code>locked</code> to true in Tile after the word is submitted and deemed valid.
  * This indicates that the Tile is "locked" in place on the board and its position cannot 
  * be changed. 
  */
 public void lockActiveTiles(){
   for (int i = 0; i < activeSpaces.size(); i++) {
     activeSpaces.get(i).getTile().lock(); 
   }
 }
 
 // Testing method
 public static void main (String args[])
 {
   Board test = new Board();
   System.out.println(test);
    //Because the GUI is effectively a driver for this class, and true, extensive testing would be difficult for this
    //class, we have not included a main testing method. 
  }
}
