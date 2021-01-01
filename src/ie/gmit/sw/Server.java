package ie.gmit.sw;

//importing the neccesary from java util and scanners etc
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

//main class server that extends thread
///thread allow it to be multithreaded
public class Server extends Thread {
	// declaring variables
	private Socket conn;
	private int id;
	ObjectOutputStream out;
	ObjectInputStream in;

	String storedUser = "default";
	String storedPass = "default";
	String regid = "default";
	String option;
	String response;
	String message;
//declaring the arraylists that are gonna be used throughout
	ArrayList<Users> myUsers = new ArrayList<Users>();
	ArrayList<Fleet> myFleet = new ArrayList<Fleet>();
	ArrayList<Sales> mySales = new ArrayList<Sales>();
//declaring more variables
	String storedName = "default";
	String storedAge = "default";
	String storedID = "default";

	String storedClubID = "default";
	String storedVendor = "default";
	String storedValuation = "default";

	String storedLastKM = "default";
	String storedLastDate = "default";
	String storedNextKM = "default";
	String storedCurrentKm = "default";

	String storedServiceAgent = "default";
	String storedkmMachine = "default";
	String storedserverDesc = "default";

	String salesName = "default";
	String salesID = "default";
//boolean to see if the user should be granted access
	// set to false so that credentials can be checked
	boolean found = false;

//server method
	// allows a connection
	public Server(Socket c, int i, ArrayList<Users> u, ArrayList<Fleet> m, ArrayList<Sales> s) {
		conn = c;
		id = i;
	}

//run method
	// all the code is mainly done in here
	public void run() {
		try {
			out = new ObjectOutputStream(conn.getOutputStream());
			in = new ObjectInputStream(conn.getInputStream());

			// Server Conversation
			// do while statement
			do {
				message = (String) in.readObject();
				// message to the user asking them to select an option
				out.writeObject("Press 1 for Login or 2 for Register");
				out.flush();

				option = (String) in.readObject();
				// if option = 1 do this
				if (option.equalsIgnoreCase("1")) {
					// read from the file for valid users
					readIn();
					// output message to the user giving instruction
					out.writeObject("Please enter the username and password");
					out.flush();
					// store the information for username in stored user
					storedUser = (String) in.readObject();
					// store the information for pass in storedPass
					storedPass = (String) in.readObject();
					// loop through the users arraylist
					for (int i = 0; i < myUsers.size(); i++) {
						// if the credentials match, found = true
						if (storedUser.equalsIgnoreCase(myUsers.get(i).username)
								&& storedPass.equalsIgnoreCase(myUsers.get(i).password)) {
							found = true;
							break;
						}
						// else it is still false
						else {
							found = false;
						}
					}
					// if its found then allow the user to login and proceed to the application
					if (found == true) {
						response = "OK";

						out.writeObject("OK");
						out.flush();
						// else they may try to login again
					} else if (found == false) {
						response = "FAIL";
						out.writeObject("FAIL");
						out.flush();
					}
				} // if the option =2
				else if (option.equalsIgnoreCase("2")) {
					// this is used for writing to the file
					FileWriter fr = new FileWriter("UsersFile", true);
					BufferedWriter br = new BufferedWriter(fr);
					// Register
					out.writeObject("Please enter the username, password and ID");
					out.flush();

					storedUser = (String) in.readObject();
					storedPass = (String) in.readObject();
					regid = (String) in.readObject();
					// store the new user in the arraylist
					Users newUsers = new Users(storedUser, storedPass);
					// write to the file
					br.write("\n" + storedUser + " " + storedPass);
					myUsers.add(newUsers);

					response = "OK";

					out.writeObject("OK");
					out.flush();
					// close the bufferedreader and filewriter
					br.close();
					fr.close();

				}

			} while (option.equalsIgnoreCase("2") || response.equalsIgnoreCase("FAIL"));
			// here the user is logged into the menu
			do {

				// Logged in menu
				out.writeObject("\n1: Add to fleet" + "\n2: Search Fleet" + "\n3: Update KM on machinery"
						+ "\n4: Remove an Item from fleet" + "\n5: Register a Fleet Item for sale"
						+ "\n6: Search specific Fleet item for sale" + "\n7: Search all items for sale"
						+ "\n8: Update the Service Records");
				out.flush();
				option = (String) in.readObject();
				// if else statements to allow the user to carry out the different functions
				if (option.equalsIgnoreCase("1")) {
					// calling the function
					add();

				} else if (option.equalsIgnoreCase("2")) {
					// calling the function
					search();
				} else if (option.equalsIgnoreCase("3")) {
					// calling the function
					update();

				} else if (option.equalsIgnoreCase("4")) {
					// calling the function
					delete();

				} else if (option.equalsIgnoreCase("5")) {
					// calling the function
					registerSales();

				} else if (option.equalsIgnoreCase("6")) {
					// calling the function
					// search fleet item for sale
					searchSales();
				} else if (option.equalsIgnoreCase("7")) {
					// calling the function
					searchAllSales();
				} else if (option.equalsIgnoreCase("8")) {
					// calling the function
					updateService();
				}
				// repeat the menu after completing a function
				out.writeObject("Press 1 to repeat or 2 to exit");
				out.flush();

				option = (String) in.readObject();

			} // close the program when the user does not enter 1
			while (option.equalsIgnoreCase("1"));
		}

		catch (Exception e) {

		}
	}

