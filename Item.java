///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TheGame.java
// File:             Item.java
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
/**
 * players can interact (pick up, use, drop) with. Items, when used, 
 * do specific things to a room. They can light a dark room, or make
 *  a room habitable
 *   They can also open locked doors, or hidden pathways to new rooms.
 *
 * <p>Bugs: none
 *
 * @author Zishuai Zou
 */
public class Item {
	// name of item
	private String	name;
	// description of item
	private String	description;
	//TODO add remaining private data members
	private boolean activation;
	private String message;
	private boolean onetimeuse;
	private String usedstring;
	/**
	 * Constructs an Item Object. The parameters message and 
	 * on_useString are as explained above. 
	 * If activated is true, the item is active 
	 * and has been used. If oneTimeUse is true, 
	 * the item can be used only once. 
	 * Throw an IllegalArgumentException 
	 * if parameters are missing or invalid.
	 *
	 * @param String name, String description, boolean activated, 
			String message,boolean oneTimeUse, String usedString
	 * @return (Item)
	 */
	public Item(String name, String description, boolean activated, 
			String message,boolean oneTimeUse, String usedString){
    	//TODO Remove this exception and implement the method
    	this.name=name;
    	this.description=description;
    	this.activation=activated;
    	this.message=message;
    	this.onetimeuse=oneTimeUse;
    	this.usedstring=usedString;
    	
	}
	/**
	 * Getter method for the Name of the Item.
	 *
	 * @return (String)
	 */
	public String getName(){
    	//TODO Remove this exception and implement the method
		return this.name;
	}
	/**
	 * Getter method for the Description of the Item
	 *
	 * @return (String)
	 */
	public String getDescription(){
    	//TODO Remove this exception and implement the method
		return this.description;
	}
	/**
	 * Checks if the item is activated. 
	 * Returns true if it is,
	 *  else return false.
	 *
	 * @return (boolean)
	 */
	public boolean activated(){
    	//TODO Remove this exception and implement the method
		return this.activation;
	}
	/**
	 * Returns the "message" that the item wants to send to the room. This 
	 * is used in the notifyRoom() function in TheGame class
	 *
	 * @return String
	 */
	public String on_use(){
    	//TODO Remove this exception and implement the method
		return this.message;
	}
	/**
	 * Activates the object. 
	 * This changes the activation status to true.
	 *
	 * @return 
	 */
	public void activate(){
    	//TODO Remove this exception and implement the method
		this.activation=true;
	}
	/**
	 * Returns the "on_useString" for the Item. This is printed in the 
	 * notifyRoom() function in TheGame class after an item has
	 *  been used and is active.
	 *
	 * @return (String)
	 */
	public String on_useString(){
    	//TODO Remove this exception and implement the method
		return this.usedstring;
	}
	/**
	 * Returns true if the item can only be used once. Else returns false. 
	 * This is used in TheGame to remove single-time use items 
	 * after they are used.
	 *
	 * @return boolean
	 */
	public boolean isOneTimeUse(){
    	//TODO Remove this exception and implement the method
		return this.onetimeuse;
	}
	
	@Override
	//This returns a String consisting of the name and description of the Item
	//This has been done for you.
	//DO NOT MODIFY
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Item Name: " + this.name);
		sb.append(System.getProperty("line.separator"));
		sb.append("Description: " + this.description);
		return sb.toString();
	}
}
