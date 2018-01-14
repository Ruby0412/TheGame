
//DO NOT MODIFY THIS CLASS
//This class and its methods have been implemented for you
public class MessageHandler {
	private String expectedMessage;
	private String type; //visibility, habitability, or room
	private String roomName; //room name
	
	public MessageHandler(String msg, String type, String roomName){

		//check for illegal arguments and throw an Illegal Argument Exception
		if(msg == null || type == null )
			throw new IllegalArgumentException();
		if(!type.equals("visibility" )&& 
				!type.equals("habitability") && !type.equals("room"))
			throw new IllegalArgumentException();
		if(type.equals("room") && roomName == null)
			throw new IllegalArgumentException();
		
		//Assign class variables the values
		this.expectedMessage = msg;
		this.type = type;
		this.roomName = roomName;
	}
	
	//Getter methods
	public String getExpectedMessage(){
		return expectedMessage;
	}
	
	public String getType(){
		return type;
	}
	
	public String getRoomName(){
		return roomName;
	}
}
