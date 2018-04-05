/********************************************************************
  * AdjMatGraphPlusDriver.java 
  * Created by: Diana Eastman
  * Created: April 25, 2013
  * Purpose: Driver class for AdjMatGraphPlus.java containing testing 
  * code for methods implemented in Exam 3, CS 230
  ********************************************************************/

public class AdjMatGraphPlusDriver {

  public AdjMatGraphPlusDriver() {
    // Empty constructor
  }

  /******************************************************************
    Testing program.
    ******************************************************************/
 
  public static void main (String args[]){
    System.out.println("Problems for Midterm 3");
    System.out.println("______________________");
    AdjMatGraphPlus<String> graphFromTGF = new AdjMatGraphPlus<String>("Sample-Graph.tgf");
    System.out.println(graphFromTGF);
    System.out.println("Vertex E is a sink (TRUE): " + graphFromTGF.isSink("E"));
    System.out.println("Vertex C is a sink (FALSE): " + graphFromTGF.isSink("C"));
    System.out.println("Vertex O is a sink (FALSE): " + graphFromTGF.isSink("O"));
    System.out.println("Vertex O is a source (FALSE): " + graphFromTGF.isSource("O"));
    System.out.println("A list of all of the sinks in the graph: " + graphFromTGF.allSinks());
    System.out.println("Vertex A is a source (TRUE): " + graphFromTGF.isSource("A"));
    System.out.println("Vertex B is a source (FALSE): " + graphFromTGF.isSource("B"));
    System.out.println("A list of all of the sources in the graph: " + graphFromTGF.allSources());
    System.out.println("Vertex H is isolated (TRUE): " + graphFromTGF.isIsolated("H"));
    
    AdjMatGraphPlus<String> sampleDAG = new AdjMatGraphPlus<String>("Sample-DAG.tgf");
    System.out.println("\nA sample directed graph with no cycles: Sample-DAG.tgf.");
    System.out.println(sampleDAG);
    System.out.println("A list of all of the sources in the graph: " + sampleDAG.allSources());
    System.out.println("A list of all of the sinks in the graph: " + sampleDAG.allSinks());
    System.out.println("Clone sampleDAG prior to executing a topological sort.");
    AdjMatGraphPlus<String> sampleDAGClone =  (AdjMatGraphPlus<String>)(sampleDAG.getClone());
    System.out.println("Print the clone:\n" + sampleDAGClone);
    System.out.println("A topological sort performed on sampleDAG's clone (algorithm that selects a sink): " + sampleDAGClone.topologicalSortAlt());
    AdjMatGraphPlus<String> sampleDAGClone2 =  (AdjMatGraphPlus<String>)(sampleDAG.getClone());
    System.out.println("A topological sort performed on sampleDAG's clone (algorithm that selects a source): " + sampleDAGClone2.topologicalSort());
    System.out.println("DFS starting from vertex A: " + sampleDAG.DFS("a"));
    System.out.println("BFS starting from vertex A: "  + sampleDAG.BFS("a"));

    AdjMatGraphPlus<String> containsCycle = new AdjMatGraphPlus<String>("Contains-Cycle.tgf");
    System.out.println("\nA sample graph that contains cycles: Contains-Cycle.tgf.");
    System.out.println("Graph containsCycle contains a cycle (TRUE): " + containsCycle.containsCycle());
    System.out.println("Attempt to execute topological sort on containsCycle: " + containsCycle.topologicalSort());
    System.out.println("Graph sampleDAG does not contain a cycle (FALSE): " + sampleDAG.containsCycle());
    System.out.println("DFS starting from non-existent vertex S: " + containsCycle.DFS("S"));
    System.out.println("BFS starting from non-existent vertex S: " + containsCycle.BFS("S"));
    System.out.println("DFS starting from vertex A: " + containsCycle.DFS("A"));

    AdjMatGraphPlus<String> testBFSDFS = new AdjMatGraphPlus<String>("DFS-BFS.tgf");
    System.out.println(testBFSDFS);
    System.out.println("DFS starting from vertex s: " + testBFSDFS.DFS("s"));
    System.out.println("BFS starting from vertex s: "  + testBFSDFS.BFS("s"));
    System.out.println("DFS starting from vertex a (sink): " + testBFSDFS.DFS("a"));
    System.out.println("BFS starting from vertex a (sink): "  + testBFSDFS.BFS("a"));
  }
}