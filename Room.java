///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TheGame.java
// File:             Room.java
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * The room describes where the player is located. 
 * The first room listed in the file is
 *  the starting "location" of the player.
 *
 * <p>Bugs: none
 *
 * @author Zishuai Zou
 */
public class Room {
	//name of the room
	private	String	name;
	//description of the room
	private	String	description;
	//whether the room is lit or dark
	private	boolean	visibility;
	//whether the room is habitable
	private	boolean habitability;
	//reason for room not being habitable (only relevant when habitability is false)
	private String	habitableMsg;
	//items in the room
	private	Set<Item>	items;
	// message handlers
	private	List<MessageHandler> handlers;
	//locked rooms and the reason for their being locked
	private HashMap<Room, String> lockedPassages;	
	//Do not add anymore data members
	
	public Room(String name, String description, boolean visibility, boolean habitability,
			String habMsg, Set<Item> items, List<MessageHandler> handlers){
		this.name = name;
		this.description = description;
		this.visibility = visibility;
		this.habitability = habitability;
		this.habitableMsg = habMsg;
		this.items = items;
		this.handlers = handlers;
		lockedPassages = new HashMap<Room,String>();
	}
	/**
	 * Getter method to get the Room's name
	 *
	 * @return String
	 */
	public String getName(){
    	//TODO Remove this exception and implement the method
		return this.name;
	}
	/**
	 * Returns whether the visibility in the room is true or false
	 *
	 * @return boolean;
	 */
	public boolean isVisible(){
    	//TODO Remove this exception and implement the method
		return this.visibility;
	}
	/**
	 * Returns true if the room is habitable, otherwise returns false
	 *
	 * @return boolean
	 */
	public boolean isHabitable(){
    	//TODO Remove this exception and implement the method
		return this.habitability;
	}
	/**
	 * If the room is not habitable, returns a String describing the 
	 * reason why it is not habitable. 
	 * If the room is habitable returns null.
	 *
	 * @return String
	 */
	public String getHabitableMsg(){
    	//TODO Remove this exception and implement the method
		return this.habitableMsg;
	}
	/**
	 * Returns the HashMap containing the lockedRooms and the corresponding 
	 * reasons why they are locked.
	 *
	 * @return HashMap<Room, String>
	 */
	public HashMap<Room, String> getLockedPassages(){
    	//TODO Remove this exception and implement the method
		return this.lockedPassages;
	}
	/**
	 * Adds a (RoomName, ReasonWhyLocked) pair to the list of locked 
	 * passages for a room.
	 *
	 * @param Room passage, String whyLocked
	 */
	public void addLockedPassage(Room passage, String whyLocked){
    	//TODO Remove this exception and implement the method
		if(passage==null||whyLocked==null) throw new IllegalArgumentException();
		this.lockedPassages.put(passage, whyLocked);
	}
	/**
	 * If it finds the Item "itemName" in the Room's items, it returns that
	 *  Item. Otherwise it returns null
	 *
	 * @param String item
	 * @return Item
	 */
	public Item findItem(String item){
    	//TODO Remove this exception and implement the method
		if(item==null) throw new IllegalArgumentException();
		for(Item i : this.items){
			if(i.getName().equals(item)) return i;
		}
		return null;
			
	}
	/**
	 * Adds an the "item" to the Room's items. Duplicate items are not 
	 * allowed. Java Sets do not allow duplicate items to be added. 
	 * Hence we use a Set to store the items in the Room. Returns 
	 * true if item is added, returns false otherwise
	 *
	 * @param Item item
	 * @return (boolean)
	 */
	public boolean addItem(Item item){
    	//TODO Remove this exception and implement the method
		if(item==null) throw new IllegalArgumentException();
		return this.items.add(item);
	}
	/**
	 * Removes the "item" from the Rooms Set of items. Returns true if
	 *  item was removed, false otherwise.
	 *
	 * @param Item item
	 * @return boolean
	 */
	public boolean removeItem(Item item){
    	//TODO Remove this exception and implement the method
		if(item==null) throw new IllegalArgumentException();
		return this.items.remove(item);
	}

	/***
	 * Receives messages from items used by the player and executes the 
	 * appropriate action stored in a message handler
	 * @param message is the "message" sent by the item
	 * @return null, this Room or Unlocked Room depending on MessageHandler
	 * DO NOT MODIFY THIS METHOD
	 */
	public Room receiveMessage(String message){
		Iterator<MessageHandler> itr = handlers.iterator();
		MessageHandler msg = null;
		while(itr.hasNext()){
			msg = itr.next();
			if(msg.getExpectedMessage().equalsIgnoreCase(message))
				break;
			msg = null;
		}
		if(msg == null)
			return null;
		switch(msg.getType()){
		case("visibility") :
			this.visibility = true;
			return this;
		case("habitability") :
			this.habitability = true;
			return this;
		case("room") :
			for(Room rm : this.lockedPassages.keySet()){
				if(rm.getName().equalsIgnoreCase(msg.getRoomName())){
					this.lockedPassages.remove(rm);
					return rm;
				}
			}
		default:
			return null;
		}
	}

	@Override
	//Returns a String consisting of the Rooms name, its description,
	//its items and locked rooms.
	// DO NOT MODIFY THIS METHOD
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Welcome to the " + name + "!");
		sb.append(System.getProperty("line.separator"));
		if(!this.visibility){
			sb.append("Its too dark to see a thing!");
			sb.append(System.getProperty("line.separator"));
			sb.append("Places that can be reached from here :");
			sb.append(System.getProperty("line.separator"));
			for(Room rm :this.lockedPassages.keySet()){
				sb.append(rm.getName());
				sb.append(System.getProperty("line.separator"));
			}
			return sb.toString();
		}
		sb.append(description);
		sb.append(System.getProperty("line.separator"));
		if(this.items.size() >0){ 
			sb.append("Some things that stand out from the rest :");
			sb.append(System.getProperty("line.separator"));
		}
		Iterator<Item> itr = this.items.iterator();
		while(itr.hasNext()){
			sb.append(itr.next().getName());
			sb.append(System.getProperty("line.separator"));
		}
		sb.append("Places that can be reached from here :");
		sb.append(System.getProperty("line.separator"));
		for(Room rm :this.lockedPassages.keySet()){
			sb.append(rm.getName());
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
}

