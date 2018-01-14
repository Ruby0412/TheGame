///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TheGame.java
// File:             DirectedGraph.java
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
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/**
 * implements GraphADT with an adjacency lists representation. 
 * The graph has the following properties, as enforced by the addEdge() method
 *
 * <p>Bugs: none
 *
 * @author Zishuai Zou
 */
public class DirectedGraph<V> implements GraphADT<V>{
	private HashMap<V, ArrayList<V>> hashmap;
    //DO NOT ADD ANY OTHER DATA MEMBERS
	/**
	 * Creates an empty graph.
	 *
	 * @param (parameter name) (Describe the first parameter here)
	 * @param (parameter name) (Do the same for each additional parameter)
	 * @return (description of the return value)
	 */
    public DirectedGraph() {
    	//TODO Remove this exception and implement the method
    	
    	this.hashmap = new HashMap<V, ArrayList<V>>();
    }
    /**
	 * Creates a graph from a preconstructed hashmap. 
	 * This method will be used for testing your submissions.
	 *
	 * @param (HashMap<V, ArrayList<V>>) 
	 * @return (DirectedGraph)
	 */
    public DirectedGraph(HashMap<V, ArrayList<V>> hashmap) {
    	//TODO Remove this exception and implement the method
    	if(hashmap==null) throw new IllegalArgumentException();
		this.hashmap = hashmap;
    }
    /**
	 * Adds the specified vertex to this graph if not already
	 *  present. More formally, adds the specified vertex v
	 *   to this graph if this graph contains no vertex u such
	 *    that u.equals(v). If this graph already contains such 
	 *    vertex, the call leaves this graph unchanged
	 *     and returns false.
	 *
	 * @param V vertex
	 * @return (boolean)
	 */
    @Override
    public boolean addVertex(V vertex) {
		//TODO Remove this exception and implement the method
    	if(vertex==null) throw new IllegalArgumentException();
    	if(this.hashmap.containsKey(vertex)){
    		return false;
    	}
    	this.hashmap.put(vertex, new ArrayList<V>());
    	return true;
    }
    /**
	 * Creates a new edge from vertex v1
	 *  to v2 and returns true, if v1 and
	 *   v2 are not the same vertex and an
	 *    edge does not already exist from
	 *     v1 to v2. Returns false otherwise.
	 *     Vertices v1 and v2 must already 
	 *     exist in this graph. If they are 
	 *     not found in the graph IllegalArgumen
	 *     tException is thrown.
	 *
	 * @param V v1
	 * @param V v2
	 * @return boolean
	 */
    @Override
    public boolean addEdge(V v1, V v2) {
		//TODO Remove this exception and implement the method
    	if(v1==null||v2==null) throw new IllegalArgumentException();
    	if(v1.equals(v2)||this.hashmap.get(v1).contains(v2)){
    		return false;
    	}
    	return this.hashmap.get(v1).add(v2);
    	
		
    }
    /**
	 * Returns a set of all vertices to which there are outward edges from v.
	 * Vertex v must already exist in this graph. 
	 * If it is not found in the graph IllegalArgumentException
	 *  is thrown.
	 *
	 * @param V vertex
	 * @return Set<V>
	 */
    @Override
    public Set<V> getNeighbors(V vertex) {
		//TODO Remove this exception and implement the method
    	if(vertex==null) throw new IllegalArgumentException();
		return new HashSet<V>(this.hashmap.get(vertex));
    }
    /**
	 * If both v1 and v2 exist in the graph,
	 *  and an edge exists from v1 to v2,
	 *   remove the edge from this graph. 
	 *   Otherwise, do nothing.
	 *
	 * @param (parameter name) (Describe the first parameter here)
	 * @param (parameter name) (Do the same for each additional parameter)
	 * @return (description of the return value)
	 */
    @Override
    public void removeEdge(V v1, V v2) {
		//TODO Remove this exception and implement the method
    	if(v1==null||v2==null) throw new IllegalArgumentException();
		if(!this.hashmap.containsKey(v1)||
				!this.hashmap.containsKey(v2)||
				!this.hashmap.get(v1).contains(v2)){
			// otherwise do nothing
		}
		this.hashmap.get(v1).remove(v2);
    }
    /**
	 * Returns a set of all the vertices in the graph.
	 *
	 * @return (Set<V>)
	 */
    @Override
    public Set<V> getAllVertices() {
		//TODO Remove this exception and implement the method
		return this.hashmap.keySet();
    }
    /**
	 * 
	 *
	 * @param (parameter name) (Describe the first parameter here)
	 * @param (parameter name) (Do the same for each additional parameter)
	 * @return (description of the return value)
	 */
    @Override
    //Returns a String that depicts the Structure of the Graph
    //This prints the adjacency list
    //This has been done for you
    //DO NOT MODIFY
    public String toString() {
        StringWriter writer = new StringWriter();
        for (V vertex: this.hashmap.keySet()) {
            writer.append(vertex + " -> " + hashmap.get(vertex) + "\n");
        }
        return writer.toString();
    }

}
