/** 
 * School.java
 * Purpose: Defines the CS program ratings in terms of Academics, Publication Impact, 
 * Research, and Overall Rating of a single School
 * Written by: Diana Eastman
 * Modified date: February 19, 2013
 */

public class School {
  private String name;
  private int academicRating;
  private int researchRating;
  private int pubImpactRating;
  private int overallRating;
  public int rank;
  
  /**
   *   School Constructor
   *   @param name 
   *   @param aRating (Academic rating)
   *   @param rRating (Research rating)
   *   @param pRating (Publication impact rating)
   */
  public School (String name, int aRating, int rRating, int pRating) 
  {
    this.name = name;
    academicRating = aRating;
    researchRating = rRating;
    pubImpactRating = pRating;
    overallRating = 1;
    rank = 0;
  }
  
   /**
  * Getter for School name.
  * @return name
  */
  public String getName()
  {
    return name;
  }
 
  /**
  * Getter for academicRating.
  * @return academicRating
  */
 public int getAcademicRating() 
 {
   return academicRating;
 }
 
 /**
  * Getter for ResearchRating.
  * @return researchRating
  */
 public int getResearchRating() 
 {
   return researchRating;
 }
 
 /**
  * Getter for pubImpactRating.
  * @return pubImpactRating
  */
 public int getPubImpactRating() {
   return pubImpactRating;
 }
 
 /**
  * Getter for overallRating.
  * @return overallRating
  */
 public int getOverallRating() 
 {
   return overallRating;
 }
 
 /**
  * Compute overallRating by multiplying each factor by a given weight (1-5)
  * @return overallRating
  */
 public int computeRating (int aweight, int rweight, int pweight) 
 {
   overallRating = (aweight*academicRating) + (rweight*researchRating) + (pweight*pubImpactRating);
   return overallRating;
 }
  
  /**
   *  toString() method to display School Class
   *  @return String
   */
  public String toString() 
  {
    String results ="";
    results += "Name: " + name + "\n";
    results += "Academics: " + academicRating + "\n";
    results += "Research: " + researchRating + "\n";
    results += "Publications: " + pubImpactRating + "\n";
    results += "Overall rating: " + overallRating + "\n";
    results += "Rank: " + rank + "\n"; 
    results += "******************************** \n";
    return results;
  }
  
  //Testing method
  public static void main(String args[]) 
  {
   School mit = new School("MIT", 10, 10, 7);
   School stanford = new School("Stanford", 8, 10, 9); 
   School cmu = new School("CMU", 7, 8, 6);
   School berkeley = new School("UC Berkeley", 9, 9, 9);
   berkeley.computeRating(4, 5, 3);
   stanford.computeRating(4, 5, 3);
   cmu.computeRating(4, 5, 3);
   mit.computeRating(4, 5, 3);
   System.out.println(mit);
   System.out.println(stanford);
   System.out.println(cmu);
   System.out.println(berkeley);
  }
} // End School Class