	// add a new machine to the fleet
	public void add() throws IOException, ClassNotFoundException {
		// open the necassary for writing to the file
		FileWriter fr = new FileWriter("FleetFile", true);
		BufferedWriter br = new BufferedWriter(fr);
		// add a new machine provided the machine is not already added
		boolean isthere = false;
		out.writeObject("Please enter the machinery information:");
		out.flush();

		int fleetsize = myFleet.size() + 1;
		// read in all the details from the user input
		storedName = (String) in.readObject();
		storedAge = (String) in.readObject();
		storedID = (String) in.readObject();

		storedClubID = (String) in.readObject();
		storedVendor = (String) in.readObject();
		storedValuation = (String) in.readObject();

		storedLastKM = (String) in.readObject();
		storedLastDate = (String) in.readObject();
		storedNextKM = (String) in.readObject();
		storedCurrentKm = (String) in.readObject();
		// check against the current arraylist to see if machine is eligible to be added
		for (int i = 0; i < myFleet.size(); i++) {
			if (storedID.equalsIgnoreCase(myFleet.get(i).Id)) {
				isthere = true;
			}

			else {
				isthere = false;

			}
			// add the new machine to the arraylist
		}
		if (isthere == false) {
			Fleet newFleet = new Fleet(storedName, storedAge, storedID, storedClubID, storedVendor, storedValuation,
					storedLastKM, storedLastDate, storedNextKM, storedCurrentKm, storedServiceAgent, storedkmMachine,
					storedserverDesc);

			myFleet.add(newFleet);
			// write to a file
			br.write("\n" + storedName + " " + storedAge + " " + storedID + " " + storedClubID + " " + storedVendor
					+ " " + storedValuation + " " + storedLastKM + " " + storedLastDate + " " + storedNextKM + " "
					+ storedCurrentKm);
			// write a message to the user with all the details
			out.writeObject("" + storedName + " " + storedAge + " " + storedID + " " + storedClubID + " " + storedVendor
					+ " " + storedValuation + " " + storedLastKM + " " + storedLastDate + " " + storedNextKM + " "
					+ storedCurrentKm);
			out.flush();
			// close the file writing tools
			br.close();
			fr.close();
		}
	}

