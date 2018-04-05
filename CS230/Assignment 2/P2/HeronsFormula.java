/* 
 HeronsFormula.java
 Purpose: Compare the area of two triangles and determine if they are equal within 0.01 sq. units.
 If the areas are within the tolerance, print that the areas are essentially the same.
 CS230 Homework Assignment P2
 Written by: Diana Eastman
 Modified date: 
*/

import java.util.*;

public class HeronsFormula
{
  public static final double TOLERANCE = 0.01;
  private static double difference = 0.01;
  
  public static void main (String[] args)
  { 
    Triangle triangle1 = new Triangle();
    Triangle triangle2 = new Triangle();
    
    System.out.println("Let's do some geometry. This program will compare the area of two " +
    "triangles and determine if they are equal within 0.01 sq. units. Enter triangle 1's " +
    "three side lengths, separated by spaces.");
    
    Scanner in = new Scanner(System.in);
    
    boolean invalid1 = true;
    do
    {
      System.out.println("The lengths must make a valid triangle.");
      String sideLength = in.nextLine(); 
      if (triangle1.getSides(sideLength))
        invalid1 = false;
    } while (invalid1);
    
    System.out.println("Enter triangle 2's three side lengths, separated by spaces.");
    
    boolean invalid2 = true;
    do
    {
      System.out.println("The lengths must make a valid triangle.");
      String sideLength = in.nextLine(); 
      if (triangle2.getSides(sideLength))
        invalid2 = false;
    } while (invalid2);
    
   // Compare the areas and output result

  difference = Triangle.getDifference(triangle1.ComputeHeronsArea(), triangle2.ComputeHeronsArea());
  
  switch(Triangle.compareArea(triangle1.ComputeHeronsArea(), triangle2.ComputeHeronsArea(), TOLERANCE))
  {
   case 0: System.out.println("The two triangles have essentially the same area" +
                              " because their difference is " + difference +
                              " which is less than the permitted TOLERANCE of 0.01");
     break;
   case 1: System.out.println("The first triangle is " + difference + " bigger than the second");
     break;
   case 2: System.out.println("The second triangle is " + difference + " bigger than the first");
     break;  
  } 
  }
}