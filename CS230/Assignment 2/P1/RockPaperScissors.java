/* 
 RockPaperScissors.java
 Purpose: Simulates a Rock Paper Scissors game with a user against the computer
 Written by: Diana Eastman
 Modified date: February 11, 2013
*/

import java.util.*;

public class RockPaperScissors
{
  // private member variables
  private int computerHand;
  private int userHand;
  private int numWins;
  private int numTies;
  private int numLosses;
  private int gameStatus;
  
  // Constructor
  public RockPaperScissors ()
  {
    computerHand = 1; // Initialize to rock
    userHand = 1; // Initiatilize to rock
    gameStatus = 1; // Initialize to win
    numWins = numTies = numLosses = 0;
  }
  
  // Methods
  public void intro()
  {
    System.out.println("Welcome to Rock Paper Scissors");
  }
  
  public boolean GenerateHands()
  {
    // Generate the computer hand by getting a random number between 0 and 2
    Random random = new Random(); 
    computerHand = random.nextInt(3) ;
    
    // Generate user hand
    Scanner in = new Scanner(System.in);
    String input = "";
    
   // Ask for human input and valid the input given; if invalid (i.e., not in 0-3), prompt again
    while (true)
    {
      System.out.print("Select your weapon or quit the game: 0 = Rock, 1 = Scissors, 2 = Paper, 3 = QUIT GAME");
      input = in.nextLine();
      try
      {
        userHand = Integer.parseInt(input);
        if ((userHand >= 0) && (userHand <= 3))
          break;
      }
      catch (NumberFormatException e)
      {
        // Do nothing and ask for input again
      }
    }
    if (userHand!=3) { // Quit the game
      return true;
    }
    else {
      return false;
    }
  }
  
  //Compare computerHand and UserHand, determine who won, and then increment the running totals
  public void CompareHands()   // Rock = 0, Scissors = 1, Paper = 2
  {
    if (computerHand == userHand) {
      numTies++;
      gameStatus = 0;
    }
    else if ((computerHand - userHand + 3) % 3 == 1)
    {
      numWins++;
      gameStatus = 1;
    }
    else {
      numLosses++;
      gameStatus = 2;
    }
  }
  
  // Rock beats scissors (0 over 1)
  // Paper beats rock (2 over 0)
  // Scissors beats paper (1 over 2)
  
  private String statustoString(int value)
  {
    String status = "";
    switch (value)
    {
      case 1: status = "Win";
              break;
      case 0: status = "Tie";
              break;
      case 2: status = "Lose";
              break;
    }
    return (status);
  }
  
  public String getGameStatus()
  {
    return (statustoString(gameStatus));
  }
  
  private String handtoString(int value)
  {
    String hand = "";
    switch (value)
    {
      case 0: hand = "Rock";
              break;
      case 1: hand = "Scissors";
              break;
      case 2: hand = "Paper";
              break;
    }
    return (hand);
  }

  public String getComputerHand()
  {
    return (handtoString(computerHand));
  }
  
  public String getUserHand()
  {
    return (handtoString(userHand));
  }
  
  //toString method will print out the running totals when the user wants to exit the game
  public String toString()
  {
    return ("Num Wins: " + numWins + "\n" + 
            "Num Ties: " + numTies + "\n" +
            "Num Losses: " + numLosses + "\n");
  }
  
  public static void main (String[] args)
  { 
    RockPaperScissors myGame = new RockPaperScissors();
    boolean keepGoing = true;
    myGame.intro();
    
    while (true)
    {
      keepGoing = myGame.GenerateHands();
      if (keepGoing == false)
      {
        System.out.println(myGame);
        break;
      }
      myGame.CompareHands();
      System.out.println("The computer's hand is: " + myGame.getComputerHand());
      System.out.println("Your hand is: " + myGame.getUserHand());
      System.out.println("You " + myGame.getGameStatus() + " this round.");
      
    }
  }
 
}