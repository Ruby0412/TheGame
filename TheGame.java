///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            TheGame
// Files:            DirectedGraph.java, Player.java, Item.java, Room.java, TheGame.java
// Semester:         367 Fall 2015
//
// Author:           Zishuai Zou
// Email:            zzou6@wisc.edu
// CS Login:         zou
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 2
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Hangyu Pi
// Email:            hpi2@wisc.edu
// CS Login:         hangyu
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 2
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          None
//                   
//
// Online sources:   None
//                    
//                   
//////////////////////////// 80 columns wide //////////////////////////////////
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
/**
 * the core engine that is responsible for reading the input
 *  file and processing player commands. 
 *  TheGame is initialized by passing the input file
 *
 * <p>Bugs: none
 *
 * @author Zishuai Zou
 */
public class TheGame {
	private static String gameIntro; // initial introduction to the game
	private static String winningMessage; //winning message of game
	private static String gameInfo; //additional game info
	private static boolean gameWon = false; //state of the game
	private static Scanner scanner = null; //for reading files
	private static Scanner ioscanner = null; //for reading standard input
	private static Player player; //object for player of the game
	private static Room location; //current room in which player is located
	private static Room winningRoom; //Room which player must reach to win
	private static Item winningItem; //Item which player must find
	private static DirectedGraph<Room> layout; //Graph structure of the Rooms