	// update the machinery based on details in the brief
	public void update() throws IOException, ClassNotFoundException {
		// assigning variables
		String newlastKM;
		String newNextKM;
		String currentKM;
		FileWriter fr = new FileWriter("FleetFile", true);
		BufferedWriter br = new BufferedWriter(fr);
		out.writeObject("\n1: Update Last Service in KM" + "\n2: Update Next Service in KM" + "\n3: Update Current KM");
		out.flush();
		String choice = (String) in.readObject();

		out.writeObject("Please enter the ID to update");
		out.flush();
		// num enter
		String num1 = (String) in.readObject();
		// if else statements
		// carries out different updates based on the user input
		// loop through and find the id then update
		if (choice.equalsIgnoreCase("1")) {
			for (int i = 0; i < myFleet.size(); i++) {
				if (myFleet.get(i).Id.equals(num1)) {
					out.writeObject("Enter Last Service in KM");
					out.flush();
					newlastKM = (String) in.readObject();
					myFleet.get(i).lastKM = newlastKM;
					out.writeObject("Item updated");
					out.flush();
				}
			}
			out.writeObject("Item updated");
			out.flush();
		} else if (choice.equalsIgnoreCase("2")) {
			for (int i = 0; i < myFleet.size(); i++) {
				if (myFleet.get(i).Id.equals(num1)) {
					out.writeObject("Enter Next Service in KM");
					out.flush();
					newNextKM = (String) in.readObject();
					myFleet.get(i).nextKM = newNextKM;
					out.writeObject("Item updated");
					out.flush();
				}
			}
			out.writeObject("name updated");
			out.flush();
		} else if (choice.equalsIgnoreCase("3")) {
			for (int i = 0; i < myFleet.size(); i++) {
				if (myFleet.get(i).Id.equals(num1)) {
					out.writeObject("Enter Current KM");
					out.flush();
					currentKM = (String) in.readObject();
					myFleet.get(i).currentKM = currentKM;
					out.writeObject("Item updated");
					out.flush();
				}
				// write the new line to the file
				br.write("\n" + storedName + " " + storedAge + " " + storedID + " " + storedClubID + " " + storedVendor
						+ " " + storedValuation + " " + myFleet.get(i).lastKM + " " + storedLastDate + " "
						+ myFleet.get(i).nextKM + " " + myFleet.get(i).currentKM);
			}
			// message to the user
			out.writeObject("name updated");
			out.flush();
			// close the file writers
			br.close();
			fr.close();
		}
	}

	// update other parts of array, originally set to null

	// NOTE**
	// i wasnt too sure on the brief if this was the correct way you wanted it done
	// or if
	// it was to update other bits
	// i went with this way to show that you can add to the array
	public void updateService() throws ClassNotFoundException, IOException {
		FileWriter fr = new FileWriter("FleetFile", true);
		BufferedWriter br = new BufferedWriter(fr);
		out.writeObject("Please enter the ID to update");
		out.flush();
		// num enter
		// Fleet newFleet = new Fleet();
		String num1 = (String) in.readObject();
		// loop throught the array and find the relevant machine
		for (int i = 0; i < myFleet.size(); i++) {
			if (myFleet.get(i).Id.equals(num1)) {
				out.writeObject("Please enter:" + "\n1: the service agent" + "\n2: the stored km on machine"
						+ "\n3: the server description");
				out.flush();
				// assign new values to the ones set to default originally
				storedServiceAgent = (String) in.readObject();
				storedkmMachine = (String) in.readObject();
				storedserverDesc = (String) in.readObject();

				myFleet.get(i).serviceAgent = storedServiceAgent;
				myFleet.get(i).kmMachine = storedkmMachine;
				myFleet.get(i).serverDesc = storedserverDesc;

				out.writeObject("Item updated");
				out.flush();
				// write tot the file and close the filewriters
				br.write("\n" + myFleet.get(i).name + " " + myFleet.get(i).age + " " + myFleet.get(i).Id + " "
						+ myFleet.get(i).clubID + " " + myFleet.get(i).Vendor + " " + myFleet.get(i).Valuation + " "
						+ myFleet.get(i).lastKM + " " + myFleet.get(i).lastDate + " " + myFleet.get(i).nextKM + " "
						+ myFleet.get(i).currentKM + " " + myFleet.get(i).serviceAgent + " " + myFleet.get(i).kmMachine
						+ " " + myFleet.get(i).serverDesc);

				br.close();
				fr.close();

			}

		}
		// output a message to the user
		out.writeObject("Item updated");
		out.flush();

	}

	// delete fromj the arraylist
	public void delete() throws IOException, ClassNotFoundException {
		// message
		out.writeObject("Please enter the ID to delete");
		out.flush();
		// input
		String num1 = (String) in.readObject();

		// for loop and if statement to find and delete a machine
		for (int i = 0; i < myFleet.size(); i++) {
			if (myFleet.get(i).Id.equals(num1)) {
				out.writeObject("Name of machine to Delete: " + myFleet.get(i).name);
				out.flush();
				// remove from the arraylist
				storedName = (String) in.readObject();
				myFleet.remove(i);
			}

		}
		// message to user
		out.writeObject("Deleted machine");
		out.flush();
	}

