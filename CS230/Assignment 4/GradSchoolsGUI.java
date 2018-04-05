//********************************************************************
//  GradSchoolsGUI.java 
//  Created by: Diana Eastman
//  Created: March 4, 2013

//  Purpose: Creates a Tabbed Pane GUI with 3 Tabs to allow a user to 
//  add data about graduate schools to a collection and then use 
//  components (sliders and radio buttons) to dynamically rank the 
//  school in the collection based on Academics, Publication Impact,
//  Research or Overall Rating. This GUI uses GradSchools.java and
//  Schools.java to facilitate the creation of School and GradSchool
//  Objects and perform any backend operations
//********************************************************************

import javax.swing.*;
import java.awt.Container;

public class GradSchoolsGUI
{
  public GradSchools myGradschools;
   //-----------------------------------------------------------------
   //  Sets up a frame containing a tabbed pane with three tabs 
   //  Calls the GradSchools constructor and then passes 
   //  myGradSchools as a parameter to AddSchools and EvalSchools
   //  Adds three schools to the collection for testing purposes
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      JFrame myframe = new JFrame ("Graduate School Picker");
      myframe.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      
      GradSchools gsCollection = new GradSchools(20);
      gsCollection.addSchool("Research U", 3, 5, 1); //Add schools for testing purposes
      gsCollection.addSchool("Publications U", 1, 3, 5);
      gsCollection.addSchool("Academics U", 5, 3, 1);

      JTabbedPane mytpane = new JTabbedPane();
      mytpane.addTab ("About", new AboutPanel());
      mytpane.addTab ("Add School", new AddSchoolsPanel(gsCollection));
      mytpane.addTab ("Evaluate", new EvalSchoolsPanel(gsCollection));

      myframe.getContentPane().add(mytpane);

      myframe.pack();
      myframe.setVisible(true);
   }
}
