/**
 * FILENAME:  GameGUI.java
 * WHO:       
 * WHEN:     
 * WHAT:      
 */

import javax.swing.*;
import java.awt.Container;

public class GameGUI
{
  public Game myGame;

   public static void main (String[] args)
   {
      JFrame myframe = new JFrame ("Words With Enemies");
      myframe.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      
      //String player1Name = JOptionPane.showInputDialog ("Enter Player 1's name:");
      //String player2Name = JOptionPane.showInputDialog ("Enter Player 2's name:");
      
      Game myGame = new Game("Player 1", "Player 2");

      JTabbedPane mytpane = new JTabbedPane();
      mytpane.addTab ("Play Game", new GamePanel(myGame));
      myframe.getContentPane().add(mytpane);

      myframe.pack();
      myframe.setVisible(true);
   }
}
