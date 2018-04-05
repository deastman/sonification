import javax.swing.*;
import java.awt.Container;

public class GameGUI
{
   //-----------------------------------------------------------------

   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      JFrame myframe = new JFrame ("Words With Enemies");
      myframe.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      JTabbedPane mytpane = new JTabbedPane();
      mytpane.addTab ("Play Game", new GamePanel());
      myframe.getContentPane().add(mytpane);

      myframe.pack();
      myframe.setVisible(true);
   }
}