	public static void main(String[] args) {
		
		if (args.length != 1) {
			System.err.println("Bad invocation! Correct usage: "
					+ "java AppStore <gameFile>");
			System.exit(1);
		}

		boolean didInitialize = initializeGame(args[0]);
		

		if (!didInitialize) {
			System.err.println("Failed to initialize the application!");
			System.exit(1);
		}

		System.out.println(gameIntro); // game intro

		processUserCommands();
	}
	/**
	 * Reads the file named "gameFile" and initializes all variables for 
	 * the game to run smoothly. (Also allows the player to input his
	 *  name - This part has been done for you.)
	 *
	 * @param String gameFile
	 * @return boolean
	 */
	private static boolean initializeGame(String gameFile) {
		
		try {
			// reads player name
			System.out.println("Welcome worthy squire! What might be your name?");
			ioscanner = new Scanner(System.in);
			String playerName = ioscanner.nextLine();
			
			//TODO Remove this exception and implement the rest of this method 
			//	inside this try-catch block
			//  Please follow the steps outlined in the Specifications
			/*
			 * You will read the contents of the input file using Java File IO and
			 * then process them to initialize your player and layout (DirectedGraph)
			 */
			TheGame.layout= new DirectedGraph<Room>();
			TheGame.player=new Player(playerName, new HashSet<Item>());
			java.io.File file = new java.io.File(gameFile);
			TheGame.scanner= new Scanner(file);
			TheGame.gameIntro = TheGame.scanner.nextLine();
			TheGame.winningMessage = TheGame.scanner.nextLine();
			TheGame.gameInfo = TheGame.scanner.nextLine();
			ArrayList<String> context = new ArrayList<String>();
			while(TheGame.scanner.hasNextLine()){
				context.add(TheGame.scanner.nextLine());
			}
			Iterator<String> itr = context.iterator();
			

			String a = itr.next();
			
			
			// start initialize player
			if(a.contains("#player items:")){
				a=itr.next();
				while(a.contains("#item:")){
					boolean isWinI = false;
					if(a.contains("#win")&&isWinI)return false;
					if(a.contains("#win")) isWinI = true;
					String itemname = itr.next().trim();
					String itemdescription = itr.next().trim();
					a=itr.next();
					boolean activa = false;
					if(a.contains("true"))activa = true;
					String Imessage = itr.next().trim();
					a=itr.next();
					boolean oneTuse = false;
					if(a.contains("true")) oneTuse=true;
					String Pmessage = itr.next().trim();
					Item I = new Item(itemname,
							itemdescription,
							activa,
							Imessage,
							oneTuse,
							Pmessage);
					TheGame.player.addItem(I);
					if(isWinI)TheGame.winningItem=I;
					a=itr.next();
				}
			}
			else{
				return false;
			}
			
			
			
			
			// get follow ups
			// initialize rooms
			boolean startLoc = true;
			while(a.contains("#room:")){
				
				boolean isWinR = false;
				if(a.contains("#win")&&isWinR)return false;
				if(a.contains("#win")) isWinR = true;
				
			
				
				
				// get room name
				String roomName = itr.next().trim();
				// get room descrip
				String roomDiscrip = itr.next().trim();
				// get visibility
				a=itr.next();
				boolean visibi = false;
				if(a.contains("true")) visibi=true;
				// get habi
				a=itr.next();
				boolean habi = false;
				String whyNotHabi=null;
				if(a.contains("true")) habi=true;
				else  whyNotHabi = itr.next().trim();
				
				// items in room
				Set<Item> items= new HashSet<Item>();
				//messageHandlers in room
				List<MessageHandler> handlers = new ArrayList<MessageHandler>();
				
				
				a=itr.next();
				
				
				//get items
				while(a.contains("#item:")){
					boolean isWinI = false;
					if(a.contains("#win")&&isWinI)throw new IllegalArgumentException("No two winnig Item");
					if(a.contains("#win")) isWinI = true;
					String itemname = itr.next().trim();
					String itemdescription = itr.next().trim();
					a=itr.next();
					boolean activa = false;
					if(a.contains("true"))activa = true;
					String Imessage = itr.next().trim();
					a=itr.next();
					boolean oneTuse = false;
					if(a.contains("true")) oneTuse=true;
					String Pmessage = itr.next().trim();
					Item I = new Item(itemname,
							itemdescription,
							activa,
							Imessage,
							oneTuse,
							Pmessage);
					items.add(I);
					if(isWinI)TheGame.winningItem=I;
					
					a=itr.next();
				}
				
				// get msghandler
				while(a.contains("#messageHandler:")){
					MessageHandler MH;
					String firstline = itr.next().trim();
					String secondline = itr.next().trim();
					String thirdline = null;
					if(secondline.contains("room"))thirdline = itr.next().trim();

					MH = new MessageHandler(firstline,secondline,thirdline);
					handlers.add(MH);
					
					a= itr.next();
				}
				
				//create room
				Room r = new Room(roomName,roomDiscrip,visibi,habi,whyNotHabi,items,handlers);
				TheGame.layout.addVertex(r);
				if (isWinR)TheGame.winningRoom = r;
				
				if(startLoc) {
					TheGame.location = r;
					startLoc = false;
				}
	
			}
			
			
			
			// initialize locked passages
			if(a.contains("#locked passages:")){
				a=itr.next();
				while(!a.contains("#")){
					String roompairs = a;
					Scanner scnr = new Scanner(roompairs);
					String room1 = scnr.next();
					String room2 = scnr.next();
					String lockReason = itr.next();
					Room r1 =findroomByName(room1);
					Room r2 =findroomByName(room2);
					
					r1.addLockedPassage(r2, lockReason);
					a= itr.next();
					
				}
				
			}
			
			//initialize adjacency list
			if(a.contains("#Adjacency List:")){
				while(itr.hasNext()){
					String info = itr.next();
					Scanner scnr = new Scanner(info);
					Room source = findroomByName(scnr.next());
					while(scnr.hasNext()){
						layout.addEdge(source, findroomByName(scnr.next()));
					}
				}
			}
			// End of initialization
			
			return true;
		
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 
	 *
	 * @param (parameter name) (Describe the first parameter here)
	 * @param (parameter name) (Do the same for each additional parameter)
	 * @return (description of the return value)
	 */
	//helper method
	private static Room findroomByName(String name){
		for(Room r: TheGame.layout.getAllVertices() ){
			if(r.getName().equals(name)) return r;
		}
		return null;
	}
	


	private static void processUserCommands() {
		String command = null;
		do {

			System.out.print("\nPlease Enter a command ([H]elp):");
			command = ioscanner.next();
			switch (command.toLowerCase()) {
			case "p": // pick up
				processPickUp(ioscanner.nextLine().trim());
				goalStateReached();
				break;
			case "d": // put down item
				processPutDown(ioscanner.nextLine().trim());
				break;
			case "u": // use item
				processUse(ioscanner.nextLine().trim());
				break;
			case "lr":// look around
				processLookAround();
				break;
			case "lt":// look at
				processLookAt(ioscanner.nextLine().trim());
				break;
			case "ls":// look at sack
				System.out.println(player.printSack());
				break;
			case "g":// goto room
				processGoTo(ioscanner.nextLine().trim());
				goalStateReached();
				break;
			case "q":
				System.out.println("You Quit! You, " + player.getName() + ", are a loser!!");
				break;
			case "i":
				System.out.println(gameInfo);
				break;
			case "h":
				System.out
						.println("\nCommands are indicated in [], and may be followed by \n"+
							"any additional information which may be needed, indicated within <>.\n"
								+ "\t[p]  pick up item: <item name>\n"
								+ "\t[d]  put down item: <item name>\n"
								+ "\t[u]  use item: <item name>\n"
								+ "\t[lr] look around\n"
								+ "\t[lt] look at item: <item name>\n"
								+ "\t[ls] look in your magic sack\n"
								+ "\t[g]  go to: <destination name>\n"
								+ "\t[q]  quit\n"
								+ "\t[i]  game info\n");
				break;
			default:
				System.out.println("Unrecognized Command!");
				break;
			}
		} while (!command.equalsIgnoreCase("q") && !gameWon);
		ioscanner.close();
	}

	private static void processLookAround() {
		System.out.print(location.toString());
		for(Room rm : layout.getNeighbors(location)){
			System.out.println(rm.getName());
		}
	}

	private static void processLookAt(String item) {
		Item itm = player.findItem(item);
		if(itm == null){
			itm = location.findItem(item);
		}
		if(itm == null){
			System.out.println(item + " not found");
		}
		else
			System.out.println(itm.toString());
	}

	private static void processPickUp(String item) {
		if(player.findItem(item) != null){
			System.out.println(item + " already in sack");
			return;
		}
		Item newItem = location.findItem(item);
		if(newItem == null){
			System.out.println("Could not find " + item);
			return;
		}
		player.addItem(newItem);
		location.removeItem(newItem);
		System.out.println("You picked up ");
		System.out.println(newItem.toString());
	}
	
	private static void processPutDown(String item) {
		if(player.findItem(item) == null){
			System.out.println(item + " not in sack");
			return;
		}
		Item newItem = player.findItem(item);
		location.addItem(newItem);
		player.removeItem(newItem);
		System.out.println("You put down " + item);
	}

	private static void processUse(String item) {
		Item newItem = player.findItem(item);
		if(newItem == null){
			System.out.println("Your magic sack doesn't have a " + item);
			return;
		}
		if (newItem.activated()) {
			System.out.println(item + " already in use");
			return;
		}
		if(notifyRoom(newItem)){
			if (newItem.isOneTimeUse()) {
				player.removeItem(newItem);
			}
		}
	}

	private static void processGoTo(String destination) {
		Room dest = findRoomInNeighbours(destination);
		if(dest == null) {
			for(Room rm : location.getLockedPassages().keySet()){
				if(rm.getName().equalsIgnoreCase(destination)){
					System.out.println(location.getLockedPassages().get(rm));
					return;
				}
			}
			System.out.println("Cannot go to " + destination + " from here");
			return;
		}
		Room prevLoc = location;
		location = dest;
		if(!player.getActiveItems().isEmpty())
			System.out.println("The following items are active:");
		for(Item itm:player.getActiveItems()){
			notifyRoom(itm);
		}
		if(!dest.isHabitable()){
			System.out.println("Thou shall not pass because");
			System.out.println(dest.getHabitableMsg());
			location = prevLoc;
			return;
		}
		
		System.out.println();
		processLookAround();
	}

	private static boolean notifyRoom(Item item) {
		Room toUnlock = location.receiveMessage(item.on_use());
		if (toUnlock == null) {
			if(!item.activated())
				System.out.println("The " + item.getName() + " cannot be used here");
			return false;
		} else if (toUnlock == location) {
			System.out.println(item.getName() + ": " + item.on_useString());
			item.activate();
		} else {
			// add edge from location to to Unlock
			layout.addEdge(location, toUnlock);
			if(!item.activated())
				System.out.println(item.on_useString());
			item.activate();
		}
		return true;
	}

	private static Room findRoomInNeighbours(String room) {
		Set<Room> neighbours = layout.getNeighbors(location);
		for(Room rm : neighbours){
			if(rm.getName().equalsIgnoreCase(room)){
				return rm;
			}
		}
		return null;
	}

	private static void goalStateReached() {
		if ((location == winningRoom && player.hasItem(winningItem)) 
				|| (location == winningRoom && winningItem == null)){
			System.out.println("Congratulations, " + player.getName() + "!");
			System.out.println(winningMessage);
			System.out.println(gameInfo);
			gameWon = true;
		}
	}

}
