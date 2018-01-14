///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TheGame.java
// File:             GraphADT.java
// Semester:         367 Fall 2015
//
// Author:           Zishuai Zou
// CS Login:         zou
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 2
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Hangyu Pi
// Email:            hpi2@wisc.edu
// CS Login:         hangyu
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 2
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.Set;

public interface GraphADT<V> {
	
	/** Adds the specified vertex to this graph if not already present. More 
	 * formally, adds the specified vertex v to this graph if this graph 
	 * contains no vertex u such that u.equals(v). If this graph already 
	 * contains such vertex, the call leaves this graph unchanged and returns false.
	 * @param vertex
	 * @return
	 */
   boolean addVertex(V vertex);
   
   /**
    * Creates a new edge from vertex v1 to v2 and returns true, 
    * if v1.equals(v2) evaluates to false and an edge does not already exist 
    * from v1 and v2. Returns false otherwise. 
	* Vertices v1 and v2 must already exist in this graph. If they are not found in 
	* the graph IllegalArgumentException is thrown.
    * @param v1 
    * @param v2
    * @return
    */
   boolean addEdge(V v1, V v2);
   
   /**If both v1 and v2 exist in the graph, and an edge exists from v1 to v2, 
    * remove the edge from this graph. Otherwise, do nothing.
    * 
    * @param v1
    * @param v2
    */
   void removeEdge(V v1, V v2);
   
   /**
    * Returns a set of all vertices to which v has outward edges. 
	* Vertex v must already exist in this graph. If it is not found in the graph 
	* IllegalArgumentException is thrown
    * @param vertex
    * @return
    */
   Set<V> getNeighbors(V vertex);
   
   /**
    * Returns a set of all the vertices in the graph.
    * @return
    */
   Set<V> getAllVertices();

}