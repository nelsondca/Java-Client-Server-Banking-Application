package sw;
import java.net.Socket;
import java.io.*;

//Class ServerThread: Handles server-side communication for each connected client.
public class ServerThread extends Thread{

	Socket myConnection;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	String message1;
	String message2;
	String message3;
	Clients users;
	Client user;
	 // Constructor: Initializes the thread with a client socket and a reference to the users list.
	public ServerThread(Socket socket, Clients users)
	{
		myConnection = socket;
		this.users = users;
	}
	 // Overridden run method: Main logic for client-server communication.
    public void run() {
	
	{
		try
		{
		
			// Setting up output and input streams for communication.
			out = new ObjectOutputStream(myConnection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(myConnection.getInputStream());
		
			//Server Communication
			do
			{	// Check if the user is logged in.
				boolean userLogged = (user != null ? true : false);
				 // Send initial communication messages based on login status.
				sendMessage(userLogged ? "User Logged" : "User Not Logged");

                // Provide options based on user login status.
				if(!userLogged) {
					sendMessage("Option 1: Register System");
					sendMessage("Option 2: Log-in to the online banking system");
				}
					
					
				if(userLogged) {
					sendMessage("Option 1: Lodge money to your account");
					sendMessage("Option 2: Retrieve all registered users listing");
					sendMessage("Option 3: Transfer money to another account");
					sendMessage("Option 4: View all transaction on your bank account");
					sendMessage("Option 5: Update your password");
					sendMessage("Option 6: Log-out");	
				}
					
				sendMessage("Option 0: Exit");
				
				sendMessage("Select your option:");
				message = (String)in.readObject();
			
				if(!userLogged) {
					if(message.equalsIgnoreCase("1")) {
						sendMessage("Enter your Name:");
						String name = (String)in.readObject();
						sendMessage("Enter your email:");
						String email = (String)in.readObject();
						sendMessage("Enter your password:");
						String password = (String)in.readObject();
						sendMessage("Enter your address:");
						String address = (String)in.readObject();
						sendMessage("Enter your PPS:");
						String pss = (String)in.readObject();
						
						String isAdded = users.addUser(name, pss, email, password, address);
						sendMessage(isAdded);	
	
					}else if(message.equalsIgnoreCase("2")) {
						sendMessage("Enter your Email:");
						String email = (String)in.readObject();
						sendMessage("Enter your PPS Number:");
						String PPSNumber = (String)in.readObject();
						sendMessage("Enter your password:");
						String pass = (String)in.readObject();
						
						user = users.checkLogin(email, pass, PPSNumber);
						sendMessage(user != null ? "Success" : "Error");
					}
				}
				
				if(userLogged) {			
					if(message.equalsIgnoreCase("1")) {
						sendMessage("Enter the money:");
						double money = Double.parseDouble((String)in.readObject());
						boolean isUpdated = user.setBalance(money , false, users);
						sendMessage(isUpdated ? "Success" : "Error");
					}
					else if(message.equalsIgnoreCase("2")) {
						sendMessage("List of users registered");
						sendMessage(users.toString());
					}
					else if(message.equalsIgnoreCase("3")) {
						sendMessage("Enter Email:");
						String emailUser = (String)in.readObject();
						sendMessage("Enter PPS Number:");
						String ppsNumberUser = (String)in.readObject();
						Client u = users.checkUser(user, emailUser, ppsNumberUser);
						if(u != null) {
							sendMessage("Success");
							sendMessage("Enter the amount:");
							double moneySend = Double.parseDouble((String)in.readObject());
							boolean isUpdatedOwn = user.setBalance(-moneySend , true, users);
							boolean isUpdated = u.setBalance(moneySend , false, users);
							sendMessage(isUpdatedOwn && isUpdated ? "Success" : "Error");
						}else {
							sendMessage("Error");
						}
					}
					else if(message.equalsIgnoreCase("4")) {
						sendMessage("Your Transactions:");
						sendMessage(user.getTransacctions());
					}
					
					else if(message.equalsIgnoreCase("5")) {
						sendMessage("Enter your new password: ");
						String password = (String)in.readObject();
						boolean isUpdated = user.setPassword(password, users);
						sendMessage(isUpdated ? "Success" : "Error");
					}
					
					else if(message.equalsIgnoreCase("6")) {
						user = null;
						sendMessage(user == null ? "Success" : "Error");
					}
				}
				
			}while(!message.equalsIgnoreCase("0"));
			sendMessage("User Disconnected");
			// Closing communication streams.
			in.close();
			out.close();
		}
		catch(ClassNotFoundException classnot)
		{
					System.err.println("Data received in unknown format");
		}
		catch(IOException e)
		{
			
		}
	}
		
		
	}
	// sendMessage method: Sends a message to the client.
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("Serverside - " + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
}
