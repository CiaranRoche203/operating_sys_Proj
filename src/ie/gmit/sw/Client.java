package ie.gmit.sw;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

//client class
public class Client {
//main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// declaring and assigning variables to be used
		Scanner input = new Scanner(System.in);

		Socket connection;
		ObjectOutputStream out;
		ObjectInputStream in;
		String message;
		String response = "";
		String option;

		try {
			// connect to the server
			connection = new Socket("127.0.0.1", 25000);
			out = new ObjectOutputStream(connection.getOutputStream());
			in = new ObjectInputStream(connection.getInputStream());

			// Conversation starts.....
			// while connected do
			do {
				out.writeObject("GetMenu");
				out.flush();
				// message asking user to select an option
				message = (String) in.readObject();
				System.out.println(message);
				option = input.nextLine();

				out.writeObject(option);
				out.flush();
				// if 1 then login
				if (option.equalsIgnoreCase("1")) {
					// print out the message
					message = (String) in.readObject();
					System.out.println(message);

					// Username
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// Password
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					// sending a response
					response = (String) in.readObject();
					System.out.println(response);

				}
				// if option = 2 register
				else if (option.equalsIgnoreCase("2")) { // message to user
					message = (String) in.readObject();
					System.out.println(message);

					// Username
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// Password
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// ID
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					// send a response
					response = (String) in.readObject();
					System.out.println(response);
				}
			} while (option.equalsIgnoreCase("2") || response.equalsIgnoreCase("FAIL"));

			do {
				//message to user diplaying menu and taking input
				message = (String) in.readObject();
				System.out.println(message);
				response = input.nextLine();
				out.writeObject(response);
				out.flush();
				//if its 1 then add a machine
				if (response.equalsIgnoreCase("1")) {
					// message
					message = (String) in.readObject();
					System.out.println(message);
					// name
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					// age
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					// ID
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// clubID
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					// Vendor
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					// Valuation
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// lastkm
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					// lastDate
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					// next km
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					// current
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// print message
					message = (String) in.readObject();
					System.out.println(message);

				} else if (response.equalsIgnoreCase("2")) {
					// please enter id to search for
					message = (String) in.readObject();
					System.out.println(message);
					// read the num 1 input
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// output message
					message = (String) in.readObject();
					System.out.println(message);

				} else if (response.equalsIgnoreCase("3")) {
					// message in
					message = (String) in.readObject();
					System.out.println(message);
					
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					
					//output
					message = (String) in.readObject();
					System.out.println(message);
					// num enter
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					
					// id enter
					message = (String) in.readObject();
					System.out.println(message);
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					
					//display the responses to the user
					message = (String) in.readObject();
					System.out.println(message);
					
					message = (String) in.readObject();
					System.out.println(message);
				} else if (response.equalsIgnoreCase("4")) {
					// message in
					message = (String) in.readObject();
					System.out.println(message);
					
					// num enter
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					
					// id enter
					message = (String) in.readObject();
					System.out.println(message);
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					
					//item has been deleted message
					message = (String) in.readObject();
					System.out.println(message);
					
				} else if (response.equalsIgnoreCase("5")) {
					message = (String) in.readObject();
					System.out.println(message);
					
					// num enter
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					
					// id enter
					message = (String) in.readObject();
					System.out.println(message);

					message = (String) in.readObject();
					System.out.println(message);

					message = (String) in.readObject();
					System.out.println(message);
				} else if (response.equalsIgnoreCase("6")) {
					
					message = (String) in.readObject();
					System.out.println(message);
					
					// num enter
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					//messages back to the user
					message = (String) in.readObject();
					System.out.println(message);
					
					message = (String) in.readObject();
					System.out.println(message);
					
				} else if (response.equalsIgnoreCase("7")) {
					//display the sales list
					message = (String) in.readObject();
					System.out.println(message);
					//messages back to user
					message = (String) in.readObject();
					System.out.println(message);
					message = (String) in.readObject();
					System.out.println(message);
					
				} else if (response.equalsIgnoreCase("8")) {
					message = (String) in.readObject();
					System.out.println(message);
					//enter input
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					//message to user
					message = (String) in.readObject();
					System.out.println(message);
					//new values
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					//new values
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					//new values
					response = input.nextLine();
					out.writeObject(response);
					out.flush();
					
					//messages to user
					message = (String) in.readObject();
					System.out.println(message);

					message = (String) in.readObject();
					System.out.println(message);

				}
				//1 or 2 option
				message = (String) in.readObject();
				System.out.println(message);
				response = input.nextLine();
				out.writeObject(response);
				out.flush();
			} while (response.equalsIgnoreCase("1"));

		} catch (Exception e) {
			System.out.println("Error");
		}
	}

}