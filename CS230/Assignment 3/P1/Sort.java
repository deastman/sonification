import java.util.*; 

public class Sort {  
  
  /*
  * sorts the integers in the input array in increasing order
  */
  public static void sortArray (int[] arrayA) {
    int maxNum;    // maximum integer so far
    int maxIndex;  // index of maximum integer
    int i, j;
    for (j = arrayA.length - 1; j > 0; j--) { 
      maxIndex = 0;
      maxNum = arrayA[0];
      System.out.println("maxIndex: " + maxIndex);
      System.out.println("maxNum (outerloop) is " + maxNum);
      for (i = 1; i <= j; i++) 
        if (arrayA[i] > maxNum) {
          maxNum = arrayA[i];
          maxIndex = i;
          System.out.println("i is: " + i);
          System.out.println("maxNum (innerloop) is " + maxNum);
          //System.out.println(Arrays.toString(array));
        }
      swap(arrayA, maxIndex, j);
      
    }
  }
  
/** 
  * exchanges the contents of locations i and j in the input array
  */
  private static void swap (int[] arrayA, int i, int j) {
    int temp = arrayA[i];
    arrayA[i] = arrayA[j];
    arrayA[j] = temp;
    System.out.println(Arrays.toString(arrayA));
  }

public static void main (String[] args) {
  int[] arrayA = { 230, 100,1};
  sortArray(arrayA);
  System.out.println(Arrays.toString(arrayA));
}
}

