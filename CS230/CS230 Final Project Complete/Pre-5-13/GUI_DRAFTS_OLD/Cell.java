import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cell extends JPanel {
    private final int CELL_SIZE = 1;
    
    /*Tile Colors*/
    public static Color twColor = new Color(255, 0, 0); // Triple word cell - bright red 
    public static Color dwColor = new Color(255, 153, 255); // Double word cell - light magenta
    public static Color tlColor = new Color(0, 51, 255); // Triple letter cell - blue
    public static Color dlColor = new Color(102, 204, 255); // Double letter cell - light blue
    public static Color defaultColor = new Color(255, 255, 255); // Default cell - white
    
    private int xPos;
    private int yPos;
    private int premiumStatus;
    private boolean occupied; // Occupied = true means that the cell contains a letter 

    private JLabel selected = null;
    private JLabel clicked = null;
    private JLabel letterLabel;
    
    private ImageIcon unoccupiedIcon;
    private ImageIcon letterImg;
   
    public Cell(int xPos, int yPos, int premiumStatus){
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(CELL_SIZE));
        setBackground(setBgColor(premiumStatus)); // Determine what kind of cell it is, then bgColor follows
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        
        /*Add ImageIcons - for testing purposes*/
        unoccupiedIcon = new ImageIcon ("img/transparent.png");
        letterImg = new ImageIcon ("img/transparent.png");
        letterLabel = new JLabel("", unoccupiedIcon, JLabel.CENTER);

        add(letterLabel);
        
        this.xPos = xPos;
        this.yPos = yPos;
        this.premiumStatus = premiumStatus;

        letterLabel.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            JLabel clicked = (JLabel) e.getComponent();
            
            if (clicked == null) {
              return;
            }
            if (selected != null) {
              selected.removeAll();
              selected.revalidate();
              selected.setIcon(unoccupiedIcon);
              selected.repaint();
              occupied = false;
              System.out.println(occupied);
              selected = null;
              return;
            }
            if (selected == null){
              selected = clicked;
              occupied = true;
              System.out.println(occupied);
              selected.setIcon(letterImg);
              selected.revalidate();
              selected.repaint();
            }
          }
        });
    }
    
    public Color setBgColor(int premiumStatus){
      Color tileColor = defaultColor;
      switch (premiumStatus) {
        case 0: tileColor = defaultColor;
                     break;
        case 1: tileColor = defaultColor;
                     break;
        case 2: tileColor = dwColor;
                     break;
        case 3: tileColor = twColor;
                     break;
        case 4: tileColor = dlColor;
                     break;
        case 5: tileColor = tlColor;
                     break;
        }
      return tileColor;
    }

    public int getXPos(){
        return xPos;
    }
    
    public int getYPos(){
        return yPos;
    }
    
    public boolean isOccupied(){
        return occupied;
    }
    
    public int getPremiumStatus(){
        return premiumStatus;
    }
    
    public boolean setLetter(/*Letter l*/){
      if (isOccupied()==true)
        return false;
      else 
        letterImg = new ImageIcon ("img/A.png");
      return true;
    }
}