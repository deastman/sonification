/**
 * FILENAME:  HandPanel.java
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


public class HandPanel extends THPanel {
  private final static int HAND_SIZE = 7;
  private Hand myHand;  
  private Game myGame;
  private Player myPlayer;
  private JPanel myHandPanel;

  //constructor. Takes a game and which player in the game it refers to. Maybe we should have a check here to make sure that player is part of that game.
  public HandPanel(Game g, Player p) {
    super(g, p.getHand(), HAND_SIZE, 1); //THPanel makes myGame and myTH
    myHand = p.getHand();
    myPlayer = p;
    add(makeHandPanel());
  }

  private JPanel makeHandPanel(){
     myHandPanel = super.makeTHPanel(); 
    return myHandPanel;
  }
  
  //getter for associated player
  public Player getPlayer(){
    return myPlayer;
  }
}