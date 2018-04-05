/* 
 Triangle.java
 Purpose: A class that describes the properties of a triangle and includes methods
 that perform actions on these properties, such as computing the area.
 CS230 Homework Assignment P2
 Written by: Diana Eastman
 Modified date: 
*/

public class Triangle 
{
 // Private data declarations
 private double side1;
 private double side2;
 private double side3;
 private double area;
 
 // Constructor 
 public Triangle()
 {
  side1 = 0;
  side2 = 0;
  side3 = 0;
  area = 0;
 }
 
 // Methods
 
  /** Given an input string of three integers, splits the string into the three 
   * constituent integers at the white space delimiter and determines if three values
   * have been inputted
   * @param input  the side lengths of a triangle as one string 
   * @return    boolean true for three sides inputted
   */

 public boolean getSides(String sideLength)
 {
  String[] triangleSideArray;
  double[] sides = null;
  
  triangleSideArray = (sideLength.split("\\s+"));;
  sides = new double[triangleSideArray.length];
  for (int i = 0; i < triangleSideArray.length; i++)
  {
   sides[i] = Double.parseDouble(triangleSideArray[i]);
   //System.out.println("Array:" + sides[index]);
  }
  if (validateSides(sides))
  {
   side1 = sides[0];
   side2 = sides[1];
   side3 = sides[2];
   return true;
  }
  else
  {
   return false;
  }
 }
 
  /** Determines if the sidelengths provided make a valid 
    * triangle and returns true if valid
   * @param input  an array of three side lengths
   * @return    boolean true for valid triangle
   */
 
 private boolean validateSides(double[] sides)
 {
   if (sides.length == 3)
   {
     if ((sides[0] + sides[1] > sides[2]) &&
         (sides[1] + sides[2] > sides[0]) &&
         (sides[0] + sides[2] > sides[1]))
     {
       return true;
     }    
   }
   return false;
 }
  
 /** Computes and returns the area of a triangle
   * using Heron's Formula
   * @return    triangle area
   */
 public double ComputeHeronsArea()
 {
  double semiPerimeter = (side1 + side2 + side3)/2;
  area = Math.sqrt(semiPerimeter * (semiPerimeter - side1) *
           (semiPerimeter - side2) *
           (semiPerimeter - side3));
  
  // Round to nearest thousandth and return
  //area = Math.round(1000*area)/((double)(1000));
  return (area);
 }
 
  /** Gets the difference between two triangle areas
   * @param input  first triangle area 
   * @param input second triangle area
   * @return    difference between two triangle areas
   */
 
 public static double getDifference(double firstArea, double secondArea)
 {
  double difference = Math.abs(firstArea - secondArea);
  difference = Math.round(1000*difference)/((double)(1000));
  return difference;
 }
 
   /** Compares the area of two triangles and determines if they are
   * essentially the same, given a methematical tolerance
   * @param input  first triangle area 
   * @param input second triangle area
   * @param input tolerance
   * @return   int
   */
 
 public static int compareArea(double firstArea, double secondArea, double tolerance)
 {
  double difference = firstArea - secondArea;
  
  if (Math.abs(difference) < tolerance)
   return 0;
  
  if (difference > 0)
   return 1;
  else
   return 2;
 }
 
}
 
