package service;

import service.ChatService;
import com.model.User;
import com.model.Contact;
import com.model.Message;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CLI {
	private ChatService chatService;
	public Scanner scanner = new Scanner(System.in);
	private User currentUser;

	public CLI() throws SQLException, ClassNotFoundException {
		chatService = new ChatService();
	}

	public void start() {
		System.out.println("Welcome to the CLI Chat Application!");
		boolean loggedIn = false;

		// Login loop
		while (!loggedIn) {
			System.out.print("Enter username: ");
			String username = scanner.nextLine();
			System.out.print("Enter password: ");
			String password = scanner.nextLine();

			try {
				currentUser = chatService.authenticateUser(username, password);
				if (currentUser != null) {
					System.out.println("Login successful!");
					loggedIn = true;
					showMainMenu();
				} else {
					System.out.println("Invalid credentials. Please try again.");
				}
			} catch (SQLException e) {
				System.out.println("An error occurred during login. Please try again.");
				e.printStackTrace();
			}
		}
	}

	private void showMainMenu() {
		while (true) {
			System.out.println("\nMain Menu:");
			System.out.println("1. Send Private Message");
			System.out.println("2. View Private Messages");
			System.out.println("3. Send Group Message");
			System.out.println("4. View Group Messages");
			System.out.println("5. Add Contact");
			System.out.println("6. View Contacts");
			System.out.println("7. Logout");
			System.out.print("Choose an option: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			try {
				switch (choice) {
				case 1:
					sendPrivateMessage();
					break;
				case 2:
					viewPrivateMessages();
					break;
				case 3:
					sendGroupMessage();
					break;
				case 4:
					viewGroupMessages();
					break;
				case 5:
					addContact();
					break;
				case 6:
					viewContacts();
					break;
				case 7:
					System.out.println("Logging out...");
					return;
				default:
					System.out.println("Invalid option. Please try again.");
				}
			} catch (SQLException e) {
				System.out.println("An error occurred. Please try again.");
				e.printStackTrace();
			}
		}
	}

	private void sendPrivateMessage() throws SQLException {
		System.out.print("Enter receiver's username: ");
		String receiverUsername = scanner.nextLine();
		System.out.print("Enter message: ");
		String messageText = scanner.nextLine();

		// Fetch receiver's user ID (implementation omitted for brevity)
		int receiverId = 2; // Replace with actual logic to fetch receiverId by username
		chatService.sendPrivateMessage(currentUser.getUserId(), receiverId, messageText);
		System.out.println("Message sent!");
	}

	private void viewPrivateMessages() throws SQLException {
		List<Message> privateMessages = chatService.getPrivateMessages(currentUser.getUserId());
		if (privateMessages.isEmpty()) {
			System.out.println("No private messages found.");
		} else {
			System.out.println("\nPrivate Messages:");
			for (Message message : privateMessages) {
				System.out.println("From: " + message.getSenderId() + " | Message: " + message.getMessageText()
						+ " | Time: " + message.getTimestamp());
			}
		}
	}

	private void sendGroupMessage() throws SQLException {
		System.out.print("Enter group ID: ");
		int groupId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter message: ");
		String messageText = scanner.nextLine();

		chatService.sendGroupMessage(currentUser.getUserId(), groupId, messageText);
		System.out.println("Group message sent!");
	}

	private void viewGroupMessages() throws SQLException {
		List<Message> groupMessages = chatService.getGroupMessages(currentUser.getUserId());
		if (groupMessages.isEmpty()) {
			System.out.println("No group messages found.");
		} else {
			System.out.println("\nGroup Messages:");
			for (Message message : groupMessages) {
				System.out.println("Group: " + message.getGroupId() + " | From: " + message.getSenderId()
						+ " | Message: " + message.getMessageText() + " | Time: " + message.getTimestamp());
			}
		}
	}

	private void addContact() throws SQLException {
		System.out.print("Enter contact name: ");
		String contactName = scanner.nextLine();
		System.out.print("Enter phone number: ");
		String phoneNumber = scanner.nextLine();

		chatService.addContact(currentUser.getUserId(), contactName, phoneNumber);
		System.out.println("Contact added!");
	}

	private void viewContacts() throws SQLException {
		List<Contact> contacts = chatService.getContacts(currentUser.getUserId());
		if (contacts.isEmpty()) {
			System.out.println("No contacts found.");
		} else {
			System.out.println("\nContacts:");
			for (Contact contact : contacts) {
				System.out.println("Name: " + contact.getContactName() + " | Phone: " + contact.getPhoneNumber());
			}
		}
	}

	public static void main(String[] args) {
	    try {
	        CLI cli = new CLI();
	        cli.start();
	    } catch (SQLException e) {
	        System.out.println("Error initializing application: " + e.getMessage());
	        e.printStackTrace();
	    } catch (ClassNotFoundException e){
	        System.out.println("Error initializing application: " + e.getMessage());
	        e.printStackTrace();
	    }
	   

	}
}