//********************************************************************
//  AddSchoolsPanel.java 
//  Created by: Diana Eastman
//  Created: March 4, 2013
//  Purpose: Represents the AddSchools Panel for GradSchoolsGUI.java
//********************************************************************

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.BorderFactory;   

public class AddSchoolsPanel extends JPanel {
  //Declare instance variables
  private GradSchools gsCollection;
  
  private JPanel outerPanel, instructions, controls, newSchool;
  private JLabel schoolLabel, instructionsLabel, academicsLabel, researchLabel, publicationsLabel;
  private JComboBox gsAcademics, gsResearch, gsPublications;
  private JTextField gsNameInput;
  private JButton addSchool;
  private JTextArea txtArea;
  private JScrollPane scroll;
  
  private int aSelection;
  private int rSelection;
  private int pSelection;
  
  /*Constructor*/
  public AddSchoolsPanel(GradSchools gsCollection){ 
    
    this.gsCollection = gsCollection;
    String[] rank = {"0", "1", "2", "3", "4", "5"}; //Array of Strings, passed to each ComboBox
    
    /*BorderLayout used for this tab*/
    setLayout(new BorderLayout());
   
    /*Declare Labels*/
    instructionsLabel = new JLabel("Fill in the information to add a school, then click \"Add School\".");
    schoolLabel = new JLabel("School Name"); //Label for JTextField
    academicsLabel = new JLabel("Academics"); //Labels for each of the ComboBoxes
    researchLabel = new JLabel("Research");
    publicationsLabel = new JLabel("Publications");   
    
    /*Initalize all 3 ComboBoxes and the school name JTextField and set alignment of each to left*/
    gsAcademics = new JComboBox(rank); gsAcademics.setAlignmentX (Component.LEFT_ALIGNMENT); 
    gsResearch = new JComboBox(rank); gsResearch.setAlignmentX (Component.LEFT_ALIGNMENT); 
    gsPublications = new JComboBox(rank); gsPublications.setAlignmentX (Component.LEFT_ALIGNMENT); 
    addSchool = new JButton("Add School"); addSchool.setAlignmentX (Component.LEFT_ALIGNMENT);
    gsNameInput = new JTextField(10); gsNameInput.setAlignmentX (Component.LEFT_ALIGNMENT);
    
    /*Set up ComboBox listeners*/
    gsAcademics.addActionListener (new ComboListener());
    gsResearch.addActionListener (new ComboListener());
    gsPublications.addActionListener (new ComboListener());
    
    /*Set up Add School Button listener*/
    addSchool.addActionListener(new ButtonListener());
    
    /*Create a new JTextArea and populate it with informational text*/
    txtArea = new JTextArea("Your new school will be added to those in the database and appear here.", 200, 400);
    txtArea.setAlignmentX(Component.LEFT_ALIGNMENT);
    txtArea.setLineWrap(true); //Wrap text so JTextArea remains within preferred dimensions; otherwise no line breaks
    txtArea.setWrapStyleWord(true); 
    Border border = BorderFactory.createLineBorder(Color.BLACK); //Add a black border to the outside of the JTextArea
    txtArea.setBorder(BorderFactory.createCompoundBorder(border, 
            BorderFactory.createEmptyBorder(10, 10, 10, 10))); //Empty border creates a margin btwn JTextArea and panel edge
    
    txtArea.setEditable(false); //Set textArea to non-editable
    scroll = new JScrollPane(txtArea); //Add scrollbars to JTextArea to allow text to scroll if beyond dimensions
    scroll.setPreferredSize(new Dimension(450, 400));
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    /*Create a new JPanel to hold the text instructions for using this tab; set its bg color to ghost white*/
    instructions = new JPanel();
    instructions.setBackground(new Color (248, 248, 255));

    /*Create a new JPanel to hold the ComboBoxes, JTextField for school name, and Add School button; set bg color to light blue*/
    controls = new JPanel();
    controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
    controls.setBorder(BorderFactory.createCompoundBorder(border, 
            BorderFactory.createEmptyBorder(10, 10, 10, 10))); //Empty border creates a margin btwn JPanel and edge
    controls.setBackground(new Color(240, 248, 255));

    /*Create a new JPanel to hold the JTextArea for schools listing*/
    newSchool = new JPanel();
    newSchool.setBackground(new Color (119, 136, 153));
    newSchool.setBorder(BorderFactory.createCompoundBorder(border, 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    
    /*Add components to JPanels*/
    instructions.add(instructionsLabel);
    controls.add (Box.createRigidArea (new Dimension (0, 20))); //RigidAreas create white space between components
    controls.add(schoolLabel);
    controls.add(gsNameInput);
    controls.add (Box.createRigidArea (new Dimension (0, 20)));
    controls.add(academicsLabel);
    controls.add(gsAcademics);
    controls.add (Box.createRigidArea (new Dimension (0, 20)));
    controls.add(researchLabel);
    controls.add(gsResearch);
    controls.add (Box.createRigidArea (new Dimension (0, 20)));
    controls.add(publicationsLabel);
    controls.add(gsPublications);
    controls.add (Box.createRigidArea (new Dimension (0, 20)));
    controls.add(addSchool);
    controls.add (Box.createRigidArea (new Dimension (0, 300))); //Large RigidArea between Add School button and bottom of controls JPanel
    newSchool.add (scroll);

    /*Add Panels to BorderLayout*/
    add(instructions, BorderLayout.NORTH);
    add(newSchool, BorderLayout.CENTER);
    add(controls, BorderLayout.WEST);
  }
  
  public void updateTextArea(){
    //Gets the new collection of schools and stores in temp array for clarity
    School temp[] = gsCollection.getCollection();
 
    txtArea.setText("");
    /* Loops through temp array from last position (displaying school last added to collection first) 
     * Appends each school in collection to JTextArea 
     * Used in lieu of toString(), which wasn't displaying satisfactorily in GUI*/
    for (int i=gsCollection.count-1; i > -1; i--){
      txtArea.append("School Name: " + temp[i].getName());
      txtArea.append(System.getProperty("line.separator")); //Line break
      txtArea.append("Academics: " + temp[i].getAcademicRating()
                       + " " + "Publications: " + temp[i].getPubImpactRating() 
                       + " " + "Research: " + " " +
                     temp[i].getResearchRating());
      txtArea.append(System.getProperty("line.separator")); //Line break
      txtArea.append(System.getProperty("line.separator")); //Line break
    }
  }
  
   //*****************************************************************
   //  Represents the listener for all three ComboBoxes
   //*****************************************************************
  
  private class ComboListener implements ActionListener{
     private ComboListener(){
    }
     /*Gets the valueOf ComboBox indices on button press and used in addSchools() method below*/
    public void actionPerformed (ActionEvent event){
      aSelection = gsAcademics.getSelectedIndex();
      pSelection =  gsPublications.getSelectedIndex();
      rSelection = gsResearch.getSelectedIndex();
    }
  }
  
   //*****************************************************************
   //  Represents the listener for the Add School button
   //*****************************************************************
  private class ButtonListener implements ActionListener {
    private ButtonListener(){
    }
    public void actionPerformed (ActionEvent event){
      if (event.getSource() == addSchool) {
        String name = gsNameInput.getText();
        /*If a school name is not entered, show dialog box; addSchool() not called*/
        if (name.equals("")) {
        JOptionPane.showMessageDialog(null,
                                      "You must specify a school name.",
                                      "Missing Information",JOptionPane.WARNING_MESSAGE);
        }
        else {
        String a = String.valueOf(aSelection);
        String r = String.valueOf(rSelection);
        String p = String.valueOf(pSelection);
        
        /*Call method addSchool(), which has been modified to return false if school name exists in collection, 
         * to determine if the school already exists in the collection.
         * If school exists, dialog box shown with appropriate warning message; school is not added 
         * Otherwise, the school is added to the collection and updateTextArea() method called */
        
        if (gsCollection.addSchool(name, Integer.parseInt(a), Integer.parseInt(r), Integer.parseInt(p))==false) {
          JOptionPane.showMessageDialog(null,
                                      "This school already exists in the database. Please add a unique school.",
                                      "Duplicate Entry",JOptionPane.WARNING_MESSAGE);

        }
        else {
        updateTextArea();
        }
        gsNameInput.setText(""); //Clear school name JTextField after submission (whether school name is valid or not)
        }
      }
    }
  }
}