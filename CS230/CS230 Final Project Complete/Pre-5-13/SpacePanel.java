/**
 * FILENAME:  SpacePanel.java
 * WHO:       
 * WHEN:     
 * WHAT:      
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SpacePanel extends JPanel {
    private final int CELL_SIZE = 1;
    private JLabel tileLabel;
    private JLabel selected = null;
    private JLabel clicked = null;
    private ImageIcon unoccupiedIcon;
    private ImageIcon tileIcon;
    private Space mySpace;
   
    public SpacePanel(Space s)
    {
      mySpace = s;
      setOpaque(true);
      setBorder(BorderFactory.createBevelBorder(CELL_SIZE));
      setBackground(mySpace.getBgColor());
      setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        
      /*Add ImageIcons - for testing purposes*/
      unoccupiedIcon = new ImageIcon ("img/transparent.png");
      tileIcon = new ImageIcon ("img/transparent.png");
      tileLabel = new JLabel("", unoccupiedIcon, JLabel.CENTER);
      add(tileLabel);
        
        tileLabel.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            clicked = (JLabel) e.getComponent();
            
            if (clicked == null) {
              return;
            }
            if (selected != null) {
              selected.removeAll();
              System.out.println("Unclicked");
              selected.revalidate();
              selected.setIcon(unoccupiedIcon);
              selected.repaint();
              selected = null;
              return;
            }
            // Need to recode so that selected vs unselected corresponds to whether or not the space is unoccupied
            if (selected == null){
              selected = clicked;
              System.out.println("Clicked");
              ImageIcon currentLetter = new ImageIcon ("img/A.png"); // Hard-coded example - will need to get the current tile
              selected.setIcon(currentLetter); //Placeholder
              selected.revalidate();
              selected.repaint();
            }
          }
        });
    }
}