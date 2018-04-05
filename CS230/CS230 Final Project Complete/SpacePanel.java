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
  private boolean selected = false;
  private JLabel clicked = null;
  private ImageIcon unoccupiedIcon ;
  private TileHolder ownerTH;
  private THPanel ownerPanel;
  private Space mySpace;
  
   /**
  * Constructor. Takes in the "owner" THPanel that this space panel will be a part of, and its coordinates in that THPanel
  */
  public SpacePanel(THPanel tp, int x, int y)
  {
    ownerPanel = tp;
    ownerTH = ownerPanel.getTH();
    mySpace = ownerTH.getSpace(x, y);
    mySpace.setSpacePanel(this);
    setOpaque(true);
    unoccupiedIcon = new ImageIcon("img/transparent.png");
    setBorder(BorderFactory.createBevelBorder(CELL_SIZE));
    setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
    add(makeTileLabel());
    setBackground(mySpace.getColor());
  }
  
  
   /**
  * Makes a space no longer selected
  */
  public void unselectSpace()
  {
    selected = false; //marks it as not selected
    mySpace.setCurrent(false); //no longer current
    //System.out.println("Unclicked");
  }
  
   /**
  * Makes a space selected
  */
  public void selectSpace()
  {
    selected = true; //marks it as selected
    mySpace.setCurrent(true); //makes current
    //System.out.println("Clicked");
  }
  
   /**
  * Updates appearance (tile, background, shading) of this space panel
  */
  public void refreshDisplay()
  {
    if (!mySpace.isFree()){ //if isn't free, display its letter tile image
      //System.out.println("Space" + mySpace.getxPos() + "," + mySpace.getyPos());
      ImageIcon currentLetter = new ImageIcon (mySpace.getTile().createImgPath());
      tileLabel.setIcon(currentLetter);}
    else{ //if it is, show the blank icon
      tileLabel.setIcon(unoccupiedIcon);} 
    
    if (mySpace.isCurrent()){ //if it is the current space, highlight it so the user knows
      setBackground(mySpace.getColor().darker());}
    else {
      setBackground(mySpace.getColor()); //if it isn't, change it back to the light gray
    }
    tileLabel.repaint(); //update the display with these changes
  }
  
   /**
  * Returns whether or not this space is the selected space
  * @return boolean of whether it's selected
  */
  public boolean getSelected()
  {
    return selected;
  }
  
  /**
  * Creates the JLabel for this tile with its icon and listener
  * @return JLabel of the tile
  */
  private JLabel makeTileLabel(){
    tileLabel = new JLabel("", unoccupiedIcon, JLabel.CENTER);
    tileLabel.addMouseListener(new SpaceListener());
    return tileLabel;
  }
  
  private class  SpaceListener extends MouseAdapter{
    
    /**
  * Makes the mouse listener
  */
    public void mouseClicked(MouseEvent e) {
      clicked = (JLabel) e.getComponent();
      if (clicked == null) { //didn't click on this space
        return;
      }
      
      if (!selected) { //selected means that this space is selected
        if (ownerTH.getCurrentSpace() != null) //if something else is selected already
          ownerTH.getCurrentSpace().getSpacePanel().unselectSpace(); //deselect that space
        selectSpace(); //select this one
        ownerPanel.checkPlays(); //see if this click means a tile should be moved, if so move it. Includes refresh all. KB:This all could be organized better..
        return;
      }
      else{
        unselectSpace(); //if this space is selected, unselected and refresh the display
        ownerPanel.refreshAll();
      }
     
    }
  }
}