	// search for a machine
	public void search() throws IOException, ClassNotFoundException {
		out.writeObject("Please enter the ID to search for");
		out.flush();

		String num1 = (String) in.readObject();

		response = "OK";
		for (int i = 0; i < myFleet.size(); i++) {
			if (myFleet.get(i).Id.equals(num1)) {
				out.writeObject("ID of machine: " + myFleet.get(i).Id + " " + myFleet.get(i).name + " "
						+ myFleet.get(i).age + " " + myFleet.get(i).clubID + " " + myFleet.get(i).Vendor + " "
						+ myFleet.get(i).Valuation + " " + myFleet.get(i).lastKM + " " + myFleet.get(i).nextKM + " "
						+ myFleet.get(i).currentKM + " " + myFleet.get(i).serviceAgent + " " + myFleet.get(i).kmMachine
						+ " " + myFleet.get(i).serverDesc);
				out.flush();
			}
		}
	}

	// register an item in the sales arraylist
	public void registerSales() throws IOException, ClassNotFoundException {
		FileWriter fr = new FileWriter("SalesFile", true);
		BufferedWriter br = new BufferedWriter(fr);
		// Sales newSales = new Sales();
		out.writeObject("Please enter the ID register");
		out.flush();

		String num1 = (String) in.readObject();
		// find the item in the fleet arraylist
		// then assign new variables its values and add to the sales arraylist
		for (int i = 0; i < myFleet.size(); i++) {
			if (myFleet.get(i).Id.equals(num1)) {
				out.writeObject("Name of machine to register: " + myFleet.get(i).name);
				out.flush();
				salesName = myFleet.get(i).name;
				myFleet.get(i).name = salesName;

				salesID = myFleet.get(i).Id;
				myFleet.get(i).Id = salesID;

				out.writeObject("registering: " + myFleet.get(i).name + " " + myFleet.get(i).Id);
				out.flush();
				Sales newsSales = new Sales(myFleet.get(i).name, myFleet.get(i).Id);

				mySales.add(newsSales);
				br.write(myFleet.get(i).name + " " + myFleet.get(i).Id);

				br.close();
				fr.close();

			}
		}
		out.writeObject("Registered machine");
		out.flush();
	}

	// search through the sales arraylist
	// similar to the search in fleet
	public void searchSales() throws IOException, ClassNotFoundException {
		out.writeObject("Please enter the ID to search for");
		out.flush();

		String num1 = (String) in.readObject();
		for (int i = 0; i < mySales.size(); i++) {
			if (mySales.get(i).Id.equals(num1)) {
				out.writeObject("Name of machine: " + mySales.get(i).name);
				out.flush();
			}
		}
		out.writeObject("searched machine");
		out.flush();
	}

	// display all the items in the sales Array list
	public void searchAllSales() throws IOException {
		for (int i = 0; i < mySales.size(); i++) {
			out.writeObject("Registered Items " + mySales.get(i).name);
			out.flush();
		}
		out.writeObject("Searched Items");
		out.flush();
	}

	// function to read in the users from file
	public void readIn() throws FileNotFoundException {

		Scanner p = new Scanner(new File("UsersFile"));

		while (p.hasNext()) {
			myUsers.add(new Users(p.next(), p.next()));
		}

	}

	// read in the salesFile
	public void salesFile() throws FileNotFoundException {
		Scanner p = new Scanner(new File("SalesFile"));

		while (p.hasNext()) {
			mySales.add(new Sales(p.next(), p.next()));
		}
	}

	// main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket listener;
		Socket connection;
		int id = 0;
		ArrayList<Users> registered = null;
		ArrayList<Fleet> machinery = null;
		ArrayList<Sales> sales = null;
		try {
			listener = new ServerSocket(25000, 10);

			while (true) {

				System.out.println("Listening for a connection");
				connection = listener.accept();
				System.out.println("Received Connection from " + connection.getInetAddress());

				Server th = new Server(connection, id, registered, machinery, sales);
				id++;
				th.start();

			}

		} catch (Exception e) {

		}

	}
}