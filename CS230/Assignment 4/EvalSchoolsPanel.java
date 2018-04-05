//********************************************************************
//  EvalSchoolsPanel.java 
//  Created by: Diana Eastman
//  Created: March 4, 2013
//  Purpose: Represents the EvalSchools Panel for GradSchoolsGUI.java
//********************************************************************

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class EvalSchoolsPanel extends JPanel{
  
  /*Instance variables*/
  private GradSchools gsCollection;
  
  private JPanel controls;
  private JLabel aLabel, pLabel, rLabel;
  
  private JSlider aSlider, pSlider, rSlider;
  private JRadioButton academics, publications, research, overall;
  private JTextArea txtArea;
  
  /*Constructor*/
  public EvalSchoolsPanel(GradSchools gsCollection){
    
    this.gsCollection = gsCollection;

    //Set up Academics slider
    aSlider = new JSlider (JSlider.HORIZONTAL, 0, 5, 0);
    aSlider.setMajorTickSpacing (5); //Rating is from 0 - 5 (repeated for all three sliders)
    aSlider.setMinorTickSpacing (1); //Rating scale increments by 1 (repeated for all three sliders)
    aSlider.setPaintTicks (true);
    aSlider.setPaintLabels (true);
    aSlider.setAlignmentX (Component.LEFT_ALIGNMENT);
    
    //Set up Publications slider
    pSlider = new JSlider (JSlider.HORIZONTAL, 0, 5, 0);
    pSlider.setMajorTickSpacing (5);
    pSlider.setMinorTickSpacing (1);
    pSlider.setPaintTicks (true);
    pSlider.setPaintLabels (true);
    pSlider.setAlignmentX (Component.LEFT_ALIGNMENT);
    
    //Set up Research slider
    rSlider = new JSlider (JSlider.HORIZONTAL, 0, 5, 0);
    rSlider.setMajorTickSpacing (5);
    rSlider.setMinorTickSpacing (1);
    rSlider.setPaintTicks (true);
    rSlider.setPaintLabels (true);
    rSlider.setAlignmentX (Component.LEFT_ALIGNMENT);
    
    //Set up slider listeners
    SliderListener listenerSlider = new SliderListener();
    aSlider.addChangeListener (listenerSlider);
    pSlider.addChangeListener (listenerSlider);
    rSlider.addChangeListener (listenerSlider);
    
    //Create labels for each of the three sliders
    aLabel = new JLabel ("Academics: 0");
    aLabel.setAlignmentX (Component.LEFT_ALIGNMENT);
    pLabel = new JLabel ("Publications: 0");
    pLabel.setAlignmentX (Component.LEFT_ALIGNMENT);
    rLabel = new JLabel ("Research: 0");
    rLabel.setAlignmentX (Component.LEFT_ALIGNMENT);
    
    //Set up radio buttons; will determine how the user would like to rank the schools in her/his colllection
    academics = new JRadioButton("Academics", true);
    publications = new JRadioButton("Publications", true);
    research = new JRadioButton("Research", true);
    overall = new JRadioButton("Overall", true);
    
    //Add radio buttons to a button group
    ButtonGroup group = new ButtonGroup();
    group.add(academics);
    group.add(publications);
    group.add(research);
    group.add(overall);
    
    //Set up radio button listeners
    CategoryListener listenerRadio = new CategoryListener();
    academics.addActionListener(listenerRadio);
    publications.addActionListener(listenerRadio);
    research.addActionListener(listenerRadio);
    overall.addActionListener(listenerRadio);
    
    /*Create a new JTextArea and populate it with informational text initially*/
    txtArea = new JTextArea("Use the sliders to change how highly you weigh each factor. " +
                            "Toggle the radio buttons to change the ranking order, based on factor. " + 
                            "The top 3 schools will be printed below.");
    txtArea.setAlignmentX(Component.LEFT_ALIGNMENT);
    txtArea.setLineWrap(true); //Wrap text so JTextArea remains within preferred dimensions; otherwise no line breaks
    txtArea.setWrapStyleWord(true);
    txtArea.setEditable(false); //Set textArea to non-editable
    Border border = BorderFactory.createLineBorder(Color.BLACK); //Add a black border to the outside of the JTextArea
    txtArea.setBorder(BorderFactory.createCompoundBorder(border, //Empty border creates a margin btwn JTextArea and panel edge
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    
    /*Create a new JPanel to hold the sliders and radio buttons; set layout to BoxLayout; add all components to panel*/
    controls = new JPanel();
    controls.setPreferredSize(new Dimension(450, 550));
    BoxLayout layout = new BoxLayout (controls, BoxLayout.Y_AXIS);
    controls.setLayout (layout);
    controls.add (aLabel);
    controls.add (aSlider);
    controls.add (Box.createRigidArea (new Dimension (0, 20)));
    controls.add (pLabel);
    controls.add (pSlider);
    controls.add (Box.createRigidArea (new Dimension (0, 20)));
    controls.add (rLabel);
    controls.add (rSlider);
    controls.add (Box.createRigidArea (new Dimension (0, 20)));
    controls.add (academics);
    controls.add (publications);
    controls.add (research);
    controls.add (overall);
    controls.add (Box.createRigidArea (new Dimension (0, 20)));
    controls.add (txtArea);
         
    add (controls);
  }
    //--------------------------------------------------------------
    //  Gets the top three schools based on weighting (determined
    //  by sliders) and/or factor (determined by radio buttons)
    //  Replaces informational JTextArea with empty string
    //  before each call of method and appends the top three schools
    //  to JTextArea through each turn of the loop
    //--------------------------------------------------------------
   public void updateTextArea(){
    School[] temp = gsCollection.getTopThree();
    int count = temp.length;
    txtArea.setText("");
    for (int i=0; i < count; i++){
      txtArea.append("School Name: " + temp[i].getName());
      txtArea.append(System.getProperty("line.separator"));
      txtArea.append("Academics: " + temp[i].getAcademicRating()
                       + " " + "Publications: " + temp[i].getPubImpactRating() 
                       + " " + "Research: " + " " +
                     temp[i].getResearchRating() + " " + "Overall Rating: " + temp[i].getOverallRating());
      txtArea.append(System.getProperty("line.separator"));
      txtArea.append(System.getProperty("line.separator"));
    }
   }
  
   //*****************************************************************
   //  Represents the listener for all three sliders.
   //*****************************************************************
  private class CategoryListener implements ActionListener{
      
    public void actionPerformed (ActionEvent event){
        Object source = event.getSource();
        if (source == academics)
          gsCollection.rankSchools("Academics");
        else if (source == publications)
          gsCollection.rankSchools("Publications");
        else if (source == research)
          gsCollection.rankSchools("Research");
        else gsCollection.rankSchools("Overall");
        updateTextArea(); //Calls updateTextArea to reorder print out, based on factor chosen 
      }
   }
  
  private class SliderListener implements ChangeListener{
    private int aweight, pweight, rweight;

      //--------------------------------------------------------------
      //  Gets the value of each slider, then updates the labels and
      //  computes the Overall Rating; calls updateTextArea 
      //  based on weighting chosen
      //--------------------------------------------------------------
      public void stateChanged (ChangeEvent event)
      {
         aweight = aSlider.getValue();
         pweight = pSlider.getValue();
         rweight = rSlider.getValue();

         aLabel.setText ("Academics: " + aweight);
         pLabel.setText ("Publications: " + pweight);
         rLabel.setText ("Research: " + rweight);
         
         gsCollection.computeRating(aweight, pweight, rweight);
         updateTextArea();
      }
  }
}


