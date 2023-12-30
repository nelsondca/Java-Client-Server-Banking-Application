package sw;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Represents a client with personal and transaction details.
public class Client implements Serializable {
	//Client variables
    private String name;
    private String pps;
    private String email;
    private String password;
    private String address;
    private double balance;
    private List<String> transacctions;

    // Constructor initializes a client with the provided details.
    public Client(String name, String pps, String email, String password, String address) {
        this.name = name;
        this.pps = pps;
        this.email = email;
        this.password = password;
        this.address = address;
        balance = 0;
        transacctions = new ArrayList<String>();
    }

    // Default constructor.
    public Client() {
    }

    // Getter and setter methods for client .
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPPS() {
        return pps;
    }

    public void setPPS(String pps) {
        this.pps = pps;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    //Updates the password and saves user data.
    public boolean setPassword(String password, Clients users) {
        boolean result = false;
        if(password.length() > 0) {
            this.password = password;
            result = true;
            
            saveUsersToFile(users);
        }
        
        return result;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    //Updates the balance and records the transaction.
    public boolean setBalance(double balance, boolean isNegative, Clients users) {
        boolean result = false;
        double newBalance = getBalance() + balance;
        
        if(((balance > 0.0 && !isNegative) || (balance < 0.0 && isNegative)) && newBalance >= 0) {
            this.balance = newBalance;
            
            setTransacctions("ID: " + this.pps + " Deposit " + balance +"€" + " Current balance " + this.balance+"€");
            saveUsersToFile(users);
            result = true;    
        }
        return result;
    }

    //Returns a string of all transactions.
    public String getTransacctions() {
        String result = "";
        for (String transacction : transacctions) {
            result += transacction+"\n";
        }
        return result;
    }

    //Adds a transaction and saves it to file.
    public void setTransacctions(String transacction) {
        transacctions.add(transacction);
        saveTransactionsToFile();
        
    }
    
    // Saves transactions to a file.
    private void saveTransactionsToFile() {
        try (ObjectOutputStream transactionStream = new ObjectOutputStream(new FileOutputStream("TransactionData.txt"))) {
            transactionStream.writeObject(transacctions);
        } catch (IOException ioException) {
            System.err.println("Error saving transactions: " + ioException.getMessage());
            ioException.printStackTrace();
        }
    }

    // Saves user data to a file.
    private void saveUsersToFile(Clients clientData) {
        try (ObjectOutputStream userStream = new ObjectOutputStream(new FileOutputStream("UserData.txt"))) {
            userStream.writeObject(clientData);
        } catch (IOException ioException) {
            System.err.println("Error saving user data: " + ioException.getMessage());
            ioException.printStackTrace();
        }
    }

    //Compares clients based on email and PPS.
    @Override
    public boolean equals(Object obj) {
        Client user = (Client)obj;
        return this.getEmail().equalsIgnoreCase(user.getEmail()) || this.getPPS().equalsIgnoreCase(user.getPPS());
    }

    //Provides a string representation of the client.
    @Override
    public String toString() {
        return name + "  ID: " + pps + " Email: " + email;
    }
}
