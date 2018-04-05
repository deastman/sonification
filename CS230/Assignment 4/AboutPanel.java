//********************************************************************
//  About.java 
//  Created by: Diana Eastman
//  Created: March 4, 2013
//  Purpose: Represents the About Panel for GradSchoolsGUI.java
//********************************************************************

import java.awt.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.AttributeSet;
import javax.swing.border.Border;
import javax.swing.BorderFactory;  

public class AboutPanel extends JPanel
{
  public AboutPanel()
  {
    setLayout (new BoxLayout(this, BoxLayout.Y_AXIS));
    setBackground (new Color (240, 248, 255));
    
    JLabel headerText = new JLabel("Find the right grad school; it's easy.");
    JLabel instructionText = new JLabel("Use the Add School tab to create your personal collection of grad schools.");
    JLabel instructionText2 = new JLabel("Then, use the Evaluate tab to order your collection by the factors you care about!");
    
    Border border = BorderFactory.createLineBorder(new Color (240, 248, 255)); //Add a black border to the outside of the JTextArea
    headerText.setBorder(BorderFactory.createCompoundBorder(border, 
            BorderFactory.createEmptyBorder(20, 20, 20, 20))); //Empty border creates a margin btwn JTextArea and panel edge
    
    instructionText.setBorder(BorderFactory.createCompoundBorder(border, 
            BorderFactory.createEmptyBorder(10, 50, 5, 50))); //Empty border creates a margin btwn JTextArea and panel edge
    instructionText2.setBorder(BorderFactory.createCompoundBorder(border, 
            BorderFactory.createEmptyBorder(5, 50, 10, 50))); //Empty border creates a margin btwn JTextArea and panel edge
    
    headerText.setFont(new Font("Sans-serif", Font.BOLD, 40));
    instructionText.setFont(new Font("Sans-serif", Font.BOLD, 18));
    instructionText2.setFont(new Font("Sans-serif", Font.BOLD, 18));

    add(headerText);
    add(instructionText);
    add(instructionText2);

  }
}