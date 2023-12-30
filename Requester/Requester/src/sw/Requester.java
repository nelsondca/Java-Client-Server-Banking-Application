package sw;

import java.io.*;
import java.net.*;
import java.util.Scanner;

//Handles client-side networking and communication with a server.
public class Requester {
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String userLogged;
	String message;
	String response;
	Scanner input;

	// Constructor: Initializes the Scanner for user input.
	Requester() {
		input = new Scanner(System.in);
	}

	// run method: Main logic for establishing and handling socket communication.
	void run() {
		try {
			//Creating a socket to connect to the server at localhost and the respective port.
			requestSocket = new Socket("127.0.0.1", 8000);
			System.out.println("Connected to localhost");

			//Setting up Input and Output streams for communication.
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());

			// Main communication loop with the server.
			try {
				do {
					// Receiving and processing the login status from the server.
					userLogged = (String) in.readObject();

					if (userLogged.equalsIgnoreCase("User Not Logged")) {
						message = (String) in.readObject();
						System.out.println(message);
						message = (String) in.readObject();
						System.out.println(message);
					}

					if (userLogged.equalsIgnoreCase("User Logged")) {
						message = (String) in.readObject();
						System.out.println(message);
						message = (String) in.readObject();
						System.out.println(message);
						message = (String) in.readObject();
						System.out.println(message);
						message = (String) in.readObject();
						System.out.println(message);
						message = (String) in.readObject();
						System.out.println(message);
						message = (String) in.readObject();
						System.out.println(message);
					}

					message = (String) in.readObject();
					System.out.println(message);

					message = (String) in.readObject();
					System.out.println(message);

					response = input.next();
					sendMessage(response);

					if (userLogged.equalsIgnoreCase("User Not Logged")) {
						if (response.equalsIgnoreCase("1")) {
							message = (String) in.readObject();
							System.out.println(message);
							response = input.next();
							sendMessage(response);

							message = (String) in.readObject();
							System.out.println(message);
							response = input.next();
							sendMessage(response);

							message = (String) in.readObject();
							System.out.println(message);
							response = input.next();
							sendMessage(response);

							message = (String) in.readObject();
							System.out.println(message);
							response = input.next();
							sendMessage(response);

							message = (String) in.readObject();
							System.out.println(message);
							response = input.next();
							sendMessage(response);

							message = (String) in.readObject();
							if (message.equalsIgnoreCase("Success")) {
								System.out.println("Registered successfully");
							} else {
								System.out.println("Already registered in the system");
							}
						} else if (response.equalsIgnoreCase("2")) {
							message = (String) in.readObject();
							System.out.println(message);
							response = input.next();
							sendMessage(response);

							message = (String) in.readObject();
							System.out.println(message);
							response = input.next();
							sendMessage(response);

							message = (String) in.readObject();
							System.out.println(message);
							response = input.next();
							sendMessage(response);

							message = (String) in.readObject();
							if (message.equalsIgnoreCase("Success")) {
								System.out.println("Log-In with success");
							} else {
								System.out.println("Wrong Credentials");
							}
						}
					}

					if (userLogged.equalsIgnoreCase("User Logged")) {
						if (response.equalsIgnoreCase("1")) {
							message = (String) in.readObject();
							System.out.println(message);

							response = input.next();
							sendMessage(response);

							message = (String) in.readObject();
							if (message.equalsIgnoreCase("Success")) {
								System.out.println("Balance Updated");
							} else {
								System.out.println("Incorrect amount");
							}
						} else if (response.equalsIgnoreCase("2")) {
							message = (String) in.readObject();
							System.out.println(message);

							message = (String) in.readObject();
							System.out.println(message);
						} else if (response.equalsIgnoreCase("3")) {
							message = (String) in.readObject();
							System.out.println(message);

							response = input.next();
							sendMessage(response);

							message = (String) in.readObject();
							System.out.println(message);

							response = input.next();
							sendMessage(response);

							message = (String) in.readObject();
							if (message.equalsIgnoreCase("Success")) {

								message = (String) in.readObject();
								System.out.println(message);

								response = input.next();
								sendMessage(response);

								message = (String) in.readObject();
								if (message.equalsIgnoreCase("Success")) {
									System.out.println("Funds transfered with success");
								} else {
									System.out.println("Error transfering funds");
								}
							} else {
								System.out.println("Wrong user");
							}
						} else if (response.equalsIgnoreCase("4")) {
							message = (String) in.readObject();
							System.out.println(message);
							message = (String) in.readObject();
							System.out.println(message);
						} else if (response.equalsIgnoreCase("5")) {
							message = (String) in.readObject();
							System.out.println(message);
							response = input.next();
							sendMessage(response);

							message = (String) in.readObject();
							if (message.equalsIgnoreCase("Success")) {
								System.out.println("Password changed");
							} else {
								System.out.println("Error changing password");
							}
						} else if (response.equalsIgnoreCase("6")) {

							message = (String) in.readObject();
							if (message.equalsIgnoreCase("Success")) {
								System.out.println("Logged out");
							} else {
								System.out.println("Error logging out");
							}
						}
					}

				} while (!message.equalsIgnoreCase("0")); //Loop until '0' is inputed.
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			//Closing all streams and the socket connection.
			try {
				in.close();
				out.close();
				requestSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	//Sends a message to the server.
	void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.println("Clientside - " + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	//Creates an instance of Requester and starts it.
	public static void main(String args[]) {
		Requester client = new Requester();
		client.run();
	}
}
