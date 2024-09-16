Project Description
Title: Java Client-Server Banking Application

Overview:
This project is a banking system simulation that operates over a client-server model. It uses Java socket programming to enable communication between clients and the server. The application allows users to register, log in, manage their accounts, perform transactions, and view their transaction history. It also includes functionalities for transferring funds between accounts and updating user information.



Key Components:

-ServerThread: Manages individual client connections and handles server-side communication.

-Requester: Acts as the client-side application to communicate with the server.

-Client: Represents a user account with attributes like name, email, password, and transaction history.

-Clients: Manages a collection of Client objects, including functionalities to add new clients and check login credentials.

-User, Provider, Users: Additional classes that likely manage specific user details, services, and collections of users or providers.


Application User Guide 

(Server Setup)

Start the server application.

The server listens for client connections and handles requests.

(Client Setup)

Launch the client application (Requester).

Connect to the server using the specified IP address and port.

sign the Application

(Registration and Login)

New users can register by providing their name, email, password, address, and PPS number.
Existing users can log in using their email, password, and PPS number.


Account Management:
Users can view their transaction history/Users can update their password/Balance inquiries and updates can be performed/

Transaction Handling:
Users can lodge money into their accounts/Money can be transferred to other users' accounts list of all registered users can be retrieved.


Logging Out and Exiting:
Users can log out of their account/The application can be exited using the designated option.


Ensure secure network conditions.

Notes
Is not intended for real-world financial transactions.
There is a lot of room for improvement in the code, like validation, etc.
