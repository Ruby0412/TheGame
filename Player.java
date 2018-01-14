///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TheGame.java
// File:             Player.java
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
import java.util.HashSet;
import java.util.Set;

/**
 * 
 *
 * <p>Bugs: none
 *
 * @author Zishuai Zou
 */
public class Player {
	// player name
	private String name;
	// the magic sack held by the player that contains all his/her items
	private Set<Item> magicSack;
	//Do not add anymore private data members
	/**
	 *  Each Player needs a name, and a magic sack to store all his items.
	 *   Once a player picks up an item, it goes in the sack, 
	 *   and he can use it whenever he wants.
	 *
	 * @param String name, Set<Item> startingItems
	 * @return (Player)
	 */
	public Player(String name, Set<Item> startingItems){
		this.name = name;
		this.magicSack = startingItems;
	}
	/**
	 * Getter method for the Name of the player
	 *
	 * @return (String)
	 */
	public String getName(){
    	//TODO Remove this exception and implement the method
		return this.name;

	}
	
	//Returns a String consisting of the items in the sack
	//DO NOT MODIFY THIS METHOD
	public String printSack(){
		//neatly printed items in sack
		StringBuilder sb = new StringBuilder();
		sb.append("Scanning contents of your magic sack");
		sb.append(System.getProperty("line.separator"));
		for(Item itm : magicSack){
			sb.append(itm.getName());
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
	/**
	 * Iterate through the sack, and find the items whose status is 
	 * activated. This is used in TheGame class when a user enters 
	 * a new room, so that all active items work in the new room.
	 *
	 * @return (Set<Item>)
	 */
	public Set<Item> getActiveItems(){
    	//TODO Remove this exception and implement the method
		Set<Item> result = new HashSet<Item>();
		for(Item i :this.magicSack){
			if(i.activated()) result.add(i);
		}
		return result;
	}
	/**
	 * Find the Item in the sack whose name is "itemName". Return the item
	 *  if you find it, otherwise return null.
	 *
	 * @param String item
	 * @return Item
	 */
	public Item findItem(String item){
    	//TODO Remove this exception and implement the method
		for(Item i :this.magicSack){
			if(i.getName().equals(item)) return i;
		}
		return null;

	}
	/**
	 * Checks if the player has the "item" in his sack. Returns true if he 
	 * does, otherwise returns false.
	 *
	 * @param (Item item)
	 * @return (boolean)
	 */
	public boolean hasItem(Item item){
    	//TODO Remove this exception and implement the method
		if(item==null) throw new IllegalArgumentException();
		if(this.magicSack.contains(item))return true;
		return false;
	}
	/**
	 * Adds the "item" to the Player's sack. Duplicate items are not
	 *  allowed. (Read the bullet above to see how to handle this.)
	 *   Returns true if item successfully added, else returns false.
	 *
	 * @param (Item item)
	 * @return boolean
	 */
	public boolean addItem(Item item){
    	//TODO Remove this exception and implement the method
		if(item==null) throw new IllegalArgumentException(); 
		return this.magicSack.add(item);
	}
	/**
	 * Removes the "item" from the sack. Returns true if removal 
	 * is successful, else returns false.
	 *
	 * @param (Item item) (Describe the first parameter here)
	 * @return (boolean)
	 */
	public boolean removeItem(Item item){
    	//TODO Remove this exception and implement the method
		if(item==null) throw new IllegalArgumentException();
		return this.magicSack.remove(item);

	}
}
